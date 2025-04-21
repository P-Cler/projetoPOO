package org.serratec.entidade;

import java.time.LocalDate;

public class FolhaPagamento {
	private Integer codigo;
	private Integer idFuncionario;
	private LocalDate dataPagamento;
	private Double descontoINSS;
	private Double descontoIR;
	private Double salarioLiquido;

	

	public FolhaPagamento(Integer codigo, Integer id_funcionario, LocalDate dataPagamento, Double descontoINSS,
			Double descontoIR, Double salarioLiquido) {
		super();
		this.codigo = codigo;
		this.idFuncionario = id_funcionario;
		this.dataPagamento = dataPagamento;
		this.descontoINSS = descontoINSS;
		this.descontoIR = descontoIR;
		this.salarioLiquido = salarioLiquido;
	}
	
	public FolhaPagamento(Integer id_funcionario, LocalDate dataPagamento, Double descontoINSS, Double descontoIR,
			Double salarioLiquido) {
		super();
		this.idFuncionario = id_funcionario;
		this.dataPagamento = dataPagamento;
		this.descontoINSS = descontoINSS;
		this.descontoIR = descontoIR;
		this.salarioLiquido = salarioLiquido;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setiDFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Double getDescontoINSS() {
		return descontoINSS;
	}

	public void setDescontoINSS(Double descontoINSS) {
		this.descontoINSS = descontoINSS;
	}

	public Double getDescontoIR() {
		return descontoIR;
	}

	public void setDescontoIR(Double descontoIR) {
		this.descontoIR = descontoIR;
	}

	public Double getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(Double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}

}
