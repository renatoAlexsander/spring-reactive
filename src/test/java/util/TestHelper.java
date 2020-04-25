package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestHelper {

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper()
            .writeValueAsString(object);
    }
}
