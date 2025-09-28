package upeu.edu.pe.ExamenU1_ticona.service.impl;

import upeu.edu.pe.ExamenU1_ticona.controller.exceptions.ResourceNotFoundException;
import upeu.edu.pe.ExamenU1_ticona.dto.ClienteDTO;
import upeu.edu.pe.ExamenU1_ticona.entity.Cliente;
import upeu.edu.pe.ExamenU1_ticona.mappers.ClienteMapper;
import upeu.edu.pe.ExamenU1_ticona.repository.ClienteRepository;
import upeu.edu.pe.ExamenU1_ticona.service.service.ClienteService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteDTO create(ClienteDTO clienteDTO) throws ServiceException {
        try {
            Cliente cliente = clienteMapper.toEntity(clienteDTO);
            Cliente clienteSaved = clienteRepository.save(cliente);
            return clienteMapper.toDTO(clienteSaved);
        }catch(Exception e) {
            throw new ServiceException("Error al crear Cliente: "+e.getMessage());
        }
    }

    @Override
    public ClienteDTO update(Long aLong, ClienteDTO clienteDTO) throws ServiceException {
        try {
            Cliente cliente1 = clienteRepository.findById(aLong).orElseThrow(() -> new ServiceException("No existe el cliente"));
            cliente1.setTelefono(clienteDTO.getTelefono());
            cliente1.setDireccion(clienteDTO.getDireccion());
            cliente1.setRazon_social(clienteDTO.getRazon_social());


            return clienteMapper.toDTO(clienteRepository.save(cliente1));
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar el cliente: "+e);
        }
    }

    @Override
    public ClienteDTO findById(Long aLong) throws ServiceException {
        try {
            Cliente cliente1 =  clienteRepository.findById(aLong).orElseThrow(() -> new ServiceException("No existe el cliente"));
            return clienteMapper.toDTO(cliente1);
        }catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al leer el cliente con id " + aLong, e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        try {
            if(!clienteRepository.findById(aLong).isPresent()){
                throw new ServiceException("No existe el cliente");
            }
            clienteRepository.deleteById(aLong);
        }catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el cliente con id " + aLong, e);
        }
    }

    @Override
    public List<ClienteDTO> findAll() throws ServiceException {
        try {
            return clienteMapper.toDTOs(clienteRepository.findAll());
        }catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al listar los clientes " + e);
        }
    }
}
