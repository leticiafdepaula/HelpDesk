package com.leticia.helpDesk.services;

import com.leticia.helpDesk.domain.Pessoa;
import com.leticia.helpDesk.domain.Tecnico;
import com.leticia.helpDesk.dtos.TecnicoDTO;
import com.leticia.helpDesk.exception.DataIntegrityViolationException;
import com.leticia.helpDesk.exception.ObjectNotFoundException;
import com.leticia.helpDesk.repositories.PessoaRepository;
import com.leticia.helpDesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

        @Autowired
        private TecnicoRepository tecnicoRepository;
        @Autowired
        private PessoaRepository pessoaRepository;

        public Tecnico findById(Integer id) {
                Optional<Tecnico> obj = tecnicoRepository.findById(id);
                return obj.orElseThrow(()-> new ObjectNotFoundException("OBJETO NÃO ENCONTRADO COM ID: " + id));
        }

        public List<Tecnico> findAll() {
                return tecnicoRepository.findAll();
        }

        public Tecnico create(TecnicoDTO objDTO) {
                objDTO.setId(null);
                validaPorCpfEEmail(objDTO);
                Tecnico newObj = new Tecnico(objDTO);
                return tecnicoRepository.save(newObj);
        }

        public Tecnico update(Integer id, TecnicoDTO objDTO) {
                objDTO.setId(id);
                Tecnico oldObj = findById(id);
                validaPorCpfEEmail(objDTO);
                oldObj = new Tecnico(objDTO);
                return tecnicoRepository.save(oldObj);
        }

        public void delete(Integer id) {
                Tecnico obj = findById(id);
                if( obj.getChamados().size() > 0 ) {
                        throw new DataIntegrityViolationException("Tecnico possui ordens de serviço e não pode ser deletado");
                }
                   pessoaRepository.deleteById(id);
        }

        private void validaPorCpfEEmail(TecnicoDTO objDTO) {
                Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
                if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
                  throw new DataIntegrityViolationException("CPF já cadastrado!");
                }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
                if (obj.isPresent() && obj.get().getEmail()!= objDTO.getEmail()) {
                        throw new DataIntegrityViolationException("Email já cadastrado!");
                }
        }


}
