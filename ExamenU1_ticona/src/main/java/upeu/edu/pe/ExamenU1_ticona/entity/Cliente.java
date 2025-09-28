package upeu.edu.pe.ExamenU1_ticona.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="TBL_CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TELEFONO", unique = true, nullable = false, length = 15)
    private String telefono;

    @Column(name = "DIRECCION", unique = true, nullable = false, length = 150)
    private String direccion;

    @Column(name = "RAZON_SOCIAL", unique = true, nullable = false, length = 200)
    private String razon_social;

}
