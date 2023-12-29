package kr.springboot.playground.retry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class HttpRequestServiceTest {

    @Autowired
    HttpRequestService httpRequestService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void request() {
        String response = httpRequestService.request();
        System.out.println(response);
        Map<String, String> responseBody = null;
        try {
            responseBody = objectMapper.readValue(response, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get("url")).isEqualTo("https://httpbin.org/get");
    }

    @Test
    void templateRequest() {
        String response = httpRequestService.requestTemplate();
        Map<String, String> responseBody = null;
        try {
            responseBody = objectMapper.readValue(response, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get("url")).isEqualTo("https://httpbin.org/get");
    }

}
