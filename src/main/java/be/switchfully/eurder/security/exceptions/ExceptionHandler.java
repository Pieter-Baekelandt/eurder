package be.switchfully.eurder.security.exceptions;

import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionHandler {

    @ServerExceptionMapper(value = { UnauthorizedException.class, UnknownUserException.class, WrongPasswordException.class})
    public RestResponse<String> UnauthorisedException(RuntimeException ex) {
        return RestResponse.status(Response.Status.UNAUTHORIZED,ex.getMessage());
    }
    @ServerExceptionMapper(value = {IllegalArgumentException.class})
    public RestResponse<String> IllegalArgumentException(RuntimeException ex) {
        return RestResponse.status(Response.Status.PARTIAL_CONTENT,ex.getMessage());
    }
}
