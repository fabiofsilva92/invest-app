package com.app.invest.service;

import com.app.invest.model.Banco;
import com.app.invest.model.RegistroInvestimento;
import com.app.invest.repository.InvestimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class InvestimentoService {

    @Autowired
    private InvestimentoRepository investimentoRepository;

    @Autowired
    private FileService fileService;

    public List<RegistroInvestimento> findAll(){
        return investimentoRepository.findAll();
    }

    public RegistroInvestimento findById(Long id) throws Throwable {
        return investimentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Investimento não encontrada"));
    }

    public RegistroInvestimento create(RegistroInvestimento investimento){
        var created = investimentoRepository.save(investimento);
        try{
            fileService.generateInsertInvestimento(created);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return created;
    }

    public RegistroInvestimento updateInvestimento(RegistroInvestimento investimento){
        investimento.setId(investimentoRepository.findById(investimento.getId()).orElseThrow(() -> new RuntimeException("Investimento não encontrada para atualizar")).getId());
        return investimentoRepository.save(investimento);
    }

    public void deleteInvestimento(Long id){
        investimentoRepository.delete(investimentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Investimento não encontrada para deletar")));
    }

}
