package com.skilloo.api.services;


import com.skilloo.api.dto.aula.AulaDTO;
import com.skilloo.api.dto.aula.AulaInsertDTO;
import com.skilloo.api.entities.Aula;
import com.skilloo.api.entities.Turma;
import com.skilloo.api.entities.User;
import com.skilloo.api.repositories.AulaRepository;
import com.skilloo.api.repositories.MateriaRepository;
import com.skilloo.api.repositories.ProfessorRepository;
import com.skilloo.api.repositories.TurmaRepository;
import com.skilloo.api.services.exceptions.DataNotFoundException;
import com.skilloo.api.services.exceptions.ForbiddenException;
import com.skilloo.api.services.exceptions.NenhumaAulaAtribuidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.*;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Transactional
    public AulaDTO findById(Long id){

        Optional<Aula> aula = aulaRepository.findById(id);

        //caso o optional volte vazio
        if (aula.isEmpty()){
            throw new DataNotFoundException("Id not found: " + id);
        }

        return new AulaDTO(aula.get());
    }

    @Transactional
    public Page<AulaDTO> buscarAulasPorTurma(Long idTurma, DayOfWeek dia, Pageable pageable){
        return aulaRepository.buscarAulasPorTurma(idTurma, dia, pageable).map(AulaDTO::new);
    }

    @Transactional
    public void verificarSeProfessorEDocenteDaAula(Long idAula, Long idProfessor){

        Optional<Aula> aula = aulaRepository.findById(idAula);

        //caso o optional volte vazio
        if (aula.isEmpty()){
            throw new DataNotFoundException("Id not found: " + idAula);
        }


        boolean isDocente = false;
        for (User user : aula.get().getProfessores()){
            if (Objects.equals(user.getId(), idProfessor)) {
                isDocente = true;
                break;
            }
        }

        if (!isDocente){
            throw new ForbiddenException("Você não é o docente desta aula!");
        }

    }

    @Transactional
    public Page<AulaDTO> buscarAulasDoDia(Long idUser){

        //definindo o presente dia
        int dia = definirDia();

        //buscando as aulas do professor
        List<Aula> aulas = aulaRepository.findAllByProfessorId(idUser);

        //filtrando as aulas pelo dia atual e transformando em DTO
        List<AulaDTO> aulaDTOS = aulas.stream().filter(aula -> aula.getDia().getValue() == dia).map(AulaDTO::new).toList();

        if(aulaDTOS.isEmpty()){
            throw new NenhumaAulaAtribuidaException("Você não tem aulas atribuidas para hoje.");
        }
        return new PageImpl<>(aulaDTOS);
    }


    @Transactional
    public boolean verificarSeProfessorTemAulasComUmaTurma(Long idUser, Long idTurma, Long idMateria){

        //false - ele nao tem aulas
        //true - ele tem aulas
        return !(aulaRepository.verificarSeProfessorTemAulaComUmaTurma(idUser, idTurma, idMateria).isEmpty());
    }

    @Transactional
    public Page<AulaDTO> buscarAulasPorDia(DayOfWeek diaDaSemana, Long idUser, Pageable pageable) {

        return aulaRepository.buscarAulasPorDia(diaDaSemana, idUser, pageable).map(AulaDTO::new);
    }


    //método para buscar em número inteiro a representação do dia de hj

    private int definirDia() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
        int day = cal.get(Calendar.DAY_OF_WEEK) - 1;

        //fim de semana
        if(day == 0 || day == 6){
            day = 1;
        }
        //definindo a variavel final do dia
        return day;
    }

    public void atualizarHorario(Long idTurma, DayOfWeek dia, List<AulaInsertDTO> dtos) {

        List<Aula> aulas =  aulaRepository.buscarAulasPorTurma(idTurma, dia);

        if (!aulas.isEmpty()){
            aulas.forEach(x -> aulaRepository.delete(x));
        }

        Turma turma = turmaRepository.getReferenceById(idTurma);

        dtos.forEach(dto -> {
            Aula aula = new Aula();
            aula.setDia(dia);
            aula.setHorario(dto.getHorario());
            aula.setProfessores(dto.getProfessoresId().stream().map(x -> professorRepository.getReferenceById(x)).toList());
            aula.setMateria(materiaRepository.getReferenceById(dto.getMateriaId()));
            aula.setTurma(turma);

            aulaRepository.save(aula);
        });
    }
}
