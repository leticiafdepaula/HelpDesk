package com.leticia.helpDesk.resources;

import com.leticia.helpDesk.domain.Tecnico;
import com.leticia.helpDesk.dtos.TecnicoDTO;
import com.leticia.helpDesk.services.TecnicoService;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico obj = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDTO> listdtos = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listdtos);
    }
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody  TecnicoDTO tecnicoDTO) {
            Tecnico newObj = tecnicoService.create(tecnicoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
            return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody  TecnicoDTO objDTO) {
        Tecnico oldObj = tecnicoService.update(id, objDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(oldObj));

    }
        @DeleteMapping(value = "/{id}")
        public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id) {
            tecnicoService.delete(id);
        }
}