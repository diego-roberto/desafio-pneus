package io.github.diegors.desafiopneus.adapter.controller;

import io.github.diegors.desafiopneus.application.dto.VeiculoComPneusDTO;
import io.github.diegors.desafiopneus.application.exception.ServiceException;
import io.github.diegors.desafiopneus.application.service.VeiculoPneuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Validated
@RestController
@RequestMapping("/veiculo-pneus")
public class VeiculoPneuController {

    @Autowired
    private VeiculoPneuService veiculoPneuService;

    @PutMapping("/vincular")
    public ResponseEntity<?> bind(
            @RequestParam("placa") String placa,
            @RequestParam("numeroFogo") Integer numeroFogo,
            @RequestParam("posicao") String posicao) {
        try {
            Optional<VeiculoComPneusDTO> dto = veiculoPneuService.bind(placa, numeroFogo, posicao);
            return dto.map(veiculoComPneusDTO -> new ResponseEntity<>(veiculoComPneusDTO, HttpStatus.CREATED))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/desvincular")
    public ResponseEntity<?> unbind(
            @RequestParam("placa") String placa,
            @RequestParam("numeroFogo") Integer numeroFogo) {
        try {
            Optional<VeiculoComPneusDTO> dto = veiculoPneuService.unbind(placa, numeroFogo);
            return dto.map(veiculoComPneusDTO -> new ResponseEntity<>(veiculoComPneusDTO, HttpStatus.CREATED))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
