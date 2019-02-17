package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import nf.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class AjoutExamen extends JPanel {
    private JPanel generalPanel;
    private JButton annulerButton;
    private JButton ajouterButton;
    private JButton choisirImageButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JTextArea ajoutDExamenTextArea;
    private JTextArea crArea;
    private JLabel ajoutPatientLabel;
    private JLabel imageLabel;
    private JButton dateButton;
    private JLabel dateLabel;
    private JLabel crLabel;
    private JButton validateTypeExamButton;
    private JButton validateServiceButton;
    private MainWindow mainWindow;
    private Patient patient;
    private Examen examen;
    private DefaultComboBoxModel<TypeExamen> model;
    private DefaultComboBoxModel<ServiceHosp> model2;
    GregorianCalendar cal = new GregorianCalendar();
    private CompteRendu cr;
    private ArrayList<Image> li;
    private String numArchiv;
    private String idPersonnel;
    private String idPR;
    private PersonnelServiceRadio ps;
    private TypeExamen tp;
    private ServiceHosp sh;


    public AjoutExamen(MainWindow mainWindow) {
        this.mainWindow = mainWindow;


        li = null;
        numArchiv = "957635";
        //idPersonnel= "0917983967";
        idPR = "08719859265";
        Patient patient = new Patient(idPR);

        //examen.setNumArchivage(numArchiv);

        ps = new PersonnelServiceRadio(mainWindow.getIdMed(), "Robert", "Amandine", Profession.PH);
        //examen.setPraticien(ps);
        model = new DefaultComboBoxModel();
        comboBox4.setModel(model);
        remplissageComboTypeExam();
        // tp = (TypeExamen) comboBox4.getSelectedItem();


        model2 = new DefaultComboBoxModel();
        comboBox5.setModel(model2);
        remplissageComboService();
        //sh = (ServiceHosp) comboBox5.getSelectedItem();


        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                retourAcceuil();
            }
        });


        crArea.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                String contenu = crArea.getText();
                //cr.setCompteRendu(contenu);
                //cr.setIdExam("984368");
                // examen.setIdExam(cr.getIdExam());
                cr = new CompteRendu(contenu, "75630202");

            }
        });


        creationExam();
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    //mainWindow.getSir().getConn().addCompteRendu(examen, cr, ps);
                    mainWindow.getSir().getConn().addExamen(examen, patient);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "erreur");
                }
            }
        });

        //essai pour choisir un type d'examen, mais fait planter l'ajout d'examen à cause d'un conflit avec la méthode de Connexion
        /*comboBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                comboBox4 = (JComboBox) actionEvent.getSource();
                tp = (TypeExamen) comboBox4.getSelectedItem();

            }
        });*/
        dateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dateLabel = new JLabel();
                dateLabel.setText("Date : " + LocalDate.now().toString());
                crArea.setText(crArea.getText() + dateLabel);
            }
        });
        validateTypeExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // tp = TypeExamen.ANGIOGRAPHIE;
                if (comboBox4.getSelectedItem().equals("ANGIOGRAPHIE")) {
                    tp = TypeExamen.ANGIOGRAPHIE;
                }
                if (comboBox4.getSelectedItem().equals("ECHOENDOSCOPIE")) {
                    tp = TypeExamen.ECHOENDOSCOPIE;
                }
                if (comboBox4.getSelectedItem().equals("ECHOGRAPHIE")) {
                    tp = TypeExamen.ECHOGRAPHIE;
                }
                if (comboBox4.getSelectedItem().equals("MAMMOGRAPHIE")) {
                    tp = TypeExamen.MAMMOGRAPHIE;
                }
                if (comboBox4.getSelectedItem().equals("RADIOGRAPHIE")) {
                    tp = TypeExamen.RADIOGRAPHIE;
                }
            }
        });
        validateServiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });


        comboBox5.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                sh = (ServiceHosp) comboBox5.getSelectedItem();
            }
        });
    }


    public void creationExam() {
        examen = new Examen("75630202", cal, numArchiv, idPR, tp, ps, sh);
    }

    public void remplissageComboTypeExam() {

        model.addElement(TypeExamen.ANGIOGRAPHIE);
        model.addElement(TypeExamen.ECHOENDOSCOPIE);
        model.addElement(TypeExamen.ECHOGRAPHIE);
        model.addElement(TypeExamen.MAMMOGRAPHIE);
        model.addElement(TypeExamen.RADIOGRAPHIE);
    }


    public void remplissageComboService() {

        model2.addElement(ServiceHosp.CARDIOLOGIE);
        model2.addElement(ServiceHosp.DERMATOLOGIE);
        model2.addElement(ServiceHosp.NEUROLOGIE);
        model2.addElement(ServiceHosp.PNEUMOLOGIE);
        model2.addElement(ServiceHosp.PSYCHIATRIE);
        model2.addElement(ServiceHosp.UROLOGIE);
        model2.addElement(ServiceHosp.GENICO_OBSTETRIE);
    }


    public void retourAcceuil() {
        try {
            this.mainWindow.setContentPane(new Acceuil(mainWindow).getMainPanel());
            this.mainWindow.revalidate();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problème d'interface");
        }
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
        generalPanel = new JPanel();
        generalPanel.setLayout(new BorderLayout(0, 0));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        generalPanel.add(panel1, BorderLayout.SOUTH);
        annulerButton = new JButton();
        annulerButton.setText("Annuler");
        panel1.add(annulerButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        ajouterButton = new JButton();
        ajouterButton.setText("Ajouter");
        panel1.add(ajouterButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ajoutPatientLabel = new JLabel();
        ajoutPatientLabel.setText("Ajout d'examen");
        generalPanel.add(ajoutPatientLabel, BorderLayout.NORTH);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        generalPanel.add(panel2, BorderLayout.CENTER);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        panel2.add(panel3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        crLabel = new JLabel();
        crLabel.setText("Compte-rendu");
        panel3.add(crLabel, BorderLayout.WEST);
        ajoutDExamenTextArea = new JTextArea();
        panel3.add(ajoutDExamenTextArea, BorderLayout.EAST);
        crArea = new JTextArea();
        panel3.add(crArea, BorderLayout.CENTER);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel2.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        imageLabel = new JLabel();
        imageLabel.setText("Image :");
        panel4.add(imageLabel);
        choisirImageButton = new JButton();
        choisirImageButton.setText("Choisir image");
        panel4.add(choisirImageButton);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel2.add(panel5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Service hospitalier : ");
        panel5.add(label1);
        comboBox5 = new JComboBox();
        panel5.add(comboBox5);
        validateServiceButton = new JButton();
        validateServiceButton.setText("OK");
        panel5.add(validateServiceButton);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel2.add(panel6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Type d'examen : ");
        panel6.add(label2);
        comboBox4 = new JComboBox();
        panel6.add(comboBox4);
        validateTypeExamButton = new JButton();
        validateTypeExamButton.setText("OK");
        panel6.add(validateTypeExamButton);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel7, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        dateButton = new JButton();
        dateButton.setText("Ajouter la date");
        panel7.add(dateButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dateLabel = new JLabel();
        dateLabel.setText("Date");
        panel7.add(dateLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return generalPanel;
    }

    public JPanel getGeneralPanel() {
        return generalPanel;
    }

    public void setGeneralPanel(JPanel generalPanel) {
        this.generalPanel = generalPanel;
    }

    public JButton getAnnulerButton() {
        return annulerButton;
    }

    public void setAnnulerButton(JButton annulerButton) {
        this.annulerButton = annulerButton;
    }

    public JButton getAjouterButton() {
        return ajouterButton;
    }

    public void setAjouterButton(JButton ajouterButton) {
        this.ajouterButton = ajouterButton;
    }

    public JButton getChoisirImageButton() {
        return choisirImageButton;
    }

    public void setChoisirImageButton(JButton choisirImageButton) {
        this.choisirImageButton = choisirImageButton;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public void setComboBox1(JComboBox comboBox1) {
        this.comboBox1 = comboBox1;
    }

    public JComboBox getComboBox2() {
        return comboBox2;
    }

    public void setComboBox2(JComboBox comboBox2) {
        this.comboBox2 = comboBox2;
    }

    public JComboBox getComboBox3() {
        return comboBox3;
    }

    public void setComboBox3(JComboBox comboBox3) {
        this.comboBox3 = comboBox3;
    }

    public JComboBox getComboBox4() {
        return comboBox4;
    }

    public void setComboBox4(JComboBox comboBox4) {
        this.comboBox4 = comboBox4;
    }

    public JComboBox getComboBox5() {
        return comboBox5;
    }

    public void setComboBox5(JComboBox comboBox5) {
        this.comboBox5 = comboBox5;
    }

    public JTextArea getAjoutDExamenTextArea() {
        return ajoutDExamenTextArea;
    }

    public void setAjoutDExamenTextArea(JTextArea ajoutDExamenTextArea) {
        this.ajoutDExamenTextArea = ajoutDExamenTextArea;
    }

    public JTextArea getCrArea() {
        return crArea;
    }

    public void setCrArea(JTextArea crArea) {
        this.crArea = crArea;
    }

    public JLabel getAjoutPatientLabel() {
        return ajoutPatientLabel;
    }

    public void setAjoutPatientLabel(JLabel ajoutPatientLabel) {
        this.ajoutPatientLabel = ajoutPatientLabel;
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }

    public void setImageLabel(JLabel imageLabel) {
        this.imageLabel = imageLabel;
    }
}
