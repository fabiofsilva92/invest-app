package com.app.invest.service;

import com.app.invest.model.Pessoa;
import com.app.invest.repository.PessoaRepository;
import com.app.invest.utils.QueryLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private QueryLogger queryLogger;

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }

    public Pessoa findById(Long id) throws Throwable {
        return pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    public Pessoa create(Pessoa pessoa){
        Pessoa created = pessoaRepository.save(pessoa);
        queryLogger.logToFile("insert into pessoa (nome) values ('"+pessoa.getNome()+"');");
        return created;
    }

    public Pessoa updatePessoa(Pessoa pessoa, Long id){
        Pessoa updated = pessoaRepository.findById(pessoa.getId()).orElseThrow(() ->
                new RuntimeException("Pessoa não encontrada para atualizar"));
        pessoa.setId(updated.getId());
        updated = pessoaRepository.save(pessoa);
        queryLogger.logToFile("update pessoa set nome = '"+pessoa.getNome()+"' where id = "+pessoa.getId()+";");
        return updated;
    }

    public void deletePessoa(Long id){
        pessoaRepository.delete(pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada para deletar")));
        queryLogger.logToFile("delete from pessoa where id = "+id+";");
    }
}
