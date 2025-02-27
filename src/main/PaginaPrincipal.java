import javax.swing.*;
import javax.swing.SpringLayout.Constraints;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
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
import java.io.BufferedReader;




public class PaginaPrincipal extends GeneralView{

    private JButton closeSession,post;
    private JPanel internalView,calendar;
    private JPanel postFeed;
    private JScrollPane postScroll; 
    private JScrollBar postScrollBar;
    //Necesitaremos una clase VistaPublicación o algo, con un JPanel sea como tal lo que se muestra a partir de la entidad Publicación
    //Estas VistaPublicación estarían en el postScroll que debería de funcionar e ir bajando, y no sé si ver
    private BufferedReader readerIndex;
    private ArrayList<PostFrame> postCascade; 


    public PaginaPrincipal(){

        initializeComponents();

        internalView.setLayout(null);

        addComponents();

    }

    

    private void initializeComponents(){

        internalView = new JPanel();

        closeSession = new JButton("Cerrar Sesi\u00F3n");
        closeSession.setBackground(IngSocColor.black);
        closeSession.setForeground(IngSocColor.white);
        closeSession.setBounds(70,630,160,45);

        postFeed = new JPanel();
        postFeed.setLayout(new GridBagLayout());
        postScroll = new JScrollPane(postFeed);


        postCascade = new ArrayList<PostFrame>();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,0,50,0);

        try {
            readerIndex = new BufferedReader(new FileReader("postDatabase.txt"));
            postCascade = new ArrayList<PostFrame>();


            PostFrame aux = new PostFrame(readerIndex);
            postCascade.add(aux);
            postFeed.add(aux,constraints);


        } catch (IOException e) {
            System.out.println("Error al abrir el archivo desde Pagina Principal");
        }

        /*for(int i=0;i<2;i++){

            PostFrame aux = new PostFrame(String.valueOf(i));
            postCascade.add(aux);
            constraints.gridy = i;
            postFeed.add(aux,constraints);

        }*/


        //Esto es sólo temporal para probar hoy, si funciona, a la principal también sin duda
        postScrollBar = postScroll.getVerticalScrollBar();
        postScrollBar.setUnitIncrement(20);//Para la rueda del ratón
        postScrollBar.setBlockIncrement(20);//Para las flechitas de arriba/abajo

        
        postScrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (!postScrollBar.getValueIsAdjusting()) {
                    int extent = postScrollBar.getModel().getExtent();
                    int maximum = postScrollBar.getModel().getMaximum();
                    int value = postScrollBar.getValue();

                    if (value + extent >= maximum) {
                        System.out.println("Has llegado al final del JScrollPane");
                        /*
                        for(int i=0;i<2;i++){

                            PostFrame aux = new PostFrame(String.valueOf(postCascade.size()));
                            constraints.gridy = postCascade.size();
                            constraints.gridx=0;
                            postCascade.add(aux);
                            postFeed.add(aux,constraints);
                
                        }*/

                    }
                }
            }
        });

        //MAS O MENOS AQUI  TERMINAN


        postFeed.setBounds(0,0,100,100);
        postScroll.setBounds(300,20,450,660);
        //postScroll.setBorder(null); CON ESTO LE PODEMOS QUITAR EL BORDE PERO POR AHORA PARA SABER DONDE ESTA

        calendar = new JPanel();
        calendar.setOpaque(false);
        calendar.setBackground(new Color(100,100,100));
        calendar.setBounds(50,20,200,200);
        calendar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        calendar.add(new JLabel("Placeholder"));

        post = new JButton("Publicar");
        post.setBackground(IngSocColor.black);
        post.setForeground(IngSocColor.white);
        post.setBounds(70,400,160,45);






    }

    private void addComponents(){

        internalView.add(closeSession);
        internalView.add(postScroll);
        internalView.add(calendar);
        internalView.add(post);


        add(internalView);

    }

    public JButton getCloseSessionButton(){
        return closeSession;
    }

    public JButton getPostButton(){
        return post;
    }

    public static void main(String args[]){

        System.out.println("Vista Pagina Principal Inicializada");

    }

}