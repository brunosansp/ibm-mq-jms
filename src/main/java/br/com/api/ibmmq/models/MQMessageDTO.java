package br.com.api.ibmmq.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
@Component
public class MQMessageDTO {
  
  private String message;
  private String identifier;
  
  public MQMessageDTO(MQMessageDTO message) {
    this.message = message.getMessage();
    this.identifier = message.getIdentifier();
  }
}
