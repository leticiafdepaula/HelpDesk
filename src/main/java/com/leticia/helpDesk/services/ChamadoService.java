package com.leticia.helpDesk.services;

import com.leticia.helpDesk.domain.Chamado;
import com.leticia.helpDesk.domain.Cliente;
import com.leticia.helpDesk.domain.Tecnico;
import com.leticia.helpDesk.domain.enums.Prioridade;
import com.leticia.helpDesk.domain.enums.Status;
import com.leticia.helpDesk.dtos.ChamadoDTO;
import com.leticia.helpDesk.exception.ObjectNotFoundException;
import com.leticia.helpDesk.repositories.ChamadoRepository;
import com.leticia.helpDesk.repositories.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

       public Chamado findById(Integer id) {
           Optional<Chamado> obj = chamadoRepository.findById(id);
           return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com o ID: " + id));
       }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado create(@Valid  ChamadoDTO objDTO) {
       return chamadoRepository.save(newChamado(objDTO));
    }

    private Chamado newChamado(ChamadoDTO objDTO) {
        Tecnico tecnico = tecnicoService.findById(objDTO.getTecnico());
        Cliente cliente = clienteService.findById(objDTO.getCliente());
        Chamado chamado = new Chamado();
        if(objDTO.getId() != null) {
            chamado.setId(objDTO.getId());
        }
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(objDTO.getStatus()));
        chamado.setTitulo(objDTO.getTitulo());
        chamado.setObservacoes(objDTO.getObservacoes());
        return chamado;
    }
}
