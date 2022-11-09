package pe.pontificia.proyectorc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.pontificia.proyectorc.model.Institucion;

import java.util.List;
import java.util.Optional;

public interface InstitucionRepository extends JpaRepository<Institucion, Integer> {

}
