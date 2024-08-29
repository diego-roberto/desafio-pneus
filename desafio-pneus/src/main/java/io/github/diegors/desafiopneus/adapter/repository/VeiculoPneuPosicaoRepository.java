package io.github.diegors.desafiopneus.adapter.repository;

import io.github.diegors.desafiopneus.domain.model.Pneu;
import io.github.diegors.desafiopneus.domain.model.Veiculo;
import io.github.diegors.desafiopneus.domain.model.VeiculoPneuPosicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoPneuPosicaoRepository extends JpaRepository<VeiculoPneuPosicao, Long> {

    boolean existsByPneu(Pneu pneu);

    Optional<VeiculoPneuPosicao> findByVeiculoAndPneu(Veiculo veiculo, Pneu pneu);

    long countByVeiculo(Veiculo veiculo);
}

