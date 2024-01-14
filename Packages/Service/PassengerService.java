package com.airline.service;

import java.util.List;

import com.airline.dto.PassengerDTO;
import com.airline.entity.Passenger;

public interface PassengerService {
  String createPassenger(Passenger passenger);
  PassengerDTO updatePassenger(int id, Passenger passenger);
  PassengerDTO getPassengerById(int id);
  List<PassengerDTO> getAllPassenger();
  String deletePassengerById(int id);
  void deleteAllPassengers();
  List<PassengerDTO> getPassengerByName(String name);
  PassengerDTO getPassengerByEmail(String email);
}
