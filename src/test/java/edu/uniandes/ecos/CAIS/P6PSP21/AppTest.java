package edu.uniandes.ecos.CAIS.P6PSP21;

import edu.uniandes.ecos.CAIS.P6PSP21.controller.Program;
import edu.uniandes.ecos.CAIS.P6PSP21.model.Function;
import edu.uniandes.ecos.CAIS.P6PSP21.model.Gamma;
import edu.uniandes.ecos.CAIS.P6PSP21.model.TDistribution;
import java.io.File;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testContentIsValid() {

        Program program6 = new Program();

        assertTrue(program6.checkContentIsValid("4"));
        assertTrue(program6.checkContentIsValid("0.4"));
        assertTrue(program6.checkContentIsValid("5.2"));
        assertFalse(program6.checkContentIsValid("a"));

    }

    public void testFileExist() {
        Program program6 = new Program();
        String path = System.getProperty("user.dir");
        path += "/src/resources/files";
        File file = new File(path + "/TestCases");
        assertTrue(program6.checkFileExist(file));
        File file2 = new File(path + "/TestCases2");
        assertFalse(program6.checkFileExist(file2));
    }

    public void testCalculateXCaseOne() {
        Function function = new Function(0.20, 6);
        function.calculateX();

        assertEquals("X debe ser 0.55338", 0.55338, function.getX(), 0.001);
    }
    
    public void testCalculateXCaseTwo() {
        Function function = new Function(0.45, 15);
        function.calculateX();

        assertEquals("X debe ser 1.75305", 1.75305, function.getX(), 0.001);
    }
    
    public void testCalculateXCaseThree() {
        Function function = new Function(0.495, 4);
        function.calculateX();

        assertEquals("X debe ser 4.60409", 4.60409, function.getX(), 0.01);
    }

    public void testCalculateGamma() {
        Gamma gamma = new Gamma();
        assertEquals("El valor de la funcion Gamma de 5 debe ser 24 ", 24, gamma.gamma(5), 0.00001);
        assertEquals("El valor de la funcion Gamma de 9/2 debe ser 11.63173 ", 11.63173, gamma.gamma(4.5), 0.00001);
    }

    public void testCalculateDistribution() {
        TDistribution distribution = new TDistribution();
        assertEquals("El valor de la funcion de Distribucion T debe ser 0.388035", 0.388035, distribution.calculateDistribution(9, 0), 0.00001);
    }
}
