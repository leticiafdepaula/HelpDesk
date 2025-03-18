package com.leticia.helpDesk.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.leticia.helpDesk.domain.Tecnico;
import com.leticia.helpDesk.domain.enums.Perfil;
import jakarta.validation.constraints.NotNull;
import java.security.SecureRandomParameters;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TecnicoDTO implements SecureRandomParameters {
    private static final long serialVersionUID = 1L;

    protected Integer id;

    @NotNull(message = " O campo NOME é requerido")
    protected String nome;

    @NotNull(message = " O campo CPF é requerido")
    protected String cpf;

    @NotNull(message = " O campo EMAIL é requerido")
    protected String email;

    @NotNull(message = " O campo SENHA é requerido")
    protected String senha;

    protected Set<Integer> perfis =  new HashSet<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("dataCriacao")
    protected LocalDateTime dataCriacao = LocalDateTime.now();

    public TecnicoDTO() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public TecnicoDTO(Tecnico obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf =obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = (obj.getDataCriacao() != null) ? obj.getDataCriacao() : LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil( Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
