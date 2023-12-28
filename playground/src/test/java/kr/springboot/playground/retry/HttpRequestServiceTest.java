package kr.springboot.playground.retry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class HttpRequestServiceTest {

    @Autowired
    HttpRequestService httpRequestService;

    @Test
    void request() {
        String response = httpRequestService.request();
        System.out.println(response);
        assertThat(response).isNotNull();
    }

}
