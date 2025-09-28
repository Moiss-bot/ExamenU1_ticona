package upeu.edu.pe.ExamenU1_ticona.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import upeu.edu.pe.ExamenU1_ticona.dto.ParticipaDTO;
import upeu.edu.pe.ExamenU1_ticona.entity.Participa;
import upeu.edu.pe.ExamenU1_ticona.mappers.base.BaseMappers;

@Mapper(componentModel = "spring")
public interface ParticipaMapper extends BaseMappers<Participa, ParticipaDTO> {
    @Mapping(source = "proyecto.id", target = "proyectoId")
    @Mapping(source = "colaborador.id", target = "colaboradorId")
    ParticipaDTO toDTO(Participa participa);

    @InheritInverseConfiguration
    Participa toEntity(ParticipaDTO participaDTO);
}
