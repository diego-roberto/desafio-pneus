package io.github.diegors.desafiopneus.unitario.veiculo;

import io.github.diegors.desafiopneus.adapter.repository.VeiculoRepository;
import io.github.diegors.desafiopneus.application.dto.VeiculoComPneusDTO;
import io.github.diegors.desafiopneus.application.dto.VeiculoDTO;
import io.github.diegors.desafiopneus.application.mapper.VeiculoMapper;
import io.github.diegors.desafiopneus.application.service.VeiculoService;
import io.github.diegors.desafiopneus.domain.model.Pneu;
import io.github.diegors.desafiopneus.domain.model.Veiculo;
import io.github.diegors.desafiopneus.domain.model.VeiculoPneuPosicao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class VeiculoServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @Mock
    private VeiculoMapper veiculoMapper;

    @InjectMocks
    private VeiculoService veiculoService;

    @Test
    @DisplayName("Deve retornar a listagem de veículos")
    void deveRetornarListaDeVeiculos() {

        Pneu pneu1 = new Pneu(1L, 111, "Michelin", 32.5, true, null);
        Pneu pneu2 = new Pneu(2L, 112, "Michelin", 32.5, true, null);
        List<VeiculoPneuPosicao> posicoes = List.of(
                new VeiculoPneuPosicao(1L, null, pneu1, "Dianteiro Esquerdo"),
                new VeiculoPneuPosicao(2L, null, pneu2, "Dianteiro Direito")
        );
        Veiculo veiculo = new Veiculo(1L, "ABC1234", "Ford", 12000.5, true, posicoes);
        VeiculoDTO veiculoDTO = new VeiculoDTO("ABC1234", "Ford", 12000.5, "Ativo");

        Mockito.when(veiculoRepository.findAll()).thenReturn(List.of(veiculo));
        Mockito.when(veiculoMapper.mapToVeiculoDTO(veiculo)).thenReturn(veiculoDTO);

        List<VeiculoDTO> result = veiculoService.findAll();

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(veiculoDTO, result.get(0));
    }


    @Test
    @DisplayName("Deve retornar veículo com pneus por placa")
    void deveRetornarVeiculoEPneusPorPlaca() {
        Veiculo veiculo = new Veiculo(/*...*/);
        VeiculoComPneusDTO expectedDTO = new VeiculoComPneusDTO(/*...*/);

        Mockito.when(veiculoRepository.findByPlacaAndStatusTrue("ABC1234")).thenReturn(Optional.of(veiculo));
        Mockito.when(veiculoMapper.mapToVeiculoComPneusDTO(veiculo)).thenReturn(expectedDTO);

        Optional<VeiculoComPneusDTO> result = veiculoService.findByPlaca("ABC1234");

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(expectedDTO, result.get());
    }
}
