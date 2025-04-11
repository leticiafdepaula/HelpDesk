package com.leticia.helpDesk.services;

import com.leticia.helpDesk.domain.Chamado;
import com.leticia.helpDesk.domain.Cliente;
import com.leticia.helpDesk.domain.Tecnico;
import com.leticia.helpDesk.domain.enums.Prioridade;
import com.leticia.helpDesk.domain.enums.Status;
import com.leticia.helpDesk.repositories.ChamadoRepository;
import com.leticia.helpDesk.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DBServices {

    @Value("${app.default.password}")
    private String senha;

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;


    public void instanciaDB() {
        System.out.println("Senha injetada: " + senha);

        Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "550.482.150-95", "valdir@mail.com", encoder.encode(senha));
        Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", encoder.encode(senha));
        Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", encoder.encode(senha));
        Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", encoder.encode(senha));
        Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", encoder.encode(senha));

        Cliente cli1 = new Cliente(null, encoder.encode(senha), "einstein@mail.com", "111.661.890-74", "Albert Einstein");
        Cliente cli2 = new Cliente(null, encoder.encode(senha), "curie@mail.com", "322.429.140-06", "Marie Curie");
        Cliente cli3 = new Cliente(null, encoder.encode(senha), "darwin@mail.com", "792.043.830-62", "Charles Darwin");
        Cliente cli4 = new Cliente(null, encoder.encode(senha), "hawking@mail.com", "177.409.680-30", "Stephen Hawking");
        Cliente cli5 = new Cliente(null, encoder.encode(senha), "planck@mail.com", "081.399.300-83", "Max Planck");


        Chamado c1 = Chamado.builder()
                .id(null)
                .prioridade(Prioridade.MEDIA)
                .status(Status.ANDAMENTO)
                .titulo("Chamado 1")
                .dataAbertura(LocalDate.now())
                .observacoes("TESTE1")
                .tecnico(tec1)
                .cliente(cli2)
                .build();

        Chamado c2 = Chamado.builder()
                .id(null)
                .prioridade(Prioridade.ALTA)
                .status(Status.ABERTO)
                .titulo("Chamado 2")
                .dataAbertura(LocalDate.now())
                .observacoes("TESTE2 ")
                .tecnico(tec2)
                .cliente(cli3)
                .build();


        Chamado c3 = Chamado.builder()
                .id(null)
                .prioridade(Prioridade.BAIXA)
                .status(Status.ENCERRADO)
                .titulo("Chamado 3")
                .dataAbertura(LocalDate.now())
                .observacoes("TESTE3 ")
                .tecnico(tec2)
                .cliente(cli3)
                .build();

        Chamado c4 = Chamado.builder()
                .id(null)
                .prioridade(Prioridade.ALTA)
                .status(Status.ABERTO)
                .titulo("Chamado 4")
                .dataAbertura(LocalDate.now())
                .observacoes("TESTE4 ")
                .tecnico(tec2)
                .cliente(cli3)
                .build();

        Chamado c5 = Chamado.builder()
                .id(null)
                .prioridade(Prioridade.MEDIA)
                .status(Status.ANDAMENTO)
                .titulo("Chamado 5")
                .dataAbertura(LocalDate.now())
                .observacoes("TESTE5 ")
                .tecnico(tec2)
                .cliente(cli1)
                .build();

        Chamado c6 = Chamado.builder()
                .id(null)
                .prioridade(Prioridade.BAIXA)
                .status(Status.ENCERRADO)
                .titulo("Chamado 6")
                .dataAbertura(LocalDate.now())
                .observacoes("TESTE6 ")
                .tecnico(tec1)
                .cliente(cli5)
                .build();

        pessoaRepository.saveAll(Arrays.asList(tec1,tec2,tec3,tec4,tec5, cli1,cli2,cli3,cli4,cli5));
        chamadoRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6));
}
}
