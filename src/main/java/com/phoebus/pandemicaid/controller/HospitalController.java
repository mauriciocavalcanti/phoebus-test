package com.phoebus.pandemicaid.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.phoebus.pandemicaid.model.Fullness;
import com.phoebus.pandemicaid.model.Hospital;
import com.phoebus.pandemicaid.service.HospitalService;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {

  @Autowired
  private HospitalService hospitalService;

  @PostMapping(value = "")
  public ResponseEntity<Object> postHospital(@Validated @RequestBody Hospital hospital) {
    try {
      hospital = hospitalService.postHospital(hospital);
      return new ResponseEntity<>(hospital, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
  
  @GetMapping(value = "")
  public ResponseEntity<Object> getHospitals() {
    try {
      List<Hospital> hospitals = hospitalService.getHospitals();
      return new ResponseEntity<>(hospitals, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(value = "/{id}/fullnesses")
  public ResponseEntity<Object> postFullness(@PathVariable("id") Long hospitalId,
      @Validated @RequestBody Fullness fullness) {
    try {
      fullness = hospitalService.postFullness(hospitalId, fullness);
      return new ResponseEntity<>(fullness, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/{id}/fullnesses")
  public ResponseEntity<Object> getFullnesses(@PathVariable("id") Long hospitalId) {
    try {
      List<Fullness> fullnesses = hospitalService.getFullnesses(hospitalId);
      return new ResponseEntity<>(fullnesses, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
