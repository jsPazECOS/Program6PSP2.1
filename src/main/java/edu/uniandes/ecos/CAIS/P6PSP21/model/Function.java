/**
 * Programa: Programa 6 - PSP 2.1
 *
 * @author Juan Sebastian Paz Prieto
 * @date 17/04/2017 Clase: Function Descripcion: Clase que representa cada
 * funcion a la cual se le va a calcular la integral
 *
 */
package edu.uniandes.ecos.CAIS.P6PSP21.model;

public class Function {

    private double X;
    private double dof;
    private double P;

    /**
     * Metodo mediante el cual se inicializan las variables P y Dof, leidas
     * desde un archivo plano.
     *
     * @param P
     * @param dof
     */
    public Function(double P, double dof) {
        this.P = P;
        this.dof = dof;
    }

    /**
     * Metodo que permite acceder al valor de X
     *
     * @return un valor <code>Double</code> del valor de X.
     */
    public double getX() {
        return X;
    }

    /**
     * Metodo que permite acceder al valor de dof
     *
     * @return un valor <code>Double</code> del valor de X.
     */
    public double getDof() {
        return dof;
    }

    /**
     * Metodo que permite acceder al valor de P
     *
     * @return un valor <code>Double</code> del valor de P.
     */
    public double getP() {
        return P;
    }

    /**
     * Método que instancia un objeto de tipo Simpson Rule, a partir de un valor
     * de X que se calcula hasta que el resultado de la integral de la función
     * usando la regla de Simpson sea igual a P.
     */
    public void calculateX() {
        double X = 1.0;
        SimpsonRule rule = new SimpsonRule(10, 0.00001, X, this.dof);
        double value = rule.calculate();
        double diffP = value - this.P;
        double d = 0.5;
        double diffP2 = 0.0;
        int i = 0;
        while (Math.abs(diffP) > 0.00001) {
            if (i == 0) {
                if (diffP < 0.00001) {
                    X += d;
                } else {
                    X -= d;
                }
                i++;
            }
            if (Math.signum(diffP) != Math.signum(diffP2)) {
                d /= 2.0;
                diffP2 = diffP;
            }
            if (diffP < 0.00001) {
                X += d;
            } else {
                X -= d;
            }
            rule = new SimpsonRule(10, 0.00001, X, this.dof);
            value = rule.calculate();
            diffP = value - this.P;
        }
        this.X = X;
    }

}
