package io.github.diegors.desafiopneus.unitario.VeiculoPneu;

import io.github.diegors.desafiopneus.adapter.repository.PneuRepository;
import io.github.diegors.desafiopneus.adapter.repository.VeiculoPneuPosicaoRepository;
import io.github.diegors.desafiopneus.adapter.repository.VeiculoRepository;
import io.github.diegors.desafiopneus.application.dto.VeiculoComPneusDTO;
import io.github.diegors.desafiopneus.application.mapper.VeiculoMapper;
import io.github.diegors.desafiopneus.application.service.VeiculoPneuService;
import io.github.diegors.desafiopneus.domain.model.Pneu;
import io.github.diegors.desafiopneus.domain.model.Veiculo;
import io.github.diegors.desafiopneus.domain.model.VeiculoPneuPosicao;
import io.github.diegors.desafiopneus.domain.model.enums.TipoPneu;
import io.github.diegors.desafiopneus.domain.model.enums.TipoVeiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@ExtendWith(MockitoExtension.class)
public class VeiculoPneuServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @Mock
    private VeiculoPneuPosicaoRepository veiculoPneuPosicaoRepository;

    @Mock
    private PneuRepository pneuRepository;

    @Mock
    private VeiculoMapper veiculoMapper;

    @InjectMocks
    private VeiculoPneuService veiculoPneuService;

    public String placa = "ABC1234";
    public Integer numeroFogo = 12345;
    public String posicao = "Dianteiro Esquerdo";

    public Veiculo criarVeiculo() {
        return Veiculo.builder()
                .id(1L)
                .placa(placa)
                .marca("Ford")
                .quilometragem(12000.5)
                .status(true)
                .tipoVeiculo(TipoVeiculo.CARRO)
                .build();
    }

    public Pneu criarPneu() {
        return Pneu.builder()
                .numeroFogo(numeroFogo)
                .marca("Michelin")
                .pressaoAtual(32.5)
                .status(true)
                .tipoPneu(TipoPneu.ALTA_SEVERIDADE)
                .build();
    }

    @Test
    @DisplayName("Deve vincular um pneu a um veículo")
    void deveVincularPneuAVeiculo() {
        Veiculo veiculo = criarVeiculo();
        Pneu pneu = criarPneu();
        VeiculoPneuPosicao veiculoPneuPosicao = new VeiculoPneuPosicao(null, veiculo, pneu, posicao);

        Mockito.when(veiculoRepository.findByPlacaAndStatusTrue(placa)).thenReturn(Optional.of(veiculo));
        Mockito.when(pneuRepository.findByNumeroFogoAndStatusTrue(numeroFogo)).thenReturn(Optional.of(pneu));
        Mockito.when(veiculoPneuPosicaoRepository.existsByPneu(pneu)).thenReturn(false);
        Mockito.when(veiculoPneuPosicaoRepository.save(Mockito.any(VeiculoPneuPosicao.class))).thenReturn(veiculoPneuPosicao);
        Mockito.when(veiculoMapper.mapToVeiculoComPneusDTO(veiculo)).thenReturn(new VeiculoComPneusDTO());

        Optional<VeiculoComPneusDTO> resultado = veiculoPneuService.bind(placa, numeroFogo, posicao);

        Assertions.assertTrue(resultado.isPresent());
        Mockito.verify(veiculoPneuPosicaoRepository).save(Mockito.any(VeiculoPneuPosicao.class));
    }

    @Test
    @DisplayName("Deve desvincular um pneu de um veículo")
    void deveDesvincularPneuDeVeiculo() {
        Veiculo veiculo = criarVeiculo();
        Pneu pneu = criarPneu();
        VeiculoPneuPosicao veiculoPneuPosicao = new VeiculoPneuPosicao(null, veiculo, pneu, posicao);

        Mockito.when(veiculoRepository.findByPlacaAndStatusTrue("ABC1234")).thenReturn(Optional.of(veiculo));
        Mockito.when(pneuRepository.findByNumeroFogoAndStatusTrue(12345)).thenReturn(Optional.of(pneu));
        Mockito.when(veiculoPneuPosicaoRepository.findByVeiculoAndPneu(veiculo, pneu)).thenReturn(Optional.of(veiculoPneuPosicao));
        Mockito.when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        Mockito.when(veiculoMapper.mapToVeiculoComPneusDTO(veiculo)).thenReturn(new VeiculoComPneusDTO());

        Optional<VeiculoComPneusDTO> result = veiculoPneuService.unbind("ABC1234", 12345);

        assertTrue(result.isPresent());
        Mockito.verify(veiculoPneuPosicaoRepository).delete(veiculoPneuPosicao);
    }

}
