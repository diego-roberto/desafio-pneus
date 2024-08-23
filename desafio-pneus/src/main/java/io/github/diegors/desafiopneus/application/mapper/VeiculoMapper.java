package io.github.diegors.desafiopneus.application.mapper;

import io.github.diegors.desafiopneus.application.dto.PneuPosicaoDTO;
import io.github.diegors.desafiopneus.application.dto.VeiculoComPneusDTO;
import io.github.diegors.desafiopneus.application.dto.VeiculoDTO;
import io.github.diegors.desafiopneus.domain.model.Veiculo;
import io.github.diegors.desafiopneus.domain.model.VeiculoPneuPosicao;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class VeiculoMapper {

    public VeiculoDTO mapToVeiculoDTO(Veiculo veiculo) {
        return VeiculoDTO.builder()
                .placa(veiculo.getPlaca())
                .marca(veiculo.getMarca())
                .quilometragem(veiculo.getQuilometragem())
                .status(Boolean.TRUE.equals(veiculo.getStatus()) ? "Ativo" : "Inativo")
                .build();
    }

    public VeiculoComPneusDTO mapToVeiculoComPneusDTO(Veiculo veiculo) {
        return VeiculoComPneusDTO.builder()
                .placa(veiculo.getPlaca())
                .marca(veiculo.getMarca())
                .quilometragem(veiculo.getQuilometragem())
                .status(Boolean.TRUE.equals(veiculo.getStatus()) ? "Ativo" : "Inativo")
                .pneus(veiculo.getVeiculoPneuPosicoes().stream()
                        .filter(Objects::nonNull)
                        .map(this::mapToPneuPosicaoDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    private PneuPosicaoDTO mapToPneuPosicaoDTO(VeiculoPneuPosicao veiculoPneuPosicao) {
        return PneuPosicaoDTO.builder()
                .numeroFogo(veiculoPneuPosicao.getPneu().getNumeroFogo())
                .marca(veiculoPneuPosicao.getPneu().getMarca())
                .pressaoAtual(veiculoPneuPosicao.getPneu().getPressaoAtual())
                .status(Boolean.TRUE.equals(veiculoPneuPosicao.getPneu().getStatus()) ? "Ativo" : "Inativo")
                .posicao(veiculoPneuPosicao.getPosicao())
                .build();
    }
}