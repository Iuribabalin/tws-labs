package ru.iuribabalin.app.rest.filter;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import ru.iuribabalin.app.soap.exception.model.EmployeeErrorDto;

import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class BasicAuthFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        System.out.println("getHeaders: " + requestContext.getHeaders());
        List<String> authHeaders = requestContext.getHeaders().get(HttpHeaders.AUTHORIZATION);

        if (authHeaders == null || authHeaders.isEmpty()) {
            abortWithUnauthorized(requestContext);
            return;
        }

        String authHeader = authHeaders.get(0);
        if (!authHeader.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        String encodedCredentials = authHeader.substring(AUTHORIZATION_HEADER_PREFIX.length()).trim();
        String credentials = new String(Base64.getDecoder().decode(encodedCredentials));
        StringTokenizer tokenizer = new StringTokenizer(credentials, ":");

        String username = tokenizer.nextToken();
        String password = tokenizer.nextToken();

        if (!"test".equals(username) || !"password".equals(password)) {
            abortWithUnauthorized(requestContext);
        }
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity(EmployeeErrorDto.builder().message("Unauthorized access").build())
                        .build());
    }
}
