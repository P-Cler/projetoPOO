package org.serratec.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class Funcionario extends Pessoa {
	private Integer id_funcionario;
	private Double salario_bruto;

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, Double salario_bruto) {
		super(nome, cpf, dataNascimento);
		this.salario_bruto = salario_bruto;
	}

	public Funcionario(Integer id_funcionario, String nome, String cpf, LocalDate dataNascimento,
			Double salario_bruto) {
		super(nome, cpf, dataNascimento);
		this.id_funcionario = id_funcionario;
		this.salario_bruto = salario_bruto;
	}

	@Override
	public String toString() {
		return " ID:" + id_funcionario + " " + super.toString() + ", Salário bruto:" + salario_bruto;
	}

	public Integer getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(Integer id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public Double getSalario_bruto() {
		return salario_bruto;
	}

	public void setSalario_bruto(Double salario_bruto) {
		this.salario_bruto = salario_bruto;
	}

}
