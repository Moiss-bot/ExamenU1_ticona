package upeu.edu.pe.ExamenU1_ticona.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upeu.edu.pe.ExamenU1_ticona.dto.ProyectoDTO;
import upeu.edu.pe.ExamenU1_ticona.entity.Cliente;
import upeu.edu.pe.ExamenU1_ticona.entity.Proyecto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-24T16:34:26-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ProyectoMapperImpl implements ProyectoMapper {

    @Autowired
    private ParticipaMapper participaMapper;

    @Override
    public List<ProyectoDTO> toDTOs(List<Proyecto> list) {
        if ( list == null ) {
            return null;
        }

        List<ProyectoDTO> list1 = new ArrayList<ProyectoDTO>( list.size() );
        for ( Proyecto proyecto : list ) {
            list1.add( toDTO( proyecto ) );
        }

        return list1;
    }

    @Override
    public List<Proyecto> toEntityList(List<ProyectoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Proyecto> list1 = new ArrayList<Proyecto>( list.size() );
        for ( ProyectoDTO proyectoDTO : list ) {
            list1.add( toEntity( proyectoDTO ) );
        }

        return list1;
    }

    @Override
    public ProyectoDTO toDTO(Proyecto proyecto) {
        if ( proyecto == null ) {
            return null;
        }

        ProyectoDTO proyectoDTO = new ProyectoDTO();

        proyectoDTO.setClienteId( proyectoClienteId( proyecto ) );
        proyectoDTO.setParticipas( participaMapper.toDTOs( proyecto.getParticipas() ) );
        proyectoDTO.setId( proyecto.getId() );
        proyectoDTO.setDescripcion( proyecto.getDescripcion() );
        proyectoDTO.setPrecio( proyecto.getPrecio() );
        proyectoDTO.setFecha_inicio( proyecto.getFecha_inicio() );
        proyectoDTO.setFecha_fin( proyecto.getFecha_fin() );

        return proyectoDTO;
    }

    @Override
    public Proyecto toEntity(ProyectoDTO proyectoDTO) {
        if ( proyectoDTO == null ) {
            return null;
        }

        Proyecto.ProyectoBuilder proyecto = Proyecto.builder();

        proyecto.cliente( proyectoDTOToCliente( proyectoDTO ) );
        proyecto.participas( participaMapper.toEntityList( proyectoDTO.getParticipas() ) );
        proyecto.id( proyectoDTO.getId() );
        proyecto.descripcion( proyectoDTO.getDescripcion() );
        proyecto.precio( proyectoDTO.getPrecio() );
        proyecto.fecha_inicio( proyectoDTO.getFecha_inicio() );
        proyecto.fecha_fin( proyectoDTO.getFecha_fin() );

        return proyecto.build();
    }

    private Long proyectoClienteId(Proyecto proyecto) {
        Cliente cliente = proyecto.getCliente();
        if ( cliente == null ) {
            return null;
        }
        return cliente.getId();
    }

    protected Cliente proyectoDTOToCliente(ProyectoDTO proyectoDTO) {
        if ( proyectoDTO == null ) {
            return null;
        }

        Cliente.ClienteBuilder cliente = Cliente.builder();

        cliente.id( proyectoDTO.getClienteId() );

        return cliente.build();
    }
}
