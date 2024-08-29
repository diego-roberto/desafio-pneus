package io.github.diegors.desafiopneus.domain.model;

import io.github.diegors.desafiopneus.domain.model.enums.TipoVeiculo;
import io.github.diegors.desafiopneus.infrastructure.persistence.converter.TipoVeiculoConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;

    private String marca;

    private Double quilometragem;

    private Boolean status;

    @Enumerated(EnumType.STRING)
    @Convert(converter = TipoVeiculoConverter.class)
    private TipoVeiculo tipoVeiculo;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VeiculoPneuPosicao> veiculoPneuPosicoes;

}
