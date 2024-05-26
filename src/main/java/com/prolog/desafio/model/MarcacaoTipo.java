package com.prolog.desafio.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "marcacao_tipo")
public class MarcacaoTipo {

    @Id
    private Long codigo;
    private String nome;
    private Long tempoRecomendadoMinutos;

    // Getters e Setters

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTempoRecomendadoMinutos() {
        return tempoRecomendadoMinutos;
    }

    public void setTempoRecomendadoMinutos(Long tempoRecomendadoMinutos) {
        this.tempoRecomendadoMinutos = tempoRecomendadoMinutos;
    }
}
