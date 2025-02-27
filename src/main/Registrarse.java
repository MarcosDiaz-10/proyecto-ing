import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Registrarse extends GeneralView {
    private JTextFieldCustom firstName, lastName, password, repeatPassword;
    private JButton register;
    private JLabel registerTitleLabel, firstNameLabel, lastNameLabel, schoolLabel, rol, passwordLabel,
            repeatPasswordLabel, IngSocLogo;
    private JPanel internalView;
    private ButtonGroup buttonGroup;
    private JRadioButton professor, student, personel;
    private JComboBox school;

    public Registrarse() {
        initializeComponents();
        internalView.setLayout(null);
        addComponents();
    }

    private void initializeComponents() {
        internalView = new JPanel();

        registerTitleLabel = new JLabel();
        setLabel(registerTitleLabel, "Registro", new Font("Arial", Font.BOLD, 18), IngSocColor.black);
        registerTitleLabel.setBounds(330, 130, 200, 100);

        firstNameLabel = new JLabel();
        setLabel(firstNameLabel, "Nombre", new Font("Arial", Font.BOLD, 12), IngSocColor.black);
        firstNameLabel.setBounds(160, 200, 50, 25);

        lastNameLabel = new JLabel();
        setLabel(lastNameLabel, "Apellido", new Font("Arial", Font.BOLD, 12), IngSocColor.black);
        lastNameLabel.setBounds(380, 200, 200, 25);

        firstName = new JTextFieldCustom("Nombre");
        firstName.setBounds(160, 230, 200, 30);

        lastName = new JTextFieldCustom("Apellido");
        lastName.setBounds(383, 230, 200, 30);

        schoolLabel = new JLabel();
        setLabel(schoolLabel, "Escuela", new Font("Arial", Font.BOLD, 12), IngSocColor.black);
        schoolLabel.setBounds(160, 270, 50, 25);

        school = new JComboBox();
        school.setBounds(160, 300, 200, 30);
        school.addItem("Biolog\u00EDa");
        school.addItem("Computaci\u00F3n");
        school.addItem("F\u00EDsica");
        school.addItem("Qu\uu00EDmica");
        school.addItem("Matem\u00E1tica");
        school.addItem("Geoqu\uu00EDmica");



        rol = new JLabel();
        setLabel(rol, "Rol", new Font("Arial", Font.BOLD, 12), IngSocColor.black);
        rol.setBounds(160, 310, 200, 25);

        buttonGroup = new ButtonGroup();
        professor = new JRadioButton("Profesor");
        professor.setBounds(160, 340, 80, 25);
        student = new JRadioButton("Estudiante");
        student.setBounds(255, 340, 100, 25);
        personel = new JRadioButton("Personal");
        personel.setBounds(360, 340, 80, 25);
        buttonGroup.add(professor);
        buttonGroup.add(student);
        buttonGroup.add(personel);

        passwordLabel = new JLabel();
        setLabel(passwordLabel, "Contrase\u00F1a", new Font("Arial", Font.BOLD, 12), IngSocColor.black);
        passwordLabel.setBounds(160, 370, 100, 25);

        password = new JTextFieldCustom("Contrase\u00F1a");
        password.setBounds(160, 400, 423, 30);

        repeatPasswordLabel = new JLabel();
        setLabel(repeatPasswordLabel, "Repetir Contrase\u00F1a", new Font("Arial", Font.BOLD, 12), IngSocColor.black);
        repeatPasswordLabel.setBounds(160, 440, 150, 25);

        repeatPassword = new JTextFieldCustom("Repetir Contrase\u00F1a");
        repeatPassword.setBounds(160, 470, 423, 30);

        register = new JButton("Registrarse");
        register.setBackground(IngSocColor.black);
        register.setForeground(IngSocColor.white);
        register.setBounds(160, 510, 423, 30);

        ImageIcon IngSocIcon = new ImageIcon("zlogo2redondeado.png");
        Image IngSocRedimension = IngSocIcon.getImage();
        IngSocRedimension = IngSocRedimension.getScaledInstance(141, 120, Image.SCALE_SMOOTH);
        IngSocIcon = new ImageIcon(IngSocRedimension);
        IngSocLogo = new JLabel(IngSocIcon);
        IngSocLogo.setBounds(301, 550, 141, 120);
    }

    private void addComponents() {
        internalView.add(registerTitleLabel);
        internalView.add(firstNameLabel);
        internalView.add(lastNameLabel);
        internalView.add(firstName);
        internalView.add(lastName);
        internalView.add(schoolLabel);
        internalView.add(school);
        internalView.add(rol);
        internalView.add(professor);
        internalView.add(student);
        internalView.add(personel);
        internalView.add(passwordLabel);
        internalView.add(password);
        internalView.add(repeatPasswordLabel);
        internalView.add(repeatPassword);
        internalView.add(register);
        internalView.add(IngSocLogo);

        add(internalView);

    }

    public JButton getRegisterButton() {
        return register;
    }

    private String getSelectedRole() {
        if (professor.isSelected()) {
            return "Profesor";
        } else if (student.isSelected()) {
            return "Estudiante";
        } else if (personel.isSelected()) {
            return "Personal";
        }
        return "No especificado";
    }

    public boolean saveInformation() {

        String firstNameString = firstName.getText();
        String lastNameString = lastName.getText();
        //Esto quizás se pueda hacer algo como JButtonGroup.getSElection() para obtener el botón directamente y ahorrarnos el método
        String rolString = getSelectedRole();
        String passwordString = password.getText();
        String repeatPasswordString = repeatPassword.getText();

        if (firstNameString.length() < 1) {
            JOptionPane.showMessageDialog(this, "Tiene que ingresar un nombre ",
                    "Error de Nombre", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (lastNameString.length() < 1) {
            JOptionPane.showMessageDialog(this, "Tiene que ingresar un Apellido ",
                    "Error de Apellido", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (rolString.equals("No especificado")) {
            JOptionPane.showMessageDialog(this, "Tiene que ingresar un rol ",
                    "Error de Escuela", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (passwordString.length() < 1) {
            JOptionPane.showMessageDialog(this, "Tiene que ingresar una contraseña ",
                    "Error de contraseña ", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!passwordString.equals(repeatPasswordString)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden. Intente nuevamente ",
                    "Error de Contraseña", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String informacion = firstNameString + "-" + lastNameString + "-" + passwordString + "-" + school.getSelectedItem() + "-" + rolString + '\n';
        /*try (FileWriter writer = new FileWriter("/src/DB/database.txt", true)) {*/
        try (FileWriter writer = new FileWriter("database.txt", true)) {
            writer.write(informacion);
            JOptionPane.showMessageDialog(this, "Informaci\u00F3n guardada con éxito");
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la informaci\u00F3n: " + ex.getMessage(), "Error",
            JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //De nevo, quizá esta función podría ser booleana y así poder manejar bien el cambio de pantallas
    }

    public static void main(String args[]) {

        System.out.println("Vista Registrarse Inicializada");
    }
}
