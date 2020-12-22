package com.herbert.avaliacao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Entidade {
	private Long id;
	private Integer quantidade;
	private Double precoUnitario;
	
	public Double totalItem() {
		return this.quantidade * precoUnitario;
	}
}
