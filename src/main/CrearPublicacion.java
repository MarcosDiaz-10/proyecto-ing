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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CrearPublicacion extends GeneralView {
    private JLabel createPostLabel, postTitleLabel, postSubtitlelabel, interestLabel, descriptionLabel,
            postkindlabel, IngSocLogo, imageLabel;
    private JPanel internalView;
    private JTextFieldCustom title, subtitle, description;
    private JComboBox postType, interestType;
    private JButton createPost, attach;
    private String rutaImg;

    public CrearPublicacion() {
        initializeComponents();
        internalView.setLayout(null);
        addComponents();

    }

    private void initializeComponents() {
        internalView = new JPanel();

        createPostLabel = new JLabel();
        setLabel(createPostLabel, "Crear Publicación", new Font("Arial", Font.BOLD, 24), IngSocColor.black);
        createPostLabel.setBounds(300, 20, 300, 50);

        ImageIcon ima1 = new ImageIcon("src/main/camera.png");
        Image image = ima1.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Adjust size as needed
        ima1 = new ImageIcon(scaledImage);
        imageLabel = new JLabel(ima1);
        imageLabel.setBounds(120, 100, 250, 200);

        // // Check if the image has been loaded correctly
        // if (imageLabel.getIconWidth() == -1) {
        // System.out.println("Error: La imagen no se pudo cargar.");
        // } else {
        // System.out.println("Imagen cargada correctamente.");
        // }

        postTitleLabel = new JLabel();
        setLabel(postTitleLabel, "Título de la publicación", new Font("Arial", Font.BOLD, 16), IngSocColor.black);
        postTitleLabel.setBounds(500, 100, 200, 50);

        postSubtitlelabel = new JLabel();
        setLabel(postSubtitlelabel, "Subtítulo", new Font("Arial", Font.BOLD, 16), IngSocColor.black);
        postSubtitlelabel.setBounds(500, 180, 200, 50);

        interestLabel = new JLabel();
        setLabel(interestLabel, "Tipo de publicación", new Font("Arial", Font.BOLD, 16), IngSocColor.black);
        interestLabel.setBounds(500, 260, 200, 50);

        postkindlabel = new JLabel();
        setLabel(postkindlabel, "Tipo de interés", new Font("Arial", Font.BOLD, 16), IngSocColor.black);
        postkindlabel.setBounds(500, 340, 200, 50);

        descriptionLabel = new JLabel();
        setLabel(descriptionLabel, "Descripción", new Font("Arial", Font.BOLD, 16), IngSocColor.black);
        descriptionLabel.setBounds(80, 400, 200, 50);

        title = new JTextFieldCustom("ingrese el título de la publicación");
        title.setBounds(500, 150, 200, 25);

        subtitle = new JTextFieldCustom("ingrese el subtítulo de la publicación");
        subtitle.setBounds(500, 230, 200, 25);

        description = new JTextFieldCustom("Descripción...");
        description.setBounds(80, 450, 360, 100);

        interestType = new JComboBox();
        interestType.setBounds(500, 310, 200, 20);
        interestType.addItem("Recreacción");
        interestType.addItem("Noticias");
        interestType.addItem("Biología");
        interestType.addItem("Matemáticas");
        interestType.addItem("Computación y tecnología");
        interestType.addItem("Física");
        interestType.addItem("Química");
        interestType.addItem("Geografía");

        postType = new JComboBox();
        postType.setBounds(500, 390, 200, 20);
        postType.addItem("Taller");
        postType.addItem("Seminario");
        postType.addItem("Proyecto de investigación");
        postType.addItem("Evento Social");

        createPost = new JButton("Crear Publicación");
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
                rutaImg = saveImage(null, imageLabel);

            }
        });

        DragAndDropImage(imageLabel);
    }

    private void addComponents() {
        internalView.add(createPostLabel);
        // internalView.add(imageLabel);
        internalView.add(postTitleLabel);
        internalView.add(postSubtitlelabel);
        internalView.add(interestLabel);
        internalView.add(postkindlabel);
        internalView.add(descriptionLabel);
        internalView.add(title);
        internalView.add(subtitle);
        internalView.add(description);
        internalView.add(postType);
        internalView.add(interestType);
        internalView.add(createPost);
        internalView.add(attach);
        internalView.add(imageLabel);

        add(internalView);

    }

    public JButton getCreatePostButton() {
        return createPost;
    }

    private void DragAndDropImage(JLabel imagLabel) {
        new DropTarget(imagLabel, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent event) {
                try {
                    event.acceptDrop(DnDConstants.ACTION_COPY);

                    Transferable transferable = event.getTransferable();
                    if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        List<File> files = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);

                        if (files.size() == 1) {
                            File file = files.get(0);

                            // Verificar si es una imagen (por extensión)
                            if (isImageFile(file)) {
                                // Guardar la imagen en la ruta deseada
                                rutaImg = saveImage(file, imagLabel);

                            } else {
                                // Si no es una imagen, rechazar el drop
                                event.rejectDrop();
                                JOptionPane.showMessageDialog(imagLabel,
                                        "Solo se permiten archivos de imagen (JPG, PNG, GIF, BMP).",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            // Si se arrastra más de un archivo, rechazar el drop
                            event.rejectDrop();
                            JOptionPane.showMessageDialog(imagLabel,
                                    "Solo se permite arrastrar una imagen a la vez.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } catch (UnsupportedFlavorException e) {
                    // Manejar la excepción si el tipo de dato no es compatible
                    event.rejectDrop();
                    JOptionPane.showMessageDialog(imagLabel,
                            "Formato de archivo no compatible.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException e) {
                    // Manejar errores de entrada/salida
                    event.rejectDrop();
                    JOptionPane.showMessageDialog(imagLabel,
                            "Error al procesar el archivo: " + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    // Manejar cualquier otra excepción
                    event.rejectDrop();
                    JOptionPane.showMessageDialog(imagLabel,
                            "Error inesperado: " + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public Boolean saveInformationPublication() {

        String postTitle = title.getText();
        String postSubtitle = subtitle.getText();
        String interstTypeValue = interestType.getSelectedItem().toString();
        String postTypeValue = postType.getSelectedItem().toString();
        String descriptionValue = description.getText();

        if (postTitle.length() < 1) {
            JOptionPane.showMessageDialog(this, "Tiene que ingresar un Titulo",
                    "Error de Titulo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (postSubtitle.length() < 1) {
            JOptionPane.showMessageDialog(this, "Tiene que ingresar un Subtitulo",
                    "Error de Subtitulo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (interstTypeValue.length() < 1) {
            JOptionPane.showMessageDialog(this, "Tiene que ingresar un Tipo De Interés ",
                    "Error de Tipo De Interés", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (postTypeValue.length() < 1) {
            JOptionPane.showMessageDialog(this, "Tiene que ingresar un Tipo De Publicación",
                    "Error de Tipo De publicación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (descriptionValue.length() < 1) {
            JOptionPane.showMessageDialog(this, "Tiene que ingresar una Description",
                    "Error de Descripción", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (rutaImg.length() < 1) {
            JOptionPane.showMessageDialog(this, "Tiene que ingresar una Imagen",
                    "Error de Imagen", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String informacion = "\n" + postTitle + "\n" + postSubtitle + "\n" + 1 + "\n" + rutaImg + "\n"
                + interstTypeValue + "\n"
                + postTypeValue
                + '\n' + descriptionValue + '\n';
        /* try (FileWriter writer = new FileWriter("/src/DB/database.txt", true)) { */
        try (FileWriter writer = new FileWriter("src/main/postDatabase.txt", true)) {
            writer.write(informacion);
            JOptionPane.showMessageDialog(this, "Informaci\u00F3n guardada con éxito");
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la informaci\u00F3n: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    private String saveImage(File img, JLabel imagLabel) {
        File archivo = img;

        if (img == null) {

            // Mostrar un cuadro de diálogo para elegir la ubicación y el nombre del archivo
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleccionar imagen");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Imágenes", "jpg", "jpeg", "png", "gif", "bmp"));
            int seleccionUsuario = fileChooser.showOpenDialog(this);
            if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
                archivo = fileChooser.getSelectedFile();
            } else {
                archivo = null;
            }
        }

        if (archivo != null) {
            String rutaDestino = "src/main";

            try {
                Path origin = archivo.toPath();

                Path destino = Paths.get(rutaDestino, archivo.getName());

                Files.copy(origin, destino);

                // Mostrar la imagen en el JLabel
                ImageIcon icon = new ImageIcon(destino.toString());
                Image image = icon.getImage();
                ImageIcon scaledImage = new ImageIcon(
                        image.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                imageLabel.setIcon(scaledImage);
                return destino.toString();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al guardar la imagen: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                return "";
            }

        }
        return "";
    }

    private boolean isImageFile(File file) {
        String nombre = file.getName().toLowerCase();
        return nombre.endsWith(".jpg") || nombre.endsWith(".jpeg") ||
                nombre.endsWith(".png") || nombre.endsWith(".gif") ||
                nombre.endsWith(".bmp");
    }

    public static void main(String args[]) {
        // Crear el marco (JFrame)
        JFrame frame = new JFrame("Crear Publicación");
        CrearPublicacion crearPublicacion = new CrearPublicacion();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700); // Tamaño del marco
        frame.add(crearPublicacion); // Agregar la vista de crear publicación
        frame.setVisible(true); // Hacer visible el marco

        System.out.println("Vista crear publicación Inicializada");
    }

}
