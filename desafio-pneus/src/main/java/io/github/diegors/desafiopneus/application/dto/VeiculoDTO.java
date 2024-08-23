package io.github.diegors.desafiopneus.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDTO {

    private String placa;
    private String marca;
    private Double quilometragem;
    private String status;

}
