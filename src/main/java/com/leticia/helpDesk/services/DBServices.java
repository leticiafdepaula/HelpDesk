package com.leticia.helpDesk.services;

import com.leticia.helpDesk.domain.Chamado;
import com.leticia.helpDesk.domain.Cliente;
import com.leticia.helpDesk.domain.Tecnico;
import com.leticia.helpDesk.domain.enums.Perfil;
import com.leticia.helpDesk.domain.enums.Prioridade;
import com.leticia.helpDesk.domain.enums.Status;
import com.leticia.helpDesk.repositories.ChamadoRepository;
import com.leticia.helpDesk.repositories.ClienteRepository;
import com.leticia.helpDesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBServices {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;


    public void instanciaDB() {

            Tecnico tec1 = new Tecnico(null, "leticia ferreira", "46509572060"," leticia@mail.com","123");
            tec1.addPerfil(Perfil.ADMIN);

            Cliente cli1 = new Cliente(null, "123", "torvalds@mail.com", "74291163029","Linus Torvalds");

            Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);


            tecnicoRepository.saveAll(Arrays.asList(tec1));
            clienteRepository.saveAll(Arrays.asList(cli1));
            chamadoRepository.saveAll(Arrays.asList(c1));
        }
    }

