package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import nf.Connexion;
import nf.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.GregorianCalendar;

public class Acceuil extends JPanel {
    public JPanel mainPanel;
    private JList<String> list1 = new JList<>();
    private JButton ajoutExamButton;
    private JButton accesImageButton;
    private JButton CRButton;
    private JButton admissionButton;
    private JButton button5;
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel southPanel;
    private JPanel centrePanel;
    private JLabel nameLabel;
    private JLabel iconLabel;
    private JLabel dateLabel;
    private JButton button2;
    private MainWindow mainWindow;
    private Connexion connexion;
    private DefaultListModel<String> model = new DefaultListModel<>();
    private String id;
    private String nom;
    private String prenom;
    private GregorianCalendar daten;
    private String numss;
    private essaisConnexion ec;
    // private SIR sir;


    public Acceuil(MainWindow mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        id = "";


        // sir = new SIR();
        //connexion = new Connexion();
        $$$setupUI$$$();
        list1.setModel(model);
        //model.addElement(new DMR("2", "Robert", "Amandine", new GregorianCalendar(), "17264187463"));
        for (int i = 0; i < mainWindow.getSir().getListeDMR().size(); i++) {
            model.addElement(mainWindow.getSir().getListeDMR().get(i).getPatient().getNom() + " " + mainWindow.getSir().getListeDMR().get(i).getPatient().getPrenom());
        }

        //nameLabel.setText("Mr/Mme " + mainWindow.getSir().getConn().getPersonnelServiceRadio(id).getNom() + " " + mainWindow.getSir().getConn().getPersonnelServiceRadio(id).getPrenom());
         nameLabel.setText(mainWindow.getIdMed());
        ajoutExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openAjouterPatient();
            }
        });


        accesImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openImage();
            }
        });
    }

    public void openImage() {
        try {
            this.mainWindow.setContentPane(new Image(mainWindow).getMainPanel());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problème d'interface");
        }
    }

    public void openAjouterPatient() {
        try {
            this.mainWindow.setContentPane(new AjoutExamen(mainWindow).getGeneralPanel());
            this.mainWindow.revalidate();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problème d'interface");
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JList getList1() {
        return list1;
    }

    public void setList1(JList list1) {
        this.list1 = list1;

    }

    public JButton getAjoutExamButton() {
        return ajoutExamButton;
    }

    public void setAjoutExamButton(JButton ajoutExamButton) {
        this.ajoutExamButton = ajoutExamButton;
    }

    public JButton getAccesImageButton() {
        return accesImageButton;
    }

    public void setAccesImageButton(JButton accesImageButton) {
        this.accesImageButton = accesImageButton;
    }

    public JButton getCRButton() {
        return CRButton;
    }

    public void setCRButton(JButton CRButton) {
        this.CRButton = CRButton;
    }

    public JButton getAdmissionButton() {
        return admissionButton;
    }

    public void setAdmissionButton(JButton admissionButton) {
        this.admissionButton = admissionButton;
    }

    public JButton getButton5() {
        return button5;
    }

    public void setButton5(JButton button5) {
        this.button5 = button5;
    }

    public JPanel getNorthPanel() {
        return northPanel;
    }

    public void setNorthPanel(JPanel northPanel) {
        this.northPanel = northPanel;
    }

    public JPanel getWestPanel() {
        return westPanel;
    }

    public void setWestPanel(JPanel westPanel) {
        this.westPanel = westPanel;
    }

    public JPanel getSouthPanel() {
        return southPanel;
    }

    public void setSouthPanel(JPanel southPanel) {
        this.southPanel = southPanel;
    }

    public JPanel getCentrePanel() {
        return centrePanel;
    }

    public void setCentrePanel(JPanel centrePanel) {
        this.centrePanel = centrePanel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        /*try {
            //this.nameLabel = mainWindow.getSir().getConn().getPersonnelServiceRadio(ec.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public JLabel getIconLabel() {
        return iconLabel;
    }

    public void setIconLabel(JLabel iconLabel) {
        this.iconLabel = iconLabel;
    }

    public JLabel getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(JLabel dateLabel) {
        this.dateLabel = dateLabel;
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
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setPreferredSize(new Dimension(600, 600));
        northPanel = new JPanel();
        northPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(northPanel, BorderLayout.NORTH);
        nameLabel = new JLabel();
        nameLabel.setText("");
        northPanel.add(nameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon(getClass().getResource("/icone medecin.png")));
        iconLabel.setText("");
        northPanel.add(iconLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        northPanel.add(dateLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        northPanel.add(panel1, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        admissionButton = new JButton();
        admissionButton.setLabel("Admission Patient");
        admissionButton.setText("Admission Patient");
        panel1.add(admissionButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        button2 = new JButton();
        button2.setText("Button");
        panel1.add(button2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        westPanel = new JPanel();
        westPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(westPanel, BorderLayout.WEST);
        list1 = new Patient();
        westPanel.add(list1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        accesImageButton = new JButton();
        accesImageButton.setText("Accès images");
        southPanel.add(accesImageButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CRButton = new JButton();
        CRButton.setText("Accès CR");
        southPanel.add(CRButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ajoutExamButton = new JButton();
        ajoutExamButton.setText("Ajouter un examen");
        southPanel.add(ajoutExamButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        centrePanel = new JPanel();
        centrePanel.setLayout(new BorderLayout(0, 0));
        mainPanel.add(centrePanel, BorderLayout.CENTER);
        final JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon(getClass().getResource("/PrincetonHealthCenterpicture.png")));
        label1.setPreferredSize(new Dimension(400, 400));
        label1.setText("");
        centrePanel.add(label1, BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        dateLabel = new JLabel();
        dateLabel.setText("Date : " + LocalDate.now().toString());
    }
}
