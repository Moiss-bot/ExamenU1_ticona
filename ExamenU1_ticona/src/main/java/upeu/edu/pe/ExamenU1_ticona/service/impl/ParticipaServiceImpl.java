package upeu.edu.pe.ExamenU1_ticona.service.impl;

import org.hibernate.service.spi.ServiceException;
import upeu.edu.pe.ExamenU1_ticona.controller.exceptions.ResourceNotFoundException;
import upeu.edu.pe.ExamenU1_ticona.dto.ParticipaDTO;
import upeu.edu.pe.ExamenU1_ticona.entity.Colaborador;
import upeu.edu.pe.ExamenU1_ticona.entity.Participa;
import upeu.edu.pe.ExamenU1_ticona.entity.Proyecto;
import upeu.edu.pe.ExamenU1_ticona.mappers.ParticipaMapper;
import upeu.edu.pe.ExamenU1_ticona.repository.ColaboradorRepository;
import upeu.edu.pe.ExamenU1_ticona.repository.ParticipaRepository;
import upeu.edu.pe.ExamenU1_ticona.repository.ProyectoRepository;
import upeu.edu.pe.ExamenU1_ticona.service.service.ParticipaService;

import java.math.BigDecimal;
import java.util.List;

public class ParticipaServiceImpl implements ParticipaService {
    private final ParticipaRepository participaRepository;
    private final ParticipaMapper participaMapper;
    private final ProyectoRepository proyectoRepository;
    private final ColaboradorRepository colaboradorRepository;

    public ParticipaServiceImpl(ParticipaRepository participaRepository,ParticipaMapper participaMapper,ProyectoRepository proyectoRepository,ColaboradorRepository colaboradorRepository) {
        this.participaRepository = participaRepository;
        this.participaMapper = participaMapper;
        this.proyectoRepository = proyectoRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    public ParticipaDTO create(ParticipaDTO participaDTO) throws ServiceException {
        if (participaDTO == null) {
            throw new IllegalArgumentException("El Participa no puede ser nulo.");
        }

        Proyecto proyecto = proyectoRepository.findById(participaDTO.getProyectoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Proyecto no encontrado con ID: " + participaDTO.getProyectoId()));

        Colaborador colaborador = colaboradorRepository.findById(participaDTO.getColaboradorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Colaborador no encontrado con ID: " + participaDTO.getColaboradorId()));

        try {
            Participa participa = participaMapper.toEntity(participaDTO);

            // 🔑 Seteamos las relaciones
            participa.setProyecto(proyecto);
            participa.setColaborador(colaborador);

            return participaMapper.toDTO(participaRepository.save(participa));
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la participación", e);
        }
    }

    @Override
    public ParticipaDTO update(Long aLong, ParticipaDTO participaDTO) throws ServiceException {
        if (aLong == null || participaDTO == null) {
            throw new IllegalArgumentException("El ID y el participa no pueden ser nulos");
        }
        Participa existente = participaRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Participación no encontrada con ID: " + aLong));

        Proyecto proyecto = proyectoRepository.findById(participaDTO.getProyectoId())
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con ID: " + participaDTO.getProyectoId()));

        Colaborador colaborador = colaboradorRepository.findById(participaDTO.getColaboradorId())
                .orElseThrow(() -> new ResourceNotFoundException("Colaborador no encontrado con ID: " + participaDTO.getColaboradorId()));


        try {
            // 🔑 Actualizamos las relaciones
            existente.setProyecto(proyecto);
            existente.setColaborador(colaborador);

            Participa actualizado = participaRepository.save(existente);
            return participaMapper.toDTO(actualizado);

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ParticipaDTO findById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulos");
        }
        Participa existente = participaRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Participa no encontrado con ID: " + aLong));
        try {
            return participaMapper.toDTO(existente);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        Participa existente = participaRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Participa no encontrado con ID: " + aLong));
        try{
            participaRepository.delete(existente);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ParticipaDTO> findAll() throws ServiceException {
        List<Participa> participas = participaRepository.findAll();
        if (participas.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron participas registrados");
        }

        return participas.stream()
                .map(participaMapper::toDTO)
                .toList();
    }
}
