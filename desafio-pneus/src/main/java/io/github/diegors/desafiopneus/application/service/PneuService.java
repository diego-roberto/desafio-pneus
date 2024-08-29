package io.github.diegors.desafiopneus.application.service;

import io.github.diegors.desafiopneus.adapter.repository.PneuRepository;
import io.github.diegors.desafiopneus.application.dto.PneuDTO;
import io.github.diegors.desafiopneus.application.exception.ServiceException;
import io.github.diegors.desafiopneus.application.mapper.PneuMapper;
import io.github.diegors.desafiopneus.domain.model.Pneu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PneuService {

    @Autowired
    private PneuRepository pneuRepository;

    @Autowired
    private PneuMapper pneuMapper;

    public Optional<PneuDTO> findById(Long id) {
        try {
            Optional<Pneu> dto = pneuRepository.findByIdAndStatusTrue(id);
            return dto.map(pneu -> pneuMapper.mapToPneuDTO(pneu));
        } catch (Exception e) {
            throw new ServiceException("Erro ao buscar o Pneu com ID: " + id, e);
        }
    }

    public PneuDTO save(PneuDTO pneuDTO) {
        try {
            Pneu pneu = pneuMapper.mapToPneu(pneuDTO);
            Pneu savedPneu = pneuRepository.save(pneu);
            return pneuMapper.mapToPneuDTO(savedPneu);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Tipo de pneu inválido: " + pneuDTO.getTipoPneu(), e);
        } catch (Exception e) {
            throw new ServiceException("Erro ao salvar o pneu", e);
        }
    }
}
