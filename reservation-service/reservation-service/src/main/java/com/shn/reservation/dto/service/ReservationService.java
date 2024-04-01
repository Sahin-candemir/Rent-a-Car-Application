package com.shn.reservation.dto.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shn.commonservice.messaging.SendEmailRequest;
import com.shn.reservation.dto.client.UserServiceClient;
import com.shn.reservation.dto.client.VehicleClient;
import com.shn.reservation.dto.dto.*;
import com.shn.reservation.dto.exception.ReservationNotFoundException;
import com.shn.reservation.dto.exception.VehicleInUseException;
import com.shn.reservation.dto.exception.ErrorMessage;
import com.shn.reservation.dto.model.Reservation;
import com.shn.reservation.dto.model.ReservationStatus;
import com.shn.reservation.dto.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final ReservationRepository reservationRepository;
    private final ModelMapper mapper;
    private final VehicleClient vehicleClient;
    private final UserServiceClient userServiceClient;
    private final RabbitTemplate rabbitTemplate;
    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        CarResponse carResponse = vehicleClient.getCarById(reservationRequest.getVehicleId()).getBody();
        UserResponse userResponse = userServiceClient.getUserById(reservationRequest.getUserId()).getBody();
        if (carResponse != null && carResponse.isAvailable()){
            Reservation reservation = new Reservation();
            reservation.setUserId(reservationRequest.getUserId());
            reservation.setVehicleId(reservationRequest.getVehicleId());
            reservation.setEndDate(reservationRequest.getEndDate());
            reservation.setStartDate(reservationRequest.getStartDate());
            reservation.setStatus(ReservationStatus.PENDING);

            vehicleClient.updateCarAvailable(reservationRequest.getVehicleId()).getBody();

            Reservation saveReservation = reservationRepository.save(reservation);

            SendEmailRequest sendEmailRequest =
                    new SendEmailRequest(userResponse.getEmailAddress(), "Car rent reservation success TEST RABBİTMQ.");

            //rabbitTemplate.convertAndSend(exchange, routingKey, sendEmailRequest);
            try {
                // Jackson ObjectMapper kullanarak JSON formatına çevirme
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonMessage = objectMapper.writeValueAsString(sendEmailRequest);

                // JSON formatındaki mesajı RabbitMQ kuyruğuna gönderme
                rabbitTemplate.convertAndSend(exchange, routingKey, jsonMessage);
            } catch (Exception e) {
                // Hata durumunda loglama yapabilirsiniz.
                log.error("Error sending message", e);
            }

            return ReservationResponse.builder()
                    .userId(saveReservation.getUserId())
                    .vehicleId(saveReservation.getVehicleId())
                    .endDate(saveReservation.getEndDate())
                    .startDate(saveReservation.getStartDate())
                    .status(saveReservation.getStatus())
                    .build();
        }
        else{
            ErrorMessage errorMessage = null;
            errorMessage = new ErrorMessage(
                    new Date().toString(),
                    409,
                    "The vehicle is currently use.",
                    "/api/reservation"
            );
            throw new VehicleInUseException(errorMessage);
        }

    }

    public void updateReservationStatus(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: "+id));
        reservation.setStatus(ReservationStatus.CONFIRMED);
        reservationRepository.save(reservation);
    }

    public List<ReservationResponse> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(reservation -> mapper.map(reservation, ReservationResponse.class)).collect(Collectors.toList());
    }

    public ReservationResponse getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: "+id));

        return mapper.map(reservation, ReservationResponse.class);
    }
}
