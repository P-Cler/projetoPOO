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


	public Double getDescontoIr() {
		return descontoIr;
	}


	public void setDescontoIr(Double descontoIr) {
		this.descontoIr = descontoIr;
	}


	@Override
	public String toString() {
		return "Funcionario [salarioBruto=" + salarioBruto + ", descontoInss=" + descontoInss + ", descontoIr="
				+ descontoIr + "]";
	}
	
	
	
}
