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
public class Tecnico extends Pessoa {

    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Tecnico(Integer id, String senha, String email, String cpf, String nome, List<Chamado> chamados) {
        super(id, senha, email, cpf, nome);
        this.chamados = chamados;
        addPerfil(Perfil.CLIENTE);
    }
}