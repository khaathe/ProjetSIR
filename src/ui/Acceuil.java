package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import nf.DMR;
import nf.Examen;
import nf.Patient;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Acceuil extends JPanel {
    public JPanel mainPanel;
    private JList list;
    private DefaultListModel listModel;
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
    private JButton numeriserButton;
    private JPanel patientPanel;
    private JPanel infoPatientPanel;
    private JPanel examenPanel;
    private JLabel infoPatientLabel;
    private JTree examTree;
    private JPanel menuPanel;
    private MainWindow mainWindow;
    private HashMap<DefaultMutableTreeNode, Examen> nodeToExam;
    private String id;


    public Acceuil(MainWindow mainWindow) throws Exception {
        this.mainWindow = mainWindow;

        id = mainWindow.getSir().getPersonneConnecte().getIdMedical();


        list = new JList();
        listModel = new DefaultListModel();
        examTree = new JTree();
        examTree.setRootVisible(false);

        $$$setupUI$$$();


        //model.addElement(new DMR("2", "Robert", "Amandine", new GregorianCalendar(), "17264187463"));


        //nameLabel.setText("Mr/Mme " + mainWindow.getSir().getConn().getPersonnelServiceRadio(id).getNom() + " " + mainWindow.getSir().getConn().getPersonnelServiceRadio(id).getPrenom());
        nameLabel.setText("Mr/Mme " + mainWindow.getIdMed());
        ajoutExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //openAjouterPatient();
            }
        });

        initComponent();


        initList();


        accesImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openImage();
            }
        });
        for (int i = 0; i < mainWindow.getSir().getConn().getListePersonnel().size(); i++) {
            System.out.println(mainWindow.getSir().getConn().getListePersonnel().get(i).getProfession());
        }
        System.out.println(mainWindow.getIdMed());
//        System.out.println(mainWindow.getSir().getConn().getPersonnelServiceRadio(id));

        initListener();

        initDifferentialAccess();
    }


    public void initComponent() {
        nameLabel.setText("Mr/Mme " + mainWindow.getSir().getPersonneConnecte().getNom()
                + " " + mainWindow.getSir().getPersonneConnecte().getPrenom()
                + " (" + mainWindow.getSir().getPersonneConnecte().getIdMedical() + ")"
        );
        menuPanel.setVisible(true);
        centrePanel.setVisible(false);
    }

    public void initDifferentialAccess() {
        switch (mainWindow.getSir().getPersonneConnecte().getProfession()) {
            case PH:
                admissionButton.setVisible(false);
                button2.setVisible(false);
                numeriserButton.setVisible(false);
                break;
            case MANIPULATEUR:
                ajoutExamButton.setVisible(false);
                CRButton.setVisible(false);
                break;
            case SECRETAIRE_MEDICALE:
                ajoutExamButton.setVisible(false);
                CRButton.setVisible(false);
                numeriserButton.setVisible(false);
                accesImageButton.setVisible(false);
                break;
        }
    }

    public void initListener() {
        ajoutExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openAjouterExam();
            }
        });

        accesImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openImage();
            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                displayPatient();
            }
        });

        CRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            openCR();
            }
        });
    }

    public void initList() {
        for (DMR d : mainWindow.getSir().getListeDMR()) {
            listModel.addElement(d);
        }
        list.setModel(listModel);
    }

    /*public void initialize() throws Exception {
        for (int i = 0; i < mainWindow.getSir().getConn().getDMR().size(); i++) {
            model.addElement(mainWindow.getSir().getConn().getDMR().get(i).getPatient().getNom() + " " + mainWindow.getSir().getConn().getDMR().get(i).getPatient().getPrenom());
        }
        list1.setModel(model);
    }*/

    public void openCR() {
        try {
            Examen exam = nodeToExam.get(examTree.getLastSelectedPathComponent());
            if (exam == null) {
                throw new NullPointerException("Veuilez choisir un examen");
            } else {
                JOptionPane.showMessageDialog(this, exam.getCr().toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void openImage() {
        try {
            Examen examen = nodeToExam.get(examTree.getLastSelectedPathComponent());
            if (examen == null)
                throw new NullPointerException("Veuilez choisir un examen");
            else if (examen.getImages().size() == 0)
                throw new Exception("Aucune image pour cet examen");
            this.mainWindow.setContentPane(new Image(mainWindow, this, examen.getImages()).getMainPanel());
            this.mainWindow.revalidate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void openAjouterExam() {
        try {
            this.mainWindow.setContentPane(new AjoutExamen(mainWindow, this).getGeneralPanel());
            this.mainWindow.revalidate();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problème d'interface");
        }
    }

    public void displayPatient() {
        DMR dmr = (DMR) list.getSelectedValue();
        Patient patient = dmr.getPatient();
        infoPatientLabel.setText(patient.toString());
        ArrayList<Examen> listeExamen = null;
        try {
            listeExamen = mainWindow.getSir().getConn().getExamen(patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listeExamen.size() > 0)
            buildExameTree(listeExamen);
        else
            examTree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode()));
        centrePanel.setVisible(true);
    }

    public void buildExameTree(ArrayList<Examen> listeExamen) {
        DefaultMutableTreeNode allExamn = new DefaultMutableTreeNode();
        DefaultTreeModel model = new DefaultTreeModel(allExamn);
        nodeToExam = new HashMap<>();
        for (Examen e : listeExamen) {
            DefaultMutableTreeNode examNode = new DefaultMutableTreeNode(e);
            examNode.add(new DefaultMutableTreeNode("numArchivage : " + e.getNumArchivage()));
            examNode.add(new DefaultMutableTreeNode("type : " + e.getTypeExamen()));
            examNode.add(new DefaultMutableTreeNode("Fait par " + e.getPraticien()));
            examNode.add(new DefaultMutableTreeNode("Service " + e.getService()));
            model.insertNodeInto(examNode, (MutableTreeNode) model.getRoot(), 0);
            nodeToExam.put(examNode, e);
        }
        examTree.setModel(model);
        revalidate();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JList getList() {
        return list;
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
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        northPanel.add(menuPanel, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        admissionButton = new JButton();
        admissionButton.setLabel("Admission Patient");
        admissionButton.setText("Admission Patient");
        menuPanel.add(admissionButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        menuPanel.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        button2 = new JButton();
        button2.setText("Button");
        menuPanel.add(button2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        westPanel = new JPanel();
        westPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(westPanel, BorderLayout.WEST);
        final JScrollPane scrollPane1 = new JScrollPane();
        westPanel.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list = new JList();
        scrollPane1.setViewportView(list);
        centrePanel = new JPanel();
        centrePanel.setLayout(new BorderLayout(0, 0));
        mainPanel.add(centrePanel, BorderLayout.CENTER);
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        centrePanel.add(southPanel, BorderLayout.SOUTH);
        accesImageButton = new JButton();
        accesImageButton.setText("Accès images");
        southPanel.add(accesImageButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CRButton = new JButton();
        CRButton.setText("Accès CR");
        southPanel.add(CRButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ajoutExamButton = new JButton();
        ajoutExamButton.setText("Ajouter un examen");
        southPanel.add(ajoutExamButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numeriserButton = new JButton();
        numeriserButton.setText("Numeriser");
        southPanel.add(numeriserButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        centrePanel.add(scrollPane2, BorderLayout.CENTER);
        patientPanel = new JPanel();
        patientPanel.setLayout(new BorderLayout(0, 0));
        scrollPane2.setViewportView(patientPanel);
        infoPatientPanel = new JPanel();
        infoPatientPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        patientPanel.add(infoPatientPanel, BorderLayout.NORTH);
        infoPatientLabel = new JLabel();
        infoPatientLabel.setText("");
        infoPatientPanel.add(infoPatientLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 50), null, 0, false));
        examenPanel = new JPanel();
        examenPanel.setLayout(new BorderLayout(0, 0));
        patientPanel.add(examenPanel, BorderLayout.CENTER);
        examTree.setEditable(false);
        examTree.setEnabled(true);
        examenPanel.add(examTree, BorderLayout.CENTER);
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
