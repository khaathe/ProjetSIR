package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import nf.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

//import javax.swing.*;
/*import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;*/

public class Accueil extends JPanel implements PropertyChangeListener {
    private JPanel mainPanel;
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
    // private JButton trieButton;
    private JButton numeriserButton;
    private JPanel patientPanel;
    private JPanel infoPatientPanel;
    private JPanel examenPanel;
    private JLabel infoPatientLabel;
    private JTree examTree;
    private JPanel menuPanel;
    private JButton deconnexion;
    private JButton imprimerButton;
    private JTextField searchDMRtextField;
    private JButton searchMagnifierLabel;
    private JLabel searchLabel;
    private JTextArea crTextArea;
    private JPanel crPanel;
    private JPanel closePanel;
    private JButton closeButton;
    private JButton crButton;
    private JLabel crAnnonceLabel;
    private MainWindow mainWindow;
    private HashMap<DefaultMutableTreeNode, Examen> nodeToExam;


    public Accueil(MainWindow mainWindow) throws Exception {
        this.mainWindow = mainWindow;
        examTree = new JTree();
        iconLabel = new JLabel();
        dateLabel = new JLabel();
        list = new JList();
        listModel = new DefaultListModel();


        $$$setupUI$$$();

        initComponent();

        initList();

        initListener();

        initDifferentialAccess();


        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                crPanel.setVisible(false);
            }
        });

    }


    public void initComponent() {
        nameLabel.setText("Mr/Mme " + mainWindow.getSir().getPersonneConnecte().getNom()
                + " " + mainWindow.getSir().getPersonneConnecte().getPrenom()
                + " (" + mainWindow.getSir().getPersonneConnecte().getIdMedical() + ")"
        );

        dateLabel.setText("Date : " + LocalDate.now().toString());

        menuPanel.setVisible(true);
        centrePanel.setVisible(false);
        crPanel.setVisible(false);
        mainWindow.getSir().getHl7().addPropertyChangeListener(this);
    }

    public void initDifferentialAccess() {
        switch (mainWindow.getSir().getPersonneConnecte().getProfession()) {

            case PH:
                admissionButton.setVisible(false);
                searchLabel.setVisible(false);
                searchDMRtextField.setVisible(false);
                searchMagnifierLabel.setVisible(false);
                numeriserButton.setVisible(false);
                iconLabel.setIcon(new ImageIcon("resources/iconeMedecin.png"));
                break;

            case MANIPULATEUR:
                ajoutExamButton.setVisible(false);
                crButton.setVisible(false);
                imprimerButton.setVisible(false);
                iconLabel.setIcon(new ImageIcon("resources/iconeManipulateur.png"));
                searchMagnifierLabel.setIcon(new ImageIcon("resources/searchmagnifierIcon.png"));
                break;

            case SECRETAIRE:
                ajoutExamButton.setVisible(false);
                crButton.setVisible(false);
                numeriserButton.setVisible(false);
                accesImageButton.setVisible(false);
                imprimerButton.setVisible(false);
                iconLabel.setIcon(new ImageIcon("resources/iconeSecretaireMed.png"));
                searchMagnifierLabel.setIcon(new ImageIcon("resources/searchmagnifierIcon.png"));
                break;
        }
        mainWindow.revalidate();
    }

    public void initListener() {

        /*crButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                affichageCR();
            }
        });

        /*this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                rezisePanel();
            }
        });*/

        examTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                affichageCR();
                
            }
        });

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

        list.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyTyped(e);
                displayPatient();
            }
        });


        deconnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deconnection();
            }
        });

        numeriserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numeriser();
            }
        });

        admissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admission();
            }
        });

        imprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimer();
            }
        });

        searchMagnifierLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    nouvelleList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        searchDMRtextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    try {
                        nouvelleList();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

   /* public void rezisePanel() {
        int w = patientPanel.getWidth() - examenPanel.getWidth();
        int h = patientPanel.getHeight() - infoPatientPanel.getHeight();
        examenPanel.setPreferredSize(new Dimension(examTree.getWidth(), h));
        crPanel.setPreferredSize(new Dimension(w, h));
        this.revalidate();
    }*/

    public void nouvelleList() throws Exception {
        listModel.clear();
        for (DMR d : mainWindow.getSir().rechercheDMR(searchDMRtextField.getText())) {
            listModel.addElement(d);
        }
        list.setModel(listModel);
    }

    public void initList() {
        listModel = new DefaultListModel();
        for (DMR d : mainWindow.getSir().getListeDMR()) {
            listModel.addElement(d);
        }
        list.setModel(listModel);
    }

    public void deconnection() {
        int res = JOptionPane.showConfirmDialog(null, "Etes vous sur de vouloir vous deconnecter ?", "Confirmer deconnexion", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try {
                this.mainWindow.getSir().deconnection();
                this.mainWindow.setContentPane(new Authentification(this.mainWindow).getConnexionPanel());
                this.mainWindow.pack();
                this.mainWindow.setResizable(false);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Echec de la deconnection", "Error deconnection", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void openImage() {
        try {
            Examen examen = nodeToExam.get(examTree.getLastSelectedPathComponent());
            if (examen == null)
                throw new NullPointerException("Veuilez choisir un examen");
            else if (examen.getImages().size() == 0)
                throw new Exception("Aucune image pour cet examen");
            this.mainWindow.setContentPane(new VisualisationImage(mainWindow, this, examen.getImages()).getGeneralPanel());
            this.mainWindow.revalidate();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void affichageCR() {

        try {
            DMR dmr = (DMR) list.getSelectedValue();
            String num = dmr.getPatient().getIdPR();
            Examen examen = nodeToExam.get(examTree.getLastSelectedPathComponent());


            if (num.equals(examen.getPatient().getIdPR())) { //essais de récupérer l'idPR du patient selectionné dans la liste de patients, et le compare à celui de l'examen selectionné
                                                            //si idPR égaux, il s'agit du même patient, alors affichage normal. Sinon, au changement de patient, le crPanel doit se
                                                            //réinitialiser pour être vide à l'ouverture du nouveau DMR et non garder en mémoire le CR du dernier exam sélectionné (puisqu'il
                                                            //s'agit même d'un autre patient)
                crTextArea.setText(examen.getCr().getCompteRendu());
                crTextArea.setLineWrap(true);
                crPanel.setVisible(true);
                revalidate();

            } else {
                crPanel.removeAll();
            }

        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(this, "Erreur examen ou CR vide");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    public void openAjouterExam() {
        try {
            mainWindow.setContentPane(new AjoutExamen(mainWindow, this).getGeneralPanel());
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
        List<Examen> listeExamen = null;
        if (dmr.getListeExamen().size() == 0) {
            try {
                listeExamen = mainWindow.getSir().getConn().getExamen(patient);
                dmr.setListeExamen(listeExamen);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            listeExamen = dmr.getListeExamen();
        if (listeExamen.size() > 0)
            buildExameTree(listeExamen);
        else
            getExamTree().setModel(new DefaultTreeModel(new DefaultMutableTreeNode()));
        centrePanel.setVisible(true);
    }

    public void buildExameTree(List<Examen> listeExamen) {
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
        examTree.setRootVisible(false);
        revalidate();
    }

    public void numeriser() {
        try {
            Examen examen = nodeToExam.get(examTree.getLastSelectedPathComponent());
            if (examen == null)
                throw new NullPointerException("Veuilez choisir un examen");
            AbstractImage image = new Numeriseur(examen.getNumArchivage()).run();
            if (image != null) {
                mainWindow.getSir().addImageToExam(examen, image);
            }
            this.mainWindow.revalidate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void imprimer() {
        try {
            Examen examen = nodeToExam.get(examTree.getLastSelectedPathComponent());
            if (examen == null)
                throw new NullPointerException("Veuilez choisir un examen");
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(new ExamenPrinter(examen));
            if (job.printDialog()) {
                job.print();
            }
            this.mainWindow.revalidate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void admission() {
        try {
            mainWindow.getSir().admitPatient();
            initList();
            admissionButton.setForeground(Color.blue);
            mainWindow.getSir().getHl7().ecoute();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            JOptionPane.showMessageDialog(null, "Aucun patient a admettre", "Erreur admission", JOptionPane.ERROR_MESSAGE);
        }
        catch (java.sql.SQLIntegrityConstraintViolationException e){
            JOptionPane.showMessageDialog(null, "Ce patient a déjà été admit", "Erreur admission", JOptionPane.ERROR_MESSAGE);
        }catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur admission", JOptionPane.ERROR_MESSAGE);
        }


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
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setPreferredSize(new Dimension(600, 600));
        northPanel = new JPanel();
        northPanel.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(northPanel, BorderLayout.NORTH);
        nameLabel = new JLabel();
        nameLabel.setText("");
        northPanel.add(nameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        iconLabel = new JLabel();
        iconLabel.setText("");
        northPanel.add(iconLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayoutManager(1, 6, new Insets(0, 0, 0, 0), -1, -1));
        northPanel.add(menuPanel, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        admissionButton = new JButton();
        admissionButton.setLabel("Admission Patient");
        admissionButton.setText("Admission Patient");
        menuPanel.add(admissionButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchLabel = new JLabel();
        searchLabel.setText("Recherche DMR :");
        menuPanel.add(searchLabel, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchDMRtextField = new JTextField();
        menuPanel.add(searchDMRtextField, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchMagnifierLabel = new JButton();
        searchMagnifierLabel.setText("");
        menuPanel.add(searchMagnifierLabel, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        menuPanel.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        menuPanel.add(spacer2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        dateLabel = new JLabel();
        dateLabel.setText("");
        northPanel.add(dateLabel, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deconnexion = new JButton();
        deconnexion.setText("Deconnexion");
        northPanel.add(deconnexion, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        northPanel.add(spacer3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
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
        southPanel.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        centrePanel.add(southPanel, BorderLayout.SOUTH);
        accesImageButton = new JButton();
        accesImageButton.setText("Accès images");
        southPanel.add(accesImageButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ajoutExamButton = new JButton();
        ajoutExamButton.setText("Ajouter un examen");
        southPanel.add(ajoutExamButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numeriserButton = new JButton();
        numeriserButton.setText("Numeriser");
        southPanel.add(numeriserButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        imprimerButton = new JButton();
        imprimerButton.setText("imprimer");
        southPanel.add(imprimerButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        crButton = new JButton();
        crButton.setText("Accès CR");
        southPanel.add(crButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientPanel = new JPanel();
        patientPanel.setLayout(new BorderLayout(0, 0));
        centrePanel.add(patientPanel, BorderLayout.CENTER);
        infoPatientPanel = new JPanel();
        infoPatientPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        patientPanel.add(infoPatientPanel, BorderLayout.NORTH);
        infoPatientLabel = new JLabel();
        infoPatientLabel.setText("");
        infoPatientPanel.add(infoPatientLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 50), null, 0, false));
        examenPanel = new JPanel();
        examenPanel.setLayout(new BorderLayout(0, 0));
        patientPanel.add(examenPanel, BorderLayout.CENTER);
        crPanel = new JPanel();
        crPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        examenPanel.add(crPanel, BorderLayout.EAST);
        closePanel = new JPanel();
        closePanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        crPanel.add(closePanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        closeButton = new JButton();
        closeButton.setText("Fermer");
        closePanel.add(closeButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        closePanel.add(spacer4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        crAnnonceLabel = new JLabel();
        crAnnonceLabel.setText("Compte-rendu de l'examen");
        closePanel.add(crAnnonceLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        crTextArea = new JTextArea();
        crPanel.add(crTextArea, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        examTree = new JTree();
        examTree.setEditable(true);
        examTree.putClientProperty("JTree.lineStyle", "");
        examenPanel.add(examTree, BorderLayout.WEST);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here


    }


    public JTree getExamTree() {
        return examTree;
    }

    public HashMap<DefaultMutableTreeNode, Examen> getNodeToExam() {
        return nodeToExam;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        admissionButton.setForeground(Color.red);
    }
}