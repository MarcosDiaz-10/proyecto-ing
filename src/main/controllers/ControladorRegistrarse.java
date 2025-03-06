package controllers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;

import org.w3c.dom.events.MouseEvent;

import views.Registrarse;

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

public class ControladorRegistrarse {

    private String firstNameString;
    private String lastNameString;
    private String rolString;
    private String passwordString;
    private String repeatPasswordString;
    private String schoolString;

    public ControladorRegistrarse() {

    }

    public int saveInformation(Registrarse registerView) {

        firstNameString = registerView.getFirstName();
        lastNameString = registerView.getLastName();
        rolString = registerView.getSelectedRole();
        passwordString = registerView.getPassword();
        repeatPasswordString = registerView.getRepeatPassword();
        schoolString = registerView.getSchool();

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

        String informacion = firstNameString + "-" + lastNameString + "-" + passwordString + "-" + schoolString + "-"
                + rolString + '\n';
        /* try (FileWriter writer = new FileWriter("/src/DB/database.txt", true)) { */
        try (FileWriter writer = new FileWriter("src/data/Database.txt", true)) {
            writer.write(informacion);
            return 0;
        } catch (IOException ex) {
            return -1;
        }

    }

    public String getRol() {
        return rolString;
    }

    public String getFirstName() {
        return firstNameString;
    }

    public String getLastName() {
        return lastNameString;
    }

    public String getPassword() {
        return passwordString;
    }

    public String getRepeatPassword() {
        return repeatPasswordString;
    }

    public String getSchool() {
        return schoolString;
    }

    public static void main(String args[]) {
        System.out.println("ControladorInicarSesion inicializado");
    }

}