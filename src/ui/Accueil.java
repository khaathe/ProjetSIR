package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import nf.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Accueil extends JPanel implements PropertyChangeListener {
    private  static String errorExamen = "Veuillez choisir un examen";

    private JPanel mainPanel;
    private JList list;


    private DefaultListModel listModel;


    private JButton ajoutExamButton;
    private JButton accesImageButton;
    private JButton admissionButton;
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel centrePanel;

    private JLabel nameLabel;
    private JLabel iconLabel;
    private JLabel dateLabel;
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
    private JPanel southPanel;
    private JButton resetButton;
    private MainWindow mainWindow;
    private HashMap<DefaultMutableTreeNode, Examen> nodeToExam;


    public Accueil(MainWindow mainWindow) {
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
    }


    public void initComponent() {
        nameLabel.setText("Mr/Mme " + mainWindow.getSir().getPersonneConnecte().getNom()
                + " " + mainWindow.getSir().getPersonneConnecte().getPrenom()
                + " (" + mainWindow.getSir().getPersonneConnecte().getIdMedical() + ")"
        );

        dateLabel.setText("Date : " + LocalDate.now().toString());

        examTree.setRootVisible(false);
        DefaultTreeCellRenderer treeCellRenderer = new DefaultTreeCellRenderer();
        treeCellRenderer.setClosedIcon(null);
        treeCellRenderer.setOpenIcon(null);
        treeCellRenderer.setLeafIcon(null);
        examTree.setCellRenderer(treeCellRenderer);

        menuPanel.setVisible(true);
        centrePanel.setVisible(false);
        crPanel.setVisible(false);
        southPanel.setVisible(false);
        mainWindow.getSir().getHl7().addPropertyChangeListener(this);
    }

    public void initDifferentialAccess() {
        switch (mainWindow.getSir().getPersonneConnecte().getProfession()) {
            case PH:
                admissionButton.setVisible(false);
                numeriserButton.setVisible(false);
                iconLabel.setIcon(new ImageIcon("resources/iconeMedecin.png"));
                break;

            case MANIPULATEUR:
                ajoutExamButton.setVisible(false);
                imprimerButton.setVisible(false);
                iconLabel.setIcon(new ImageIcon("resources/iconeManipulateur.png"));
                break;

            case SECRETAIRE:
                ajoutExamButton.setVisible(false);
                numeriserButton.setVisible(false);
                accesImageButton.setVisible(false);
                imprimerButton.setVisible(false);
                iconLabel.setIcon(new ImageIcon("resources/iconeSecretaireMed.png"));
                break;

            default:
                break;
        }
        mainWindow.revalidate();
    }

    public void initListener() {

        examTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                affichageCR();

            }
        });

        ajoutExamButton.addActionListener( actionEvent -> openAjouterExam() );

        accesImageButton.addActionListener( actionEvent -> openImage() );

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


        deconnexion.addActionListener( actionEvent -> deconnection() );

        numeriserButton.addActionListener( actionEvent -> numeriser() );

        admissionButton.addActionListener( actionEvent -> admission() );

        imprimerButton.addActionListener( actionEvent -> imprimer() );

        searchMagnifierLabel.addActionListener( actionEvent ->
             {
                try {
                    nouvelleList();
                } catch (Exception e) {
                    Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
                }
            }
        );
        searchDMRtextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    nouvelleList();
                }
            }
        });

        closeButton.addActionListener( actionEvent -> crPanel.setVisible(false) );

        resetButton.addActionListener( actionEvent -> initList() );

    }

    public void nouvelleList() {
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
                Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
                JOptionPane.showMessageDialog(null, "Echec de la deconnection", "Error deconnection", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void openImage() {
        try {
            Examen examen = nodeToExam.get(examTree.getLastSelectedPathComponent());
            if (examen == null)
                throw new NullPointerException(errorExamen);
            if (examen.getImages().size() == 0) {
                List<AbstractImage> listeImage = mainWindow.getSir().getConn().getImage(examen.getNumArchivage());
                if (listeImage.isEmpty())
                    throw new NullPointerException("Aucune image pour cet examen");
                examen.setImages(listeImage);
            }
            this.mainWindow.setContentPane(new VisualisationImage(mainWindow, this, examen.getImages()).getGeneralPanel());
            this.mainWindow.revalidate();
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void affichageCR() {
        try {
            if (mainWindow.getSir().getPersonneConnecte().getProfession() == Profession.PH) {
                Examen examen = nodeToExam.get(examTree.getLastSelectedPathComponent());
                crTextArea.setText(examen.getCr().getCompteRendu());
                crTextArea.setLineWrap(true);
                crPanel.setVisible(true);
                revalidate();
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
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
            JOptionPane.showMessageDialog(null, "Problème d'interface");
        }
    }

    public void displayPatient() {
        crPanel.setVisible(false);
        southPanel.setVisible(true);
        DMR dmr = (DMR) list.getSelectedValue();
        Patient patient = dmr.getPatient();
        infoPatientLabel.setText(patient.toString());
        List<Examen> listeExamen = null;
        try {
            if (dmr.getListeExamen().size() == 0) {
                listeExamen = mainWindow.getSir().getConn().getExamen(patient);
                dmr.setListeExamen(listeExamen);
            } else
                listeExamen = dmr.getListeExamen();
            if ( !listeExamen.isEmpty())
                buildExameTree(listeExamen);
            else
                getExamTree().setModel(new DefaultTreeModel(new DefaultMutableTreeNode()));
        } catch (NullPointerException npe) {
            Logger.getAnonymousLogger().log(Level.WARNING, npe.getMessage());
            JOptionPane.showMessageDialog(null, "Aucun examen trouve");
            getExamTree().setModel(new DefaultTreeModel(new DefaultMutableTreeNode()));
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
            getExamTree().setModel(new DefaultTreeModel(new DefaultMutableTreeNode()));
        }
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
        revalidate();
    }

    public void numeriser() {
        try {
            Examen examen = nodeToExam.get(examTree.getLastSelectedPathComponent());
            if (examen == null)
                throw new NullPointerException(errorExamen);
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
                throw new NullPointerException(errorExamen);
            if (examen.getImages().size() == 0)
                examen.setImages(mainWindow.getSir().getConn().getImage(examen.getNumArchivage()));
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(new ExamenPrinter(examen));
            if (job.printDialog()) {
                job.print();
            }
            this.mainWindow.revalidate();
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
            JOptionPane.showMessageDialog(this, "Un probleme est apparu lors de l'impression");
        }
    }

    public void admission() {
        String errorAdmin = "Erreur admission";
        try {
            mainWindow.getSir().admitPatient();
            initList();
            admissionButton.setBackground( new Color(68, 140, 255));
            mainWindow.getSir().getHl7().ecoute();
        } catch (NullPointerException npe) {
            Logger.getAnonymousLogger().log(Level.WARNING, npe.getMessage());
            JOptionPane.showMessageDialog(null, "Aucun patient a admettre", errorAdmin, JOptionPane.ERROR_MESSAGE);
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
            JOptionPane.showMessageDialog(null, "Ce patient a déjà été admit", errorAdmin, JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), errorAdmin, JOptionPane.ERROR_MESSAGE);
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

    public JTree getExamTree() {
        return examTree;
    }

    public Map<DefaultMutableTreeNode, Examen> getNodeToExam() {
        return nodeToExam;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        admissionButton.setBackground(Color.red);
    }
}