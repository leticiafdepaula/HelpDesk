package com.leticia.helpDesk.services;

import com.leticia.helpDesk.domain.Cliente;
import com.leticia.helpDesk.domain.Pessoa;
import com.leticia.helpDesk.dtos.ClienteDTO;
import com.leticia.helpDesk.exception.DataIntegrityViolationException;
import com.leticia.helpDesk.exception.ObjectNotFoundException;
import com.leticia.helpDesk.repositories.ClienteRepository;
import com.leticia.helpDesk.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

        @Autowired
        private ClienteRepository clienteRepository;
        @Autowired
        private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

        public Cliente findById(Integer id) {
                Optional<Cliente> obj = clienteRepository.findById(id);
                return obj.orElseThrow(()-> new ObjectNotFoundException("OBJETO NÃO ENCONTRADO COM ID: " + id));
        }

        public List<Cliente> findAll() {
                return clienteRepository.findAll();
        }

        public Cliente create(ClienteDTO objDTO) {
                objDTO.setId(null);
                objDTO.setSenha(bcryptPasswordEncoder.encode(objDTO.getSenha()));
                validaPorCpfEEmail(objDTO);
                Cliente newObj = new Cliente(objDTO);
                return clienteRepository.save(newObj);
        }

        public Cliente update(Integer id, ClienteDTO objDTO) {
                objDTO.setId(id);
                Cliente oldObj = findById(id);
                validaPorCpfEEmail(objDTO);
                oldObj = new Cliente(objDTO);
                return clienteRepository.save(oldObj);
        }

        public void delete(Integer id) {
                Cliente obj = findById(id);
                if( obj.getChamados().size() > 0 ) {
                        throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado.");
                }
                   pessoaRepository.deleteById(id);
        }

        private void validaPorCpfEEmail(ClienteDTO objDTO) {
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
