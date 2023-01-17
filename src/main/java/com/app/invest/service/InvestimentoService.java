package com.app.invest.service;

import com.app.invest.model.RegistroInvestimento;
import com.app.invest.repository.InvestimentoRepository;
import com.app.invest.utils.QueryLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class InvestimentoService {

    @Autowired
    private InvestimentoRepository investimentoRepository;

    @Autowired
    private QueryLogger queryLogger;

    public List<RegistroInvestimento> findAll(){
        return investimentoRepository.findAll();
    }

    public RegistroInvestimento findById(Long id) throws Throwable {
        return investimentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Investimento não encontrada"));
    }

    public RegistroInvestimento create(RegistroInvestimento investimento){
        var created = investimentoRepository.save(investimento);

        queryLogger.writeQueriesToFileInSeparateThread("insert into registro_investimento " +
                "(banco, data, investimento, investidor, rendimento, valor, vencimento) values " +
                "("+investimento.getBanco().getId()+", '" +
                investimento.getData()+"', '" +
                investimento.getInvestimento()+"', " +
                investimento.getPessoa().getId()+", '" +
                investimento.getRendimento()+"', " +
                investimento.getValor()+", '" +
                investimento.getVencimento()+"');");

        System.out.println("Terminado Create");
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
