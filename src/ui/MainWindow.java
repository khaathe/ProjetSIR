package ui;

import nf.SIR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {


    public static final Dimension MIN_DIM = new Dimension(900, 100);

    private static String titleMainWindow = "SIR";
    private SIR sir;



    public MainWindow () {
        super(titleMainWindow);
        sir = new SIR();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int res = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter le logiciel ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    try {
                        sir.deconnection();
                        System.exit(0);
                    } catch (NullPointerException e1){
                        System.exit(0);
                    }
                    catch (Exception e2) {
                        JOptionPane.showMessageDialog(null, "Erreur impossible de vous deconnecter", "Erreur fermeture", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public String getIdMed () {return  sir.getPersonneConnecte().getIdMedical(); }

    public static void main(String[] args){
        SwingUtilities.invokeLater( () -> {
                MainWindow  window = new MainWindow();
                window.setContentPane(new Authentification(window).getConnexionPanel());
                window.setMinimumSize(MIN_DIM);
                window.pack();
                window.setVisible(true);
                window.setResizable(false);
            }
        );
    }

    public SIR getSir() {
        return sir;
    }

}