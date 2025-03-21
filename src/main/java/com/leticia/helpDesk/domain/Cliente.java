package com.leticia.helpDesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leticia.helpDesk.domain.enums.Perfil;
import com.leticia.helpDesk.dtos.ClienteDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Cliente(ClienteDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf =obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = (obj.getDataCriacao() != null) ? obj.getDataCriacao() : LocalDateTime.now();
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
