package io.github.diegors.desafiopneus.domain.model.enums;

import lombok.Getter;

@Getter
public enum TipoVeiculo {
    CARRO(4),
    TRUCK(8),
    TOCO_SEMI_PESADO(6),
    TRACADO(10),
    BI_TRUCK_8X2(12),
    BI_TRUCK_8X4(16);

    private final int quantidadePneus;

    TipoVeiculo(int quantidadePneus) {
        this.quantidadePneus = quantidadePneus;
    }

}
