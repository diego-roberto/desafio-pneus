package io.github.diegors.desafiopneus.adapter.repository;

import io.github.diegors.desafiopneus.domain.model.Pneu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PneuRepository extends JpaRepository<Pneu, Long> {

    Optional<Pneu> findByIdAndStatusTrue(Long id);

    Optional<Pneu> findByNumeroFogoAndStatusTrue(Integer numeroFogo);
}
