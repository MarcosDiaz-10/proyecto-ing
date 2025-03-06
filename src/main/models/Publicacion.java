package models;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import javax.swing.event.ChangeEvent; // Importa la clase ChangeEvent
import java.awt.event.ActionListener; // Importa la clase ActionListener
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.PushbackReader;
import java.io.BufferedReader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent
import java.awt.event.MouseEvent; // Importa la clase MouseEvent
import java.io.FileReader;
import java.io.IOException;
import java.awt.Image; // Importa la clase Image
import javax.swing.ImageIcon;

public class Publicacion {

    private String classification;
    private String title;
    private boolean hasImage;
    private String text;
    private String owner;
    private int day, month, year;
    private JLabel imagen;

    public Publicacion(BufferedReader reader) {

        title = new String();
        text = new String();
        classification = new String();

        try {
            classification = reader.readLine();
            title = reader.readLine();

            String[] dateAux = reader.readLine().split("-");

            day = Integer.parseInt(dateAux[0]);
            month = Integer.parseInt(dateAux[1]);
            year = Integer.parseInt(dateAux[2]);

            hasImage = "1".equals(reader.readLine());

            if (hasImage) {
                ImageIcon postImage = new ImageIcon(reader.readLine());

                Image postImageRedimension = postImage.getImage();
                postImageRedimension = postImageRedimension.getScaledInstance(325, 325,
                        postImageRedimension.SCALE_SMOOTH);
                postImage = new ImageIcon(postImageRedimension);

                imagen = new JLabel(postImage);
            } else {
                reader.readLine();// Saltamos la linea
            }
            text = new String();
            String line = new String();
            line = reader.readLine();
            text = line;
            owner = reader.readLine();
            reader.readLine();// Para eliminar el último "-"

        } catch (IOException e) {
            System.out.println("Error al leer el archivo desde Publicacion");
        }
    }

    public String getClassification() {
        return classification;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public boolean hasImage() {
        return hasImage;
    }

    public JLabel getImage() {
        return imagen;
    }

    public String getOwner() {
        return owner;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getDate() {
        return day + "-" + month + "-" + year;
    }

    public static void main(String args[]) {
        System.out.println("Publicacion inicializada");
    }

}