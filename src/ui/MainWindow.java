package ui;

import nf.SIR;
import ui.view.essaisConnexion;

import javax.swing.*;

public class MainWindow extends JFrame {
    private static String title = "SIR";
    private SIR sir;




    public MainWindow (){
        super(title);
        sir = new SIR();


}

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                MainWindow window = new MainWindow();
                System.out.println("test");
                window.setContentPane(new essaisConnexion(window).getConnexionPanel());
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.pack();
                window.setVisible(true);


            }
        });

    }

}