package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {

  public static Map<String, Parking> parkingMap = new HashMap<>();

  static {
    var id = getUUID();
    Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
    parkingMap.put(id, parking);
  }

  public List<Parking> fingAll() {
    return parkingMap.values().stream().collect(Collectors.toList());
  }

  private static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
