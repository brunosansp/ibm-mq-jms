package br.com.api.ibmmq.services;

import br.com.api.ibmmq.configs.MQProperties;
import br.com.api.ibmmq.models.MQMessageDTO;
import com.ibm.mq.jms.MQQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import java.nio.charset.StandardCharsets;


@Slf4j
@Service
public class MQService {
  
  private final JmsTemplate jmsTemplate;
  
  private final MQProperties mqProperties;
  
  public MQService(JmsTemplate jmsTemplate, MQProperties mqProperties, MQMessageDTO message) {
    this.jmsTemplate = jmsTemplate;
    this.mqProperties = mqProperties;
  }
  
  public ResponseEntity<MQMessageDTO> publish(MQMessageDTO messageDTO) throws JMSException {
    log.info("### 1 ### Order Service sending order messageDTO '{}' to the queue", messageDTO.getMessage());
    
    MQQueue messageRequestQueue = new MQQueue(mqProperties.getQueueRequest());
    
    jmsTemplate.send(session -> {
      Message message = session.createTextMessage("teste");
//      message.setJMSCorrelationID();
//      message.setJMSMessageID();
      return message;
    });
    jmsTemplate.convertAndSend(messageRequestQueue, messageDTO.getMessage(), textMessage -> {
      textMessage.setJMSCorrelationID(messageDTO.getIdentifier());
      return textMessage;
    });
    
    return new ResponseEntity<>(messageDTO, HttpStatus.ACCEPTED);
  }
  
  public ResponseEntity<MQMessageDTO> recv(String correlationID) throws JMSException {
    log.info("Looking for message '{}'", correlationID);
    String convertedID = bytesToHex(correlationID.getBytes());
    final String selectorExpression = String.format("JMSCorrelationID='ID:%s'");
    final TextMessage responseMessage = (TextMessage) jmsTemplate.receiveSelected(
      mqProperties.getQueueResponse(), selectorExpression
    );
    MQMessageDTO response = MQMessageDTO.builder()
      .message(responseMessage.getText())
      .identifier(convertedID)
      .identifier(correlationID)
      .build();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
  private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes();
  public static String bytesToHex(byte[] bytes) {
    byte[] hexChars = new byte[bytes.length * 2];
    for (int i = 0; i < bytes.length; i++) {
      int v = bytes[i] & 0xFF;
      hexChars[i * 2] = HEX_ARRAY[v >>> 4];
      hexChars[i * 2 + 1]= HEX_ARRAY[v & 0x0F];
    }
    return new String(hexChars, StandardCharsets.UTF_8);
  }
}
