package upeu.edu.pe.ExamenU1_ticona.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import upeu.edu.pe.ExamenU1_ticona.dto.ColaboradorDTO;
import upeu.edu.pe.ExamenU1_ticona.entity.Colaborador;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-24T16:34:26-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ColaboradorMapperImpl implements ColaboradorMapper {

    @Override
    public ColaboradorDTO toDTO(Colaborador entity) {
        if ( entity == null ) {
            return null;
        }

        ColaboradorDTO colaboradorDTO = new ColaboradorDTO();

        colaboradorDTO.setId( entity.getId() );
        colaboradorDTO.setNif( entity.getNif() );
        colaboradorDTO.setNombre( entity.getNombre() );
        colaboradorDTO.setDomicilio( entity.getDomicilio() );
        colaboradorDTO.setTelefono( entity.getTelefono() );
        colaboradorDTO.setBanco( entity.getBanco() );
        colaboradorDTO.setNum_cuenta( entity.getNum_cuenta() );

        return colaboradorDTO;
    }

    @Override
    public Colaborador toEntity(ColaboradorDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Colaborador.ColaboradorBuilder colaborador = Colaborador.builder();

        colaborador.id( dto.getId() );
        colaborador.nif( dto.getNif() );
        colaborador.nombre( dto.getNombre() );
        colaborador.domicilio( dto.getDomicilio() );
        colaborador.telefono( dto.getTelefono() );
        colaborador.banco( dto.getBanco() );
        colaborador.num_cuenta( dto.getNum_cuenta() );

        return colaborador.build();
    }

    @Override
    public List<ColaboradorDTO> toDTOs(List<Colaborador> list) {
        if ( list == null ) {
            return null;
        }

        List<ColaboradorDTO> list1 = new ArrayList<ColaboradorDTO>( list.size() );
        for ( Colaborador colaborador : list ) {
            list1.add( toDTO( colaborador ) );
        }

        return list1;
    }

    @Override
    public List<Colaborador> toEntityList(List<ColaboradorDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Colaborador> list1 = new ArrayList<Colaborador>( list.size() );
        for ( ColaboradorDTO colaboradorDTO : list ) {
            list1.add( toEntity( colaboradorDTO ) );
        }

        return list1;
    }
}
