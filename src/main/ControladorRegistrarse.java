import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;

import org.w3c.dom.events.MouseEvent;

import javax.swing.event.ChangeEvent; // Importa la clase ChangeEvent
import java.awt.event.ActionListener; // Importa la clase ActionListener
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent

import javax.swing.*;
import java.awt.*;


public class ControladorRegistrarse{

    public ControladorRegistrarse(){

    }

    public int saveInformation(Registrarse registerView){

        String firstNameString = registerView.getFirstName();
        String lastNameString = registerView.getLastName();
        //Esto quizás se pueda hacer algo como JButtonGroup.getSElection() para obtener el botón directamente y ahorrarnos el método
        String rolString = registerView.getSelectedRole();
        String passwordString = registerView.getPassword();
        String repeatPasswordString = registerView.getRepeatPassword();

        if (firstNameString.length() < 1) {
            return 1;
        }

        if (lastNameString.length() < 1) {
            return 2;
        }

        if (rolString.equals("No especificado")) {
            return 3;
        }

        if (passwordString.length() < 1) {
            return 4;
        }
        if (!passwordString.equals(repeatPasswordString)) {
            return 5;
        }

        String informacion = firstNameString + "-" + lastNameString + "-" + passwordString + "-" + registerView.getSchool() + "-" + rolString + '\n';
        /*try (FileWriter writer = new FileWriter("/src/DB/database.txt", true)) {*/
        try (FileWriter writer = new FileWriter("database.txt", true)) {
            writer.write(informacion);
            return 0;
        } catch (IOException ex) {
            return -1;
        }

    }
   
    public static void main(String args[]){
        System.out.println("ControladorInicarSesion inicializado");
    }

}