package br.com.api.ibmmq.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@Component
public class MQProperties {
  
  @Value("${ibm.mq.queueManager}")
  private String queueManager;
  
  @Value("${ibm.mq.channel}")
  private String channel;
  
  @Value("${ibm.mq.queueRequest}")
  private String queueRequest;
  
  @Value("${ibm.mq.queueResponse}")
  private String queueResponse;
  
  @Value("${ibm.mq.connName}")
  private String connName;
  
  @Value("${ibm.mq.port}")
  private Integer port;
  
}
