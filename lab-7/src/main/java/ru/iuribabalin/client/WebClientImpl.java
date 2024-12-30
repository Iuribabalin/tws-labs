package ru.iuribabalin.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.iuribabalin.client.exception.ClientException;
import ru.iuribabalin.client.exception.EmployeeErrorDto;
import ru.iuribabalin.client.model.*;
import ru.iuribabalin.command.impl.juddi.connect.Connection;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static ru.iuribabalin.Main.connectUrl;

public class WebClientImpl {
    private final ObjectMapper MAPPER = new ObjectMapper();
    private final HttpClient httpClient;

    public WebClientImpl() {
        httpClient = HttpClient.newBuilder().build();
    }

    public EmployeeSearchResponseDto search(
            String firstName, String lastName, Position position, Department department, String data
    ) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(
                        new URI(
                                connectUrl + "/api/employees/search"
                                        + buildRequestParams(firstName, lastName, position, department, data)
                        )
                )
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();
        return MAPPER.readValue(
                httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body(),
                EmployeeSearchResponseDto.class
        );
    }

    public EmployeeResponseDto create(EmployeeRequestDto requestDto) throws URISyntaxException, IOException, InterruptedException, ClientException {
        String requestBody = MAPPER.writeValueAsString(requestDto);
        HttpRequest request = HttpRequest.newBuilder(new URI(connectUrl + "/api/employees"))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
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
        HttpRequest request = HttpRequest.newBuilder(new URI(connectUrl + "/api/employees/" + id))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 400) {
            EmployeeErrorDto errorDto = MAPPER.readValue(response.body().toString(), EmployeeErrorDto.class);
            throw new ClientException(errorDto.getMessage(), response.statusCode());
        }
        return MAPPER.readValue(response.body().toString(), EmployeeResponseDto.class);
    }

    public void deleteEmployee(Long id) throws URISyntaxException, IOException, InterruptedException, ClientException {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        HttpRequest request = HttpRequest.newBuilder(new URI(connectUrl + "/api/employees/" + id))
                .timeout(Duration.ofSeconds(5))
                .DELETE()
                .build();
        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
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

}
