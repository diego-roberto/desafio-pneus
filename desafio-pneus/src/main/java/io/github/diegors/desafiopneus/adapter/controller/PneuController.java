package io.github.diegors.desafiopneus.adapter.controller;

import io.github.diegors.desafiopneus.application.dto.PneuDTO;
import io.github.diegors.desafiopneus.application.service.PneuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/pneus")
public class PneuController {

    @Autowired
    private PneuService pneuService;

    /* endpoint para fins de debug */
    @GetMapping("/findById")
    public ResponseEntity<PneuDTO> findById(@RequestParam("id") Long id) {
        return pneuService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping()
    public ResponseEntity<PneuDTO> createPneu(@RequestBody PneuDTO pneu) {
        PneuDTO newPneu = pneuService.save(pneu);
        if (newPneu != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newPneu);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pneu);
    }

}
