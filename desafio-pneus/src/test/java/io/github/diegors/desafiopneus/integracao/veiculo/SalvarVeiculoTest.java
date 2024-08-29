package io.github.diegors.desafiopneus.integracao.veiculo;

import io.github.diegors.desafiopneus.integracao.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SalvarVeiculoTest extends BaseIntegrationTest {

    @Sql("/seeds/init.sql")
    @Test
    @DisplayName("Deve salvar um novo veículo")
    void deveSalvarNovoVeiculo() {
        String novoVeiculoJson = """
            {
                "placa": "TEST1234",
                "marca": "Honda",
                "quilometragem": 2000.0,
                "status": "Ativo",
                "tipoVeiculo": "CARRO"
            }
            """;

        given()
                .body(novoVeiculoJson)
                .contentType("application/json")
                .when()
                .post("/veiculos")
                .then()
                .statusCode(201)
                .body("placa", equalTo("TEST1234"))
                .body("marca", equalTo("Honda"))
                .body("quilometragem", equalTo(2000.0f))
                .body("status", equalTo("Ativo"))
                .body("tipoVeiculo", equalTo("CARRO"));
    }

}
