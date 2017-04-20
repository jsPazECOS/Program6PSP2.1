/**
  * Programa: Programa 6 - PSP 2.1
 *
 * @author Juan Sebastian Paz Prieto
 * @date 17/04/2017 Clase: SimpsonRule Descripcion: Clase que calcula el valor
 * de la de la integral de una funcion de distribucion T de un Valor X
 *
 */
package edu.uniandes.ecos.CAIS.P6PSP21.model;

public class SimpsonRule {

    private int num_seg;
    private double W;
    private double E;
    private double X;
    private double dof;

    /**
     * Metodo mediante el cual se inicializan las variables num_seg, E, X y Dof.
     *
     * @param num_seg
     * @param E
     * @param X
     * @param dof
     */
    public SimpsonRule(int num_seg, double E, double X, double dof) {
        this.num_seg = num_seg;
        this.E = E;
        this.X = X;
        this.dof = dof;
    }

    /**
     * Método que inicia el proceso de calculo de la función usando la regla de
     * Simpson
     * @return un valor <code>double</code> que representa el valor de la 
     * integral
     */
    public double calculate() {
        double secondValue = 0.0;
        if (checkIfNumSegIsEven(this.num_seg)) {
            double difference = Double.MAX_VALUE;
            while (difference >= this.E) {
                double firstValue = calculateSimpsonRule(this.num_seg);
                secondValue = calculateSimpsonRule(this.num_seg * 2);
                difference = secondValue - firstValue;
                this.num_seg = this.num_seg * 2;
            }
        } else {
            throw new UnknownError("El número de segmentos no es par");
        }

        return secondValue;
    }

    /**
     * Método que permite calcular recursivamente el valor al aplicar la regla
     * de Simpson.
     *
     * @param num_seg
     * @return un valor <code>Double</code> del valor al aplicar la regla de
     * Simpson.
     */
    private double calculateSimpsonRule(int num_seg) {
        this.W = this.X / num_seg;
        TDistribution distribution = new TDistribution();
        double firstPart = distribution.calculateDistribution(this.dof, 0);
        double secondPart = this.calculateSumatory(1, num_seg - 1, this.W, 4);
        double thirdPart = this.calculateSumatory(2, num_seg - 2, this.W, 2);
        double fourthPart = distribution.calculateDistribution(dof, this.X);

        double result = this.W / 3;
        result = result * (firstPart + secondPart + thirdPart + fourthPart);
        return result;

    }

    /**
     * Método que permite calculary la sumatoria de una serie de datos
     * dependientes del valor de W,
     *
     * @param interval
     * @param limit
     * @param W
     * @param multiplier
     * @return un valor <code>Double</code> del valor de la sumatoria de una
     * serie de datos dependientes del valor de W.
     */
    private double calculateSumatory(int interval, int limit, double W, int multiplier) {
        double sumatory = 0.0;

        for (int i = interval; i < limit; i = i + 2) {
            TDistribution distribution = new TDistribution();
            sumatory += multiplier * distribution.calculateDistribution(this.dof, W * i);
        }
        return sumatory;
    }

    /**
     * Metodo que permite comprobar si el numero de segmento es par.
     *
     * @param num_seg
     * @return un valor <code>Boolean</code> que representa si el valor 
     * de num_seg es par
     */
    public Boolean checkIfNumSegIsEven(int num_seg) {
        return (num_seg % 2 == 0) ? true : false;
    }
}
