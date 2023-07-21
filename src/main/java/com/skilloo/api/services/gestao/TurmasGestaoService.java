package com.skilloo.api.services.gestao;

import com.skilloo.api.dto.MateriaDTO;
import com.skilloo.api.dto.TurmaDTO;
import com.skilloo.api.entities.Materia;
import com.skilloo.api.entities.Turma;
import com.skilloo.api.repositories.TurmaRepository;
import com.skilloo.api.services.exceptions.DataNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TurmasGestaoService {

    @Autowired
    private TurmaRepository repository;

    @Transactional
    public Page<TurmaDTO> findAllTurmas(Pageable pageable) {

        return repository.findAll(pageable).map(TurmaDTO::new);
    }


    @Transactional
    public TurmaDTO insertSala(TurmaDTO dto) {

        Turma entity = new Turma();
        entity.setNome(dto.getNome());
        entity.setPeriodo(dto.getPeriodo());

        return new TurmaDTO(repository.save(entity));
    }

    @Transactional
    public TurmaDTO updateTurma(Long id, TurmaDTO dto) {
        try{
            Turma turma = repository.getReferenceById(id);
            turma.setNome(dto.getNome());
            turma.setPeriodo(dto.getPeriodo());

            return new TurmaDTO(repository.save(turma));
        }
        catch (EntityNotFoundException e){
            throw new DataNotFoundException("Turma não encontrada: " + id);
        }
    }

    @Transactional
    public void deleteTurma(Long id) {

        Optional<Turma> turma = repository.findById(id);

        if (turma.isEmpty()){
            throw new DataNotFoundException("Materia não encontrada: " + id);
        }

        repository.deleteById(id);
    }
}
