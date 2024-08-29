package io.github.diegors.desafiopneus.application.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoComPneusDTO {

    private String placa;
    private String marca;
    private Double quilometragem;
    private String status;
    private String tipoVeiculo;
    private List<PneuPosicaoDTO> pneus;

}
