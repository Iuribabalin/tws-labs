package ru.iuribabalin.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.iuribabalin.client.exception.ClientException;
import ru.iuribabalin.client.exception.EmployeeErrorDto;
import ru.iuribabalin.client.model.*;
import ru.iuribabalin.utils.ErrorPrint;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

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

    public CompletableFuture<EmployeeSearchResponseDto> searchAsync(
            String firstName, String lastName, Position position, Department department, String data
    ) throws URISyntaxException {
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
        return httpClient.sendAsync(request.build(), HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    if (response.statusCode() == 200) {
                        try {
                            return MAPPER.readValue(response.body(), EmployeeSearchResponseDto.class);
                        } catch (IOException e) {
                            throw new RuntimeException("Ошибка при разборе тела ответа", e);
                        }
                    } else handler4xx(response);
                    return new EmployeeSearchResponseDto();
                });
    }

    public CompletableFuture<EmployeeResponseDto> createAsync(EmployeeRequestDto requestDto)
            throws URISyntaxException, IOException {
        String requestBody = MAPPER.writeValueAsString(requestDto);
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(new URI(COMMON_URI + "/employees"))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody));

        if (isAuth) {
            requestBuilder.header("Authorization", baseAuth());
        }

        return httpClient.sendAsync(requestBuilder.build(), HttpResponse.BodyHandlers.ofString())
                .thenApply(this::getEmployeeResponseDto);
    }

    public CompletableFuture<EmployeeResponseDto> updateEmployeeAsync(Long id, EmployeeRequestDto requestDto)
            throws URISyntaxException, IOException {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        String requestBody = MAPPER.writeValueAsString(requestDto);
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(new URI(COMMON_URI + "/employees/" + id))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody));

        if (isAuth) {
            requestBuilder.header("Authorization", baseAuth());
        }

        return httpClient.sendAsync(requestBuilder.build(), HttpResponse.BodyHandlers.ofString())
                .thenApply(this::getEmployeeResponseDto);
    }


    public CompletableFuture<Void> deleteEmployeeAsync(Long id) throws URISyntaxException {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(new URI(COMMON_URI + "/employees/" + id))
                .timeout(Duration.ofSeconds(5))
                .DELETE();

        if (isAuth) {
            requestBuilder.header("Authorization", baseAuth());
        }

        return httpClient.sendAsync(requestBuilder.build(), HttpResponse.BodyHandlers.ofString())
                .thenAccept(this::handler4xx);
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

    private EmployeeResponseDto getEmployeeResponseDto(HttpResponse<String> response) {
        if (response.statusCode() == 200) {
            try {
                return MAPPER.readValue(response.body(), EmployeeResponseDto.class);
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при разборе тела ответа", e);
            }
        } else handler4xx(response);
        return new EmployeeResponseDto();
    }

    private void handler4xx(HttpResponse<String> response) {
        if (response.statusCode() >= 400) {
            try {
                EmployeeErrorDto errorDto = MAPPER.readValue(response.body(), EmployeeErrorDto.class);
                throw new ClientException(errorDto.getMessage(), response.statusCode());
            } catch (ClientException e) {
                ErrorPrint.redPrint("Request status: " + e.getCode() + ", message: " + e.getMessage());
            } catch (IOException e) {
                ErrorPrint.redPrint(e.getMessage());
            }
        }
    }

}
