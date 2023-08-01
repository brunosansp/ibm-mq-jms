package br.com.api.ibmmq.controllers;

import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableJms
@RestController
public class MqController {
  
  private final JmsTemplate jmsTemplate;
  
  public MqController(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }
  
  @PostMapping("/send")
  public String send() {
    try {
      jmsTemplate.convertAndSend("DEV.QUEUE.1", "Outro hello world.");
      return "OK!";
    } catch (JmsException e) {
      e.printStackTrace();
      return "FAIL!";
    }
  }
  
  @GetMapping("/recv")
  public String recv() {
    try {
      return jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
    } catch (JmsException e) {
      e.printStackTrace();
      return "FAIL!";
    }
  }
}
