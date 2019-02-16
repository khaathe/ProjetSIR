package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PACS extends JPanel {
    private JPanel generalPanel;
    private JButton jLabelAcceuil;
    private JButton rédactionCompteRenduButton;
    private JButton listeDesPatientsButton;
    private JButton accesPACSButton;
    private JButton jButtonNumerisation;
    private JTextField textField2;
    private JTextField textField1;
    private JLabel jLabelPACS;
    private JLabel jLabelRecherche;
    private JLabel jLabelDateJr;
    private JLabel jLabelNomMed;
    private JButton OKButton;
    private JButton OKButton1;
    private MainWindow mainWindow;

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
        generalPanel = new JPanel();
        generalPanel.setLayout(new BorderLayout(0, 0));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        generalPanel.add(panel1, BorderLayout.NORTH);
        jLabelNomMed = new JLabel();
        jLabelNomMed.setText("Dr MARTIN Frédéric");
        panel1.add(jLabelNomMed, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon(getClass().getResource("/icone medecin.png")));
        label1.setText("");
        panel1.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jLabelDateJr = new JLabel();
        jLabelDateJr.setText("07/02/2019");
        panel1.add(jLabelDateJr, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        generalPanel.add(panel2, BorderLayout.WEST);
        jLabelAcceuil = new JButton();
        jLabelAcceuil.setText("Acceuil");
        panel2.add(jLabelAcceuil, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rédactionCompteRenduButton = new JButton();
        rédactionCompteRenduButton.setText("Rédaction compte-rendu");
        panel2.add(rédactionCompteRenduButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        listeDesPatientsButton = new JButton();
        listeDesPatientsButton.setText("Liste des patients");
        panel2.add(listeDesPatientsButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accesPACSButton = new JButton();
        accesPACSButton.setText("Accès PACS");
        panel2.add(accesPACSButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jButtonNumerisation = new JButton();
        jButtonNumerisation.setText("Numérisation");
        panel2.add(jButtonNumerisation, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(5, 10, new Insets(0, 0, 0, 0), -1, -1));
        generalPanel.add(panel3, BorderLayout.EAST);
        jLabelPACS = new JLabel();
        jLabelPACS.setText("PACS");
        panel3.add(jLabelPACS, new GridConstraints(0, 0, 1, 6, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jLabelRecherche = new JLabel();
        jLabelRecherche.setText("Rechercher une image ou un dossier");
        panel3.add(jLabelRecherche, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Numéro d'achivage");
        panel3.add(label2, new GridConstraints(2, 0, 2, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Numéro identifiant patient");
        panel3.add(label3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        panel3.add(textField2, new GridConstraints(4, 1, 1, 8, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField1 = new JTextField();
        panel3.add(textField1, new GridConstraints(2, 3, 2, 6, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setIcon(new ImageIcon(getClass().getResource("/logoPACS.png")));
        label4.setText("");
        panel3.add(label4, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        OKButton = new JButton();
        OKButton.setText("OK");
        panel3.add(OKButton, new GridConstraints(4, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        OKButton1 = new JButton();
        OKButton1.setText("OK");
        panel3.add(OKButton1, new GridConstraints(2, 9, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return generalPanel;
    }

    public PACS(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        jLabelAcceuil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                retourAcceuil();
            }
        });
    }

    public void retourAcceuil() {
        try {
            this.mainWindow.setContentPane(new AcceuilMedecin(mainWindow).panel1);
            this.mainWindow.revalidate();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "retour indisponible pour le moment");
        }
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

    public JPanel getGeneralPanel() {
        return generalPanel;
    }

    public void setGeneralPanel(JPanel generalPanel) {
        this.generalPanel = generalPanel;
    }

    public JButton getjLabelAcceuil() {
        return jLabelAcceuil;
    }

    public void setjLabelAcceuil(JButton jLabelAcceuil) {
        this.jLabelAcceuil = jLabelAcceuil;
    }

    public JButton getRédactionCompteRenduButton() {
        return rédactionCompteRenduButton;
    }

    public void setRédactionCompteRenduButton(JButton rédactionCompteRenduButton) {
        this.rédactionCompteRenduButton = rédactionCompteRenduButton;
    }

    public JButton getListeDesPatientsButton() {
        return listeDesPatientsButton;
    }

    public void setListeDesPatientsButton(JButton listeDesPatientsButton) {
        this.listeDesPatientsButton = listeDesPatientsButton;
    }

    public JButton getAccesPACSButton() {
        return accesPACSButton;
    }

    public void setAccesPACSButton(JButton accesPACSButton) {
        this.accesPACSButton = accesPACSButton;
    }

    public JButton getjButtonNumerisation() {
        return jButtonNumerisation;
    }

    public void setjButtonNumerisation(JButton jButtonNumerisation) {
        this.jButtonNumerisation = jButtonNumerisation;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JLabel getjLabelPACS() {
        return jLabelPACS;
    }

    public void setjLabelPACS(JLabel jLabelPACS) {
        this.jLabelPACS = jLabelPACS;
    }

    public JLabel getjLabelRecherche() {
        return jLabelRecherche;
    }

    public void setjLabelRecherche(JLabel jLabelRecherche) {
        this.jLabelRecherche = jLabelRecherche;
    }

    public JLabel getjLabelDateJr() {
        return jLabelDateJr;
    }

    public void setjLabelDateJr(JLabel jLabelDateJr) {
        this.jLabelDateJr = jLabelDateJr;
    }

    public JLabel getjLabelNomMed() {
        return jLabelNomMed;
    }

    public void setjLabelNomMed(JLabel jLabelNomMed) {
        this.jLabelNomMed = jLabelNomMed;
    }

    public JButton getOKButton() {
        return OKButton;
    }

    public void setOKButton(JButton OKButton) {
        this.OKButton = OKButton;
    }

    public JButton getOKButton1() {
        return OKButton1;
    }

    public void setOKButton1(JButton OKButton1) {
        this.OKButton1 = OKButton1;
    }
}
