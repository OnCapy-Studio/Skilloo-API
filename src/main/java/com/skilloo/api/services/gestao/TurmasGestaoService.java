package com.skilloo.api.services.gestao;

import com.skilloo.api.dto.TurmaDTO;
import com.skilloo.api.entities.Turma;
import com.skilloo.api.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        System.out.println(dto);
        Turma entity = new Turma();
        entity.setNome(dto.getNome());
        entity.setPeriodo(dto.getPeriodo());

        return new TurmaDTO(repository.save(entity));
    }
}
