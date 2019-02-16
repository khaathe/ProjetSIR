package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import nf.Connexion;
import ui.AcceuilMedecin;
import ui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class essaisConnexion extends JPanel {

    private MainWindow mainWindow;
    private MainWindow secondWindow;
    private JPanel connexionPanel;
    private JPanel headPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JPanel southPanel;
    private JLabel imageCHUPP;
    private JLabel logoH2I;
    private JLabel mdpLabel;
    private JLabel identifiantLabel;
    private JLabel connexionLabel;
    private JTextField identifiantTextField;
    private JPasswordField passwordField;
    private JButton connexionButton;
    private JPanel resultPanel;


    public essaisConnexion(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        connexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                valider();
            }
        });
        identifiantTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyChar() == KeyEvent.VK_ENTER)
                    valider();
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyChar() == KeyEvent.VK_ENTER)
                    valider();
            }
        });
    }

   public void valider() {
        try {
            String id = identifiantTextField.getText();
            String mdp = new String(passwordField.getPassword());
            mainWindow.getSir().connection(id, mdp);
            mainWindow.setIdMed(id);
            mainWindow.setResizable(true);
            this.mainWindow.setContentPane(new AcceuilMedecin(mainWindow).getPanel1());
            this.mainWindow.revalidate();
        } catch (Exception e) {
            e.printStackTrace();
            identifiantTextField.setText("");
            passwordField.setText("");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public JPanel getConnexionPanel() {
        return connexionPanel;
    }

    public JPanel getHeadPanel() {
        return headPanel;
    }

    public JPanel getWestPanel() {
        return westPanel;
    }

    public JPanel getEastPanel() {
        return eastPanel;
    }

    public JPanel getSouthPanel() {
        return southPanel;
    }

    public JLabel getImageCHUPP() {
        return imageCHUPP;
    }

    public JLabel getLogoH2I() {
        return logoH2I;
    }

    public JLabel getMdpLabel() {
        return mdpLabel;
    }

    public JLabel getIdentifiantLabel() {
        return identifiantLabel;
    }

    public JLabel getConnexionLabel() {
        return connexionLabel;
    }

    public JTextField getIdentifiantTextField() {
        return identifiantTextField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getConnexionButton() {
        return connexionButton;
    }

    public JPanel getResultPanel() {
        return resultPanel;
    }
}
