
package github.com.claudevan.saldo.services;

import github.com.claudevan.saldo.model.ContaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class SaldoService {

    private static final Logger logger = LoggerFactory.getLogger(SaldoService.class);
    private final WebClient webClient;

    public SaldoService(@Value("${json.server.url:http://localhost:3000}") String jsonServerUrl) {
        this.webClient = WebClient.create(jsonServerUrl);
    }

    public ContaResponse getSaldo(String idConta) {
        logger.info("Consultando saldo da conta {} no JSON Server", idConta);
        try {
            return webClient
                    .get()
                    .uri("/contas?idConta={id}", idConta) // Usando query parameter
                    .retrieve()
                    .bodyToMono(ContaResponse[].class) // Mudando para array
                    .map(array -> array.length > 0 ? array[0] : null) // Pegando o saldo do primeiro elemento
                    .block();

        } catch (WebClientResponseException e) {
            logger.error("Erro ao consultar saldo da conta {}: {}", idConta, e.getMessage());
            throw new RuntimeException("Erro ao consultar saldo: " + e.getMessage());
        }
    }
}