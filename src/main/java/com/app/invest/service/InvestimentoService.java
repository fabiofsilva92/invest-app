package com.app.invest.service;

import com.app.invest.model.RegistroInvestimento;
import com.app.invest.repository.InvestimentoRepository;
import com.app.invest.utils.QueryLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        queryLogger.logToFile("insert into registro_investimento " +
                "(banco, data, investimento, investidor, rendimento, valor, vencimento) values " +
                "("+investimento.getBanco().getId()+", '" +
                investimento.getData()+"', '" +
                investimento.getInvestimento()+"', " +
                investimento.getPessoa().getId()+", '" +
                investimento.getRendimento()+"', " +
                investimento.getValor()+", '" +
                investimento.getVencimento()+"');");
        return created;
    }

    public RegistroInvestimento updateInvestimento(RegistroInvestimento investimento, Long id){
        RegistroInvestimento updated = investimentoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Investimento não encontrado para atualizar"));
        investimento.setId(updated.getId());
        updated = investimento;
        queryLogger.logToFile("update investimento set data = '"+investimento.getData()+"', " +
                "banco = "+investimento.getBanco().getId()+", " +
                "investimento = '"+investimento.getInvestimento()+"', " +
                "investidor = "+investimento.getPessoa().getId()+", " +
                "rendimento = '"+investimento.getRendimento()+"', " +
                "valor = "+investimento.getValor()+", " +
                "vencimento = '"+investimento.getVencimento()+"' " +
                "where id = "+investimento.getId()+";");
        return investimentoRepository.save(updated);
    }

    public void deleteInvestimento(Long id){
        investimentoRepository.delete(investimentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Investimento não encontrada para deletar")));
        queryLogger.logToFile("delete from investimento where id = "+id+";");
    }

}
