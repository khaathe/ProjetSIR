package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import nf.Connexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        connexionPanel = new JPanel();
        connexionPanel.setLayout(new BorderLayout(0, 0));
        connexionPanel.setPreferredSize(new Dimension(600, 600));
        headPanel = new JPanel();
        headPanel.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        headPanel.setMaximumSize(new Dimension(400, 200));
        headPanel.setMinimumSize(new Dimension(400, 200));
        headPanel.setPreferredSize(new Dimension(400, 200));
        connexionPanel.add(headPanel, BorderLayout.NORTH);
        connexionLabel = new JLabel();
        connexionLabel.setAutoscrolls(false);
        connexionLabel.setFocusCycleRoot(false);
        connexionLabel.setForeground(new Color(-4188137));
        connexionLabel.setText("Connexion");
        connexionLabel.setVerticalTextPosition(0);
        headPanel.add(connexionLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        logoH2I = new JLabel();
        logoH2I.setAlignmentX(0.5f);
        logoH2I.setIcon(new ImageIcon(getClass().getResource("/CopieLogo.png")));
        logoH2I.setText("");
        headPanel.add(logoH2I, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        imageCHUPP = new JLabel();
        imageCHUPP.setIcon(new ImageIcon(getClass().getResource("/chupp.png")));
        imageCHUPP.setInheritsPopupMenu(true);
        imageCHUPP.setText("");
        headPanel.add(imageCHUPP, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        westPanel = new JPanel();
        westPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        connexionPanel.add(westPanel, BorderLayout.WEST);
        identifiantLabel = new JLabel();
        identifiantLabel.setText("Identifiant :");
        westPanel.add(identifiantLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mdpLabel = new JLabel();
        mdpLabel.setText("Mot de passe :");
        westPanel.add(mdpLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eastPanel = new JPanel();
        eastPanel.setLayout(new BorderLayout(0, 0));
        connexionPanel.add(eastPanel, BorderLayout.SOUTH);
        connexionButton = new JButton();
        connexionButton.setHorizontalAlignment(0);
        connexionButton.setHorizontalTextPosition(0);
        connexionButton.setText("Connexion");
        eastPanel.add(connexionButton, BorderLayout.EAST);
        resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        eastPanel.add(resultPanel, BorderLayout.CENTER);
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        connexionPanel.add(southPanel, BorderLayout.EAST);
        identifiantTextField = new JTextField();
        identifiantTextField.setColumns(0);
        southPanel.add(identifiantTextField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwordField = new JPasswordField();
        southPanel.add(passwordField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return connexionPanel;
    }

}
