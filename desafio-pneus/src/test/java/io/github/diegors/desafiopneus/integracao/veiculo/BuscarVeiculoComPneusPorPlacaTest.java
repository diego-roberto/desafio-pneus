package io.github.diegors.desafiopneus.integracao.veiculo;

import io.github.diegors.desafiopneus.integracao.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.CoreMatchers.is;

public class BuscarVeiculoComPneusPorPlacaTest extends BaseIntegrationTest {

    @Sql({"/seeds/init.sql",
            "/seeds/veiculos.sql",
            "/seeds/pneus.sql",
            "/seeds/veiculo_pneu_posicoes.sql"})
    @Test
    @DisplayName("Deve buscar veiculo com pneus por placa")
    void deveBuscarVeiculoComPneusPorPlaca() {
        when().get("/veiculos/findByPlaca?placa=ABC1234")
                .then()
                .statusCode(200)
                .body("placa", is("ABC1234"))
                .body("pneus.size()", is(4));
    }

}
