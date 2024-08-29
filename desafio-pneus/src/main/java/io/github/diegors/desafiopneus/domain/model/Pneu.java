package io.github.diegors.desafiopneus.domain.model;

import io.github.diegors.desafiopneus.domain.model.enums.TipoPneu;
import io.github.diegors.desafiopneus.infrastructure.persistence.converter.TipoPneuConverter;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numeroFogo;

    private String marca;

    private Double pressaoAtual;

    private Boolean status;

    @Enumerated(EnumType.STRING)
    @Convert(converter = TipoPneuConverter.class)
    private TipoPneu tipoPneu;

    @OneToMany(mappedBy = "pneu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VeiculoPneuPosicao> veiculoPneuPosicoes;

}
