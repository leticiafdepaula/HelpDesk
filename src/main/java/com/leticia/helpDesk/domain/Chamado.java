package com.leticia.helpDesk.domain;

import com.leticia.helpDesk.domain.enums.Prioridade;
import com.leticia.helpDesk.domain.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class Chamado {

    private Integer id;
    private LocalDate dataAbertura = LocalDate.now();
    private LocalDate dataFechamento;
    private Prioridade prioridade;
    private Status status;
    private String titulo;
    private String observacoes;
    private Tecnico tecnico;
    private Cliente cliente;

    public Chamado() {
        super();
    }

    public Chamado(Integer id, Prioridade prioridade, Status status, String titulo, String observacoes, Tecnico tecnico, Cliente cliente) {
        this.id = id;
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Chamado chamado)) return false;
        return Objects.equals(id, chamado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
