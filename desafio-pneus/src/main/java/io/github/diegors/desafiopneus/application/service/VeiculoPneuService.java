package io.github.diegors.desafiopneus.application.service;

import io.github.diegors.desafiopneus.adapter.repository.PneuRepository;
import io.github.diegors.desafiopneus.adapter.repository.VeiculoPneuPosicaoRepository;
import io.github.diegors.desafiopneus.adapter.repository.VeiculoRepository;
import io.github.diegors.desafiopneus.application.dto.VeiculoComPneusDTO;
import io.github.diegors.desafiopneus.application.exception.ServiceException;
import io.github.diegors.desafiopneus.application.exception.NotFoundException;
import io.github.diegors.desafiopneus.application.mapper.VeiculoMapper;
import io.github.diegors.desafiopneus.domain.model.Pneu;
import io.github.diegors.desafiopneus.domain.model.Veiculo;
import io.github.diegors.desafiopneus.domain.model.VeiculoPneuPosicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class VeiculoPneuService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private PneuRepository pneuRepository;

    @Autowired
    private VeiculoPneuPosicaoRepository veiculoPneuPosicaoRepository;

    @Autowired
    private VeiculoMapper veiculoMapper;

    public Veiculo verifyIfVeiculoExist(String placa) {
        Optional<Veiculo> veiculoOpt = veiculoRepository.findByPlacaAndStatusTrue(placa);
        if (veiculoOpt.isEmpty())
            throw new NotFoundException("Veículo não encontrado com placa: " + placa);
        return veiculoOpt.get();
    }

    public Pneu verifyIfPneuExist(Integer numeroFogo) {
        Optional<Pneu> pneuOpt = pneuRepository.findByNumeroFogoAndStatusTrue(numeroFogo);
        if (pneuOpt.isEmpty())
            throw new NotFoundException("Pneu não encontrado com número de fogo: " + numeroFogo);
        return pneuOpt.get();
    }

    public Optional<VeiculoComPneusDTO> bind(String placa, Integer numeroFogo, String posicao) {
        try {
            Veiculo veiculo = verifyIfVeiculoExist(placa);
            Pneu pneu = verifyIfPneuExist(numeroFogo);

            long pneusVinculados = veiculoPneuPosicaoRepository.countByVeiculo(veiculo);
            if (pneusVinculados >= veiculo.getTipoVeiculo().getQuantidadePneus()) {
                throw new ServiceException("O veículo já atingiu o número máximo de pneus vinculados.");
            }

            boolean isPneuVinculado = veiculoPneuPosicaoRepository.existsByPneu(pneu);
            if (isPneuVinculado)
                throw new ServiceException("Pneu já vinculado");

            VeiculoPneuPosicao veiculoPneuPosicao = new VeiculoPneuPosicao();
            veiculoPneuPosicao.setVeiculo(veiculo);
            veiculoPneuPosicao.setPneu(pneu);
            veiculoPneuPosicao.setPosicao(posicao);
            veiculoPneuPosicaoRepository.save(veiculoPneuPosicao);

            return Optional.of(veiculoMapper.mapToVeiculoComPneusDTO(veiculo));
        } catch (NotFoundException | ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Erro ao vincular o pneu ao veículo", e);
        }
    }

    public Optional<VeiculoComPneusDTO> unbind(String placa, Integer numeroFogo) {
        try {
            Veiculo veiculo = verifyIfVeiculoExist(placa);
            Pneu pneu = verifyIfPneuExist(numeroFogo);

            Optional<VeiculoPneuPosicao> posicaoOpt = veiculoPneuPosicaoRepository.findByVeiculoAndPneu(veiculo, pneu);
            if (posicaoOpt.isEmpty()) {
                throw new ServiceException("Posição não disponível");
            }

            veiculoPneuPosicaoRepository.delete(posicaoOpt.get());
            veiculoPneuPosicaoRepository.flush();
            veiculo = veiculoRepository.findById(veiculo.getId())
                    .orElseThrow(() -> new NotFoundException("Veículo não encontrado após desvinculação"));

            return Optional.of(veiculoMapper.mapToVeiculoComPneusDTO(veiculo));
        } catch (NotFoundException | ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Erro ao desvincular o pneu ao veículo", e);
        }
    }

}
