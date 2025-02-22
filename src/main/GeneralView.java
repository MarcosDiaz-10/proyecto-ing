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
import java.awt.Font;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent


public class GeneralView extends JPanel{

    IngSocFrame frame;


    public GeneralView(){

        frame = new IngSocFrame();
        
        this.setBounds(0,0,100,100);
        this.setBackground(IngSocColor.white);
        this.setLayout(new BorderLayout());
        this.add(frame.getHeader(),BorderLayout.NORTH);
        this.add(frame.getFloor(),BorderLayout.SOUTH);
        this.frame.getHeader().setPreferredSize(new Dimension(100,100));
        this.frame.getFloor().setPreferredSize(new Dimension(100,170));


    }

    public IngSocFrame getFrame(){
        return this.frame;
    }

    protected void setLabel(JLabel label, String text, Font font, Color color){
        label.setText(text);
        label.setFont(font);
        label.setForeground(color);
    }

    protected void setLabelBorder(JLabel label, Border border){
        label.setBorder(border);
    }

    public static void main(String args[]){
        System.out.println("Vista general inicializada");
    }

}