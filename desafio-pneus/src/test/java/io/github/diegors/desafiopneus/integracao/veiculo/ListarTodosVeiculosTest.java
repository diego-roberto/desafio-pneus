package io.github.diegors.desafiopneus.integracao.veiculo;

import io.github.diegors.desafiopneus.integracao.BaseIntegrationTest;
import org.junit.jupiter.api.*;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

public class ListarTodosVeiculosTest extends BaseIntegrationTest {

    @Sql({"/seeds/init.sql", "/seeds/veiculos.sql"})
    @Test
    @DisplayName("Deve listar todos os veiculos")
    void deveListarTodosVeiculos() {

        when().get("/veiculos")
                .then()
                .statusCode(200)
                .body("size()", is(4))
                .body("placa", hasItems("ABC1234", "XYZ5678", "JKL9101", "TRK1234"));
    }
}
