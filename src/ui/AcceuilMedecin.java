package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import nf.Connexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class AcceuilMedecin extends JPanel {
    public JPanel panel1;
    private JPanel jPanelNorth;
    private JButton jButtonAccesPACS;
    private JButton jButtonRedactionCR;
    private JButton jButtonNumerisation;
    private JButton jButtonListePatient;
    private JLabel jLabelNomMed;
    private JLabel jLabelIconMed;
    private JLabel jLabelDateJr;
    private MainWindow mainWindow;
    private Connexion connexion;
    protected MainWindow secondWindow;

    public AcceuilMedecin(MainWindow mainWindow) {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();

        this.mainWindow = mainWindow;
        connexion = new Connexion();


        jButtonRedactionCR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openRedactionCR();
            }
        });
        jButtonListePatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openListePatients();
            }
        });

    }

    public void openListePatients() {
        try {
            this.mainWindow.setContentPane(new ListePatientAccesMed(mainWindow).getPanel1());
            this.mainWindow.revalidate();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "interface indisponible pour le moment");
        }
    }

    public void openRedactionCR() {
        try {
            System.out.println("peut être que je vais prendre en compte ta demande");
            this.mainWindow.setContentPane(new RedactionCR(mainWindow).getMainPanelRedac());
            this.mainWindow.revalidate();
            System.out.println("et bah non en fait");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "interface indisponible pour le moment");
        }


    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        panel1.setPreferredSize(new Dimension(500, 500));
        jPanelNorth = new JPanel();
        jPanelNorth.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(jPanelNorth, BorderLayout.NORTH);
        jLabelNomMed = new JLabel();
        jLabelNomMed.setText("Dr MARTIN Frédéric");
        jPanelNorth.add(jLabelNomMed, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jLabelIconMed = new JLabel();
        jLabelIconMed.setIcon(new ImageIcon(getClass().getResource("/icone medecin.png")));
        jLabelIconMed.setText("");
        jPanelNorth.add(jLabelIconMed, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jLabelDateJr = new JLabel();
        jLabelDateJr.setText("");
        jPanelNorth.add(jLabelDateJr, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, BorderLayout.CENTER);
        jButtonAccesPACS = new JButton();
        jButtonAccesPACS.setBackground(new Color(-10286840));
        jButtonAccesPACS.setFocusable(false);
        jButtonAccesPACS.setForeground(new Color(-2431508));
        jButtonAccesPACS.setInheritsPopupMenu(false);
        jButtonAccesPACS.setText("Accès PACS");
        panel2.add(jButtonAccesPACS, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jButtonRedactionCR = new JButton();
        jButtonRedactionCR.setBackground(new Color(-13475040));
        jButtonRedactionCR.setForeground(new Color(-2431508));
        jButtonRedactionCR.setText("Rédaction de compte-rendu");
        panel2.add(jButtonRedactionCR, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jButtonNumerisation = new JButton();
        jButtonNumerisation.setBackground(new Color(-13420957));
        jButtonNumerisation.setForeground(new Color(-2431508));
        jButtonNumerisation.setText("Numérisation");
        panel2.add(jButtonNumerisation, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jButtonListePatient = new JButton();
        jButtonListePatient.setBackground(new Color(-6008551));
        jButtonListePatient.setForeground(new Color(-2431508));
        jButtonListePatient.setText("Liste des patients");
        panel2.add(jButtonListePatient, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JButton getjButtonAccesPACS() {
        return jButtonAccesPACS;
    }

    public void setjButtonAccesPACS(JButton jButtonAccesPACS) {
        this.jButtonAccesPACS = jButtonAccesPACS;
    }

    public JButton getjButtonRedactionCR() {
        return jButtonRedactionCR;
    }

    public void setjButtonRedactionCR(JButton jButtonRedactionCR) {
        this.jButtonRedactionCR = jButtonRedactionCR;
    }

    public JButton getjButtonNumerisation() {
        return jButtonNumerisation;
    }

    public void setjButtonNumerisation(JButton jButtonNumerisation) {
        this.jButtonNumerisation = jButtonNumerisation;
    }

    public JButton getjButtonListePatient() {
        return jButtonListePatient;
    }

    public void setjButtonListePatient(JButton jButtonListePatient) {
        this.jButtonListePatient = jButtonListePatient;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public Date getjLabelDateJr() {
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        Date sqlDate = new Date(calendar.getTime().getTime());

        return sqlDate;
    }

    public void setjLabelDateJr(JLabel jLabelDateJr) {
        this.jLabelDateJr = jLabelDateJr;
    }
}
