package io.github.diegors.desafiopneus.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PneuDTO {

    private Integer numeroFogo;
    private String marca;
    private Double pressaoAtual;
    private String status;
    private String tipoPneu;

}
