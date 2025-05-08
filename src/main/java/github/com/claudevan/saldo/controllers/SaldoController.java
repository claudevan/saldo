package github.com.claudevan.saldo.controllers;

import github.com.claudevan.saldo.services.SaldoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SaldoController {

    private static final Logger logger = LoggerFactory.getLogger(SaldoController.class);
    private final SaldoService saldoService;

    public SaldoController(SaldoService balanceService) {
        this.saldoService = balanceService;
    }

    @GetMapping("/conta/{contaId}/balance")
    public ResponseEntity<?> getBalance(@PathVariable String contaId) {
        logger.info("Fetching balance for account ID: {}", contaId);
        try {
            String balance = saldoService.getBalance(contaId);
            return ResponseEntity.ok().body(balance);
        } catch (Exception e) {
            logger.error("Error fetching balance for account ID: {}", contaId, e);
            return ResponseEntity.internalServerError().body("Error fetching balance");
        }
    }
}
