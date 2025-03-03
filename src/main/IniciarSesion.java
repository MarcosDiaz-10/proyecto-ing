import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent; // Importa la clase ChangeEvent
import java.awt.event.ActionListener; // Importa la clase ActionListener
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Flow;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent
import java.awt.event.MouseEvent; // Importa la clase MouseEvent
import java.awt.*;

public class IniciarSesion extends GeneralView {

    private JTextFieldCustom firstName, lastName, password;
    private JButton register, login;
    private JLabel loginTitleLabel, firstNameLabel, lastNameLabel, passwordLabel, IngSocLogo, IngSocCatchPhrase;
    private JPanel internalView;

    public IniciarSesion() {

        initializeComponents();

        internalView.setLayout(null);

        addComponents();

    }

    private void initializeComponents() {

        internalView = new JPanel();

        register = new JButton("Registrarse");
        register.setBackground(IngSocColor.black);
        register.setForeground(IngSocColor.white);
        register.setBounds(555, 20, 160, 45);

        loginTitleLabel = new JLabel();
        setLabel(loginTitleLabel, "Iniciar Sesi\u00F3n", new Font("Arial", Font.BOLD, 18), IngSocColor.black);
        loginTitleLabel.setBounds(310, 40, 200, 100);

        firstNameLabel = new JLabel();
        setLabel(firstNameLabel, "Nombre", new Font("Arial", Font.BOLD, 12), IngSocColor.black);
        firstNameLabel.setBounds(160, 110, 50, 25);

        lastNameLabel = new JLabel();
        setLabel(lastNameLabel, "Apellido", new Font("Arial", Font.BOLD, 12), IngSocColor.black);
        lastNameLabel.setBounds(380, 110, 200, 25);

        firstName = new JTextFieldCustom("Nombre");
        firstName.setBounds(160, 140, 200, 30);

        lastName = new JTextFieldCustom("Apellido");
        lastName.setBounds(383, 140, 200, 30);

        passwordLabel = new JLabel();
        setLabel(passwordLabel, "Contrase\u00F1a", new Font("Arial", Font.BOLD, 12), IngSocColor.black);
        passwordLabel.setBounds(160, 175, 100, 25);

        password = new JTextFieldCustom("Contrase\u00F1a");
        password.setBounds(160, 200, 423, 30);

        login = new JButton("Iniciar Sesi\u00F3n");
        login.setBackground(IngSocColor.black);
        login.setForeground(IngSocColor.white);
        login.setBounds(160, 240, 423, 30);
        
        ImageIcon IngSocIcon = new ImageIcon("zlogo2redondeado.png");
        // Para escalar
        Image IngSocRedimension = IngSocIcon.getImage();
        IngSocRedimension = IngSocRedimension.getScaledInstance(283, 240, IngSocRedimension.SCALE_SMOOTH);
        IngSocIcon = new ImageIcon(IngSocRedimension);
        IngSocLogo = new JLabel(IngSocIcon);
        IngSocLogo.setBounds(230, 300, 283, 240);

        IngSocCatchPhrase = new JLabel();
        setLabel(IngSocCatchPhrase,
                "<html>&nbsp &nbsp &nbsp  Y recuerda...<br>El Gran Hermano te<br> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp vigila</html>",
                new Font("Arial", Font.BOLD, 18), IngSocColor.black);
        IngSocCatchPhrase.setBounds(280, 560, 200, 75);

    }

    private void addComponents() {

        internalView.add(register);
        internalView.add(loginTitleLabel);
        internalView.add(firstNameLabel);
        internalView.add(lastNameLabel);
        internalView.add(firstName);
        internalView.add(lastName);
        internalView.add(passwordLabel);
        internalView.add(password);
        internalView.add(login);
        internalView.add(IngSocLogo);
        internalView.add(IngSocCatchPhrase);

        add(internalView);

    }

    public JButton getRegisterButton() {
        return register;
    }

    public JButton getLoginButton() {
        return login;
    }

    public String getFirstName(){
        return firstName.getText();
    }

    public String getLastName(){
        return lastName.getText();
    }

    public String getPassword(){
        return password.getText();
    }

    public static void main(String args[]) {

        System.out.println("Vista Iniciar Sesion Inicializada");

    }

}