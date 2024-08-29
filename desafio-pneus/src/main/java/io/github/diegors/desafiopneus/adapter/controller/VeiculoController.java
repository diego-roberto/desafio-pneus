package io.github.diegors.desafiopneus.adapter.controller;

import io.github.diegors.desafiopneus.application.dto.VeiculoComPneusDTO;
import io.github.diegors.desafiopneus.application.dto.VeiculoDTO;
import io.github.diegors.desafiopneus.application.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping()
    public ResponseEntity<List<VeiculoDTO>> findAll() {
        List<VeiculoDTO> veiculos = veiculoService.findAll();
        if (veiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(veiculos);
    }

    /* endpoint para fins de debug */
    @GetMapping("/findById")
    public ResponseEntity<VeiculoComPneusDTO> findById(@RequestParam("id") Long id) {
        return veiculoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/findByPlaca")
    public ResponseEntity<VeiculoComPneusDTO> findByPlaca(@RequestParam("placa") String placa) {
        return veiculoService.findByPlaca(placa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<VeiculoDTO> createVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        VeiculoDTO newVeiculo = veiculoService.save(veiculoDTO);
        if (newVeiculo != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newVeiculo);
        }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(veiculoDTO);
    }

}
