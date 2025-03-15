package com.leticia.helpDesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leticia.helpDesk.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Pessoa{
 private static final long serialVersionUID = 1L;

    @JsonIgnore
 @OneToMany(mappedBy = "cliente")
 private List<Chamado> chamados = new ArrayList<>();

   public Cliente() {
       super();
       addPerfil(Perfil.CLIENTE);
   }

    public Cliente(Integer id, String senha, String email, String cpf, String nome) {
        super(id, senha, email, cpf, nome);
        addPerfil(Perfil.CLIENTE);

    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
