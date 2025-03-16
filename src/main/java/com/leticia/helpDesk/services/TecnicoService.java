package com.leticia.helpDesk.services;

import com.leticia.helpDesk.domain.Tecnico;
import com.leticia.helpDesk.exception.ObjectNotFoundException;
import com.leticia.helpDesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

        @Autowired
        private TecnicoRepository tecnicoRepository;

        public Tecnico findById(Integer id) {
                Optional<Tecnico> obj = tecnicoRepository.findById(id);
                return obj.orElseThrow(()-> new ObjectNotFoundException("OBJETO NÃO ENCONTRADO COM ID: " + id));
        }

        public List<Tecnico> findAll() {
                return tecnicoRepository.findAll();
        }
}
