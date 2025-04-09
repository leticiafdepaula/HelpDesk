package com.leticia.helpDesk.services;

import com.leticia.helpDesk.domain.Chamado;
import com.leticia.helpDesk.domain.Cliente;
import com.leticia.helpDesk.domain.Tecnico;
import com.leticia.helpDesk.domain.enums.Prioridade;
import com.leticia.helpDesk.domain.enums.Status;
import com.leticia.helpDesk.dtos.ChamadoDTO;
import com.leticia.helpDesk.exception.ObjectNotFoundException;
import com.leticia.helpDesk.repositories.ChamadoRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    private Prioridade prioridade;


    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com o ID: " + id));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado create(@Valid ChamadoDTO objDTO) {
        return chamadoRepository.save(newChamado(objDTO));
    }

    public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
        objDTO.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDTO);
        return chamadoRepository.save(oldObj);
    }

        private Chamado newChamado(ChamadoDTO objDTO) {
            if (objDTO.getPrioridade() == null) {
                throw new IllegalArgumentException("Prioridade não pode ser nula.");
            }
            if (objDTO.getStatus() == null) {
                throw new IllegalArgumentException("Status não pode ser nulo.");
            }

            Tecnico tecnico = tecnicoService.findById(objDTO.getTecnico());
            Cliente cliente = clienteService.findById(objDTO.getCliente());

            Chamado chamado = new Chamado();
            if (objDTO.getId() != null) {
                chamado.setId(objDTO.getId());
            }

            if (objDTO.getStatus().equals(2)) {
                chamado.setDataFechamento(LocalDate.now());
            }

            chamado.setTecnico(tecnico);
            chamado.setCliente(cliente);
            chamado.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()));
            chamado.setStatus(Status.toEnum(objDTO.getStatus()));
            chamado.setTitulo(objDTO.getTitulo());
            chamado.setObservacoes(objDTO.getObservacoes());

            return chamado;
        }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            System.out.println("Nenhum token JWT encontrado.");
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7).trim();
        System.out.println("🔍 Token recebido: " + token);

        if (token.isEmpty()) {
            System.out.println("Token vazio ou inválido.");
            chain.doFilter(request, response);
            return;
        }

    }
}
