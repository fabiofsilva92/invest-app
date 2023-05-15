package com.app.invest.service;

import com.app.invest.model.Pessoa;
import com.app.invest.repository.PessoaRepository;
import com.app.invest.utils.QueryLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private QueryLogger queryLogger;

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa create(Pessoa pessoa){
        Pessoa created = pessoaRepository.save(pessoa);
        queryLogger.logToFile("insert into pessoa (nome) values ('"+pessoa.getNome()+"');");
        return created;
    }

    public Pessoa updatePessoa(Pessoa pessoa, Long id){
        Pessoa updated = pessoaRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Pessoa não encontrada para atualizar"));
        pessoa.setId(updated.getId());
        updated = pessoaRepository.save(pessoa);
        queryLogger.logToFile("update pessoa set nome = '"+pessoa.getNome()+"' where id = "+pessoa.getId()+";");
        return updated;
    }

    public boolean deletePessoa(Long id){

        if(pessoaRepository.findById(id).isEmpty()){
            return false;
        };

        pessoaRepository.deleteById(id);
        queryLogger.logToFile("delete from pessoa where id = "+id+";");
        return true;
    }
}
