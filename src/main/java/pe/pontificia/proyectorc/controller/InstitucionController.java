package pe.pontificia.proyectorc.controller;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.pontificia.proyectorc.dto.InstitucionDTO;
import pe.pontificia.proyectorc.model.Institucion;
import pe.pontificia.proyectorc.repository.InstitucionRepository;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/admin/institucion")
public class InstitucionController {
    private  final InstitucionRepository institucionRepository;

    public InstitucionController(InstitucionRepository institucionRepository){
        this.institucionRepository=institucionRepository;
    }

    @GetMapping("")
    Page<Institucion> index(@PageableDefault(sort="nombre",size=5)Pageable pageable){
        return institucionRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    Institucion obtener(@PathVariable Integer id){
        return institucionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Institucion crear(@RequestBody @Validated InstitucionDTO institucionDTO){
        Institucion institucion=new ModelMapper().map(institucionDTO,Institucion.class);
        return institucionRepository.save(institucion);
    }

    @PutMapping("/{id}")
    Institucion actualizar(@PathVariable Integer id, @RequestBody InstitucionDTO institucionDTO){
        Institucion institucion=institucionRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        new ModelMapper().map(institucionDTO, institucion);
        return institucionRepository.save(institucion);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Integer id){
        Institucion institucion=institucionRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        institucionRepository.delete(institucion);
    }


}
