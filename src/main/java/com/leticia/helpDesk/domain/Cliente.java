package com.leticia.helpDesk.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cliente extends Pessoa{

 private List<Chamado> chamados = new ArrayList<>();

   public Cliente() {
       super();
   }

    public Cliente(Integer id, String senha, String email, String cpf, String nome, List<Chamado> chamados) {
        super(id, senha, email, cpf, nome);
        this.chamados = chamados;
    }
}
