package com.herbert.avaliacao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor implements Entidade {	
	private String Cpf;
	private String nome;
	private Double salario;
}
