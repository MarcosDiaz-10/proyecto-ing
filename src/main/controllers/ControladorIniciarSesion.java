package controllers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;

import org.w3c.dom.events.MouseEvent;

import views.IniciarSesion;

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

public class ControladorIniciarSesion {

    private String firstName;
    private String lastName;
    private String password;

    public ControladorIniciarSesion() {

    }

    public int validateInformation(IniciarSesion vistaIniciarSesion) {

        Boolean exist = false;

        firstName = vistaIniciarSesion.getFirstName();
        lastName = vistaIniciarSesion.getLastName();
        password = vistaIniciarSesion.getPassword();

        if (firstName.length() < 1) {
            return 1;
        }

        if (lastName.length() < 1) {
            return 2;
        }

        if (password.length() < 1) {
            return 3;
        }
        // String ruta = "src/DB/database.txt";
        String ruta = "src/data/Database.txt";

        try (Scanner scanner = new Scanner(new File(ruta))) {
            // Leer el archivo línea por línea

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                // Creo que, como muchas páginas, deberiamos guardarlo todo en Mayúsculas de
                // una, porque si no, MAU y mau sería dos usuarios "distintos al registrarse"
                // pero no al ingresar
                // Esto sólo comprueba si la linea contiene el nombre?
                String[] datos = linea.split("-");

                if (datos[0].toLowerCase().contains(firstName.toLowerCase())
                        && datos[1].toLowerCase().contains(lastName.toLowerCase())
                        && datos[2].toLowerCase().contains(password.toLowerCase())) {
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
        } else {
            return 0;
        }

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String data) {
        firstName = data;
    }

    public void setLastName(String data) {
        lastName = data;
    }

    public void setPassword(String data) {
        password = data;
    }

    public static void main(String args[]) {
        System.out.println("ControladorInicarSesion inicializado");
    }

}