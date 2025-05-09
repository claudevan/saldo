package github.com.claudevan.saldo.services;

import github.com.claudevan.saldo.model.ContaResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SaldoServiceTest {

    private static MockWebServer mockWebServer;
    private SaldoService saldoService;
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
        saldoService = new SaldoService(baseUrl);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @DisplayName("Deve retornar saldo quando conta existe")
    void deveRetornarSaldoQuandoContaExiste() throws Exception {
        // Arrange
        ContaResponse conta = new ContaResponse(
                "123",
                "0001",
                "12345",
                "6",
                "789",
                "1000.00",
                "1"
        );

        String jsonResponse = objectMapper.writeValueAsString(List.of(conta));

        mockWebServer.enqueue(new MockResponse()
                .setBody(jsonResponse)
                .addHeader("Content-Type", "application/json"));

        // Act
        var response = saldoService.getSaldo("123");

        // Assert
        Assertions.assertEquals("1000.00", response.saldo());
    }

    @Test
    @DisplayName("Deve lançar exceção quando conta não existe")
    void deveRetornarZeroQuandoContaNaoExiste() {
        // Arrange
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("Conta não encontrada"));

        // Act & Assert
        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> saldoService.getSaldo("999")
        );

        Assertions.assertTrue(exception.getMessage().contains("Erro ao consultar saldo"));
    }

    @Test
    @DisplayName("Deve lançar exceção quando API retorna erro")
    void deveLancarExcecaoQuandoApiRetornaErro() {
        // Arrange
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("Erro interno do servidor"));

        // Act & Assert
        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> saldoService.getSaldo("123")
        );

        Assertions.assertTrue(exception.getMessage().contains("Erro ao consultar saldo"));
    }

    @Test
    @DisplayName("Deve logar informação ao consultar saldo")
    void deveLogarInformacaoAoConsultarSaldo() throws Exception {
        // Arrange
        ContaResponse conta = new ContaResponse(
                "123",
                "0001",
                "12345",
                "6",
                "789",
                "1000.00",
                "1"
        );

        String jsonResponse = objectMapper.writeValueAsString(List.of(conta));

        mockWebServer.enqueue(new MockResponse()
                .setBody(jsonResponse)
                .addHeader("Content-Type", "application/json"));

        // Act
        saldoService.getSaldo("123");

        // Assert
        // Note: Para testar logs, você precisaria configurar um appender de teste
        // ou usar uma ferramenta como LogCaptor
    }
}