package upeu.edu.pe.ExamenU1_ticona.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import upeu.edu.pe.ExamenU1_ticona.dto.ClienteDTO;
import upeu.edu.pe.ExamenU1_ticona.entity.Cliente;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-24T16:34:26-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDTO toDTO(Cliente entity) {
        if ( entity == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId( entity.getId() );
        clienteDTO.setTelefono( entity.getTelefono() );
        clienteDTO.setDireccion( entity.getDireccion() );
        clienteDTO.setRazon_social( entity.getRazon_social() );

        return clienteDTO;
    }

    @Override
    public Cliente toEntity(ClienteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cliente.ClienteBuilder cliente = Cliente.builder();

        cliente.id( dto.getId() );
        cliente.telefono( dto.getTelefono() );
        cliente.direccion( dto.getDireccion() );
        cliente.razon_social( dto.getRazon_social() );

        return cliente.build();
    }

    @Override
    public List<ClienteDTO> toDTOs(List<Cliente> list) {
        if ( list == null ) {
            return null;
        }

        List<ClienteDTO> list1 = new ArrayList<ClienteDTO>( list.size() );
        for ( Cliente cliente : list ) {
            list1.add( toDTO( cliente ) );
        }

        return list1;
    }

    @Override
    public List<Cliente> toEntityList(List<ClienteDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Cliente> list1 = new ArrayList<Cliente>( list.size() );
        for ( ClienteDTO clienteDTO : list ) {
            list1.add( toEntity( clienteDTO ) );
        }

        return list1;
    }
}
