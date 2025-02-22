import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent; // Importa la clase ChangeEvent
import java.awt.event.ActionListener; // Importa la clase ActionListener
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.concurrent.Flow;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent
import java.awt.event.MouseEvent; // Importa la clase MouseEvent
import java.awt.*;



public class PaginaPrincipal extends GeneralView{

    private JButton closeSession,post;
    private JPanel internalView,calendar;
    private JPanel postFeed;
    private JScrollPane postScroll; 


    public PaginaPrincipal(){

        initializeComponents();

        internalView.setLayout(null);

        addComponents();

    }

    

    private void initializeComponents(){

        internalView = new JPanel();

        closeSession = new JButton("Cerrar Sesi\u00F3n");
        closeSession.setBackground(IngSocColor.black);
        closeSession.setForeground(IngSocColor.white);
        closeSession.setBounds(70,630,160,45);

        postFeed = new JPanel();
        postScroll = new JScrollPane(postFeed);

        postFeed.setOpaque(true);
        postFeed.add(new JLabel("<html>Lorem Ipsum dolor sit Amer<br>Consequectur y hasta ahí me la sé<br>eyyy<br>eyyy<br>eyyy<br>eyyy<br>eyyy<br></html>"));
        postFeed.setBounds(0,0,100,100);
        postScroll.setBounds(300,100,450,650);
        //postScroll.setBorder(null); CON ESTO LE PODEMOS QUITAR EL BORDE PERO POR AHORA PARA SABER DONDE ESTA

        calendar = new JPanel();
        calendar.setOpaque(false);
        calendar.setBackground(new Color(100,100,100));
        calendar.setBounds(50,100,200,200);
        calendar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        calendar.add(new JLabel("Placeholder"));

        post = new JButton("Publicar");
        post.setBackground(IngSocColor.black);
        post.setForeground(IngSocColor.white);
        post.setBounds(70,400,160,45);






    }

    private void addComponents(){

        internalView.add(closeSession);
        internalView.add(postScroll);
        internalView.add(calendar);
        internalView.add(post);


        add(internalView);

    }

    public JButton getCloseSessionButton(){
        return closeSession;
    }

    public JButton getPostButton(){
        return post;
    }

    public static void main(String args[]){

        System.out.println("Vista Pagina Principal Inicializada");

    }

}