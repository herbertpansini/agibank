package com.herbert.avaliacao.repository;

import com.herbert.avaliacao.model.Entidade;
import com.herbert.avaliacao.model.Vendedor;

public class VendedorRepository implements Repositorio {

	@Override
	public Entidade add(String line) {
	    String[] columns = line.split(DELIMITER);
	    
        return new Vendedor(columns[1], // CPF
                			columns[2], // Nome
                			Double.valueOf(columns[3])); // Salário
	}

}
