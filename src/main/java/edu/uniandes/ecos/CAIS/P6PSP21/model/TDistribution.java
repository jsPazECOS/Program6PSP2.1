/**
 * Programa: Programa 6 - PSP 2.1
 *
 * @author Juan Sebastian Paz Prieto
 * @date 17/04/2017 Clase: TDistribution Descripcion: Clase que calcula el valor
 * de la función de Distribucion T
 *
 */
package edu.uniandes.ecos.CAIS.P6PSP21.model;

public class TDistribution {

    /**
     * Método que permite calcular el valor de la función T de Distribucion
     *
     * @param dof
     * @param x
     * @return un valor <code>double</code> que representa el valor t de la
     * funcion de distribucion
     */
    public double calculateDistribution(double dof, double x) {
        double result = this.calculateFirstPart(dof) / this.calculateSecondPart(dof);
        result = result * this.calculateThirdPart(dof, x);
        return result;
    }

    /**
     * Método que permite calcular la primera parte de la funcion de
     * distribucion T
     *
     * @param dof
     * @return un valor <code>double</code> que representa el resultado de la
     * primera parte de la funcion de distribucion T
     */
    private double calculateFirstPart(double dof) {
        double value = dof + 1;
        value = value / 2;
        Gamma gamma = new Gamma();
        value = gamma.gamma(value);

        return value;
    }

    /**
     * Método que permite calcular la segunda parte de la funcion de
     * distribucion T
     *
     * @param dof
     * @return un valor <code>double</code> que representa el resultado de la
     * segunda parte de la funcion de distribucion T
     */
    private double calculateSecondPart(double dof) {
        double firstPart = dof * Math.PI;
        firstPart = Math.pow(firstPart, 0.5);
        Gamma gamma = new Gamma();
        double secondPart = gamma.gamma(dof / 2);

        return firstPart * secondPart;

    }

    /**
     * Método que permite calcular la tercera parte de la funcion de
     * distribucion T
     *
     * @param dof
     * @return un valor <code>double</code> que representa el resultado de la
     * tercera parte de la funcion de distribucion T
     */
    private double calculateThirdPart(double dof, double x) {
        double value = Math.pow(x, 2);
        value = value / dof;
        value = 1 + value;
        double exponent = dof + 1;
        exponent = -1 * exponent / 2;
        return Math.pow(value, exponent);
    }
}
