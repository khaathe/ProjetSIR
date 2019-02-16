package  ui;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Image extends JPanel {
    private JPanel mainPanel;
    private JSlider constrastSlider;
    private JSlider EcalircissementSlider;
    private JCheckBox inversionCheckBox;
    private JCheckBox retournementCheckBox;
    private JTextArea annotationTextArea;
    private JButton impressionbutton;
    private JButton validatebutton;
    private JButton annulebutton;
    private JPanel headPanel;
    private JLabel nameDoctorLabel;
    private JLabel iconeDoctorLabel;
    private JLabel nbImageLabel;
    private JPanel westPanel;
    private JLabel contrasteLabel;
    private JLabel eclaircissementLabel;
    private JPanel rotationPanel;
    private JLabel rotationLabel;
    private JLabel rightRotationLabel;
    private JLabel leftRotationLabel;
    private JLabel imageCerveau;
    private MainWindow mainWindow;
    private nf.Image picture;
    private Acceuil acceuil;


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        //    $$$setupUI$$$();
    }

    public Image(MainWindow mainWindow, Acceuil acceuil) {
        this.mainWindow = mainWindow;
        this.acceuil = acceuil;
        nameDoctorLabel.setText("Mr/Mme " + mainWindow.getIdMed());
        getConstrastSlider().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);


            }
        });
        annulebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                retourAcceuil();
            }
        });
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
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        headPanel = new JPanel();
        headPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(headPanel, BorderLayout.NORTH);
        nameDoctorLabel = new JLabel();
        nameDoctorLabel.setText("");
        headPanel.add(nameDoctorLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        iconeDoctorLabel = new JLabel();
        iconeDoctorLabel.setHorizontalAlignment(11);
        iconeDoctorLabel.setHorizontalTextPosition(11);
        iconeDoctorLabel.setIcon(new ImageIcon(getClass().getResource("/icone medecin.png")));
        iconeDoctorLabel.setText("");
        headPanel.add(iconeDoctorLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nbImageLabel = new JLabel();
        nbImageLabel.setText("Numéro d'archivage : ");
        headPanel.add(nbImageLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        westPanel = new JPanel();
        westPanel.setLayout(new GridLayoutManager(8, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(westPanel, BorderLayout.WEST);
        contrasteLabel = new JLabel();
        contrasteLabel.setText("Contraste");
        westPanel.add(contrasteLabel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        constrastSlider = new JSlider();
        westPanel.add(constrastSlider, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eclaircissementLabel = new JLabel();
        eclaircissementLabel.setText("Eclaircissement");
        westPanel.add(eclaircissementLabel, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        EcalircissementSlider = new JSlider();
        westPanel.add(EcalircissementSlider, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rotationPanel = new JPanel();
        rotationPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        westPanel.add(rotationPanel, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rotationLabel = new JLabel();
        rotationLabel.setText("Rotation :\n");
        rotationPanel.add(rotationLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rightRotationLabel = new JLabel();
        rightRotationLabel.setText("");
        rotationPanel.add(rightRotationLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        leftRotationLabel = new JLabel();
        leftRotationLabel.setText("");
        rotationPanel.add(leftRotationLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inversionCheckBox = new JCheckBox();
        inversionCheckBox.setText("Inversion");
        westPanel.add(inversionCheckBox, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        retournementCheckBox = new JCheckBox();
        retournementCheckBox.setText("Retournement");
        westPanel.add(retournementCheckBox, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        annotationTextArea = new JTextArea();
        annotationTextArea.setText("Annotation :");
        westPanel.add(annotationTextArea, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, BorderLayout.SOUTH);
        impressionbutton = new JButton();
        impressionbutton.setText("Imprimer");
        panel1.add(impressionbutton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        validatebutton = new JButton();
        validatebutton.setText("Valider");
        panel1.add(validatebutton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        annulebutton = new JButton();
        annulebutton.setText("Annuler");
        panel1.add(annulebutton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        imageCerveau = new JLabel();
        imageCerveau.setAutoscrolls(true);
        imageCerveau.setDoubleBuffered(false);
        imageCerveau.setEnabled(true);
        imageCerveau.setHorizontalAlignment(0);
        imageCerveau.setHorizontalTextPosition(0);
        imageCerveau.setIcon(new ImageIcon(getClass().getResource("/brainImage.png")));
        imageCerveau.setMaximumSize(new Dimension(200, 250));
        imageCerveau.setMinimumSize(new Dimension(150, 200));
        imageCerveau.setText("");
        mainPanel.add(imageCerveau, BorderLayout.EAST);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    public void retourAcceuil() {
        try {
            this.mainWindow.setContentPane(acceuil.getMainPanel());
            this.mainWindow.revalidate();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problème d'interface");
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JSlider getConstrastSlider() {
        return constrastSlider;
    }

    public void setConstrastSlider(JSlider constrastSlider) {
        this.constrastSlider = constrastSlider;
    }

    public JSlider getEcalircissementSlider() {
        return EcalircissementSlider;
    }

    public void setEcalircissementSlider(JSlider ecalircissementSlider) {
        EcalircissementSlider = ecalircissementSlider;
    }

    public JCheckBox getInversionCheckBox() {
        return inversionCheckBox;
    }

    public void setInversionCheckBox(JCheckBox inversionCheckBox) {
        this.inversionCheckBox = inversionCheckBox;
    }

    public JCheckBox getRetournementCheckBox() {
        return retournementCheckBox;
    }

    public void setRetournementCheckBox(JCheckBox retournementCheckBox) {
        this.retournementCheckBox = retournementCheckBox;
    }

    public JTextArea getAnnotationTextArea() {
        return annotationTextArea;
    }

    public void setAnnotationTextArea(JTextArea annotationTextArea) {
        this.annotationTextArea = annotationTextArea;
    }

    public JButton getImpressionbutton() {
        return impressionbutton;
    }

    public void setImpressionbutton(JButton impressionbutton) {
        this.impressionbutton = impressionbutton;
    }

    public JButton getValidatebutton() {
        return validatebutton;
    }

    public void setValidatebutton(JButton validatebutton) {
        this.validatebutton = validatebutton;
    }

    public JButton getAnnulebutton() {
        return annulebutton;
    }

    public void setAnnulebutton(JButton annulebutton) {
        this.annulebutton = annulebutton;
    }

    public JPanel getHeadPanel() {
        return headPanel;
    }

    public void setHeadPanel(JPanel headPanel) {
        this.headPanel = headPanel;
    }

    public JLabel getNameDoctorLabel() {
        return nameDoctorLabel;
    }

    public void setNameDoctorLabel(JLabel nameDoctorLabel) {
        this.nameDoctorLabel = nameDoctorLabel;
    }

    public JLabel getIconeDoctorLabel() {
        return iconeDoctorLabel;
    }

    public void setIconeDoctorLabel(JLabel iconeDoctorLabel) {
        this.iconeDoctorLabel = iconeDoctorLabel;
    }

    public JLabel getNbImageLabel() {
        return nbImageLabel;
    }

    public void setNbImageLabel(JLabel nbImageLabel) {
        this.nbImageLabel = nbImageLabel;
    }

    public JPanel getWestPanel() {
        return westPanel;
    }

    public void setWestPanel(JPanel westPanel) {
        this.westPanel = westPanel;
    }

    public JLabel getContrasteLabel() {
        return contrasteLabel;
    }

    public void setContrasteLabel(JLabel contrasteLabel) {
        this.contrasteLabel = contrasteLabel;
    }

    public JLabel getEclaircissementLabel() {
        return eclaircissementLabel;
    }

    public void setEclaircissementLabel(JLabel eclaircissementLabel) {
        this.eclaircissementLabel = eclaircissementLabel;
    }

    public JPanel getRotationPanel() {
        return rotationPanel;
    }

    public void setRotationPanel(JPanel rotationPanel) {
        this.rotationPanel = rotationPanel;
    }

    public JLabel getRotationLabel() {
        return rotationLabel;
    }

    public void setRotationLabel(JLabel rotationLabel) {
        this.rotationLabel = rotationLabel;
    }

    public JLabel getRightRotationLabel() {
        return rightRotationLabel;
    }

    public void setRightRotationLabel(JLabel rightRotationLabel) {
        this.rightRotationLabel = rightRotationLabel;
    }

    public JLabel getLeftRotationLabel() {
        return leftRotationLabel;
    }

    public void setLeftRotationLabel(JLabel leftRotationLabel) {
        this.leftRotationLabel = leftRotationLabel;
    }

    public JLabel getImageCerveau() {
        return imageCerveau;
    }

    public void setImageCerveau(JLabel imageCerveau) {
        this.imageCerveau = imageCerveau;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
   /* private void $$$setupUI$$$() {
     //
    //   panel1 = new JPanel();
    //    panel1.setLayout(new BorderLayout(0, 0));
      //  headPanel = new JPanel();
        //headPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
     //   panel1.add(headPanel, BorderLayout.NORTH);
     //   nameDoctorLabel = new JLabel();
     //   nameDoctorLabel.setText("Docteur MARTIN Frédéric");
      //  headPanel.add(nameDoctorLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
      //  iconeDoctorLabel = new JLabel();
      //  iconeDoctorLabel.setHorizontalAlignment(11);
      //  iconeDoctorLabel.setHorizontalTextPosition(11);
      //  iconeDoctorLabel.setIcon(new ImageIcon(getClass().getResource("/ui/iconeDoctor.png")));
    //  iconeDoctorLabel.setText("");
    //  headPanel.add(iconeDoctorLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    //  nbImageLabel = new JLabel();
    //  nbImageLabel.setText("Numéro d'archivage : 11992847");
    //  headPanel.add(nbImageLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    //  westPanel = new JPanel();
    //  westPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 2, new Insets(0, 0, 0, 0), -1, -1));
    //  panel1.add(westPanel, BorderLayout.WEST);
    //  contrasteLabel = new JLabel();
    //  contrasteLabel.setText("Contraste");
    //  westPanel.add(contrasteLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    //  constrastSlider = new JSlider();
    //  westPanel.add(constrastSlider, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    //  eclaircissementLabel = new JLabel();
    //  eclaircissementLabel.setText("Eclaircissement");
    //  westPanel.add(eclaircissementLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    //  EcalircissementSlider = new JSlider();
    //  westPanel.add(EcalircissementSlider, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    //  rotationPanel = new JPanel();
    //  rotationPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
    //  westPanel.add(rotationPanel, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    //  rotationLabel = new JLabel();
    //  rotationLabel.setText("Rotation :\n");
    //  rotationPanel.add(rotationLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    //  rightRotationLabel = new JLabel();
    //  rightRotationLabel.setIcon(new ImageIcon(getClass().getResource("/ui/iconeRotationDroite.png")));
    //  rightRotationLabel.setText("");
    //  rotationPanel.add(rightRotationLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    //  leftRotationLabel = new JLabel();
    //  leftRotationLabel.setIcon(new ImageIcon(getClass().getResource("/ui/iconeRotationGauche.png")));
    //  leftRotationLabel.setText("");
    //  rotationPanel.add(leftRotationLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    //  inversionCheckBox = new JCheckBox();
    //  inversionCheckBox.setText("Inversion");
    //  westPanel.add(inversionCheckBox, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    //  retournementCheckBox = new JCheckBox();
    //  retournementCheckBox.setText("Retournement");
    //  westPanel.add(retournementCheckBox, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    //  annotationTextArea = new JTextArea();
    //  annotationTextArea.setText("Annotation :");
    //  westPanel.add(annotationTextArea, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    //  final JPanel panel2 = new JPanel();
    //  panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
    //  panel1.add(panel2, BorderLayout.SOUTH);
    //  impressionbutton = new JButton();
    //  impressionbutton.setText("Imprimer");
    //  panel2.add(impressionbutton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    //  validatebutton = new JButton();
    //  validatebutton.setText("Valider");
    //  panel2.add(validatebutton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    //  annulebutton = new JButton();
    //  annulebutton.setText("Annuler");
    //  panel2.add(annulebutton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    //  imageCerveau = new JLabel();
    //  imageCerveau.setAutoscrolls(true);
    //  imageCerveau.setDoubleBuffered(false);
    //  imageCerveau.setEnabled(true);
    //  imageCerveau.setHorizontalAlignment(0);
    //  imageCerveau.setHorizontalTextPosition(0);
    //  imageCerveau.setIcon(new ImageIcon(getClass().getResource("/ui/brainImage.png")));
    //  imageCerveau.setMaximumSize(new Dimension(200, 250));
    //  imageCerveau.setMinimumSize(new Dimension(150, 200));
    //  imageCerveau.setText("");
      panel1.add(imageCerveau, BorderLayout.EAST);
      final JPanel panel3 = new JPanel();
      panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
      panel1.add(panel3, BorderLayout.CENTER);
    }*/

}