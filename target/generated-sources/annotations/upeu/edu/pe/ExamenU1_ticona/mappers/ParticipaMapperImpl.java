package upeu.edu.pe.ExamenU1_ticona.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import upeu.edu.pe.ExamenU1_ticona.dto.ParticipaDTO;
import upeu.edu.pe.ExamenU1_ticona.entity.Colaborador;
import upeu.edu.pe.ExamenU1_ticona.entity.Participa;
import upeu.edu.pe.ExamenU1_ticona.entity.Proyecto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-24T16:34:26-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ParticipaMapperImpl implements ParticipaMapper {

    @Override
    public List<ParticipaDTO> toDTOs(List<Participa> list) {
        if ( list == null ) {
            return null;
        }

        List<ParticipaDTO> list1 = new ArrayList<ParticipaDTO>( list.size() );
        for ( Participa participa : list ) {
            list1.add( toDTO( participa ) );
        }

        return list1;
    }

    @Override
    public List<Participa> toEntityList(List<ParticipaDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Participa> list1 = new ArrayList<Participa>( list.size() );
        for ( ParticipaDTO participaDTO : list ) {
            list1.add( toEntity( participaDTO ) );
        }

        return list1;
    }

    @Override
    public ParticipaDTO toDTO(Participa participa) {
        if ( participa == null ) {
            return null;
        }

        ParticipaDTO participaDTO = new ParticipaDTO();

        participaDTO.setProyectoId( participaProyectoId( participa ) );
        participaDTO.setColaboradorId( participaColaboradorId( participa ) );
        participaDTO.setId( participa.getId() );

        return participaDTO;
    }

    @Override
    public Participa toEntity(ParticipaDTO participaDTO) {
        if ( participaDTO == null ) {
            return null;
        }

        Participa.ParticipaBuilder participa = Participa.builder();

        participa.proyecto( participaDTOToProyecto( participaDTO ) );
        participa.colaborador( participaDTOToColaborador( participaDTO ) );
        participa.id( participaDTO.getId() );

        return participa.build();
    }

    private Long participaProyectoId(Participa participa) {
        Proyecto proyecto = participa.getProyecto();
        if ( proyecto == null ) {
            return null;
        }
        return proyecto.getId();
    }

    private Long participaColaboradorId(Participa participa) {
        Colaborador colaborador = participa.getColaborador();
        if ( colaborador == null ) {
            return null;
        }
        return colaborador.getId();
    }

    protected Proyecto participaDTOToProyecto(ParticipaDTO participaDTO) {
        if ( participaDTO == null ) {
            return null;
        }

        Proyecto.ProyectoBuilder proyecto = Proyecto.builder();

        proyecto.id( participaDTO.getProyectoId() );

        return proyecto.build();
    }

    protected Colaborador participaDTOToColaborador(ParticipaDTO participaDTO) {
        if ( participaDTO == null ) {
            return null;
        }

        Colaborador.ColaboradorBuilder colaborador = Colaborador.builder();

        colaborador.id( participaDTO.getColaboradorId() );

        return colaborador.build();
    }
}
