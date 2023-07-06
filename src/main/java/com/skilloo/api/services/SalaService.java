package com.skilloo.api.services;

import com.skilloo.api.dto.SalaDTO;
import com.skilloo.api.entities.Sala;
import com.skilloo.api.repositories.SalaRepository;
import com.skilloo.api.services.exceptions.DataNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository repository;

    @Transactional(readOnly = true)
    public Page<SalaDTO> findAllSalas(Pageable pageable) {
        return repository.findAll(pageable).map(SalaDTO::new);
    }

    @Transactional
    public SalaDTO insertSala(SalaDTO dto) {
        Sala entity = new Sala(null, dto.getNome());

        return new SalaDTO(repository.save(entity));
    }

    @Transactional
    public SalaDTO updateSala(Long id, SalaDTO dto) {
        try {
            Sala sala = repository.getReferenceById(id);
            sala.setNome(dto.getNome());
            return new SalaDTO(repository.save(sala));
        }
        catch (EntityNotFoundException e){
            throw new DataNotFoundException("Id not found: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {

        Optional<Sala> sala = repository.findById(id);

        if (sala.isEmpty()){
            throw new DataNotFoundException("Id not Found: " + id);
        }

        repository.deleteById(id);
    }
}
