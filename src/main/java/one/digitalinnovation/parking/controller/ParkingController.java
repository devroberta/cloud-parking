package one.digitalinnovation.parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.parking.controller.dto.ParkingDTO;
import one.digitalinnovation.parking.controller.mapper.ParkingMapper;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

  private final ParkingService parkingService;
  private final ParkingMapper parkingMapper;

  public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
    this.parkingService = parkingService;
    this.parkingMapper = parkingMapper;
  }

  @GetMapping
  @ApiOperation("Find all parkings")
  public ResponseEntity<List<ParkingDTO>> findAll(){
    List<Parking> parkingList = parkingService.fingAll();
    List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/{id}")
  @ApiOperation("Find parking by id")
  public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
    Parking parking = parkingService.findById(id);
    ParkingDTO result = parkingMapper.toParkingDTO(parking);
    return ResponseEntity.ok(result);
  }

  @PostMapping
  @ApiOperation("Create a parking")
  public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
    Parking parkingCreate = parkingMapper.toParkingCreate(dto);
    Parking parkingList = parkingService.create(parkingCreate);
    ParkingDTO result = parkingMapper.toParkingDTO(parkingList);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }
}
