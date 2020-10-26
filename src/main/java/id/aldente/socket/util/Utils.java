package id.aldente.socket.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by f.putra on 7/20/20.
 */
public class Utils {

  /**
   * Generates a random string using the default UUID
   */
  public static String generateRandomUUID(){
    return UUID.randomUUID().toString();
  }

  public static Optional<String> getJsonStringFromObject(Object object){
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return Optional.ofNullable(objectMapper.writeValueAsString(object));
    } catch (JsonProcessingException e) {
      return Optional.empty();
    }
  }
}
