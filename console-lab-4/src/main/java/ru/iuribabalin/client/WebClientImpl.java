package ru.iuribabalin.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.iuribabalin.client.model.Department;
import ru.iuribabalin.client.model.EmployeeSearchResponseDto;
import ru.iuribabalin.client.model.Position;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class WebClientImpl {
    private final ObjectMapper MAPPER = new ObjectMapper();
    private final HttpClient httpClient;
    private final String COMMON_URI = "http://localhost:9091/api";

    public WebClientImpl() {
        httpClient = HttpClient.newBuilder().build();
    }

    public EmployeeSearchResponseDto search(
            String firstName, String lastName, Position position, Department department, String data
    ) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(
                        new URI(
                                COMMON_URI + "/employees/search"
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
