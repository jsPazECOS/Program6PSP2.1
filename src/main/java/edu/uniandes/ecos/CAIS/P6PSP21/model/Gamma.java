/**
  * Programa: Programa 6 - PSP 2.1
 *
 * @author Juan Sebastian Paz Prieto
 * @date 17/04/2017 Clase: Gamma Descripcion: Clase que calcula el valor de la
 * funcion Gamma de un valor X
 *
 */
package edu.uniandes.ecos.CAIS.P6PSP21.model;

public class Gamma {
    /**
     * Método que permite calcular el logaritmo de la función Gamma
     *
     * @return un valor <code>double</code> que representa el logaritmo de la 
     * funcion Gamma
     */
    private double logGamma(double x) {
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 + 76.18009173 / (x + 0) - 86.50532033 / (x + 1)
                + 24.01409822 / (x + 2) - 1.231739516 / (x + 3)
                + 0.00120858003 / (x + 4) - 0.00000536382 / (x + 5);
        return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
    }

    /**
     * Método que permite calcular el valor de la función Gamma
     *
     * @param x
     * @return un valor <code>double</code> que representa el valor de la 
     * funcion Gamma
     */
    public double gamma(double x) {
        return Math.exp(logGamma(x));
    }

}
