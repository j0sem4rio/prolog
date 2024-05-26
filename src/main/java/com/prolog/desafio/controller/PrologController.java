package com.prolog.desafio.controller;


import com.prolog.desafio.model.Marcacao;
import com.prolog.desafio.model.Periodo;
import com.prolog.desafio.service.PrologService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PrologController {

    @Autowired
    private PrologService prologService;


    

    @GetMapping("/relatorio/{nome}")
    public ResponseEntity<List<Periodo>> getRelatorio(
            @PathVariable String nome,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
    	 List<Periodo> horasTrabalhadas = prologService.calcularHorasTrabalhadas(nome, startDate, endDate);
    	 return new ResponseEntity<>(horasTrabalhadas, HttpStatus.OK);
    }

    
}
