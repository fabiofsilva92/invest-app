package com.app.invest.repository;

import com.app.invest.model.RegistroInvestimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestimentoRepository extends JpaRepository<RegistroInvestimento, Long> {
}
