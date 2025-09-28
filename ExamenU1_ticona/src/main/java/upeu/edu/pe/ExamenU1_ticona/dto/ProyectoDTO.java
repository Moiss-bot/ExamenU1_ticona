package upeu.edu.pe.ExamenU1_ticona.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProyectoDTO {
    private Long id;
    private String descripcion;
    private BigDecimal precio;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Long clienteId;
    private List<ParticipaDTO> participas;
}
