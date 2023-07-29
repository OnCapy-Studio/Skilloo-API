package com.skilloo.api.services.professor;

import com.skilloo.api.dto.commit.CommitDTO;
import com.skilloo.api.dto.commit.CommitInsertDTO;
import com.skilloo.api.entities.Commit;
import com.skilloo.api.repositories.*;
import com.skilloo.api.services.AulaService;
import com.skilloo.api.services.exceptions.DataNotFoundException;
import com.skilloo.api.services.exceptions.ForbiddenException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class CommitService {

    @Autowired
    private CommitRepository repository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AulaService aulaService;

    @Transactional
    public Page<CommitDTO> buscarCommitsDaTurma(Long idTurma, Long idMateria, Long idUser, Pageable pageable) {

        //false - ele nao tem aulas
        //true - ele tem aulas
        if(!aulaService.verificarSeProfessorTemAulasComUmaTurma(idUser, idTurma, idMateria)){
            throw new ForbiddenException("Você não tem acesso a estes commits!");
        }

        return repository.buscarCommitsDaTurma(idTurma, idMateria, pageable);
    }

    @Transactional
    public CommitDTO insertCommit(CommitInsertDTO dto, Long idUser, Long idTurma, Long idMateria) {

        if(!aulaService.verificarSeProfessorTemAulasComUmaTurma(idUser, idTurma, idMateria)){
            throw new ForbiddenException("Você não tem acesso a estes commits!");
        }

        Commit commit = new Commit();
        copyDtoToEntity(dto, commit, idUser, idTurma, idMateria);
        return new CommitDTO(repository.save(commit));
    }

    @Transactional
    public CommitDTO updateCommit(CommitInsertDTO dto, Long idCommit, Long idUser) {
        try{
            Commit entity = repository.getReferenceById(idCommit);

            if(!Objects.equals(entity.getAutor().getId(), idUser)){
                throw new ForbiddenException("Você não tem acesso a estes commits!");
            }
            copyDtoToEntity(dto, entity);
            return new CommitDTO(repository.save(entity));
        }
        catch (EntityNotFoundException e){
            throw new DataNotFoundException("Nenhum commit encontrado com o ID: " + idCommit);
        }
    }

    @Transactional
    public void deleteCommit(Long idCommit, Long idUser) {

        Optional<Commit> entity = repository.findById(idCommit);

        if(entity.isEmpty()){
            throw new DataNotFoundException("Id not Found: " + idCommit);
        }

        if (!Objects.equals(entity.get().getAutor().getId(), idUser)){
            throw new ForbiddenException("Vocẽ não pode deletar um commit que não seja de sua autoria.");
        }

        repository.deleteById(idCommit);
    }

    private void copyDtoToEntity(CommitInsertDTO dto, Commit entity, Long idUser, Long idTurma, Long idMateria){
        entity.setTitulo(dto.getTitulo());
        entity.setDescricao(dto.getDescricao());
        entity.setData(dto.getData());
        entity.setMateria(materiaRepository.getReferenceById(idMateria));
        entity.setTurma(turmaRepository.getReferenceById(idTurma));
        entity.setAutor(professorRepository.getReferenceById(idUser));
    }

    private void copyDtoToEntity(CommitInsertDTO dto, Commit entity){
        entity.setTitulo(dto.getTitulo());
        entity.setDescricao(dto.getDescricao());
        entity.setData(dto.getData());
    }
}
