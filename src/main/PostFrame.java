
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import javax.swing.event.ChangeEvent; // Importa la clase ChangeEvent
import java.awt.event.ActionListener; // Importa la clase ActionListener
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent
import java.awt.event.MouseEvent; // Importa la clase MouseEvent
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class PostFrame extends JPanel{

    Publicacion publicacion;
    JLabel classification;
    JLabel title;

    public PostFrame(BufferedReader reader) {

        publicacion = new Publicacion(reader);

        setLayout(null);

        classification = new JLabel(publicacion.getClassification());
        classification.setBounds(10,10,100,100);
        add(classification);

        setPreferredSize(new Dimension(370,400));
        setOpaque(true);
        setBackground(new Color(0,255,0));

       
    }

    public static void main(String args[]) {

        System.out.println("PostFrame inicializado");

    }

}