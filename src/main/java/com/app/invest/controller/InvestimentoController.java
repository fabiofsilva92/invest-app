package com.app.invest.controller;

import com.app.invest.model.Pessoa;
import com.app.invest.model.RegistroInvestimento;
import com.app.invest.service.InvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invest")
public class InvestimentoController {

    @Autowired
    private InvestimentoService investimentoService;

    @GetMapping
    public ResponseEntity<List<RegistroInvestimento>> findAll(){
        return ResponseEntity.ok().body(investimentoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RegistroInvestimento> findById(
            @PathVariable Long id) throws Throwable {
        return ResponseEntity.ok().body(investimentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RegistroInvestimento> createInvestimento(@RequestBody RegistroInvestimento investimento){
        return ResponseEntity.ok().body(investimentoService.create(investimento));
    }

    @PutMapping
    public ResponseEntity<RegistroInvestimento> updateInvestimento(@RequestBody RegistroInvestimento investimento){
        return ResponseEntity.ok().body(investimentoService.updateInvestimento(investimento));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteInvestimento(@PathVariable Long id){
        investimentoService.deleteInvestimento(id);
        return ResponseEntity.ok().body("Investimento deletada");
    }

}
