package com.leticia.helpDesk.domain.enums;

import lombok.Getter;

@Getter
public enum Status {

    ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

    private Integer codigo;
    private String descricao;

    private Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static Status toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Status x : Status.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("perfil invalido");
    }
}