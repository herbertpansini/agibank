package com.herbert.avaliacao.service;

import java.util.List;

import com.herbert.avaliacao.model.Cliente;

public class ClienteService {

	public static int count(List<Cliente> clientes) {
		return clientes.size();
	}
}
