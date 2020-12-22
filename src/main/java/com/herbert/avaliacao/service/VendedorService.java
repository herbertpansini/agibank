package com.herbert.avaliacao.service;

import java.util.List;

import com.herbert.avaliacao.model.Vendedor;

public class VendedorService {

	public static int count(List<Vendedor> vendedores) {
		return vendedores.size();
	}
}
