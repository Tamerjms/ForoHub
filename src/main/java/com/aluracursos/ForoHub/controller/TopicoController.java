package com.aluracursos.ForoHub.controller;

import com.aluracursos.ForoHub.domain.topicos.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicosRepository topicosRepository;

    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopicos datosRegistroTopicos){
        topicosRepository.save(new Topicos(datosRegistroTopicos));
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<DatosListadoTopicos>> listadoTopicos(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(topicosRepository.findAll(paginacion).map(DatosListadoTopicos::new));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopicos> retornarDatosTopico(@PathVariable Long id){
        Topicos topicos = topicosRepository.getReferenceById(id);
        var datosTopicos = new DatosRespuestaTopicos(topicos.getId(), topicos.getMensaje(), topicos.getNombreCurso(), topicos.getTitulo());
        return ResponseEntity.ok(datosTopicos);
    }

    @GetMapping("/diezprimeros")
    @Transactional
    public ResponseEntity<Page<DatosListadoTopicos>> listadoTopicos10Primeros(){
        Pageable paginacion = PageRequest.of(0,10, Sort.by("fecha").ascending());
        Page<DatosListadoTopicos> pagina = topicosRepository.findAll(paginacion).map(DatosListadoTopicos::new);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/ordenpornombrecurso")
    @Transactional
    public ResponseEntity<Page<DatosListadoTopicos>> listadoTopicosPorNombreCurso(){
        Pageable paginacion = PageRequest.of(0,100, Sort.by("nombreCurso").ascending());
        Page<DatosListadoTopicos> pagina = topicosRepository.findAll(paginacion).map(DatosListadoTopicos::new);
        return ResponseEntity.ok(pagina);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopicos> actualizarTipicos(@RequestBody @Valid DatosActualizarTopicos datosActualizarTopicos){
        Optional<Topicos> optionalTopicos = topicosRepository.findById(datosActualizarTopicos.id());
        if (optionalTopicos.isPresent()) {
            Topicos topicos = optionalTopicos.get();
            topicos.actualizarDatos(datosActualizarTopicos);
            topicosRepository.save(topicos);
            return ResponseEntity.ok(new DatosRespuestaTopicos(topicos.getId(), topicos.getMensaje(), topicos.getNombreCurso(), topicos.getTitulo()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTopico(@PathVariable Long id){
        topicosRepository.deleteById(id);
    }
}
