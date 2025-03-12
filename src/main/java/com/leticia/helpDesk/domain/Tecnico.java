package com.leticia.helpDesk.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Tecnico extends Pessoa {

    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
    }

    public Tecnico(Integer id, String senha, String email, String cpf, String nome, List<Chamado> chamados) {
        super(id, senha, email, cpf, nome);
        this.chamados = chamados;
    }
}