package br.com.api.ibmmq.controllers;

import br.com.api.ibmmq.models.MQMessageDTO;
import br.com.api.ibmmq.services.MQService;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;

@EnableJms
@RestController
public class MQController {
  
  private final MQService mqService;
  
  public MQController(MQService mqService) {
    this.mqService = mqService;
  }
  
  @PostMapping("/send")
  public String send(@RequestBody MQMessageDTO message) {
    try {
      mqService.publish(message);
      return "OK!";
    } catch (JmsException | JMSException e) {
      e.printStackTrace();
      return "FAIL!";
    }
  }
  
  @GetMapping("/recv")
  public ResponseEntity<MQMessageDTO> recv(@RequestParam String correlationId) {
    try {
      return mqService.recv(correlationId);
    } catch (JmsException | JMSException e) {
      throw new RuntimeException(e);
    }
  }
}
