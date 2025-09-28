package upeu.edu.pe.ExamenU1_ticona.mappers;

import upeu.edu.pe.ExamenU1_ticona.entity.Cliente;
import upeu.edu.pe.ExamenU1_ticona.dto.ClienteDTO;
import upeu.edu.pe.ExamenU1_ticona.mappers.base.BaseMappers;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ClienteMapper extends BaseMappers<Cliente, ClienteDTO> {
}
