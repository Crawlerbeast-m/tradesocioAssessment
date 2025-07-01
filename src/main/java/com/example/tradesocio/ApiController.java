package com.example.tradesocio;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class ApiController {

    private final Counter apiCounter;

    public ApiController(MeterRegistry registry) {
        this.apiCounter = registry.counter("api_requests_total");
    }

    @RequestMapping(value = "/api", method = {RequestMethod.GET, RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String handleApi(@RequestHeader HttpHeaders headers,
                            @RequestBody(required = false) Map<String, Object> body,
                            HttpServletRequest request) {
        apiCounter.increment();

        StringBuilder response = new StringBuilder();
        response.append("Welcome to our demo API, here are the details of your request:\n\n");

        response.append("***Headers***:\n");
        headers.forEach((key, value) -> response.append(key).append(": ").append(String.join(",", value)).append("\n"));

        response.append("\n***Method***:\n").append(request.getMethod()).append("\n");

        response.append("\n***Body***:\n").append(body != null ? body.toString() : "No Body");

        return response.toString();
    }
}
