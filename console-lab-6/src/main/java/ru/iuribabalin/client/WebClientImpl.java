package ru.iuribabalin.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.iuribabalin.client.exception.ClientException;
import ru.iuribabalin.client.exception.EmployeeErrorDto;
import ru.iuribabalin.client.model.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;

public class WebClientImpl {
    private final ObjectMapper MAPPER = new ObjectMapper();
    private final HttpClient httpClient;
    private final String COMMON_URI = "http://localhost:9091/api";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "password";
    private static final Boolean isAuth = true;

    public WebClientImpl() {
        httpClient = HttpClient.newBuilder().build();
    }

    public EmployeeSearchResponseDto search(
            String firstName, String lastName, Position position, Department department, String data
    ) throws URISyntaxException, IOException, InterruptedException, ClientException {
        HttpRequest.Builder request = HttpRequest.newBuilder(
                        new URI(
                                COMMON_URI + "/employees/search"
                                        + buildRequestParams(firstName, lastName, position, department, data)
                        )
                )
                .timeout(Duration.ofSeconds(5))
                .GET();
        if (isAuth) {
            request.header("Authorization", baseAuth());
            request.header("Content-Type", "application/json");
        }
        HttpResponse response = httpClient.send(request.build(), HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return MAPPER.readValue(response.body().toString(), EmployeeSearchResponseDto.class);
        }
        if (response.statusCode() >= 400) {
            EmployeeErrorDto errorDto = MAPPER.readValue(response.body().toString(), EmployeeErrorDto.class);
            throw new ClientException(errorDto.getMessage(), response.statusCode());
        }
        return new EmployeeSearchResponseDto();
    }

    public EmployeeResponseDto create(EmployeeRequestDto requestDto) throws URISyntaxException, IOException, InterruptedException, ClientException {
        String requestBody = MAPPER.writeValueAsString(requestDto);
        HttpRequest.Builder request = HttpRequest.newBuilder(new URI(COMMON_URI + "/employees"))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody));
        if (isAuth) {
            request.header("Authorization", baseAuth());
        }
        HttpResponse response = httpClient.send(request.build(), HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return MAPPER.readValue(response.body().toString(), EmployeeResponseDto.class);
        }
        if (response.statusCode() >= 400) {
            EmployeeErrorDto errorDto = MAPPER.readValue(response.body().toString(), EmployeeErrorDto.class);
            throw new ClientException(errorDto.getMessage(), response.statusCode());
        }
        return new EmployeeResponseDto();
    }

    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto requestDto) throws URISyntaxException, IOException, InterruptedException, ClientException {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        String requestBody = MAPPER.writeValueAsString(requestDto);
        HttpRequest.Builder request = HttpRequest.newBuilder(new URI(COMMON_URI + "/employees/" + id))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody));
        if (isAuth) {
            request.header("Authorization", baseAuth());
        }
        HttpResponse response = httpClient.send(request.build(), HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return MAPPER.readValue(response.body().toString(), EmployeeResponseDto.class);
        }
        if (response.statusCode() >= 400) {
            EmployeeErrorDto errorDto = MAPPER.readValue(response.body().toString(), EmployeeErrorDto.class);
            throw new ClientException(errorDto.getMessage(), response.statusCode());
        }
        return new EmployeeResponseDto();
    }

    public void deleteEmployee(Long id) throws URISyntaxException, IOException, InterruptedException, ClientException {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        HttpRequest.Builder request = HttpRequest.newBuilder(new URI(COMMON_URI + "/employees/" + id))
                .timeout(Duration.ofSeconds(5))
                .DELETE();
        if (isAuth) {
            request.header("Authorization", baseAuth());
        }
        HttpResponse response = httpClient.send(request
                .build(), HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 400) {
            EmployeeErrorDto errorDto = MAPPER.readValue(response.body().toString(), EmployeeErrorDto.class);
            throw new ClientException(errorDto.getMessage(), response.statusCode());
        }
    }


    private String buildRequestParams(
            String firstName, String lastName, Position position, Department department, String data
    ) {
        StringBuilder params = new StringBuilder("?");
        if (firstName != null && !firstName.isEmpty()) params.append("&firstName=").append(firstName);
        if (lastName != null && !lastName.isEmpty()) params.append("&lastName=").append(lastName);
        if (position != null) params.append("&position=").append(position);
        if (department != null) params.append("&department=").append(department);
        if (data != null && !data.isEmpty()) params.append("&data=").append(data);
        return params.toString().equals("?") ? "" : params.toString();
    }

    private String baseAuth() {
        String auth = USERNAME + ":" + PASSWORD;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        return "Basic " + encodedAuth;
    }


}
