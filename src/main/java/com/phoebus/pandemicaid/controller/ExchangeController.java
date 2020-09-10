package com.phoebus.pandemicaid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.phoebus.pandemicaid.model.Exchange;
import com.phoebus.pandemicaid.service.ExchangeService;

@RestController
@RequestMapping("/exchanges")
public class ExchangeController {
  
  @Autowired
  private ExchangeService exchangeService;
  
  @PostMapping(value = "")
  public ResponseEntity<Object> postExchange(@Validated @RequestBody Exchange exchange) {
    try {
      exchange = exchangeService.postExchange(exchange);
      return new ResponseEntity<>(exchange, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

}
