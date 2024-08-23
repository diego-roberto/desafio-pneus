package io.github.diegors.desafiopneus.adapter.repository;

import io.github.diegors.desafiopneus.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByIdAndStatusTrue(Long id);

    Optional<Veiculo> findByPlacaAndStatusTrue(String placa);
}
