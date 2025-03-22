package com.leticia.helpDesk.services;

import com.leticia.helpDesk.domain.Chamado;
import com.leticia.helpDesk.exception.ObjectNotFoundException;
import com.leticia.helpDesk.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

       @Autowired
    private ChamadoRepository chamadoRepository;

       public Chamado findById(Integer id) {
           Optional<Chamado> obj = chamadoRepository.findById(id);
           return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com o ID: " + id));
       }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }
}
