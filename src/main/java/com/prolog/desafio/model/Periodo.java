package com.prolog.desafio.model;
import java.time.Duration;

public class Periodo {
    private int codigo;
    private String descricao;
    private String tempo;
    private String totalPeriodo;
    private String horasNoturnas;

    public Periodo(int codigo,String descricao, String tempo, String totalPeriodo, String horasNoturnas) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.tempo = tempo;
        this.totalPeriodo = totalPeriodo;
        this.horasNoturnas = horasNoturnas;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTempo() {
        return tempo;
    }

    public String getTotalPeriodo() {
        return totalPeriodo;
    }

    public String getHorasNoturnas() {
        return horasNoturnas;
    }

}
