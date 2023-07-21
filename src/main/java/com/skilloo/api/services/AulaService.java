package com.skilloo.api.services;


import com.skilloo.api.dto.aula.AulaDTO;
import com.skilloo.api.dto.user.UserDTO;
import com.skilloo.api.entities.Aula;
import com.skilloo.api.entities.User;
import com.skilloo.api.repositories.AulaRepository;
import com.skilloo.api.services.exceptions.DataNotFoundException;
import com.skilloo.api.services.exceptions.NaoAutorizadoException;
import com.skilloo.api.services.exceptions.NenhumaAulaAtribuidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;

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
    public Page<AulaDTO> buscarAulasPorTurma(Long idTurma, Pageable pageable){
        return aulaRepository.buscarAulasPorTurma(idTurma, pageable).map(AulaDTO::new);
    }

    @Transactional
    public void verificarSeProfessorEDocenteDaAula(Long idAula, Long idProfessor){

        Optional<Aula> aula = aulaRepository.findById(idAula);

        //caso o optional volte vazio
        if (aula.isEmpty()){
            throw new DataNotFoundException("Id not found: " + idAula);
        }

        if (aula.get().getProfessor().getId() != idProfessor){
            throw new NaoAutorizadoException("Você não é o docente desta aula!");
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

    public void deletarAulasPorProfessor(Long idUser){
        aulaRepository.deleteByProfessorId(idUser);
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
}
