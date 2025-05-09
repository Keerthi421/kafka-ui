package io.kafbat.ui.config;

import io.kafbat.ui.exception.ValidationException;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
@ConfigurationProperties("webclient")
@Data
public class WebclientProperties {

  String maxInMemoryBufferSize;
  Integer responseTimeoutMs;

  @PostConstruct
  public void validate() {
    validateAndSetDefaultBufferSize();
  }

  private void validateAndSetDefaultBufferSize() {
    if (maxInMemoryBufferSize != null) {
      try {
        DataSize.parse(maxInMemoryBufferSize);
      } catch (Exception e) {
        throw new ValidationException("Invalid format for webclient.maxInMemoryBufferSize");
      }
    }
  }

}
