import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

import org.w3c.dom.events.MouseEvent;

import javax.swing.event.ChangeEvent; // Importa la clase ChangeEvent
import java.awt.event.ActionListener; // Importa la clase ActionListener
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent


public class IngSocFrame{

    private JPanel header;
    private JPanel floor;
    private JLabel IngSocLogo, IngSocFloor, IngSocHeader,mainPage,CONEST,virtualCampus;

    public IngSocFrame(){

        header = new JPanel();
        floor = new JPanel();
        IngSocFloor = new JLabel();
        IngSocHeader = new JLabel();
        IngSocLogo = new JLabel();
        CONEST = new JLabel();
        mainPage = new JLabel();
        virtualCampus = new JLabel();

        setLabel(IngSocFloor,"IngSoc",new Font("Arial",Font.PLAIN,50),IngSocColor.black);
        setLabel(IngSocHeader,"IngSoc",new Font("Arial",Font.PLAIN,50),IngSocColor.black);
        setLabel(CONEST,"CONEST",new Font("Arial",Font.PLAIN,15),IngSocColor.black);
        setLabel(mainPage,"Pagina Principal",new Font("Arial",Font.PLAIN,15),IngSocColor.black);
        setLabel(virtualCampus,"Campus Virtual",new Font("Arial",Font.PLAIN,15),IngSocColor.black);


        ImageIcon imagen = new ImageIcon("zlogoHeader.png");
        //Para escalar
        Image imagenRedimensionada = imagen.getImage();
        imagenRedimensionada = imagenRedimensionada.getScaledInstance(200,85, imagenRedimensionada.SCALE_SMOOTH);
        imagen = new ImageIcon(imagenRedimensionada);
        IngSocLogo.setIcon(imagen);

        setLabelBorder(IngSocLogo, new EmptyBorder(0,0,0,50));
        setLabelBorder(IngSocHeader, new EmptyBorder(0,50,0,0));
        setLabelBorder(IngSocFloor, new EmptyBorder(0,0,0,50));
        setLabelBorder(CONEST, new EmptyBorder(30,70,100,40));
        setLabelBorder(mainPage, new EmptyBorder(30,50,100,20));
        setLabelBorder(virtualCampus, new EmptyBorder(30,50,100,20));

        header.setBackground(IngSocColor.red);
        header.setLayout(new BorderLayout());
        header.add(IngSocHeader,BorderLayout.WEST);
        header.add(IngSocLogo,BorderLayout.EAST);


        floor.setBackground(IngSocColor.red);
        floor.setLayout(new FlowLayout());
        floor.add(IngSocFloor);
        floor.add(mainPage);
        floor.add(CONEST);
        floor.add(virtualCampus);
       
    }

    public JLabel getMainPage(){
        return mainPage;
    }

    public JLabel getConestPage(){
        return CONEST;
    }

    public JLabel getvirtualCampusPage(){
        return virtualCampus;
    }

    private void setLabel(JLabel label, String text, Font font, Color color){
        label.setText(text);
        label.setFont(font);
        label.setForeground(color);
    }

    private void setLabelBorder(JLabel label, Border border){
        label.setBorder(border);
    }

    public JPanel getHeader(){
        return header;
    }

    public JPanel getFloor(){
        return floor;
    }

    public static void main(String args[]){

        System.out.println("Frame Inicializado");

    }

}