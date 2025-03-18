package com.leticia.helpDesk.domain;

import com.leticia.helpDesk.domain.enums.Perfil;
import com.leticia.helpDesk.dtos.TecnicoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Tecnico extends Pessoa {

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.CLIENTE);
    }
    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, senha, email, cpf, nome);
        addPerfil(Perfil.CLIENTE);
    }

    public Tecnico(TecnicoDTO obj) {
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