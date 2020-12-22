package com.herbert.avaliacao.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.herbert.avaliacao.model.Cliente;
import com.herbert.avaliacao.model.Entidade;
import com.herbert.avaliacao.model.Venda;
import com.herbert.avaliacao.model.Vendedor;
import com.herbert.avaliacao.repository.ClienteRepository;
import com.herbert.avaliacao.repository.Repositorio;
import com.herbert.avaliacao.repository.VendaRepository;
import com.herbert.avaliacao.repository.VendedorRepository;

public class ArquivoService {

	private static final String FORMATO_RELATORIO = "%dç%dç%dç%s";
	private List<Entidade> content;

	public ArquivoService() {
		content = new ArrayList<>();
	}

	public void readLines(List<String> lines){
		lines.stream().forEach(line -> readLine(line));
	}
	public void readLine(String line) {
		Repositorio repositorio = getRepository(line);
		content.add(repositorio.add(line));
	}

	public void clear(){
		content.clear();
	}

	public List<Cliente> getClientes() {
		return content.stream()
				.filter(reg -> reg instanceof Cliente)
				.map(reg -> (Cliente) reg)
				.collect(Collectors.toList());
	}

	public List<Vendedor> getVendedores() {
		return content.stream()
				.filter(reg -> reg instanceof Vendedor)
				.map(reg -> (Vendedor) reg)
				.collect(Collectors.toList());
	}

	public List<Venda> getVendas() {
		return content.stream()
				.filter(reg -> reg instanceof Venda)
				.map(reg -> (Venda) reg)
				.collect(Collectors.toList());
	}

	public Long getIdVendaMaisCara() {
		Long idVenda = 0L;
		Double maiorValor = 0.0;
		for (Venda venda : getVendas()) {
			if (venda.totalVenda() > maiorValor) {
				maiorValor = venda.totalVenda();
				idVenda = venda.getId();
			}
		}
		return idVenda;
	}

	public String getPiorVendedor() {
		Map<String, Double> collect = getVendas().stream().collect(
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

	public String getResultadoRelatorio(){
		return String.format(FORMATO_RELATORIO,
				getClientes().size(),
				getVendedores().size(),
				getIdVendaMaisCara(),
				getPiorVendedor());
	}

	private Repositorio getRepository(String line) {
		Repositorio repositorio;
		switch (line.substring(0, 3)) {
			case "001":
				repositorio = new VendedorRepository();
				break;
			case "002":
				repositorio = new ClienteRepository();
				break;
			case "003":
				repositorio = new VendaRepository();
				break;
			default:
				throw new InvalidParameterException("Tipo de dado incorreto!");
		}
		return repositorio;
	}
}