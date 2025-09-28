package upeu.edu.pe.ExamenU1_ticona.service.impl;

import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import upeu.edu.pe.ExamenU1_ticona.controller.exceptions.ResourceNotFoundException;
import upeu.edu.pe.ExamenU1_ticona.controller.exceptions.ResourceValidationException;
import upeu.edu.pe.ExamenU1_ticona.dto.ParticipaDTO;
import upeu.edu.pe.ExamenU1_ticona.dto.ProyectoDTO;
import upeu.edu.pe.ExamenU1_ticona.entity.Cliente;
import upeu.edu.pe.ExamenU1_ticona.entity.Colaborador;
import upeu.edu.pe.ExamenU1_ticona.entity.Participa;
import upeu.edu.pe.ExamenU1_ticona.entity.Proyecto;
import upeu.edu.pe.ExamenU1_ticona.mappers.ProyectoMapper;
import upeu.edu.pe.ExamenU1_ticona.repository.ClienteRepository;
import upeu.edu.pe.ExamenU1_ticona.repository.ColaboradorRepository;
import upeu.edu.pe.ExamenU1_ticona.repository.ProyectoRepository;
import upeu.edu.pe.ExamenU1_ticona.service.service.ProyectoService;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final ProyectoMapper proyectoMapper;
    private final ClienteRepository clienteRepository;
    private final ColaboradorRepository colaboradorRepository;

    public ProyectoServiceImpl(ProyectoRepository proyectoRepository,ProyectoMapper proyectoMapper, ClienteRepository clienteRepository, ColaboradorRepository colaboradorRepository) {
        this.proyectoRepository = proyectoRepository;
        this.proyectoMapper = proyectoMapper;
        this.clienteRepository = clienteRepository;
        this.colaboradorRepository = colaboradorRepository;
    }
    @Transactional
    @Override
    public ProyectoDTO create(ProyectoDTO proyectoDTO) throws ServiceException {
        if( proyectoDTO == null ) {
            throw new IllegalArgumentException("El proyecto no puede ser nulo.");
        }
        Cliente cliente = clienteRepository.findById(proyectoDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + proyectoDTO.getClienteId()));
        if (proyectoDTO.getParticipas()== null || proyectoDTO.getParticipas().isEmpty()) {
            throw new ResourceValidationException("El proyecto debe tener al menos un colaborador asociado");
        }
        Proyecto proyecto = new Proyecto();
        proyecto.setDescripcion(proyectoDTO.getDescripcion());
        proyecto.setPrecio(proyectoDTO.getPrecio());
        proyecto.setFecha_inicio(proyectoDTO.getFecha_inicio());
        proyecto.setFecha_fin(proyectoDTO.getFecha_fin());
        proyecto.setCliente(cliente);

        if (proyecto.getFecha_inicio() != null && proyecto.getFecha_fin() != null) {
            if (proyecto.getFecha_fin().before(proyecto.getFecha_inicio())) {
                throw new ResourceValidationException("La fecha de fin no puede ser anterior a la fecha de inicio");
            }
        }

            List<Participa> participas = new ArrayList<>();
            for (ParticipaDTO p : proyectoDTO.getParticipas()) {
                if (p.getColaboradorId() == null) {
                    throw new ResourceValidationException("El proyecto debe tener 1 colaborador");
                }
                Colaborador colaborador = colaboradorRepository.findById(p.getColaboradorId())
                        .orElseThrow(() -> new ResourceNotFoundException("Colaborador no encontrado con ID: " + p.getColaboradorId()));

                Participa participa = new Participa();
                participa.setProyecto(proyecto);
                participa.setColaborador(colaborador);

                participas.add(participa);
            }
            proyecto.setParticipas(participas);
            Proyecto guardada = proyectoRepository.save(proyecto);
            return proyectoMapper.toDTO(guardada);

        }

    @Transactional
    @Override
    public ProyectoDTO update(Long aLong, ProyectoDTO proyectoDTO) throws ServiceException {
        if (aLong == null || proyectoDTO == null) {
            throw new IllegalArgumentException("El ID y el proyecto no pueden ser nulos");
        }
        Proyecto proExistente = proyectoRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con ID: " + aLong));

        Cliente cliente = clienteRepository.findById(proyectoDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + proyectoDTO.getClienteId()));

        if (proyectoDTO.getParticipas() == null || proyectoDTO.getParticipas().isEmpty()) {
            throw new ResourceValidationException("El proyecto debe tener al menos un colaborador");
        }
        proExistente.setDescripcion(proyectoDTO.getDescripcion());
        proExistente.setPrecio(proyectoDTO.getPrecio());
        proExistente.setFecha_inicio(proyectoDTO.getFecha_inicio());
        proExistente.setFecha_fin(proyectoDTO.getFecha_fin());
        proExistente.setCliente(cliente);
        proExistente.getParticipas().clear();

        if (proExistente.getFecha_inicio() != null && proExistente.getFecha_fin() != null) {
            if (proExistente.getFecha_fin().before(proExistente.getFecha_inicio())) {
                throw new ResourceValidationException("La fecha de fin no puede ser anterior a la fecha de inicio");
            }
        }

        List<Participa> nuevosParticipas = new ArrayList<>();

        for (ParticipaDTO p : proyectoDTO.getParticipas()) {

            if (p.getColaboradorId() == null) {
                throw new ResourceValidationException("Cada participación debe tener un colaborador válido");
            }


            Colaborador colaborador = colaboradorRepository.findById(p.getColaboradorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Colaborador no encontrado con ID: " + p.getColaboradorId()));

            Participa participa = new Participa();
            participa.setProyecto(proExistente);
            participa.setColaborador(colaborador);

            nuevosParticipas.add(participa);
        }

        proExistente.setParticipas(nuevosParticipas);
        Proyecto actualizada = proyectoRepository.save(proExistente);
        return proyectoMapper.toDTO(actualizada);
    }

    @Transactional
    @Override
    public ProyectoDTO findById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        Proyecto venta = proyectoRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrada con ID: " + aLong));

        return proyectoMapper.toDTO(venta);
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        if (!proyectoRepository.existsById(aLong)) {
            throw new ResourceNotFoundException("Proyecto no encontrada con ID: " + aLong);
        }

        try {
            proyectoRepository.deleteById(aLong);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar el proyecto porque tiene colaboradores asociados", ex);
        }
    }
    @Transactional
    @Override
    public List<ProyectoDTO> findAll() throws ServiceException {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        return proyectos.stream()
                .map(proyectoMapper::toDTO)
                .toList();
    }
    }

