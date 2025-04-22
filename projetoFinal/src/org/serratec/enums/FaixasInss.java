package org.serratec.enums;

public enum FaixasInss {
    FAIXA1(0.00, 1518.00, 0.075, 0.00),
    FAIXA2(1518.01, 2793.88, 0.09, 22.77),
    FAIXA3(2793.89, 4190.83, 0.12, 106.60),
    FAIXA4(4190.84, 8157.41, 0.14, 190.42);

    private final Double SALARIOMINIMO;
    private final Double SALARIOMAXIMO;
    private final Double ALIQUOTA;
    private final Double DEDUCAO;
    

    
    FaixasInss(Double SALARIOMINIMO, Double SALARIOMAXIMO, Double ALIQUOTA, Double DEDUCAO) {
        this.SALARIOMINIMO = SALARIOMINIMO;
        this.SALARIOMAXIMO = SALARIOMAXIMO;
        this.ALIQUOTA = ALIQUOTA;
        this.DEDUCAO = DEDUCAO;
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
