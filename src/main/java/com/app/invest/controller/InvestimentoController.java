package com.app.invest.controller;

import com.app.invest.model.Pessoa;
import com.app.invest.model.RegistroInvestimento;
import com.app.invest.service.InvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invest")
public class InvestimentoController {

    @Autowired
    private InvestimentoService investimentoService;

    /**
     * Retrieve all investments.
     *
     * @return ResponseEntity containing a list of RegistroInvestimento objects and HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<RegistroInvestimento>> findAll(){
        return ResponseEntity.ok().body(investimentoService.findAll());
    }

    /**
     * Retrieve an investment by ID.
     *
     * @param id the ID of the investment to retrieve
     * @return ResponseEntity containing the RegistroInvestimento object and HTTP status 200 (OK)
     * @throws Throwable if the investment with the given ID does not exist
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<RegistroInvestimento> findById(
            @PathVariable Long id) {
        Optional<RegistroInvestimento> investimento = investimentoService.findById(id);

        if(investimento.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().body(investimento.get());
    }

    /**
     * Create a new investment.
     *
     * @param investimento the RegistroInvestimento object to create
     * @return ResponseEntity containing the created RegistroInvestimento object and HTTP status 200 (OK)
     */
    @PostMapping
    public ResponseEntity<RegistroInvestimento> createInvestimento(@RequestBody RegistroInvestimento investimento){
        return ResponseEntity.ok().body(investimentoService.create(investimento));
    }

    /**
     * Update an existing investment.
     *
     * @param investimento the updated RegistroInvestimento object
     * @param id the ID of the investment to update
     * @return ResponseEntity containing the updated RegistroInvestimento object and HTTP status 200 (OK)
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<RegistroInvestimento> updateInvestimento(@RequestBody RegistroInvestimento investimento,
                                                                   @PathVariable Long id){
        return ResponseEntity.ok().body(investimentoService.updateInvestimento(investimento, id));
    }

    /**
     * Delete an investment by ID.
     *
     * @param id the ID of the investment to delete
     * @return ResponseEntity with HTTP status 204 (No Content) if the investment was deleted successfully,
     *         or ResponseEntity with HTTP status 404 (Not Found) and an error message if the investment was not found
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteInvestimento(@PathVariable Long id){
        if(!investimentoService.deleteInvestimento(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso não encontrado");
        }
        return ResponseEntity.noContent().build();
    }

}
