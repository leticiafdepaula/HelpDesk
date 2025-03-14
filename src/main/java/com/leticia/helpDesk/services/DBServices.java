package com.leticia.helpDesk.services;

import com.leticia.helpDesk.domain.Chamado;
import com.leticia.helpDesk.domain.Cliente;
import com.leticia.helpDesk.domain.Tecnico;
import com.leticia.helpDesk.domain.enums.Perfil;
import com.leticia.helpDesk.domain.enums.Prioridade;
import com.leticia.helpDesk.domain.enums.Status;
import com.leticia.helpDesk.repositories.ChamadoRepository;
import com.leticia.helpDesk.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBServices {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;


    public void instanciaDB() {

        Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "550.482.150-95", "valdir@mail.com","123");
        tec1.addPerfil(Perfil.ADMIN);
        Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", "123");
        Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com","123");
        Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", "123");
        Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com","123");

        Cliente cli1 = new Cliente(null, "123 ", "einstein@mail.com", "111.661.890-74","Albert Einstein");
        Cliente cli2 = new Cliente(null, "123", "curie@mail.com", "322.429.140-06","Marie Curie");
        Cliente cli3 = new Cliente(null, "123", "darwin@mail.com", "792.043.830-62", "Charles Darwin");
        Cliente cli4 = new Cliente(null, "123", "hawking@mail.com", "177.409.680-30", "Stephen Hawking");
        Cliente cli5 = new Cliente(null, "123", "planck@mail.com", "081.399.300-83", "Max Planck");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 1", "Teste chamado 1", tec1, cli1);
        Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", tec1, cli2);
        Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", tec2, cli3);
        Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", tec3, cli3);
        Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", tec2, cli1);
        Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 7", "Teste chamado 6", tec1, cli5);

        pessoaRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, cli1, cli2, cli3, cli4, cli5));
        chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
    }
}

