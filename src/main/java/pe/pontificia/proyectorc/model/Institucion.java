package pe.pontificia.proyectorc.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Institucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idinstitucion")
    private Integer id;
    private String ruc;
    private String nombre;
    private String telefono;
    private String direccion;
    private String departamento;
    private String provincia;
    private String distrito;
    private String autoridad;
    private String periodo;

}
