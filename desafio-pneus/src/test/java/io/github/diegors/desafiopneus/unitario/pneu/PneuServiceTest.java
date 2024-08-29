package io.github.diegors.desafiopneus.unitario.pneu;

import io.github.diegors.desafiopneus.adapter.repository.PneuRepository;
import io.github.diegors.desafiopneus.application.dto.PneuDTO;
import io.github.diegors.desafiopneus.application.mapper.PneuMapper;
import io.github.diegors.desafiopneus.application.service.PneuService;
import io.github.diegors.desafiopneus.domain.model.Pneu;
import io.github.diegors.desafiopneus.domain.model.enums.TipoPneu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PneuServiceTest {

    @Mock
    private PneuRepository pneuRepository;

    @InjectMocks
    private PneuService pneuService;

    @Mock
    private PneuMapper pneuMapper;

    private Pneu criarPneu() {
        return Pneu.builder()
                .numeroFogo(12345)
                .marca("Continental")
                .pressaoAtual(35.0)
                .status(true)
                .tipoPneu(TipoPneu.MISTO)
                .build();
    }

    private PneuDTO criarPneuDTO(Pneu pneu) {
        return PneuDTO.builder()
                .numeroFogo(pneu.getNumeroFogo())
                .marca(pneu.getMarca())
                .pressaoAtual(pneu.getPressaoAtual())
                .status("Ativo")
                .tipoPneu(pneu.getTipoPneu().name())
                .build();
    }

    @Test
    @DisplayName("Deve salvar um novo pneu para um veículo")
    void deveSalvarNovoPneu() {
        Pneu novoPneu = criarPneu();
        PneuDTO novoPneuDTO = criarPneuDTO(novoPneu);
        PneuDTO pneuSalvoDTO = criarPneuDTO(novoPneu);

        Mockito.when(pneuMapper.mapToPneu(novoPneuDTO)).thenReturn(novoPneu);
        Mockito.when(pneuRepository.save(novoPneu)).thenReturn(novoPneu);
        Mockito.when(pneuMapper.mapToPneuDTO(novoPneu)).thenReturn(pneuSalvoDTO);

        PneuDTO pneuSalvo = pneuService.save(novoPneuDTO);

        Assertions.assertNotNull(pneuSalvo);
        Assertions.assertEquals(12345, pneuSalvo.getNumeroFogo());
        Assertions.assertEquals("Continental", pneuSalvo.getMarca());
        Assertions.assertEquals(35.0, pneuSalvo.getPressaoAtual());
        Assertions.assertEquals("MISTO", pneuSalvo.getTipoPneu());

        Mockito.verify(pneuMapper).mapToPneu(novoPneuDTO);
        Mockito.verify(pneuRepository).save(novoPneu);
        Mockito.verify(pneuMapper).mapToPneuDTO(novoPneu);
    }

}
