package com.herbert.avaliacao.model;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venda implements Entidade {
	private Long id;	
	private Set<Item> itens = new HashSet<>();
	private String vendedor;
	
	public Double totalVenda() {
		return itens.stream().mapToDouble(i -> i.totalItem()).sum();
	}
}
