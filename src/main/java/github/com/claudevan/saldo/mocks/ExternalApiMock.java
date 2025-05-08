package github.com.claudevan.saldo.mocks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExternalApiMock {

    private static final Map<String, String> mockData = new ConcurrentHashMap<>();

    static {
        mockData.put("5df13be4-423e-42a2-8932-0dbf6acd3b29", "{\"saldo\": \"300.00\"}");
    }

    public static String getAccountBalance(String accountId) {
        return mockData.getOrDefault(accountId, "{\"saldo\": \"0.00\"}");
    }
}