/**
 * Programa: Programa 6 - PSP 2.1
 *
 * @author Juan Sebastian Paz Prieto
 * @date 17/04/2017 Clase: App Descripcion: Clase que inicia el programa
 *
 */
package edu.uniandes.ecos.CAIS.P6PSP21.view;

import edu.uniandes.ecos.CAIS.P6PSP21.controller.Program;
import edu.uniandes.ecos.CAIS.P6PSP21.model.Function;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import static spark.Spark.*;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

public class App {

    public static void main(String[] args) {
        try {
            Program program6 = new Program();
            String path = System.getProperty("user.dir");
            path += "/src/resources/files";
            program6.readFiles(path);
            Iterator it = program6.getFunctions().iterator();
            /*
            while (it.hasNext()) {
                Function fx = (Function) it.next();
                System.out.println("X: " + fx.getX());
                System.out.println("DOF: " + fx.getDof());
                System.out.println("Valor: " + fx.getP());
            }*/
            staticFileLocation("/public");

            port(Integer.valueOf(System.getenv("PORT")));
            get("/", (req, res) -> {
                String response = "";
                while (it.hasNext()) {
                    Function fx = (Function) it.next();
                    response += "<br>Funcion: " + fx.getX();
                    response += "<br>DOF: " + fx.getDof();
                    response += "<br>Valor: " + fx.getP();

                    System.out.println("Funcion: " + fx.getX());
                    System.out.println("DOF: " + fx.getDof());
                    System.out.println("Valor: " + fx.getP());
                }
                return response;
            });
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
