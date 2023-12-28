package kr.springboot.playground.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

@Service
public class HttpRequestService {

    private int count = 0;

    @Retryable(retryFor = Exception.class, backoff = @Backoff(delay = 5000), maxAttempts = 3)
    public String request() {

        if (count == 2) {

            WebClient webClient = WebClient.builder()
                    .baseUrl("https://httpbin.org/get")
                    .build();
            String response = webClient.get()
                    .retrieve()
                    .bodyToMono(String.class).doOnError(throwable -> System.out.println(throwable.getMessage())).block();

            return response;

        }

        count++;

        WebClient webClient = WebClient.builder()
                .baseUrl("https://httpbin.org/get3")
                .build();
        String response = webClient.get().retrieve().bodyToMono(String.class).block();

        return response;
    }

}
