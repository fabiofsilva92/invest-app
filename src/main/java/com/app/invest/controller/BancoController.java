package com.app.invest.controller;

import com.app.invest.model.Banco;
import com.app.invest.model.Pessoa;
import com.app.invest.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok().body(bancoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Banco> createBanco(@RequestBody Banco banco){
        return ResponseEntity.ok().body(bancoService.create(banco));
    }

    @PutMapping
    public ResponseEntity<Banco> updateBanco(@RequestBody Banco banco){
        return ResponseEntity.ok().body(bancoService.updateBanco(banco));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteBanco(@PathVariable Long id){
        bancoService.deleteBanco(id);
        return ResponseEntity.ok().body("Banco deletado");
    }
}
