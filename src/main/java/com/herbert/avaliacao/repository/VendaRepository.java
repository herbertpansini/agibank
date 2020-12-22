package com.herbert.avaliacao.repository;

import java.util.HashSet;
import java.util.Set;

import com.herbert.avaliacao.model.Entidade;
import com.herbert.avaliacao.model.Item;
import com.herbert.avaliacao.model.Venda;

public class VendaRepository implements Repositorio {

	@Override
	public Entidade add(String line) {
		String[] columns = line.split(DELIMITER);
        
		return new Venda(Long.valueOf(columns[1]), // Id
						 addItems(columns[2]), // Itens
                		 columns[3]); // Vendedor
	}
	
	private Set<Item> addItems(String content) {
		Set<Item> itemsVenda = new HashSet<>();
		String[] items = content.replace("[", "").replace("]", "").split(",");
		for(String item : items) {
			itemsVenda.add((Item) new ItemRepository().add(item));
        }
        return itemsVenda;
	}
}
