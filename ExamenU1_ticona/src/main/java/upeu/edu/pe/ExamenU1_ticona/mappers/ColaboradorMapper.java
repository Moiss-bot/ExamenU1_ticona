package upeu.edu.pe.ExamenU1_ticona.mappers;

import org.mapstruct.Mapper;
import upeu.edu.pe.ExamenU1_ticona.dto.ColaboradorDTO;
import upeu.edu.pe.ExamenU1_ticona.entity.Colaborador;
import upeu.edu.pe.ExamenU1_ticona.mappers.base.BaseMappers;

@Mapper(componentModel = "spring")
public interface ColaboradorMapper extends BaseMappers<Colaborador, ColaboradorDTO> {
}
