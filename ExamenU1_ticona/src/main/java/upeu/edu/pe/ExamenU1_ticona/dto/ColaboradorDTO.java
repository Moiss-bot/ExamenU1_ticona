package upeu.edu.pe.ExamenU1_ticona.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ColaboradorDTO {
    private Long id;
    private String nif;
    private String nombre;
    private String domicilio;
    private String telefono;
    private String banco;
    private String num_cuenta;

}
