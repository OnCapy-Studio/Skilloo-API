package com.skilloo.api.services.gestao;

import com.skilloo.api.dto.MateriaDTO;
import com.skilloo.api.entities.Materia;
import com.skilloo.api.entities.enuns.AreasEtec;
import com.skilloo.api.repositories.MateriaRepository;
import com.skilloo.api.services.exceptions.DataNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository repository;

    @Transactional
    public Page<MateriaDTO> findAllMaterias(Pageable pageable) {
        return repository.findAll(pageable).map(MateriaDTO::new);
    }

    @Transactional
    public Page<MateriaDTO> buscarMateriasPorArea(AreasEtec area, Pageable pageable) {
        return repository.buscarMateriasPorArea(area, pageable);
    }

    @Transactional
    public MateriaDTO insertMateria(MateriaDTO dto) {
        Materia materia = new Materia();
        materia.setNome(dto.getNome());
        materia.setArea(dto.getArea());

        return new MateriaDTO(repository.save(materia));
    }

    @Transactional
    public MateriaDTO updateMateria(Long id, MateriaDTO dto) {

        try{
            Materia materia = repository.getReferenceById(id);
            materia.setNome(dto.getNome());
            materia.setArea(dto.getArea());

            return new MateriaDTO(repository.save(materia));
        }
        catch (EntityNotFoundException e){
            throw new DataNotFoundException("Materia não encontrada: " + id);
        }
    }

    @Transactional
    public void deleteMateria(Long id) {

        Optional<Materia> materia = repository.findById(id);

        if (materia.isEmpty()){
            throw new DataNotFoundException("Materia não encontrada: " + id);
        }

        repository.deleteById(id);
    }
}
