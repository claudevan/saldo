package github.com.claudevan.saldo.services;

import github.com.claudevan.saldo.mocks.ExternalApiMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SaldoService {

    private static final Logger logger = LoggerFactory.getLogger(SaldoService.class);

    public String getBalance(String accountId) {
        logger.info("Calling mock API for account ID: {}", accountId);
        return ExternalApiMock.getAccountBalance(accountId);
    }
}
