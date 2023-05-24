package com.skilloo.api.services;


import com.skilloo.api.dto.aula.AulaDTO;
import com.skilloo.api.entities.Aula;
import com.skilloo.api.repositories.AulaRepository;
import com.skilloo.api.services.exceptions.NenhumaAulaAtribuidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    @Transactional
    public List<AulaDTO> buscarAulasDoDia(Long idUser){

        //definindo o presente dia
        int dia = definirDia();

        //buscando as aulas do professor
        List<Aula> aulas = aulaRepository.findAllByProfessorId(idUser);

        //filtrando as aulas pelo dia atual e transformando em DTO
        List<AulaDTO> aulaDTOS = aulas.stream().filter(aula -> aula.getDia().getValue() == dia).map(AulaDTO::new).toList();

        if(aulaDTOS.isEmpty()){
            throw new NenhumaAulaAtribuidaException("Você não tem aulas atribuidas para hoje.");
        }
        return aulaDTOS;
    }

    public void deletarAulasPorProfessor(Long idUser){
        aulaRepository.deleteByProfessorId(idUser);
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
