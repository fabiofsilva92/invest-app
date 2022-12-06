package com.app.invest.controller;

import com.app.invest.model.Pessoa;
import com.app.invest.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll(){
        return ResponseEntity.ok().body(pessoaService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> findById(
            @PathVariable Long id) throws Throwable {
        return ResponseEntity.ok().body(pessoaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa){
        return ResponseEntity.ok().body(pessoaService.create(pessoa));
    }

    @PutMapping
    public ResponseEntity<Pessoa> updatePessoa(@RequestBody Pessoa pessoa){
        return ResponseEntity.ok().body(pessoaService.updatePessoa(pessoa));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePessoa(@PathVariable Long id){
        pessoaService.deletePessoa(id);
        return ResponseEntity.ok().body("Pessoa deletada");
    }

}
