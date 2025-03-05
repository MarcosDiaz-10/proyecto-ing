import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;

import org.w3c.dom.events.MouseEvent;

import javax.swing.event.ChangeEvent; // Importa la clase ChangeEvent
import java.awt.event.ActionListener; // Importa la clase ActionListener
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.Scanner;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.util.List;

public class ControladorCrearPublicacion {
    private String rutaImg;

    public ControladorCrearPublicacion() {

    }

    public String DragAndDropImage(JLabel imagLabel, JPanel crearPublicacionView) {
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
                                rutaImg = saveImage(file, imagLabel, crearPublicacionView);

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
        return rutaImg;
    }

    public int saveInformationPublication(CrearPublicacion crearPublicacionView, String rutaImg) {

        String postTitle = crearPublicacionView.getTitle();
        String interstTypeValue = crearPublicacionView.getInterstTypeValue();
        String postTypeValue = crearPublicacionView.getPostTypeValue();
        String descriptionValue = crearPublicacionView.getDescription();
        String date = crearPublicacionView.getdate();
        int existImg = 1;
        if (postTitle.length() < 1) {

            return 1;
        }

        if (interstTypeValue.length() < 1) {

            return 2;
        }

        if (postTypeValue.length() < 1) {

            return 3;
        }
        if (descriptionValue.length() < 1) {

            return 4;
        }
        if (rutaImg != null && rutaImg.length() < 1) {

            existImg = 0;
        }

        String informacion = "\n" + postTitle + "\n"
                + postTypeValue + "\n" + date + "\n" + existImg + "\n" + rutaImg + "\n"
                + interstTypeValue
                + '\n' + descriptionValue + "\n" + '-' + '\n';
        /* try (FileWriter writer = new FileWriter("/src/DB/database.txt", true)) { */
        try (FileWriter writer = new FileWriter("postDatabase.txt", true)) {
            writer.write(informacion);

            return 0;
        } catch (IOException ex) {
            // JOptionPane.showMessageDialog(this, "Error al guardar la informaci\u00F3n: "
            // + ex.getMessage(), "Error",
            // JOptionPane.ERROR_MESSAGE);
            // return false;

            return -1;
        }

    }

    public String saveImage(File img, JLabel imagLabel, JPanel crearPublicacionView) {
        File archivo = img;

        if (img == null) {

            // Mostrar un cuadro de diálogo para elegir la ubicación y el nombre del archivo
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleccionar imagen");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Imágenes", "jpg", "jpeg", "png", "gif", "bmp"));
            int seleccionUsuario = fileChooser.showOpenDialog(crearPublicacionView);
            if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
                archivo = fileChooser.getSelectedFile();
            } else {
                archivo = null;
            }
        }

        if (archivo != null) {
            String rutaDestino = "";

            try {
                Path origin = archivo.toPath();

                Path destino = Paths.get(rutaDestino, archivo.getName());

                Files.copy(origin, destino);

                // Mostrar la imagen en el JLabel
                ImageIcon icon = new ImageIcon(destino.toString());
                Image image = icon.getImage();
                ImageIcon scaledImage = new ImageIcon(
                        image.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                imagLabel.setIcon(scaledImage);
                return destino.toString();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(crearPublicacionView, "Error al guardar la imagen: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return "";
            }

        }
        return "";
    }

    public boolean isImageFile(File file) {
        String nombre = file.getName().toLowerCase();
        return nombre.endsWith(".jpg") || nombre.endsWith(".jpeg") ||
                nombre.endsWith(".png") || nombre.endsWith(".gif") ||
                nombre.endsWith(".bmp");
    }

    public static void main(String args[]) {
        System.out.println("ControladorInicarSesion inicializado");
    }

}