package utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;

import org.w3c.dom.events.MouseEvent;

import javax.swing.event.ChangeEvent; // Importa la clase ChangeEvent
import java.awt.event.ActionListener; // Importa la clase ActionListener
import java.awt.event.MouseAdapter;
import java.time.chrono.ThaiBuddhistChronology;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent

public class IngSocColor {

    public static Color red, black, white, gray, event, taller;

    public IngSocColor() {

        red = new Color(184, 12, 9);
        black = new Color(20, 19, 1);
        white = new Color(217, 217, 217);
        gray = new Color(170, 170, 170);
        event = new Color(180, 80, 20);
        taller = new Color(103, 18, 18);

    }

    public static void main(String args[]) {
        System.out.println("Colores inicializados");
    }

}