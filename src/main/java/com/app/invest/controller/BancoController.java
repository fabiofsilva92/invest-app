package com.app.invest.controller;

import com.app.invest.model.Banco;
import com.app.invest.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/banco")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @GetMapping
    public ResponseEntity<List<Banco>> findAll(){
        return ResponseEntity.ok().body(bancoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Banco> findById(
            @PathVariable Long id) throws Throwable {
        Optional<Banco> banco = bancoService.findById(id);

        if(banco.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok().body(banco.get());
    }

    @PostMapping
    public ResponseEntity<Banco> createBanco(@RequestBody Banco banco){
        return ResponseEntity.ok().body(bancoService.create(banco));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Banco> updateBanco(@RequestBody Banco banco,
                                             @PathVariable Long id){
        return ResponseEntity.ok().body(bancoService.updateBanco(banco, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteBanco(@PathVariable Long id){
        if(!bancoService.deleteBanco(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso não encontrado");
        };
        return ResponseEntity.noContent().build();
    }
}
