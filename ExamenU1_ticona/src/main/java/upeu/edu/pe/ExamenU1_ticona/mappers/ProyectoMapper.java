package upeu.edu.pe.ExamenU1_ticona.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import upeu.edu.pe.ExamenU1_ticona.dto.ProyectoDTO;
import upeu.edu.pe.ExamenU1_ticona.entity.Proyecto;
import upeu.edu.pe.ExamenU1_ticona.mappers.base.BaseMappers;

@Mapper(componentModel = "spring", uses = {ParticipaMapper.class})
public interface ProyectoMapper extends BaseMappers<Proyecto, ProyectoDTO> {
    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "participas", target = "participas")
    ProyectoDTO toDTO(Proyecto proyecto);

    @InheritInverseConfiguration
    Proyecto toEntity(ProyectoDTO proyectoDTO);
}
