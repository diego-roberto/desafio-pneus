package io.github.diegors.desafiopneus.application.service;

import io.github.diegors.desafiopneus.adapter.repository.VeiculoRepository;
import io.github.diegors.desafiopneus.application.dto.VeiculoComPneusDTO;
import io.github.diegors.desafiopneus.application.dto.VeiculoDTO;
import io.github.diegors.desafiopneus.application.exception.ServiceException;
import io.github.diegors.desafiopneus.application.mapper.VeiculoMapper;
import io.github.diegors.desafiopneus.domain.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private VeiculoMapper veiculoMapper;

    public List<VeiculoDTO> findAll() {
        return Optional.of(veiculoRepository.findAll())
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Veiculo::getStatus)
                .map(veiculoMapper::mapToVeiculoDTO)
                .collect(Collectors.toList());
    }

    public Optional<VeiculoComPneusDTO> findById(Long id) {
        try {
            Optional<Veiculo> dto = veiculoRepository.findByIdAndStatusTrue(id);
            return dto.map(veiculo -> veiculoMapper.mapToVeiculoComPneusDTO(veiculo));
        } catch (Exception e) {
            throw new ServiceException("Erro ao buscar o veículo com ID: " + id, e);
        }
    }

    public Optional<VeiculoComPneusDTO> findByPlaca(String placa) {
        try {
            Optional<Veiculo> dto = veiculoRepository.findByPlacaAndStatusTrue(placa);
            return dto.map(veiculo -> veiculoMapper.mapToVeiculoComPneusDTO(veiculo));
        } catch (Exception e) {
            throw new ServiceException("Erro ao buscar o veículo com Placa: " + placa, e);
        }
    }

    public VeiculoDTO save(VeiculoDTO veiculoDTO) {
        try {
            Veiculo veiculo = veiculoMapper.mapToVeiculo(veiculoDTO);
            Veiculo savedVeiculo = veiculoRepository.save(veiculo);
            return veiculoMapper.mapToVeiculoDTO(savedVeiculo);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Tipo de veículo inválido: " + veiculoDTO.getTipoVeiculo(), e);
        } catch (Exception e) {
            throw new ServiceException("Erro ao salvar o veículo", e);
        }
    }
}
