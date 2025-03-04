
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
    JLabel classification,title,text,imagen,date,owner;
    JButton comment;

    public PostFrame(BufferedReader reader) {

        initializePost(reader);

    }

    private void initializePost(BufferedReader reader){
        publicacion = new Publicacion(reader);

        setLayout(null);

        comment = new JButton("Comentar");
        comment.setBackground(IngSocColor.black);
        comment.setForeground(IngSocColor.white);
        comment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //No se me ocurre como enlazar el controlador con esto para ir a comentarPublicacion
                System.out.println("Comentar en Publicacion " + publicacion.getTitle());
            }
        });

        classification = new JLabel("<html>" + publicacion.getClassification() + "</html>");
        classification.setBounds(5,5,100,25);
        classification.setFont(new Font("Arial",Font.ITALIC,10));

        if(publicacion.getClassification().equals("Evento")){
            //Podemos dejar solo las letras también, o los bordes incluidos, idk
            classification.setForeground(IngSocColor.event);
            setBorder( BorderFactory.createLineBorder(IngSocColor.event,2));
        }else if(publicacion.getClassification().equals("Taller")){
            classification.setForeground(IngSocColor.taller);
            setBorder( BorderFactory.createLineBorder(IngSocColor.taller,2));
        }else{
            setBorder( BorderFactory.createLineBorder(Color.BLACK,1));
        }

        add(classification);

        owner = new JLabel("<html>De "+publicacion.getOwner() + "</html>");
        owner.setBounds(5,18,300,25);
        owner.setFont(new Font("Arial",Font.ITALIC,10));
        add(owner);

        title = new JLabel("<html>" + publicacion.getTitle() + "</html>");
        title.setFont(new Font("Arial",Font.BOLD,20));
        title.setBounds(20,40,350,25);;
        add(title);

        date = new JLabel(publicacion.getDate());
        date.setBounds(300,5,100,25);
        date.setFont(new Font("Arial",Font.ITALIC,10));
        add(date);


        text = new JLabel("<html>" + publicacion.getText() + "</html>");
        text.setFont(new Font("Arial",Font.PLAIN,12));

        if(publicacion.hasImage()){
            imagen = new JLabel();
            imagen.setIcon(publicacion.getImage().getIcon());

            imagen.setBounds(20,70,325,325);
            add(imagen);

            text.setBounds(15,405,335,((1+(text.getText().length())/54))*15);
        }else{
            text.setBounds(15,70,335,((1+(text.getText().length())/54))*15);
        }

        add(text);

        int extraPostSpace = ((1+(text.getText().length())/54))*15;

        if(publicacion.hasImage()){

            setPreferredSize(new Dimension(370,455+extraPostSpace));
            comment.setBounds(220,405+extraPostSpace,130,35);
        }else{
            setPreferredSize(new Dimension(370,125+extraPostSpace));
            comment.setBounds(220,75+extraPostSpace,130,35);

        }
        if(!classification.getText().equals("<html> </html>"))//Si no es la primera
            add(comment);
    }

    public JButton getComment(){
        return comment;
    }

    public static void main(String args[]) {

        System.out.println("PostFrame inicializado");

    }

}