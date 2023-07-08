package com.skilloo.api.services;

import com.skilloo.api.dto.LabDTO;
import com.skilloo.api.entities.Lab;
import com.skilloo.api.repositories.LabRepository;
import com.skilloo.api.services.exceptions.DataNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LabService {

    @Autowired
    private LabRepository repository;

    @Transactional(readOnly = true)
    public Page<LabDTO> findAllLabs(Pageable pageable) {
        return repository.findAll(pageable).map(LabDTO::new);
    }

    @Transactional
    public LabDTO insertLab(LabDTO dto) {
        Lab lab = new Lab(null, dto.getNome());
        return new LabDTO(repository.save(lab));
    }

    @Transactional
    public LabDTO updateLab(Long id, LabDTO dto) {

        try{
            Lab entity = repository.getReferenceById(id);
            entity.setNome(dto.getNome());

            return new LabDTO(repository.save(entity));
        }

        catch (EntityNotFoundException e){
            throw new DataNotFoundException("Id not found: " + id);
        }
    }

    @Transactional
    public void deleteLab(Long id) {

        Optional<Lab> lab = repository.findById(id);

        if (lab.isEmpty()){
            throw new DataNotFoundException("Id not found");
        }

        repository.deleteById(id);
    }
}
