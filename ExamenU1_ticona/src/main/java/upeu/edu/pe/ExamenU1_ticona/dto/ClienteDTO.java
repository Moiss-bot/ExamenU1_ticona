package upeu.edu.pe.ExamenU1_ticona.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDTO {

    private Long id;
    private String telefono;
    private String direccion;
    private String razon_social;
}
