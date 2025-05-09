package github.com.claudevan.saldo.exceptions;

public record ErrorResponse(
        int status,
        String message
) {}