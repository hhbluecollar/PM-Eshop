package edu.miu.eshop.product.constants;

public enum TaxRate{

    TAX_RATE(0.06);

    private double  stateTax;

     TaxRate(double  stateTax) {

        this.stateTax = stateTax;
    }

    public double getStateTax() {

        return stateTax;

    }
}
