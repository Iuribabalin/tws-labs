package ru.iuribabalin.command.impl.juddi.connect;

import lombok.*;
import ru.iuribabalin.client.exception.ConnectionException;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Connection {
    private String host;
    private Integer port;
    private Boolean secure;

    @SneakyThrows
    public String getConnectUrl() {
        if (host == null || host.isEmpty()) {
            throw new ConnectionException("Host is null or empty");
        }
        if (port == null || port < 0 || port > 65535) {
            throw new ConnectionException("Port is null or out of range");
        }
        return secure ? String.format("https://%s:%d", host, port) : String.format("http://%s:%d", host, port);
    }
}
