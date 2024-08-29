package io.github.diegors.desafiopneus.integracao.pneu;

import io.github.diegors.desafiopneus.integracao.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SalvarPneuTest extends BaseIntegrationTest {

    @Sql("/seeds/init.sql")
    @Test
    @DisplayName("Deve salvar um novo pneu")
    void deveSalvarNovoPneu() {
        String novoPneuJson = """
            {
                "numeroFogo": 12345,
                "marca": "Continental",
                "pressaoAtual": 35.0,
                "status": "Ativo",
                "tipoPneu": "MISTO"
            }
            """;

        given()
                .contentType("application/json")
                .body(novoPneuJson)
                .when()
                .post("/pneus")
                .then()
                .statusCode(201)
                .body("numeroFogo", equalTo(12345))
                .body("marca", equalTo("Continental"))
                .body("pressaoAtual", equalTo(35.0f))
                .body("status", equalTo("Ativo"))
                .body("tipoPneu", equalTo("MISTO"));
    }

}
