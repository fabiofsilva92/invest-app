package com.app.invest.service;

import com.app.invest.model.Banco;
import com.app.invest.model.Pessoa;
import com.app.invest.repository.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private FileService fileService;

    public List<Banco> findAll(){
        return bancoRepository.findAll();
    }

    public Banco findById(Long id) throws Throwable {
        return bancoRepository.findById(id).orElseThrow(() -> new RuntimeException("Banco não encontrada"));
    }

    public Banco create(Banco banco){
        var created = bancoRepository.save(banco);
        try {
            fileService.generateInsertBanco(created);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return created;
    }

    public Banco updateBanco(Banco banco){
        banco.setId(bancoRepository.findById(banco.getId()).orElseThrow(() -> new RuntimeException("Banco não encontrada para atualizar")).getId());
        return bancoRepository.save(banco);
    }

    public void deleteBanco(Long id){
        bancoRepository.delete(bancoRepository.findById(id).orElseThrow(() -> new RuntimeException("Banco não encontrada para deletar")));
    }

}
