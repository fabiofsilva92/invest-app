package com.app.invest.service;

import com.app.invest.model.Pessoa;
import com.app.invest.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private FileService fileService;

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }

    public Pessoa findById(Long id) throws Throwable {
        return pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    public Pessoa create(Pessoa pessoa) throws IOException {
        Pessoa created = pessoaRepository.save(pessoa);
        fileService.generateInsertPessoa(created);
        return created;
    }

    public Pessoa updatePessoa(Pessoa pessoa) throws IOException {
        pessoa.setId(pessoaRepository.findById(pessoa.getId()).orElseThrow(() -> new RuntimeException("Pessoa não encontrada para atualizar")).getId());
        Pessoa updated = pessoaRepository.save(pessoa);
        fileService.generateUpdatePessoa(updated);
        return updated;
    }

    public void deletePessoa(Long id){
        pessoaRepository.delete(pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada para deletar")));
    }
}
