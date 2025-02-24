
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent; // Importa la clase ChangeEvent
import java.awt.event.ActionListener; // Importa la clase ActionListener
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent; // Importa la clase ActionEvent
import java.awt.event.MouseEvent; // Importa la clase MouseEvent

public class MainWindow extends JFrame implements ActionListener, ChangeListener {

    IniciarSesion loginView;
    Registrarse registerView;
    PaginaPrincipal mainPageView;
    IngSocColor ingSocColor;

    public MainWindow() {

        ingSocColor = new IngSocColor();

        initializeLoginView();
        initializeRegisterView();
        initializeMainPageView();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("eyy");
        // add(loginView,BorderLayout.CENTER);

        // Debería empezar con loginView pero como no está implementado aún no podemos
        // pasar de login a mainpage, pero tenía que probar main page, por eso esta
        // linea es sólo temporal
        add(mainPageView, BorderLayout.CENTER);

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

            System.out.println("Iniciar Sesion");

        } else if (evento.getSource() == registerView.getRegisterButton()) {

            System.out.println("Yendo a Registrarse");
            remove(registerView);
            add(loginView, BorderLayout.CENTER);
            revalidate();
            repaint();

        } else if (evento.getSource() == mainPageView.getCloseSessionButton()) {

            System.out.println("Yendo a Iniciar Sesion");
            remove(mainPageView);
            add(loginView, BorderLayout.CENTER);
            revalidate();
            repaint();

        } else if (evento.getSource() == mainPageView.getPostButton()) {

            System.out.println("Yendo a Crear Publicacion");

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

    private void initializeMainPageView() {

        mainPageView = new PaginaPrincipal();
        initializeFrameButtons(mainPageView);

        mainPageView.getCloseSessionButton().addActionListener(this);
        mainPageView.getPostButton().addActionListener(this);

    }

    private void initializeFrameButtons(GeneralView view) {
        view.getFrame().getMainPage().addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                System.out.println("Ir a Pagina Principal");
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