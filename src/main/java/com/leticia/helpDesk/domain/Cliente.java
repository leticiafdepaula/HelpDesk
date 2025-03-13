package com.leticia.helpDesk.domain;

import com.leticia.helpDesk.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cliente extends Pessoa{
 private static final long serialVersionUID = 1L;

 @OneToMany(mappedBy = "cliente")
 private List<Chamado> chamados = new ArrayList<>();

   public Cliente() {
       super();
       addPerfil(Perfil.CLIENTE);
   }

    public Cliente(Integer id, String senha, String email, String cpf, String nome, List<Chamado> chamados) {
        super(id, senha, email, cpf, nome);
        addPerfil(Perfil.CLIENTE);
        this.chamados = chamados;
    }
}
