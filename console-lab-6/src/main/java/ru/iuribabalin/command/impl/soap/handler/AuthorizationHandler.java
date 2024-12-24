package ru.iuribabalin.command.impl.soap.handler;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.*;

public class AuthorizationHandler implements javax.xml.ws.handler.soap.SOAPHandler<SOAPMessageContext> {

    private final String username;
    private final String password;

    private final Boolean isAuth = true;

    public AuthorizationHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isOutbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (isAuth) {
            if (isOutbound) {
                String auth = username + ":" + password;
                String authHeaderValue = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
                Map<String, List<String>> headers = (Map<String, List<String>>)
                        context.get(MessageContext.HTTP_REQUEST_HEADERS);
                if (headers == null) {
                    headers = new HashMap<>();
                }
                headers.put("Authorization", Collections.singletonList(authHeaderValue));
                context.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
            }
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}
