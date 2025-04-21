package org.serratec.entidade;

import java.time.LocalDate;

public final class Dependente extends Pessoa {
	private Integer id_dependente;
	private Parentesco parentesco;
	private Integer id_funcionario;

	public Integer getId_dependente() {
		return id_dependente;
	}
	
	public Dependente(int id_dependente, String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco,
			int id_funcionario) {
		super(nome, cpf, dataNascimento);
		this.id_dependente = id_dependente;
		this.parentesco = parentesco;
		this.id_funcionario = id_funcionario;
	}
	

	public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco,
			Integer id_funcionario) {
		super(nome, cpf, dataNascimento);
		this.parentesco = parentesco;
		this.id_funcionario = id_funcionario;
	}

	@Override
	public String toString() {
		return "ID:" + id_dependente +" "+ super.toString()+ ", Parentesco:" + parentesco + ", ID do titular:"
				+ id_funcionario;
	}

	public void setId_dependente(Integer id_dependente) {
		this.id_dependente = id_dependente;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	public Integer getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(Integer id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

}
