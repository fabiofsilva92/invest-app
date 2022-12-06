package com.app.invest.service;

import com.app.invest.model.Pessoa;
import com.app.invest.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }

    public Pessoa findById(Long id) throws Throwable {
        return pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    public Pessoa create(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public Pessoa updatePessoa(Pessoa pessoa){
        pessoa.setId(pessoaRepository.findById(pessoa.getId()).orElseThrow(() -> new RuntimeException("Pessoa não encontrada para atualizar")).getId());
        return pessoaRepository.save(pessoa);
    }

    public void deletePessoa(Long id){
        pessoaRepository.delete(pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada para deletar")));
    }
}
