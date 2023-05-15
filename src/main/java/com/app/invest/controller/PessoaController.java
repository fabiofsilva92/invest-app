package com.app.invest.controller;

import com.app.invest.model.Pessoa;
import com.app.invest.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    /**
     * Retrieve all people.
     *
     * @return ResponseEntity containing a list of Pessoa objects and HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll(){
        return ResponseEntity.ok().body(pessoaService.findAll());
    }

    /**
     * Retrieve a person by ID.
     *
     * @param id the ID of the person to retrieve
     * @return ResponseEntity with HTTP status 404 (Not Found) if the person was not found
     *         or ResponseEntity containing the Pessoa object and HTTP status 200 (OK)
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> findById(
            @PathVariable Long id) {
        Optional<Pessoa> person = pessoaService.findById(id);

        if(person.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok().body(person.get());
    }

    /**
     * Create a new person.
     *
     * @param pessoa the Pessoa object to create
     * @return ResponseEntity containing the created Pessoa object and HTTP status 200 (OK)
     */
    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok().body(pessoaService.create(pessoa));
    }

    /**
     * Update an existing person.
     *
     * @param pessoa the updated Pessoa object
     * @param id the ID of the person to update
     * @return ResponseEntity containing the updated Pessoa object and HTTP status 200 (OK)
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@RequestBody Pessoa pessoa,
                                               @PathVariable Long id) {
        return ResponseEntity.ok().body(pessoaService.updatePessoa(pessoa, id));
    }

    /**
     * Delete a person by ID.
     *
     * @param id the ID of the person to delete
     * @return ResponseEntity with HTTP status 204 (No Content) if the person was deleted successfully,
     *         or ResponseEntity with HTTP status 404 (Not Found) and an error message if the person was not found
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePessoa(@PathVariable Long id){
        if(!pessoaService.deletePessoa(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso não encontrado");
        }
        return ResponseEntity.noContent().build();
    }

}
