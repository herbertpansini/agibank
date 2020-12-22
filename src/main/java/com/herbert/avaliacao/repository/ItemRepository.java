package com.herbert.avaliacao.repository;

import com.herbert.avaliacao.model.Entidade;
import com.herbert.avaliacao.model.Item;

public class ItemRepository implements Repositorio {

	@Override
	public Entidade add(String line) {
		String[] columns = line.split("-");
        
		return new Item(Long.valueOf(columns[0]), // Id
                		Integer.valueOf(columns[1]), // Quantidade
                		Double.valueOf(columns[2])); // Preço Unitário
	}

}
