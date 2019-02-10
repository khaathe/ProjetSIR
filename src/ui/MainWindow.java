package ui;

import javax.swing.*;
import java.awt.*;
import nf.*;


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

                /*
                JFrame frame = new JFrame(title);

                AcceuilMedecin a = new AcceuilMedecin();



                frame.setContentPane(a);
                frame.setPreferredSize(new Dimension(1000, 1000));
                frame.pack();

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

*/

                MainWindow mainWindow = new MainWindow();
                //AcceuilMedecin panel = new AcceuilMedecin();
//ModificationImage panel = new ModificationImage();

                //mainWindow.add(panel);
                mainWindow.setContentPane(new AcceuilMedecin());
                mainWindow.setPreferredSize(new Dimension(1000, 1000));
                mainWindow.pack();


                mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainWindow.setVisible(true);

                System.out.println(mainWindow.getContentPane());

               // System.out.println(MainWindow.getContentPane());*/

        }});

    }

}

    /*MainWindow mainWindow = new MainWindow();
    AcceuilMedecin panel = new AcceuilMedecin();
//ModificationImage panel = new ModificationImage();


        mainWindow.setContentPane((Container) panel);
                mainWindow.setPreferredSize(new Dimension(1000, 1000));
                mainWindow.pack();


                mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainWindow.setVisible(true);

                System.out.println(mainWindow.getContentPane());
                */