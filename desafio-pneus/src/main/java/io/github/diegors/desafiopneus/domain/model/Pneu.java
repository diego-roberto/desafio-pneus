package io.github.diegors.desafiopneus.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pneus")
public class Pneu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer numeroFogo;

    private String marca;

    private Double pressaoAtual;

    private Boolean status;

    @OneToMany(mappedBy = "pneu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VeiculoPneuPosicao> veiculoPneuPosicoes;

}
