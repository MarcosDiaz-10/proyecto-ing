import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CrearPublicacion extends GeneralView {
    private JLabel createPostLabel, postTitleLabel, calendarLabel, interestLabel, descriptionLabel, postkindlabel,
            IngSocLogo, imageLabel;
    private JPanel internalView;
    private JTextFieldCustom title, subtitle, description;
    private JComboBox postType, interestType;
    private JButton createPost, attach;
    ControladorCrearPublicacion controladorCrearPublicacion;
    private String rutaImg;
    private JSpinner dateSpinner;

    public CrearPublicacion() {
        initializeComponents();
        internalView.setLayout(null);
        addComponents();

    }

    private void initializeComponents() {
        controladorCrearPublicacion = new ControladorCrearPublicacion();
        internalView = new JPanel();

        createPostLabel = new JLabel();
        setLabel(createPostLabel, "Crear Publicaci\u00F3n", new Font("Arial", Font.BOLD, 24), IngSocColor.black);
        createPostLabel.setBounds(300, 20, 300, 50);

        ImageIcon ima1 = new ImageIcon("camera.png");
        Image image = ima1.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Adjust size as needed
        ima1 = new ImageIcon(scaledImage);
        imageLabel = new JLabel(ima1);
        imageLabel.setBounds(120, 100, 250, 200);

        postTitleLabel = new JLabel();
        setLabel(postTitleLabel, "T\u00EDtulo de la publicaci\u00F3n", new Font("Arial", Font.BOLD, 16), IngSocColor.black);
        postTitleLabel.setBounds(500, 100, 200, 50);

        calendarLabel = new JLabel();
        setLabel(calendarLabel, "Calendario", new Font("Arial", Font.BOLD, 16), IngSocColor.black);
        calendarLabel.setBounds(520, 180, 200, 50);

        interestLabel = new JLabel();
        setLabel(interestLabel, "Tipo de publicaci\u00F3n", new Font("Arial", Font.BOLD, 16), IngSocColor.black);
        interestLabel.setBounds(500, 340, 200, 50);

        postkindlabel = new JLabel();
        setLabel(postkindlabel, "Tipo de inter\u00E9s", new Font("Arial", Font.BOLD, 16), IngSocColor.black);
        postkindlabel.setBounds(500, 260, 200, 50);

        descriptionLabel = new JLabel();
        setLabel(descriptionLabel, "Descripci\u00F3n", new Font("Arial", Font.BOLD, 16), IngSocColor.black);
        descriptionLabel.setBounds(80, 400, 200, 50);

        title = new JTextFieldCustom("Ingrese el t\u00EDtulo de la publicaci\uu00F3n");
        title.setBounds(500, 150, 200, 25);

        // subtitle = new JTextFieldCustom("ingrese el subtítulo de la publicación");
        // subtitle.setBounds(500, 230, 200, 25);

        description = new JTextFieldCustom("Descripci\u00F3n...");
        description.setBounds(80, 450, 360, 100);

        interestType = new JComboBox();
        interestType.setBounds(500, 310, 200, 20);
        interestType.addItem("Recreaci\u00F3n");
        interestType.addItem("Noticias");
        interestType.addItem("Biolog\u00EDa");
        interestType.addItem("Matem\u0061ticas");
        interestType.addItem("Computaci\u00F3n y tecnolog\u00EDa");
        interestType.addItem("F\u00EDsica");
        interestType.addItem("Qu\u00EDmica");
        interestType.addItem("Geograf\u00EDa");

        postType = new JComboBox();
        postType.setBounds(500, 390, 200, 20);
        postType.addItem("Taller");
        postType.addItem("Seminario");
        postType.addItem("Proyecto de investigaci\u00F3n");
        postType.addItem("Evento Social");

        createPost = new JButton("Crear Publicaci\u00F3n");
        Font font = createPost.getFont();
        Font newFont = font.deriveFont(18f);
        createPost.setFont(newFont);
        createPost.setBackground(IngSocColor.black);
        createPost.setForeground(IngSocColor.white);
        createPost.setBounds(250, 600, 225, 60);

        attach = new JButton("Adjuntar Archivo");
        Font fontAtt = attach.getFont();
        Font newFontAtt = fontAtt.deriveFont(16f);
        attach.setFont(newFontAtt);
        attach.setBackground(IngSocColor.black);
        attach.setForeground(IngSocColor.white);
        attach.setBounds(150, 350, 180, 30);

        attach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rutaImg = controladorCrearPublicacion.saveImage(null, imageLabel, internalView);

            }
        });

        rutaImg = controladorCrearPublicacion.DragAndDropImage(imageLabel, internalView);

        // Crear un JSpinner con un SpinnerDateModel
        Calendar calendar = Calendar.getInstance();
        Date initialDate = calendar.getTime();
        SpinnerDateModel model = new SpinnerDateModel(initialDate, null, null, Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(editor);
        dateSpinner.setBounds(500, 230, 200, 25);
    }

    private void addComponents() {
        internalView.add(createPostLabel);
        internalView.add(postTitleLabel);
        internalView.add(calendarLabel);
        internalView.add(interestLabel);
        internalView.add(postkindlabel);
        internalView.add(descriptionLabel);
        internalView.add(title);
        internalView.add(description);
        internalView.add(postType);
        internalView.add(interestType);
        internalView.add(createPost);
        internalView.add(attach);
        internalView.add(imageLabel);
        internalView.add(dateSpinner);
        add(internalView);

    }

    public JButton getCreatePostButton() {
        return createPost;
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public String getTitle() {
        return title.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public String getInterstTypeValue() {
        return interestType.getSelectedItem().toString();
    }

    public String getPostTypeValue() {
        return postType.getSelectedItem().toString();
    }

    public String getdate() {
        return dateSpinner.getValue().toString();
    }

    public void setTitle (String data) {
        title.setText(data);
    }

    public void setInterstTypeValue (String data) {
        interestType.setSelectedItem(data);
    } 

    public void setPostTypeValue (String data) {
        postType.setSelectedItem(data);
    }

    public void setDescription (String data) {
        description.setText(data);
    }

    public void setDate (String data) {
        dateSpinner.setValue(data);
    } 

    public static void main(String args[]) {

        System.out.println("Vista Crear Publicacion Inicializada");
    }

}
