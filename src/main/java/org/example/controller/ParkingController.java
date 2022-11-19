package org.example.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.controller.dto.ParkingCreateDTO;
import org.example.controller.dto.ParkingDTO;
import org.example.controller.mapper.ParkingMapper;
import org.example.model.Parking;
import org.example.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("Find all Parkings")
    public ResponseEntity<List<ParkingDTO>> findAll() {
/*
        var parking = new Parking();
        parking.setColor("PRETO");
        parking.setLicense("MSS-1111");
        parking.setModel("GOL");
        parking.setState("SP");
*/
        //return parkingService.findAlL();
        List<Parking> parkingList = parkingService.findAlL();
        List<ParkingDTO> result = parkingMapper.toparkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}")
    @ApiOperation("Find Parkings by Id")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        Parking parking = parkingService.findById(id);
        ParkingDTO result = parkingMapper.toparkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @ApiOperation("Create Parking")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
        var parkingCreate = parkingMapper.toparkingCreate(dto);
        var parking = parkingService.create(parkingCreate);
        var result = parkingMapper.toparkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete by Id")
    public ResponseEntity<ParkingDTO> delete(@PathVariable String id) {
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @ApiOperation("Update Parking")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto) {
        var parkingCreate = parkingMapper.toparkingCreate(dto);
        var parking = parkingService.update(id, parkingCreate);
        var result = parkingMapper.toparkingDTO(parking);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("{id}")
    @ApiOperation("Exit Parking")
    public ResponseEntity<ParkingDTO> checkOut(@PathVariable String id) {
        Parking parking = parkingService.checkOut(id);
        return ResponseEntity.ok(parkingMapper.toparkingDTO(parking));
    }

}
