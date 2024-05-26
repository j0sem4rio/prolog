package com.prolog.desafio.service;

import com.prolog.desafio.model.Marcacao;
import com.prolog.desafio.model.Periodo;
import com.prolog.desafio.repository.ColaboradorRepository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;


@Service
public class PrologService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;
    
    public  List<Periodo> calcularHorasTrabalhadas(String cpfColaborador, ZonedDateTime startDate, ZonedDateTime endDate) {
    	 List<Marcacao> marcacoes =  colaboradorRepository.calcularHorasTrabalhadas(cpfColaborador, startDate, endDate);
    	 
    	 Map<String, List<Marcacao>> groupedMarcacoes = new HashMap<>();

         for (Marcacao marcacao : marcacoes) {
             String tipoMarcacao = marcacao.getTipoMarcacao().getNome();
             groupedMarcacoes.computeIfAbsent(tipoMarcacao, k -> new ArrayList<>()).add(marcacao);
         }

         List<Periodo> periodos = new ArrayList<>();

         for (Map.Entry<String, List<Marcacao>> entry : groupedMarcacoes.entrySet()) {
             String tipoMarcacao = entry.getKey();
             List<Marcacao> marcacoesList = entry.getValue();

             Duration totalDuration = Duration.ZERO;
             Duration nightDuration = Duration.ZERO;
             Duration recommendedDuration = Duration.ZERO;

             ZonedDateTime start = null;
             for (Marcacao marcacao : marcacoesList) {
                 if (marcacao.getTipo().equals("MARCACAO_INICIO")) {
                     start = marcacao.getDataHoraMarcacao();
                 } else if (marcacao.getTipo().equals("MARCACAO_FIM")) {
                     if (start != null) {
                         Duration duration = Duration.between(start, marcacao.getDataHoraMarcacao());
                         totalDuration = totalDuration.plus(duration);

                         if (isNightTime(start, marcacao.getDataHoraMarcacao())) {
                             nightDuration = nightDuration.plus(duration);
                         }

                         // Durations de exemplo; ajuste conforme necessário
                         if (tipoMarcacao.equals("Refeição")) {
                             recommendedDuration = recommendedDuration.plusMinutes(30);
                         } else if (tipoMarcacao.equals("Descanso")) {
                             recommendedDuration = recommendedDuration.plusMinutes(60);
                         } else if (tipoMarcacao.equals("Espera")) {
                             recommendedDuration = recommendedDuration.plusMinutes(60);
                         } else {
                             recommendedDuration = recommendedDuration.plusHours(12); // ou 720 minutos
                         }
                     }
                     start = null;
                 }
             }

             periodos.add(new Periodo(
            		 0,
                     tipoMarcacao,
                     formatDuration(recommendedDuration),
                     formatDuration(totalDuration),
                     formatDuration(nightDuration)
             ));
         }

//         for (Periodo periodo : periodos) {
//             System.out.printf("\"%d\",\"%s\",\"%s\",\"%s\",\"%s\"\n",
//                     periodo.getCodigo(),
//                     periodo.getDescricao(),
//                     formatDuration(periodo.getTempo()),
//                     formatDuration(periodo.getTotalPeriodo()),
//                     formatDuration(periodo.getHorasNoturnas())
//             );
//         }
    	 
    	 return periodos;
    }
    public static boolean isNightTime(ZonedDateTime start, ZonedDateTime end) {
        int startHour = start.getHour();
        int endHour = end.getHour();
        return (startHour >= 22 || startHour < 5) || (endHour >= 22 || endHour < 5);
    }
    
    public static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        long seconds = duration.minusHours(hours).minusMinutes(minutes).getSeconds();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

   
}
