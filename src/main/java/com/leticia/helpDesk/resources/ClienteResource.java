package com.leticia.helpDesk.resources;

import com.leticia.helpDesk.domain.Cliente;
import com.leticia.helpDesk.dtos.ClienteDTO;
import com.leticia.helpDesk.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        Cliente obj = clienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = clienteService.findAll();
        List<ClienteDTO> listdtos = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listdtos);
    }
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody  ClienteDTO clienteDTO) {
            Cliente newObj = clienteService.create(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
            return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody  ClienteDTO objDTO) {
       Cliente oldObj = clienteService.update(id, objDTO);
        return ResponseEntity.ok().body(new ClienteDTO(oldObj));

    }
        @DeleteMapping(value = "/{id}")
        public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id) {
            clienteService.delete(id);
            return ResponseEntity.noContent().build();
    }
}