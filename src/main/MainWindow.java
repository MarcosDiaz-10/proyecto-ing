
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import javax.swing.event.ChangeEvent; // Importa la clase ChangeEvent
import java.awt.event.ActionListener; // Importa la clase ActionListener
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent
import java.awt.event.MouseEvent; // Importa la clase MouseEvent
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class MainWindow extends JFrame implements ActionListener, ChangeListener, AdjustmentListener {

    IniciarSesion loginView;
    Registrarse registerView;
    PaginaPrincipal mainPageView;
    CrearPublicacion createPostView;
    IngSocColor ingSocColor;
    ControladorIniciarSesion controladorIniciarSesion;
    ControladorRegistrarse controladorRegistrarse;
    ControladorCrearPublicacion controladorCrearPublicacion;

    public MainWindow() {

        setIconImage(new ImageIcon("zlogo2redondeado.png").getImage());

        ingSocColor = new IngSocColor();

        initializeControllers();
        initializeLoginView();
        initializeRegisterView();
        initializeCreatePostView();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("IngSoc - La guerra es paz, la libertad es esclavitud, la ignorancia es la fuerza.");
        add(loginView, BorderLayout.CENTER);

    }

    public void initializeControllers() {
        controladorIniciarSesion = new ControladorIniciarSesion();
        controladorRegistrarse = new ControladorRegistrarse();
        controladorCrearPublicacion = new ControladorCrearPublicacion();

    }

    public void stateChanged(ChangeEvent evento) {

    }

    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource() == loginView.getRegisterButton()) {
            System.out.println("Yendo a Registrarse");
            remove(loginView);
            add(registerView, BorderLayout.CENTER);
            revalidate();
            repaint();

        } else if (evento.getSource() == loginView.getLoginButton()) {

            // Esta también debería tener un controlador, etc, misma estrategia, ve primero
            // registro para explicarles Mauricio pq ahí hiciste todas las acotaciones
            System.out.println("Iniciando Sesion (Verificando datos)");

            switch (controladorIniciarSesion.validateInformation(loginView)) {
                case 0:// Todo va bien y el usuario inicia sesion
                    JOptionPane.showMessageDialog(this, "Inicio de sesi\u00F3n exitoso", "Inicio de sesi\u00F3n",
                            JOptionPane.INFORMATION_MESSAGE);
                    initializeMainPageView();// Inicializamos la página principal
                    remove(loginView);
                    add(mainPageView, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                    break;
                case 1:// Error en el nombre
                    JOptionPane.showMessageDialog(this, "Tiene que ingresar un nombre ", "Error de Nombre",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case 2:// Error en el apellido
                    JOptionPane.showMessageDialog(this, "Tiene que ingresar un Apellido ", "Error de Apellido",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case 3:// Error en la contrasena
                    JOptionPane.showMessageDialog(this, "Tiene que ingresar una contraseña ", "Error de contraseña ",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case 4:// Error Usuario no encontrado
                    JOptionPane.showMessageDialog(this, "Usuario no encontrado", "Error de usuario",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                default:// Error general
                    JOptionPane.showMessageDialog(this, "Hubo un error. Intente de nuevo luego", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }

        } else if (evento.getSource() == registerView.getRegisterButton()) {

            switch (controladorRegistrarse.saveInformation(registerView)) {
                case 0:// Todo va bien y el usuario se registra
                    JOptionPane.showMessageDialog(this, "Informaci\u00F3n guardada con éxito");
                    System.out.println("Yendo a Iniciar Sesión");
                    remove(registerView);
                    add(loginView, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                    break;
                case 1:// Error en el nombre
                    JOptionPane.showMessageDialog(this, "Tiene que ingresar un nombre ", "Error de Nombre",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case 2:// Error en el apellido
                    JOptionPane.showMessageDialog(this, "Tiene que ingresar un Apellido ", "Error de Apellido",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case 3:// Error en el rol
                    JOptionPane.showMessageDialog(this, "Tiene que ingresar un rol ", "Error de Escuela",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case 4:// Error en la contrasena
                    JOptionPane.showMessageDialog(this, "Tiene que ingresar una contraseña ", "Error de contraseña ",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case 5:// Las contrasenas no coinciden
                    JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden. Intente nuevamente ",
                            "Error de Contraseña", JOptionPane.ERROR_MESSAGE);
                    break;
                default:// Error general
                    JOptionPane.showMessageDialog(this, "Error al guardar la informaci\u00F3n. ", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }

        } else if (evento.getSource() == mainPageView.getCloseSessionButton()) {

            System.out.println("Yendo a Iniciar Sesion");
            remove(mainPageView);
            add(loginView, BorderLayout.CENTER);
            revalidate();
            repaint();

        } else if (evento.getSource() == mainPageView.getPostButton()) {

            System.out.println("Yendo a Crear Publicacion");
            remove(mainPageView);
            add(createPostView, BorderLayout.CENTER);
            revalidate();
            repaint();

        } else if (evento.getSource() == createPostView.getCreatePostButton()) {
            System.out.println("Yendo a Pagina principal");

            switch (controladorCrearPublicacion.saveInformationPublication(createPostView,
                    createPostView.getRutaImg())) {
                case 0:
                    JOptionPane.showMessageDialog(this, "Informaci\u00F3n guardada con éxito");
                    System.out.println("Yendo a Pagina principal");
                    remove(createPostView);
                    add(mainPageView, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                    break;
                case 1:
                    JOptionPane.showMessageDialog(this, "Tiene que ingresar un Titulo",
                            "Error de Titulo", JOptionPane.ERROR_MESSAGE);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(this, "Tiene que ingresar un Tipo De Interés ",
                            "Error de Tipo De Interés", JOptionPane.ERROR_MESSAGE);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(this, "Tiene que ingresar un Tipo De Publicación",
                            "Error de Tipo De publicación", JOptionPane.ERROR_MESSAGE);

                    break;
                case 4:
                    JOptionPane.showMessageDialog(this, "Tiene que ingresar una Description",
                            "Error de Descripción", JOptionPane.ERROR_MESSAGE);
                    break;
                case 5:
                    JOptionPane.showMessageDialog(this, "Tiene que ingresar una Imagen",
                            "Error de Imagen", JOptionPane.ERROR_MESSAGE);
                    break;
                default:// Error general
                    JOptionPane.showMessageDialog(this, "Error al guardar la informaci\u00F3n. ", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }

        }

    }

    public void adjustmentValueChanged(AdjustmentEvent e) {

        if (!mainPageView.getScrollBar().getValueIsAdjusting()) {
            int extent = mainPageView.getScrollBar().getModel().getExtent();
            int maximum = mainPageView.getScrollBar().getModel().getMaximum();
            int value = mainPageView.getScrollBar().getValue();

            if (value + extent >= maximum) {
                System.out.println("Has llegado al final del JScrollPane");
                mainPageView.loadPost();

            }
        }
    }

    public void initializeLoginView() {

        loginView = new IniciarSesion();

        initializeFrameButtons(loginView);

        loginView.getRegisterButton().addActionListener(this);
        loginView.getLoginButton().addActionListener(this);

    }

    public void initializeRegisterView() {

        registerView = new Registrarse();
        initializeFrameButtons(registerView);

        registerView.getRegisterButton().addActionListener(this);

    }

    public void initializeCreatePostView() {
        createPostView = new CrearPublicacion();
        initializeFrameButtons(createPostView);
        createPostView.getCreatePostButton().addActionListener(this);
    }

    private void initializeMainPageView() {

        mainPageView = new PaginaPrincipal();
        initializeFrameButtons(mainPageView);

        mainPageView.getCloseSessionButton().addActionListener(this);
        mainPageView.getPostButton().addActionListener(this);
        mainPageView.getScrollBar().addAdjustmentListener(this);

    }

    private void initializeFrameButtons(GeneralView view) {
        view.getFrame().getMainPage().addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                System.out.println("Ir a P\u00E1gina Principal");
            }
        });
        view.getFrame().getConestPage().addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                System.out.println("Ir a CONEST");
            }
        });
        view.getFrame().getvirtualCampusPage().addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                System.out.println("Ir a Campus Virtual");
            }
        });
    }

    public static void main(String args[]) {

        MainWindow ventanaPrincipal = new MainWindow();

        ventanaPrincipal.setBounds(0, 0, 800, 1000);
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.setLocationRelativeTo(null);

    }

}