
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import javax.swing.event.ChangeEvent; // Importa la clase ChangeEvent
import java.awt.event.ActionListener; // Importa la clase ActionListener
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent
import java.awt.event.MouseEvent; // Importa la clase MouseEvent
import java.io.FileReader;
import java.io.IOException;

public class Publicacion{

    private String classification;
    private String title;
    private boolean hasImage;
    private String text;
    private JLabel  imagen;

    public Publicacion(BufferedReader reader){

        title = new String();
        text =  new String();
        classification = new String();

        try {
            classification = reader.readLine();
            System.out.println(classification);
            title = reader.readLine();
    
            hasImage = "1".equals(reader.readLine());

            if(hasImage){
                ImageIcon postImage = new ImageIcon(reader.readLine());
                imagen = new JLabel(postImage);
            }

            text = new String();
            String line = new String();
            line = reader.readLine();
            while((line != "-") && (line != null)){
                text += line;
                line = reader.readLine();
            }


        } catch (IOException e) {
            System.out.println("Error al leer el archivo desde Publicacion");
        }
    }

    public String getClassification(){
        return classification;
    }

    public String getTitle(){
        return title;
    }

    public String getText(){
        return text;
    }

    public boolean hasImage(){
        return hasImage;
    }

    public JLabel getImage(){
        return imagen;
    }

    public static void main(String args[]){
        System.out.println("Publicacion inicializada");
    } 

    

}