package com.forohub.api.controller;

import com.forohub.api.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico){
        var topico = new Topico(datosRegistroTopico);
         topicoRepository.save(topico);
         return ResponseEntity.ok(datosRegistroTopico);
    }

    @GetMapping
    public List<DatosListaTopico> listarTopicos
            (@PageableDefault(size = 10, sort = {"fechaCreacion"})Pageable paginacion){
        Page<DatosListaTopico> topicos = topicoRepository.findAll(paginacion).map(DatosListaTopico::new);
        return topicos.getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornarDatosTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico.getId(),topico.getTitulo(),
                topico.getMensaje(),topico.getAutor(),topico.getCurso());
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico (@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos){
        Optional<Topico> topicoExistente = topicoRepository.findById(id);
        if (topicoExistente.isPresent()){
            var topico = topicoRepository.getReferenceById(topicoExistente.get().getId());
            topico.actualizarInformacion(datos);
            return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(),topico.getTitulo(),
                    topico.getMensaje(),topico.getAutor(),topico.getCurso()));
        }
            return ResponseEntity.notFound().build();
    }

    //Delete logico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Optional<Topico> topicoExistente = topicoRepository.findById(id);
        if (topicoExistente.isPresent()){
            var topico = topicoRepository.getReferenceById(topicoExistente.get().getId());
            topico.desactivarTopico();
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
