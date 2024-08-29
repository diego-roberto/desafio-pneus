package io.github.diegors.desafiopneus.unitario.veiculo;

import io.github.diegors.desafiopneus.adapter.repository.VeiculoRepository;
import io.github.diegors.desafiopneus.application.dto.PneuPosicaoDTO;
import io.github.diegors.desafiopneus.application.dto.VeiculoComPneusDTO;
import io.github.diegors.desafiopneus.application.dto.VeiculoDTO;
import io.github.diegors.desafiopneus.application.mapper.VeiculoMapper;
import io.github.diegors.desafiopneus.application.service.VeiculoService;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class VeiculoServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @Mock
    private VeiculoMapper veiculoMapper;

    @InjectMocks
    private VeiculoService veiculoService;


    private Pneu criarPneu(Long id, int numeroFogo, String marca, TipoPneu tipoPneu) {
        return new Pneu(id, numeroFogo, marca, 32.5, true, tipoPneu, null);
    }

    private VeiculoPneuPosicao criarPneuPosicao(Long id, Pneu pneu, String posicao) {
        return new VeiculoPneuPosicao(id, null, pneu, posicao);
    }

    private Veiculo criarVeiculoComPneus(String placa, String marca, TipoVeiculo tipoVeiculo) {
        Pneu pneu1 = criarPneu(1L, 111, "Michelin", TipoPneu.ALTA_SEVERIDADE);
        Pneu pneu2 = criarPneu(2L, 112, "Michelin", TipoPneu.ALTA_SEVERIDADE);

        List<VeiculoPneuPosicao> posicoes = List.of(
                criarPneuPosicao(1L, pneu1, "Dianteiro Esquerdo"),
                criarPneuPosicao(2L, pneu2, "Dianteiro Direito")
        );

        return new Veiculo(1L, placa, marca, 12000.5, true, tipoVeiculo, posicoes);
    }

    private Veiculo criarVeiculo() {
        return Veiculo.builder()
                .placa("TEST1234")
                .marca("Honda")
                .quilometragem(2000.0)
                .status(true)
                .tipoVeiculo(TipoVeiculo.CARRO)
                .build();
    }

    private VeiculoDTO criarVeiculoDTO(Veiculo veiculo) {
        return new VeiculoDTO(
                veiculo.getPlaca(),
                veiculo.getMarca(),
                veiculo.getQuilometragem(),
                Boolean.TRUE.equals(veiculo.getStatus()) ? "Ativo" : "Inativo",
                veiculo.getTipoVeiculo().name()
        );
    }

    private VeiculoComPneusDTO criarVeiculoComPneusDTO(Veiculo veiculo) {
        List<PneuPosicaoDTO> pneusDTO = veiculo.getVeiculoPneuPosicoes().stream()
                .map(p -> new PneuPosicaoDTO(
                        p.getPneu().getNumeroFogo(),
                        p.getPneu().getMarca(),
                        p.getPneu().getPressaoAtual(),
                        p.getPneu().getStatus() ? "Ativo" : "Inativo",
                        p.getPosicao(),
                        p.getPneu().getTipoPneu().name()
                ))
                .collect(Collectors.toList());

        return new VeiculoComPneusDTO(
                veiculo.getPlaca(),
                veiculo.getMarca(),
                veiculo.getQuilometragem(),
                Boolean.TRUE.equals(veiculo.getStatus()) ? "Ativo" : "Inativo",
                veiculo.getTipoVeiculo().name(),
                pneusDTO
        );
    }

    @Test
    @DisplayName("Deve retornar a listagem de veículos")
    void deveRetornarListaDeVeiculos() {
        Veiculo veiculo = criarVeiculoComPneus("ABC1234", "Ford", TipoVeiculo.CARRO);
        VeiculoDTO veiculoDTO = criarVeiculoDTO(veiculo);

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
        Veiculo veiculo = criarVeiculoComPneus("ABC1234", "Ford", TipoVeiculo.CARRO);
        VeiculoComPneusDTO expectedDTO = criarVeiculoComPneusDTO(veiculo);

        Mockito.when(veiculoRepository.findByPlacaAndStatusTrue("ABC1234")).thenReturn(Optional.of(veiculo));
        Mockito.when(veiculoMapper.mapToVeiculoComPneusDTO(veiculo)).thenReturn(expectedDTO);

        Optional<VeiculoComPneusDTO> result = veiculoService.findByPlaca("ABC1234");

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(expectedDTO, result.get());
    }

    @Test
    @DisplayName("Deve salvar um novo veículo")
    void deveSalvarNovoVeiculo() {

        Veiculo novoVeiculo = criarVeiculo();
        VeiculoDTO novoVeiculoDTO = criarVeiculoDTO(novoVeiculo);
        VeiculoDTO veiculoSalvoDTO = criarVeiculoDTO(novoVeiculo);

        Mockito.when(veiculoMapper.mapToVeiculo(novoVeiculoDTO)).thenReturn(novoVeiculo);
        Mockito.when(veiculoRepository.save(novoVeiculo)).thenReturn(novoVeiculo);
        Mockito.when(veiculoMapper.mapToVeiculoDTO(novoVeiculo)).thenReturn(veiculoSalvoDTO);

        VeiculoDTO veiculoSalvo = veiculoService.save(novoVeiculoDTO);

        Assertions.assertNotNull(veiculoSalvo);
        Assertions.assertEquals("TEST1234", veiculoSalvo.getPlaca());
        Assertions.assertEquals("Honda", veiculoSalvo.getMarca());
        Assertions.assertEquals(2000.0, veiculoSalvo.getQuilometragem());
        Assertions.assertEquals("CARRO", veiculoSalvo.getTipoVeiculo());

        Mockito.verify(veiculoMapper).mapToVeiculo(novoVeiculoDTO);
        Mockito.verify(veiculoRepository).save(novoVeiculo);
        Mockito.verify(veiculoMapper).mapToVeiculoDTO(novoVeiculo);
    }

}
