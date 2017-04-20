/**
 * Programa: Programa 6 - PSP 2.1
 *
 * @author Juan Sebastian Paz Prieto
 * @date 17/04/2017 Clase: Program Descripcion: Clase que lee los archivos de
 * datos de un path, para calcular los valores de la integral de la función
 * usando la regla de Simpson
 *
 */
package edu.uniandes.ecos.CAIS.P6PSP21.controller;

import edu.uniandes.ecos.CAIS.P6PSP21.model.Function;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Program {

    private LinkedList<Function> functions;

    /**
     * Metodo que permite acceder al valor de Functions
     *
     * @return un valor <code>LinkedList</code> de las funciones.
     */
    public LinkedList<Function> getFunctions() {
        return functions;
    }

    /**
     * Metodo que lee los archivos localizados en path para instanciar objetos
     * de tipo Data
     *
     * @param path
     * @throws IOException
     */
    public void readFiles(String path) throws IOException {
        this.functions = new LinkedList<>();

        File[] files = new File(path).listFiles();

        for (File file : files) {
            if (this.checkFileExist(file)) {
                BufferedReader content = this.getFileContent(file.getPath());
                String sCurrentLine;
                while ((sCurrentLine = content.readLine()) != null) {
                    sCurrentLine = sCurrentLine.trim();
                    String data[] = sCurrentLine.split(";");
                    String p = data[0];
                    String dof = data[1];
                    if (checkContentIsValid(p) && checkContentIsValid(dof)) {
                        Function fx = new Function(Double.parseDouble(p), Double.parseDouble(dof));
                        fx.calculateX();
                        this.functions.add(fx);
                    } else {
                        throw new UnknownError("El contenido es invalido");
                    }
                }

            } else {
                throw new UnknownError("El archivo no existe");
            }

        }
    }

    /**
     * Metodo que permite obtener el contenido de un archivo
     *
     * @param path
     */
    private BufferedReader getFileContent(String path) {
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return br;
    }

    /**
     * Metodo que permite comprobar si el archivo existe
     *
     * @param file
     * @return un valor <code>Boolean</code> que representa si el archivo existe
     */
    public Boolean checkFileExist(File file) {
        return file.isFile();
    }

    /**
     * Metodo que permite comprobar si el contenido del archivo es valido
     *
     * @param line
     * @return un valor <code>Boolean</code> que representa si el contenido de 
     * un archivo es válido
     */
    public Boolean checkContentIsValid(String line) {
        try {
            double d = Double.parseDouble(line);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
