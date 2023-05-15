package com.app.invest.service;

import com.app.invest.model.Banco;
import com.app.invest.repository.BancoRepository;
import com.app.invest.utils.QueryLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepository;



    @Autowired
    private QueryLogger queryLogger;

    public List<Banco> findAll(){
        return bancoRepository.findAll();
    }

    public Optional<Banco> findById(Long id) {
        return bancoRepository.findById(id);
    }

    public Banco create(Banco banco){
        System.out.println("Before .save : "+bancoRepository.toString());
        var created = bancoRepository.save(banco);
        System.out.println("After .save : "+bancoRepository.toString());
        queryLogger.logToFile("insert into banco (nome) values ('"+banco.getNome()+"');");
        return created;
    }

    public Banco updateBanco(Banco banco, Long id){
        Banco updated = bancoRepository.findById(id).orElseThrow(() -> new RuntimeException("Banco não encontrada para atualizar"));
        banco.setId(updated.getId());
        updated = bancoRepository.save(banco);
        queryLogger.logToFile("update banco set nome = '"+banco.getNome()+"' where id = "+banco.getId()+";");
        return updated;
    }

    public boolean deleteBanco(Long id){

        if(bancoRepository.findById(id).isEmpty()){
            return false;
        }

        bancoRepository.deleteById(id);
        queryLogger.logToFile("delete from banco where id = "+id+";");
        return true;

    }

}
