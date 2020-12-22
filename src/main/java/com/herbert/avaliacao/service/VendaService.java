package com.herbert.avaliacao.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.herbert.avaliacao.model.Venda;

public class VendaService {
	
	public static Long getIdVendaMaisCara(List<Venda> vendas) {
		Long idVenda = 0L;
		Double maiorValor = 0.0;
		for (Venda venda : vendas) {
			if (venda.totalVenda() > maiorValor) {
				maiorValor = venda.totalVenda();
				idVenda = venda.getId();
			}
		}
		return idVenda;
	}
	
	public static String getPiorVendedor(List<Venda> vendas) {
		Map<String, Double> collect = vendas.stream().collect(
				Collectors.groupingBy(Venda::getVendedor, 
				Collectors.summingDouble(Venda::totalVenda))
		);
		
		Double menorValor = 0.0;
		String piorVendedor = "";
		for (Map.Entry<String, Double> entry : collect.entrySet()) {
			if (entry.getValue() < menorValor || menorValor == 0.0) {
				menorValor = entry.getValue();
				piorVendedor = entry.getKey();					
			}
		}
		return piorVendedor;
	}

}
