package io.github.diegors.desafiopneus.application.mapper;

import io.github.diegors.desafiopneus.application.dto.PneuPosicaoDTO;
import io.github.diegors.desafiopneus.application.dto.VeiculoComPneusDTO;
import io.github.diegors.desafiopneus.domain.model.Veiculo;

import java.util.stream.Collectors;

public class VeiculoMapper {

    public VeiculoComPneusDTO mapToVeiculoComPneusDTO(Veiculo veiculo) {
        return VeiculoComPneusDTO.builder()
                .placa(veiculo.getPlaca())
                .marca(veiculo.getMarca())
                .quilometragem(veiculo.getQuilometragem())
                .status(veiculo.getStatus())
                .pneus(veiculo.getVeiculoPneuPosicoes().stream()
                        .map(posicao -> PneuPosicaoDTO.builder()
                                .numeroFogo(posicao.getPneu().getNumeroFogo())
                                .marca(posicao.getPneu().getMarca())
                                .pressaoAtual(posicao.getPneu().getPressaoAtual())
                                .status(posicao.getPneu().getStatus())
                                .posicao(posicao.getPosicao())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}