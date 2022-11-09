package pe.pontificia.proyectorc.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class InstitucionDTO {
    @NotBlank
    @Size(min = 11, max = 11, message = "solo se escribe 11 digitos")
    private String ruc;

    @NotBlank
    private String nombre;

    @NotNull
    private String telefono;

    @NotNull
    private String direccion;

    @NotNull
    private String departamento;

    @NotNull
    private String provincia;

    @NotNull
    private String distrito;

    @NotNull
    private String autoridad;

    @NotNull
    private String periodo;
}
