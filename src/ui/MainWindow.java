package ui;

import nf.SIR;

import javax.naming.NamingException;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private static String title = "SIR";
    private SIR sir;



    public MainWindow () throws NamingException {
        super(title);
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
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Erreur impossible de vous deconnecter", "Erreur fermeture", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public String getIdMed () {return  sir.getPersonneConnecte().getIdMedical(); }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                MainWindow window = null;
                try {
                    window = new MainWindow();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
                window.setContentPane(new Authentification(window).getConnexionPanel());
                window.pack();
                window.setVisible(true);
                window.setResizable(false);
            }
        });
    }

    public SIR getSir() {
        return sir;
    }

}