package com.herbert.avaliacao.repository;

import com.herbert.avaliacao.model.Entidade;

public interface Repositorio {
	
	static final String DELIMITER = "ç";
	
	abstract Entidade add(String line);
}
