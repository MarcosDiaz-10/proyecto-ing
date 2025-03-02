
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import javax.swing.event.ChangeEvent; // Importa la clase ChangeEvent
import java.awt.event.ActionListener; // Importa la clase ActionListener
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
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
    IngSocColor ingSocColor;

    public MainWindow() {


        setIconImage(new ImageIcon("zlogo2redondeado.png").getImage());

        ingSocColor = new IngSocColor();

        initializeLoginView();
        initializeRegisterView();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("IngSoc - La guerra es paz, la libertad es esclavitud, la ignorancia es la fuerza.");
        add(loginView,BorderLayout.CENTER); 

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

            //Esta también debería tener un controlador, etc, misma estrategia, ve primero registro para explicarles Mauricio pq ahí hiciste todas las acotaciones
            System.out.println("Iniciando Sesion (Verificando datos)");
            if(loginView.validateInformation()){
                initializeMainPageView();//Inicializamos la página principal
                remove(loginView);
                add(mainPageView,BorderLayout.CENTER);
                revalidate();
                repaint();
            }


        } else if (evento.getSource() == registerView.getRegisterButton()) {

            //De la misma forma, sin embargo, el método saveInformation debería ser parte de una clase tipo controlador, pues es, él a fin de cuentas, quién se encarga de esa lógica
            //La vista es sólo la vista, no debería tener dentro de sí el método para manejar eso, creo
            
            if(registerView.saveInformation()){
                System.out.println("Yendo a Iniciar Sesión");
                remove(registerView);
                add(loginView, BorderLayout.CENTER);
                revalidate();
                repaint();
            }

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