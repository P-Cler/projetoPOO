package org.serratec.entidade;

import java.time.LocalDate;

import org.serratec.enums.FaixasInss;
import org.serratec.enums.FaixasIr;

public class FolhaPagamento implements Constantes {
	private Integer codigo;
	private Funcionario funcionario;
	private LocalDate dataPagamento;
	private Double descontoINSS;
	private Double descontoIR;
	private Double salarioLiquido;

	public FolhaPagamento() {
	}

	public FolhaPagamento(Integer codigo, Integer id_funcionario, LocalDate dataPagamento, Double descontoINSS,
			Double descontoIR, Double salarioLiquido) {
		super();
		this.codigo = codigo;
		this.funcionario.setId_funcionario(id_funcionario);
		this.dataPagamento = dataPagamento;
		this.descontoINSS = descontoINSS;
		this.descontoIR = descontoIR;
		this.salarioLiquido = salarioLiquido;
	}

	public FolhaPagamento(Integer id_funcionario, LocalDate dataPagamento, Double descontoINSS, Double descontoIR,
			Double salarioLiquido) {
		super();
		this.funcionario.setId_funcionario(id_funcionario);
		this.dataPagamento = dataPagamento;
		this.descontoINSS = descontoINSS;
		this.descontoIR = descontoIR;
		this.salarioLiquido = salarioLiquido;
	}

	public Double descontoInss(Funcionario funcionario) {
		for (FaixasInss faixa : FaixasInss.values()) {
			if (funcionario.getSalario_bruto() >= faixa.getSALARIOMINIMO()
					&& funcionario.getSalario_bruto() <= faixa.getSALARIOMAXIMO()) {
				this.descontoINSS = (funcionario.getSalario_bruto() * faixa.getALIQUOTA()) - faixa.getDEDUCAO();
			} else if (funcionario.getSalario_bruto() > FaixasInss.FAIXA4.getSALARIOMAXIMO()) {
				this.descontoINSS = LIMITECONTRIBUICAO;
			}
		}
		return descontoINSS;
	}

	public Double descontoIR(Funcionario funcionario) {
		for (FaixasIr faixa : FaixasIr.values()) {
			if (funcionario.getSalario_bruto() >= faixa.getSALARIOMINIMO()
					&& funcionario.getSalario_bruto() <= faixa.getSALARIOMAXIMO()) {
				this.descontoIR = ((funcionario.getSalario_bruto()
						- (funcionario.getDependentes().size() * ABATIMENTOIR) - descontoINSS) * faixa.getALIQUOTA())
						- faixa.getDEDUCAO();
			}
		}
		return descontoIR;
	}

	public Double salarioLiq(Funcionario funcionario) {
		this.salarioLiquido = funcionario.getSalario_bruto() - descontoINSS - descontoIR;
		return salarioLiquido;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getIdFuncionario() {
		return funcionario.getId_funcionario();
	}

	public void setiDFuncionario(Integer idFuncionario) {
		this.funcionario.setId_funcionario(idFuncionario);
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
