package upeu.edu.pe.ExamenU1_ticona.service.impl;

import org.hibernate.service.spi.ServiceException;
import upeu.edu.pe.ExamenU1_ticona.controller.exceptions.ResourceNotFoundException;
import upeu.edu.pe.ExamenU1_ticona.dto.ColaboradorDTO;
import upeu.edu.pe.ExamenU1_ticona.entity.Colaborador;
import upeu.edu.pe.ExamenU1_ticona.mappers.ColaboradorMapper;
import upeu.edu.pe.ExamenU1_ticona.repository.ColaboradorRepository;
import upeu.edu.pe.ExamenU1_ticona.service.service.ColaboradorService;

import java.util.List;

public class ColaboradorServiceImpl implements ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;
    private final ColaboradorMapper colaboradorMapper;

    public ColaboradorServiceImpl(ColaboradorRepository colaboradorRepository,ColaboradorMapper colaboradorMapper) {
        this.colaboradorRepository = colaboradorRepository;
        this.colaboradorMapper = colaboradorMapper;
    }


    @Override
    public ColaboradorDTO create(ColaboradorDTO colaboradorDTO) throws ServiceException {
        try {
            Colaborador colaborador = colaboradorMapper.toEntity(colaboradorDTO);
            Colaborador colaboradorSaved = colaboradorRepository.save(colaborador);
            return colaboradorMapper.toDTO(colaboradorSaved);
        }catch(Exception e) {
            throw new ServiceException("Error al crear Colaborador: "+e.getMessage());
        }
    }

    @Override
    public ColaboradorDTO update(Long aLong, ColaboradorDTO colaboradorDTO) throws ServiceException {
        try {
            Colaborador colaborador1 = colaboradorRepository.findById(aLong).orElseThrow(() -> new ServiceException("No existe el colaborador"));
            colaborador1.setNif(colaboradorDTO.getNif());
            colaborador1.setNombre(colaboradorDTO.getNombre());
            colaborador1.setDomicilio(colaboradorDTO.getDomicilio());
            colaborador1.setTelefono(colaboradorDTO.getTelefono());
            colaborador1.setBanco(colaboradorDTO.getBanco());
            colaborador1.setNum_cuenta(colaboradorDTO.getNum_cuenta());
            return colaboradorMapper.toDTO(colaboradorRepository.save(colaborador1));
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar el colaborador: "+e);
        }
    }

    @Override
    public ColaboradorDTO findById(Long aLong) throws ServiceException {
        try {
            Colaborador colaborador1 =  colaboradorRepository.findById(aLong).orElseThrow(() -> new ServiceException("No existe el colaborador"));
            return colaboradorMapper.toDTO(colaborador1);
        }catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al leer el colaborador con id " + aLong, e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        try {
            if(!colaboradorRepository.findById(aLong).isPresent()){
                throw new ServiceException("No existe el colaborador");
            }
            colaboradorRepository.deleteById(aLong);
        }catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el colaborador con id " + aLong, e);
        }
    }

    @Override
    public List<ColaboradorDTO> findAll() throws ServiceException {
        try {
            return colaboradorMapper.toDTOs(colaboradorRepository.findAll());
        }catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al listar los colaboradores " + e);
        }

    }
}
