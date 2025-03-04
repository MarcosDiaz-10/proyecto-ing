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

    public String getSelectedRole() {
        if (professor.isSelected()) {
            return "Profesor";
        } else if (student.isSelected()) {
            return "Estudiante";
        } else if (personel.isSelected()) {
            return "Personal";
        }
        return "No especificado";
    }

    public String getFirstName() {
        return firstName.getText();
    }

    public String getLastName() {
        return lastName.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public String getRepeatPassword() {
        return repeatPassword.getText();
    }

    public String getSchool() {
        return (String) school.getSelectedItem();
    }

    public void setFirstName(String data){
        firstName.setText(data);
    }

    public void setLastName(String data){
        lastName.setText(data);
    }

    public void setSchool(String data){

        switch (data) {
            case "Biolog\u00EDa":
                school.setSelectedIndex(0);
                break;
            case "Computaci\u00F3n":
                school.setSelectedIndex(1);
                break;
            case "F\u00EDsica":
                school.setSelectedIndex(2);
                break;
            case "Qu\uu00EDmica":
                school.setSelectedIndex(0);
                break;
            case "Matem\u00E1tica":
                school.setSelectedIndex(1);
                break;
            case "Geoqu\uu00EDmica":
                school.setSelectedIndex(2);
                break;
            default:
                break;
        }
    }

    public void setRol(String data){
        if (data.equals("Profesor")) {
            professor.setSelected(true);
        } else if (data.equals("Estudiante")) {
            student.setSelected(true);
        } else if (data.equals("Personal")) {
            personel.setSelected(true);
        }
    }

    public void setPassword(String data){
        password.setText(data);
    }

    public void setRepeatPassword(String data){
        repeatPassword.setText(data);
    }

    public static void main(String args[]) {

        System.out.println("Vista Registrarse Inicializada");
    }
}
