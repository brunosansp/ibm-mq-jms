//package br.com.api.ibmmq.configs;
//
//import com.ibm.mq.jms.MQConnectionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.annotation.EnableJms;
//
//import javax.jms.ConnectionFactory;
//import javax.jms.JMSException;
//
//@Configuration
//@EnableJms
//public class JmsConfig {
//
//  private final MQProperties mqProperties;
//
//  public JmsConfig(MQProperties mqProperties) {
//    this.mqProperties = mqProperties;
//  }
//
//  @Bean
//  public ConnectionFactory connectionFactory() throws JMSException {
//    MQConnectionFactory factory = new MQConnectionFactory();
//    factory.setHostName(mqProperties.getConnName());
//    factory.setPort(mqProperties.getPort());
//    factory.setQueueManager(mqProperties.getQueueManager());
//    factory.setChannel(mqProperties.getChannel());
//    factory.setTransportType(1);
//    return factory;
//  }
//}
