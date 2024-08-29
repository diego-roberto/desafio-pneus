package io.github.diegors.desafiopneus.application.mapper;

import io.github.diegors.desafiopneus.application.dto.PneuDTO;
import io.github.diegors.desafiopneus.domain.model.Pneu;
import io.github.diegors.desafiopneus.domain.model.enums.TipoPneu;
import org.springframework.stereotype.Component;

@Component
public class PneuMapper {

    public PneuDTO mapToPneuDTO(Pneu pneu) {
        return PneuDTO.builder()
                .numeroFogo(pneu.getNumeroFogo())
                .marca(pneu.getMarca())
                .pressaoAtual(pneu.getPressaoAtual())
                .status(Boolean.TRUE.equals(pneu.getStatus()) ? "Ativo" : "Inativo")
                .tipoPneu(pneu.getTipoPneu().name())
                .build();
    }

    public Pneu mapToPneu(PneuDTO pneuDTO) {
        return Pneu.builder()
                .numeroFogo(pneuDTO.getNumeroFogo())
                .marca(pneuDTO.getMarca())
                .pressaoAtual(pneuDTO.getPressaoAtual())
                .status("Ativo".equals(pneuDTO.getStatus()))
                .tipoPneu(TipoPneu.valueOf(pneuDTO.getTipoPneu()))
                .build();
    }

}
