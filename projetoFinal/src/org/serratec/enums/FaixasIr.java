package org.serratec.enums;

public enum FaixasIr {
	FAIXA1(0., 2259.20, 0., 0.),
	FAIXA2(2259.21, 2826.65, 0.075, 169.44),
	FAIXA3(2826.66, 3751.05, 0.15, 381.44),
	FAIXA4(3751.06, 4664.68, 0.225, 662.77),
	FAIXA5(4664.68, Double.MAX_VALUE, 0.275, 869.);

    private final Double SALARIOMINIMO;
    private final Double SALARIOMAXIMO;
    private final Double ALIQUOTA;
    private final Double DEDUCAO;
	
    
    private FaixasIr(Double sALARIOMINIMO, Double sALARIOMAXIMO, Double aLIQUOTA, Double dEDUCAO) {
		SALARIOMINIMO = sALARIOMINIMO;
		SALARIOMAXIMO = sALARIOMAXIMO;
		ALIQUOTA = aLIQUOTA;
		DEDUCAO = dEDUCAO;
	}


	public Double getSALARIOMINIMO() {
		return SALARIOMINIMO;
	}


	public Double getSALARIOMAXIMO() {
		return SALARIOMAXIMO;
	}


	public Double getALIQUOTA() {
		return ALIQUOTA;
	}


	public Double getDEDUCAO() {
		return DEDUCAO;
	}
    
    
}
