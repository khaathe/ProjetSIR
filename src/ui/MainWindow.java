package ui;

import nf.SIR;

import javax.naming.NamingException;
import javax.swing.*;

public class MainWindow extends JFrame {
    private static String title = "SIR";
    private SIR sir;



    public MainWindow () throws NamingException {
        super(title);
        sir = new SIR();
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
                System.out.println("test");
                window.setContentPane(new essaisConnexion(window).getConnexionPanel());
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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