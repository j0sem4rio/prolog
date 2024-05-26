package com.prolog.desafio.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prolog.desafio.model.Colaborador;
import com.prolog.desafio.model.Marcacao;



public interface ColaboradorRepository extends JpaRepository<Marcacao, Long> {
    
	@Query("SELECT m " +
	           "FROM Marcacao  m " +
	           "WHERE m.colaborador.cpf = (SELECT c.cpf FROM Colaborador c WHERE c.nome = :nomeColaborador)" +
	           "AND m.dataHoraMarcacao BETWEEN :startDate AND :endDate")
	    List<Marcacao> calcularHorasTrabalhadas(
	            @Param("nomeColaborador") String nomeColaborador,
	            @Param("startDate") ZonedDateTime startDate,
	            @Param("endDate") ZonedDateTime endDate);
}
