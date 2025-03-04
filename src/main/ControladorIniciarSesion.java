import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;

import org.w3c.dom.events.MouseEvent;

import javax.swing.event.ChangeEvent; // Importa la clase ChangeEvent
import java.awt.event.ActionListener; // Importa la clase ActionListener
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent

import javax.swing.*;
import java.awt.*;


public class ControladorIniciarSesion{

    public ControladorIniciarSesion(){

    }

    public int validateInformation(IniciarSesion vistaIniciarSesion) {

        Boolean exist = false;

        String Name = vistaIniciarSesion.getFirstName();
        String lastname = vistaIniciarSesion.getLastName();
        String password_ = vistaIniciarSesion.getPassword();

        if (Name.length() < 1) {
            return 1;
        }

        if (lastname.length() < 1) {
            return 2;
        }

        if (password_.length() < 1) {
            return 3;
        }
        //String ruta = "src/DB/database.txt";
        String ruta = "database.txt";

        try (Scanner scanner = new Scanner(new File(ruta))) {
            // Leer el archivo línea por línea
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                //Creo que, como muchas páginas, deberiamos guardarlo todo en Mayúsculas de una, porque si no, MAU y mau sería dos usuarios "distintos al registrarse" pero no al ingresar
                //Esto sólo comprueba si la linea contiene el nombre?
                if (linea.toLowerCase().contains(Name.toLowerCase())
                        && linea.toLowerCase().contains(lastname.toLowerCase())
                        && linea.toLowerCase().contains(password_.toLowerCase())) {
                    exist = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado: " + e.getMessage());
            return -1;
        }

        if (!exist) {
                    return 4;
        }else{
            return 0;
        }

    }
   
    public static void main(String args[]){
        System.out.println("ControladorInicarSesion inicializado");
    }

}