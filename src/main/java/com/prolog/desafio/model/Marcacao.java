package com.prolog.desafio.model;

import javax.persistence.*;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "Marcacao")
public class Marcacao {

    @Id
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "cod_tipo_marcacao", referencedColumnName = "codigo")
    private MarcacaoTipo tipoMarcacao;

    @ManyToOne
    @JoinColumn(name = "cpf_colaborador", referencedColumnName = "CPF")
    private Colaborador colaborador;

    @Column(name = "data_hora_marcacao")
    private ZonedDateTime dataHoraMarcacao;

    @Column(name = "tipo_marcacao")
    private String tipo;

    // Getters e Setters

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public MarcacaoTipo getTipoMarcacao() {
        return tipoMarcacao;
    }

    public void setTipoMarcacao(MarcacaoTipo tipoMarcacao) {
        this.tipoMarcacao = tipoMarcacao;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public ZonedDateTime getDataHoraMarcacao() {
        return dataHoraMarcacao;
    }

    public void setDataHoraMarcacao(ZonedDateTime dataHoraMarcacao) {
        this.dataHoraMarcacao = dataHoraMarcacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
