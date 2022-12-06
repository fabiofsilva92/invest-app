package com.app.invest.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RegistroInvestimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "investidor")
    private Pessoa pessoa;
    @OneToOne
    @JoinColumn(name = "banco")
    private Banco banco;
    private String investimento;
    private String rendimento;
    private String data;
    private String vencimento;
    private Double valor;


}
