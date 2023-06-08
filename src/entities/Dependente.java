package entities;

import entities.enums.TipoDependente;

import java.time.LocalDate;

public class Dependente {
    private String nome;
    private TipoDependente tipoDependente;
    private LocalDate dataNascimento;

    public Dependente() {
    }

    public Dependente(String nome, TipoDependente tipoDependente, LocalDate dataNascimento) {
        this.nome = nome;
        this.tipoDependente = tipoDependente;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoDependente getTipoDependente() {
        return tipoDependente;
    }

    public void setTipoDependente(TipoDependente tipoDependente) {
        this.tipoDependente = tipoDependente;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
