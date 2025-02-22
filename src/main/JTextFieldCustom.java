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

import javax.swing.*;
import java.awt.*;


public class JTextFieldCustom extends JTextField {
    private String placeholder;

    public JTextFieldCustom(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty() && !(FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.setFont(getFont().deriveFont(Font.PLAIN));
            g2.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
            g2.dispose();
        }
    }

    public static void main(String args[]){
        System.out.println("Custom Text Field inicializado");
    }

}