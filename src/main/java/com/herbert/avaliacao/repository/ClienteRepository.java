package com.herbert.avaliacao.repository;

import com.herbert.avaliacao.model.Cliente;
import com.herbert.avaliacao.model.Entidade;

public class ClienteRepository implements Repositorio {

	@Override
	public Entidade add(String line) {
		String[] columns = line.split(DELIMITER);
		
        return new Cliente(columns[1], // CNPJ
			        		columns[2], // Nome
			        		columns[3]); // Área
	}

}
