package github.com.claudevan.saldo.model;

public record ContaResponse(
        String idConta,
        String agencia,
        String conta,
        String dac,
        String idCliente,
        String saldo,
        String id
) {}