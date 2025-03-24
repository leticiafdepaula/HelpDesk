package com.leticia.helpDesk.services;

import com.leticia.helpDesk.config.AppProperties;
import com.leticia.helpDesk.domain.Chamado;
import com.leticia.helpDesk.domain.Tecnico;
import com.leticia.helpDesk.domain.enums.Prioridade;
import com.leticia.helpDesk.domain.enums.Status;
import com.leticia.helpDesk.repositories.ChamadoRepository;
import com.leticia.helpDesk.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
@Component
public class DBServices {

    @Value("${app.default.password}")
    private String senha;

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AppProperties appProperties;

    public void instanciaDB() {
        String senha = appProperties.getDefaultPassword();  // Use o método para acessar a senha


    Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "550.482.150-95", "valdir@mail.com", encoder.encode("SENHA"));
        Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", encoder.encode("SENHA"));
        Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", encoder.encode("SENHA"));
        Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", encoder.encode("SENHA"));
        Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", encoder.encode("SENHA"));

        Tecnico cli1 = new Tecnico(null, "Albert Einstein", "111.661.890-74", "einstein@mail.com", encoder.encode("SENHA"));
        Tecnico cli2 = new Tecnico(null, "Marie Curie", "322.429.140-06", "curie@mail.com", encoder.encode("SENHA"));
        Tecnico cli3 = new Tecnico(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", encoder.encode("SENHA"));
        Tecnico cli4 = new Tecnico(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", encoder.encode("SENHA"));
        Tecnico cli5 = new Tecnico(null, "Max Planck", "081.399.300-83", "planck@mail.com" , encoder.encode("SENHA"));

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

