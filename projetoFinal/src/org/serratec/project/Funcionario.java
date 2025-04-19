package org.serratec.project;

public class Funcionario extends Pessoa {
	private Double salarioBruto;
	private Double descontoInss;
	private Double descontoIr;
	
	
	public Funcionario(Double salarioBruto, Double descontoInss, Double descontoIr) {
		super();
		this.salarioBruto = salarioBruto;
		this.descontoInss = descontoInss;
		this.descontoIr = descontoIr;
	}
	
	
	
}
