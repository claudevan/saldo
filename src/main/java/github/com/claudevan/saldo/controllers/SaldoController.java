package github.com.claudevan.saldo.controllers;

import github.com.claudevan.saldo.model.ContaResponse;
import github.com.claudevan.saldo.services.SaldoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class SaldoController {

    private static final Logger logger = LoggerFactory.getLogger(SaldoController.class);
    private final SaldoService saldoService;

    public SaldoController(SaldoService balanceService) {
        this.saldoService = balanceService;
    }

    @GetMapping("/conta/{idConta}/saldo")
    public ResponseEntity<?> getSaldo(@PathVariable String idConta) {

        logger.info("Obtendo Saldo para a conta: {}", idConta);

        try {
            ContaResponse saldo = saldoService.getSaldo(idConta);
            return ResponseEntity.ok().body(saldo);
        } catch (Exception e) {
            logger.error("Erro ao obter os dados da conta: {}", idConta, e);
            return ResponseEntity.internalServerError().body("Error ao obter saldo");
        }
    }
}
