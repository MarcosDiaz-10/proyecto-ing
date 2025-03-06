package views;

import javax.swing.*;
import javax.swing.SpringLayout.Constraints;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.text.DateFormatter;

import utils.IngSocColor;

import javax.swing.event.ChangeEvent; // Importa la clase ChangeEvent
import java.awt.event.ActionListener; // Importa la clase ActionListener
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.Flow;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent
import java.awt.event.MouseEvent; // Importa la clase MouseEvent
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;

//****INTENTAR VER SI PUEDO METER ÉSTE, EL DE LOADPOST Y EL DE CALENDARIO EN UN CONTROLADOR

public class PaginaPrincipal extends GeneralView {

    private JButton closeSession, post;
    private JPanel internalView, calendar;
    private JPanel postFeed;
    private JScrollPane postScroll;
    private JScrollBar postScrollBar;
    // Necesitaremos una clase VistaPublicación o algo, con un JPanel sea como tal
    // lo que se muestra a partir de la entidad Publicación
    // Estas VistaPublicación estarían en el postScroll que debería de funcionar e
    // ir bajando, y no sé si ver
    private BufferedReader readerIndex;
    private ArrayList<PostFrame> postCascade;
    private GridBagConstraints postConstraints;
    private boolean areTherePost; // Esta variable debera ir probablemente en el controlador

    public PaginaPrincipal() {

        initializeComponents();
        initializeCalendar();

        internalView.setLayout(null);

        addComponents();

    }

    private void initializeComponents() {

        internalView = new JPanel();

        areTherePost = true;

        closeSession = new JButton("Cerrar Sesi\u00F3n");
        closeSession.setBackground(IngSocColor.black);
        closeSession.setForeground(IngSocColor.white);
        closeSession.setBounds(70, 630, 160, 45);

        postFeed = new JPanel();
        postFeed.setLayout(new GridBagLayout());
        postScroll = new JScrollPane(postFeed);

        postCascade = new ArrayList<PostFrame>();
        postConstraints = new GridBagConstraints();
        postConstraints.insets = new Insets(10, 0, 50, 0);

        try {
            readerIndex = new BufferedReader(new FileReader("src/data/postDatabase.txt"));
            postCascade = new ArrayList<PostFrame>();

            // INTENTAR VER SI PUEDO METER ÉSTE, EL DE LOADPOST Y EL DE CALENDARIO EN UN
            // CONTROLADOR

            for (int i = 0; i < 2; i++) {

                PostFrame aux = new PostFrame(readerIndex);
                postCascade.add(aux);
                postConstraints.gridx = 0;
                postConstraints.gridy = i;
                postFeed.add(aux, postConstraints);
                // aux.getComment().addActionListener(); No sé cómo hacer que ésto funcione

                readerIndex.mark(20);
                readerIndex.readLine();
                if (readerIndex == null) {
                    System.out.println("Nada");
                    areTherePost = false;
                }
                readerIndex.reset();

            }

        } catch (IOException e) {
            System.out.println("Error al abrir el archivo desde P\u00E1gina Principal");
        }

        postScrollBar = postScroll.getVerticalScrollBar();
        postScrollBar.setUnitIncrement(30);// Para la rueda del ratón
        postScrollBar.setBlockIncrement(30);// Para las flechitas de arriba/abajo

        postFeed.setBounds(0, 0, 100, 100);
        postScroll.setBounds(300, 20, 450, 660);
        postScroll.setBorder(null); // Le quita el border al JScrollBar

        post = new JButton("Publicar");
        post.setBackground(IngSocColor.black);
        post.setForeground(IngSocColor.white);
        post.setBounds(70, 400, 160, 45);

    }

    private void addComponents() {

        internalView.add(closeSession);
        internalView.add(postScroll);
        internalView.add(calendar);
        internalView.add(post);

        add(internalView);

    }

    public void loadPost() {

        if (!areTherePost) {
            System.out.println("No hay m\u00E1s publicaciones");
            return;
        }

        for (int i = 0; i < 2; i++) {

            PostFrame aux = new PostFrame(readerIndex);
            try {

                postCascade.add(aux);
                postConstraints.gridx = 0;
                postConstraints.gridy = postCascade.size();
                postFeed.add(aux, postConstraints);

                readerIndex.mark(20);
                String line = readerIndex.readLine();
                if (line == null) {
                    areTherePost = false;
                    readerIndex.reset();
                    return;
                }
                readerIndex.reset();

            } catch (Exception e) {
                System.out.println("Problema leyendo archivo en P\u00E1gina Principal");
            }

        }

    }

    private void initializeCalendar() {

        calendar = new JPanel();
        calendar.setOpaque(false);
        calendar.setBackground(new Color(100, 100, 100));
        calendar.setBounds(25, 20, 275, 275);
        calendar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        calendar.setLayout(new GridLayout(0, 7));

        JLabel[] dias = new JLabel[49];

        dias[0] = new JLabel("DOM");
        dias[1] = new JLabel("LUN");
        dias[2] = new JLabel("MAR");
        dias[3] = new JLabel("MI\u00C9");
        dias[4] = new JLabel("JUE");
        dias[5] = new JLabel("VIE");
        dias[6] = new JLabel("S\u00C1B");

        for (int i = 0; i < 7; i++) {
            dias[i].setHorizontalAlignment(SwingConstants.CENTER);
        }

        LocalDate today = LocalDate.now();
        int firstDayOfCalendar;

        if (today.withDayOfMonth(1).getDayOfWeek().getValue() == 7) {
            firstDayOfCalendar = 0;
        } else {
            firstDayOfCalendar = today.withDayOfMonth(1).getDayOfWeek().getValue();
        }

        for (int i = 7; i < firstDayOfCalendar + 7; i++) {
            dias[i] = new JLabel();
            dias[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            dias[i].setHorizontalAlignment(SwingConstants.CENTER);
        }

        for (int i = 7 + firstDayOfCalendar; i < today.lengthOfMonth() + 7 + firstDayOfCalendar; i++) {
            dias[i] = new JLabel(String.valueOf(i - firstDayOfCalendar - 6));
            dias[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            dias[i].setHorizontalAlignment(SwingConstants.CENTER);

            if (today.getDayOfMonth() == i - firstDayOfCalendar - 6) {
                dias[i].setBackground(IngSocColor.gray);
            }

        }

        for (int i = today.lengthOfMonth() + 7 + firstDayOfCalendar; i < 49; i++) {
            dias[i] = new JLabel("");
            dias[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            dias[i].setHorizontalAlignment(SwingConstants.CENTER);
        }

        for (int i = 0; i < 49; i++) {
            dias[i].setOpaque(true);
            calendar.add(dias[i]);
        }

        try {

            BufferedReader auxDateReader = new BufferedReader(new FileReader("src/data/postDatabase.txt"));

            String line = auxDateReader.readLine();
            String classificationDate = line;
            auxDateReader.readLine(); // Leemos el título de la primera
            while (line != null) {

                line = auxDateReader.readLine();// Tomamos la fecha

                String[] date = line.split("-");// La dividimos

                if (today.getYear() == Integer.parseInt(date[2])) {
                    if (today.getMonth().getValue() == Integer.parseInt(date[1])) {// Si es este mes y este año, lo
                                                                                   // ponemos en el calendario

                        for (int i = 7 + firstDayOfCalendar; i < today.lengthOfMonth() + 7 + firstDayOfCalendar; i++) {// Pasamos
                                                                                                                       // por
                                                                                                                       // el
                                                                                                                       // calendario
                                                                                                                       // buscando
                                                                                                                       // el
                                                                                                                       // dia
                            if (Integer.parseInt(dias[i].getText()) == Integer.parseInt(date[0])) {// Si es el buscamos,
                                                                                                   // lo agregamos

                                if (classificationDate.equals("Evento")) {
                                    dias[i].setBackground(IngSocColor.event);
                                    dias[i].setForeground(IngSocColor.white);
                                } else if (classificationDate.equals("Taller")) {
                                    dias[i].setBackground(IngSocColor.taller);
                                    dias[i].setForeground(IngSocColor.white);
                                }

                            }

                        }

                    }
                }

                for (int i = 0; i < 5; i++) {
                    auxDateReader.readLine(); // Leemos todo hasta la siguiente clasificacion
                }
                classificationDate = auxDateReader.readLine();// Agarramos la classificacion
                line = auxDateReader.readLine();// Leemos la siguiente linea, si es null, line sera null, si no,
                                                // simplemente la ignoramos

            }

            auxDateReader.close();

        } catch (IOException e) {
            System.out.println("Error al abrir el archivo desde P\u00E1gina Principal");
        }

    }

    public JButton getCloseSessionButton() {
        return closeSession;
    }

    public JButton getPostButton() {
        return post;
    }

    public JScrollBar getScrollBar() {
        return postScrollBar;
    }

    public static void main(String args[]) {

        System.out.println("Vista Pagina Principal Inicializada");

    }

}