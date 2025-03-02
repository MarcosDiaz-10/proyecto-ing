
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
import java.awt.Font;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent
import java.awt.event.MouseEvent; // Importa la clase MouseEvent
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.BufferedReader;
import java.awt.Dimension;

public class PostFrame extends JPanel{

    Publicacion publicacion;
    JLabel classification,title,text,imagen;
    JButton comment;

    public PostFrame(BufferedReader reader) {

        publicacion = new Publicacion(reader);

        setLayout(null);

        comment = new JButton("Comentar");
        comment.setBackground(IngSocColor.black);
        comment.setForeground(IngSocColor.white);

        classification = new JLabel(publicacion.getClassification());
        classification.setBounds(5,5,100,25);
        classification.setFont(new Font("Arial",Font.ITALIC,10));
        add(classification);

        title = new JLabel(publicacion.getTitle());
        title.setFont(new Font("Arial",Font.BOLD,20));
        title.setBounds(20,30,350,25);;
        add(title);

        text = new JLabel(publicacion.getText());
        text.setFont(new Font("Arial",Font.PLAIN,12));

        if(publicacion.hasImage()){
            imagen = new JLabel();
            imagen.setIcon(publicacion.getImage().getIcon());

            imagen.setBounds(20,60,325,325);
            //imagen.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
            add(imagen);

            text.setBounds(15,395,335,((1+(text.getText().length())/54))*15);
        }else{
            text.setBounds(15,60,335,((1+(text.getText().length())/54))*15);
        }

        add(text);

        setBorder( BorderFactory.createLineBorder(Color.BLACK,1));

        setPreferredSize(new Dimension(370,400+((1+(text.getText().length())/54))*15));


        int extraPostSpace = ((1+(text.getText().length())/54))*15;

        if(publicacion.hasImage()){

            setPreferredSize(new Dimension(370,445+extraPostSpace));
            comment.setBounds(220,405+extraPostSpace,130,35);
        }else{
            setPreferredSize(new Dimension(370,115+extraPostSpace));
            comment.setBounds(220,75+extraPostSpace,130,35);

        }
        add(comment);

    }

    public JButton getComment(){
        return comment;
    }

    public static void main(String args[]) {

        System.out.println("PostFrame inicializado");

    }

}