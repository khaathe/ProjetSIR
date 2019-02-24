package  ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import nf.Examen;
import nf.Image;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class VisualisationImage extends JPanel {
    private JPanel mainPanel;
    private JSlider constrastSlider;
    private JSlider ecalircissementSlider;
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
    private JLabel annotationLabel;
    private JLabel dateLabel;
    private JButton buttonRight;
    private JButton buttonLeft;
    private JSlider pictureSlider;
    private JLabel pictureLabel;
    private JLabel progressionContrasteLabel;
    private MainWindow mainWindow;
    private ArrayList<Image> picture;
    private Accueil accueil;
    static final int CONTRASTE_MIN = 1;
    static final int CONTRASTE_MAX = 50;
    static final int CONTRASTE_INIT = 1;
    static final int LUMINOSITE_MIN = -100;
    static final int LUMINOSITE_MAX = 100;
    static final int LUMINOSITE_INIT = 0;
    private ImagePanel imgPanel;
    private Graphics2D g;
    private ImageIcon imageIcon;


    public VisualisationImage(MainWindow mainWindow, Accueil accueil, ArrayList<Image> picture) {
        this.mainWindow = mainWindow;
        this.accueil = accueil;
        this.picture = picture;
        $$$setupUI$$$();
        initComponent();

        initListener();
        initDifferentialAccess();


        annotationTextArea.addComponentListener(new ComponentAdapter() {
        });
    }


    public void initDifferentialAccess() {
        switch (mainWindow.getSir().getPersonneConnecte().getProfession()) {

            case PH:
                iconeDoctorLabel.setIcon(new ImageIcon("resources/iconeMedecin.png"));
                break;

            case MANIPULATEUR:
                iconeDoctorLabel.setIcon(new ImageIcon("resources/iconeManipulateur.png"));
                break;
        }
        mainWindow.revalidate();
    }


    public void initComponent() {
        nameDoctorLabel.setText("Mr/Mme " + mainWindow.getSir().getPersonneConnecte().getNom()
                + " " + mainWindow.getSir().getPersonneConnecte().getPrenom()
                + " (" + mainWindow.getSir().getPersonneConnecte().getIdMedical() + ")"
        );
        dateLabel.setText("Date : " + LocalDate.now().toString());
        //progressionContrasteLabel.setText("...");
        //progressionContrasteLabel.setVisible(true);

        Examen examSelect = accueil.getNodeToExam().get(accueil.getExamTree().getLastSelectedPathComponent());
        picture = examSelect.getImages();
        nbImageLabel.setText("Numéro d'archivage : " + accueil.getNodeToExam().get(accueil.getExamTree().getLastSelectedPathComponent()).getNumArchivage());


        // pictureLabel.setIcon(new ImageIcon("C:\\Users\\amanr\\Pictures\\brainImage.png"));

        int i = 0;

        try {
            //imgPanel = new ImagePanel();
            imageIcon = new ImageIcon(new ImageIcon(picture.get(i).getImage()).getImage().getScaledInstance(300, 400, java.awt.Image.SCALE_DEFAULT));
            pictureLabel.setIcon(imageIcon);


            /*imgPanel = new ImagePanel(picture.get(i).getImage());
            pictureLabel.setIcon(new ImageIcon(imgPanel.getImg()));*/


            //pictureLabel.setIcon(new ImageIcon(picture.get(i).getImage()));
            // imgPanel.paintComponent(g);
            //imgPanel.getImg();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problème de détection d'image", "Error accès image", JOptionPane.ERROR_MESSAGE);
        }


        // new ImageIcon( new ImagePanel(examSelect.getImages().get(i).getImage()));


    }

    public void initListener() {

        retournementCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int i = pictureSlider.getValue();
                try {

                    //picture.get(i).rotation(picture.get(i).getImage());
                    Image ig = picture.get(i);
                    BufferedImage imageModif = ig.retournementHorizontal(ig.getImage());
                    ig.setRetourner();
                    imageIcon = new ImageIcon(new ImageIcon(imageModif).getImage().getScaledInstance(300, 400, java.awt.Image.SCALE_DEFAULT));
                    pictureLabel.setIcon(imageIcon);
                    //imgPanel.setImg(imageModif);
                    //pictureLabel.setIcon(new ImageIcon(imgPanel.getImg()));
                    pictureLabel.repaint();

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Problème d'inversement d'image", "Error inversement image", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        inversionCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int i = pictureSlider.getValue();
                try {

                    //picture.get(i).rotation(picture.get(i).getImage());
                    Image ig = picture.get(i);
                    BufferedImage imageModif = ig.inversion(ig.getImage());
                    ig.setInverser();

                    imageIcon = new ImageIcon(new ImageIcon(imageModif).getImage().getScaledInstance(300, 400, java.awt.Image.SCALE_DEFAULT));
                    pictureLabel.setIcon(imageIcon);
                    //imgPanel.setImg(imageModif);
                    //pictureLabel.setIcon(new ImageIcon(imgPanel.getImg()));
                    pictureLabel.repaint();

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Problème d'inversement d'image", "Error inversement image", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int i = pictureSlider.getValue();
                try {
                    //picture.get(i).setRotation(Image.ROTATE_RIGHT);
                    //picture.get(i).rotation(picture.get(i).getImage());
                    Image ig = picture.get(i);
                    ig.setRotation(Image.ROTATE_RIGHT);
                    BufferedImage imageModif = ig.rotation(ig.getImage());

                    imageIcon = new ImageIcon(new ImageIcon(imageModif).getImage().getScaledInstance(300, 400, java.awt.Image.SCALE_DEFAULT));
                    pictureLabel.setIcon(imageIcon);

                    //imgPanel.setImg(imageModif);
                    //pictureLabel.setIcon(new ImageIcon(imageModif));
                    pictureLabel.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Problème de rotation d'image", "Error rotation image", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        buttonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int i = pictureSlider.getValue();
                try {
                    //picture.get(i).setRotation(Image.ROTATE_RIGHT);
                    //picture.get(i).rotation(picture.get(i).getImage());
                    Image ig = picture.get(i);
                    ig.setRotation(Image.ROTATE_LEFT);
                    BufferedImage imageModif = ig.rotation(ig.getImage());

                    imageIcon = new ImageIcon(new ImageIcon(imageModif).getImage().getScaledInstance(300, 400, java.awt.Image.SCALE_DEFAULT));
                    pictureLabel.setIcon(imageIcon);

                    // imgPanel.setImg(imageModif);
                    // pictureLabel.setIcon(new ImageIcon(imgPanel.getImg()));
                    pictureLabel.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Problème de rotation d'image", "Error rotation image", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        constrastSlider.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);


            }
        });

        ecalircissementSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                int i = pictureSlider.getValue();
                int vlrLum = ((JSlider) changeEvent.getSource()).getValue();
                if (ecalircissementSlider.getValueIsAdjusting()) {
                    try {
                        Image ig = picture.get(i);

                        BufferedImage imageModif = ig.eclaircissement(ig.getImage());
                        ig.setLuminosite(vlrLum);

                        imageIcon = new ImageIcon(new ImageIcon(imageModif).getImage().getScaledInstance(300, 400, java.awt.Image.SCALE_DEFAULT));
                        pictureLabel.setIcon(imageIcon);
                        //imgPanel.setImg(imageModif);
                        //pictureLabel.setIcon(new ImageIcon(imgPanel.getImg()));
                        pictureLabel.repaint();

                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erreur valeur luminosité d'image", "Images introuvables", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        pictureSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                //int i = ((JSlider) changeEvent.getSource()).getValue();
                JSlider slider = (JSlider) changeEvent.getSource();
                if (slider.getValueIsAdjusting())
                    try {
                        int i = slider.getValue();
                        picture.get(i).getImage();
                        BufferedImage imageModif = picture.get(i).getImage();

                        imageIcon = new ImageIcon(new ImageIcon(imageModif).getImage().getScaledInstance(300, 400, java.awt.Image.SCALE_DEFAULT));
                        pictureLabel.setIcon(imageIcon);

                        //imgPanel.setImg(imageModif);
                        //pictureLabel.setIcon(new ImageIcon(imageModif));
                        pictureLabel.repaint();

                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Problème de détection d'image", "Images introuvables", JOptionPane.ERROR_MESSAGE);
                    }
            }

        });


        constrastSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                int i = pictureSlider.getValue();
                // progressionContrasteLabel.setText(String.valueOf(((JSlider) changeEvent.getSource()).getValue()));

                JSlider slider = (JSlider) changeEvent.getSource();
                if (slider.getValueIsAdjusting()) {
                    try {
                        Image ig = picture.get(i);
                        ig.setContraste(slider.getValue());
                        BufferedImage imageModif = ig.contraste(ig.getImage());
//                        imgPanel.setImg(imageModif);
                        //pictureLabel.setIcon(new ImageIcon(imageModif));

                        imageIcon = new ImageIcon(new ImageIcon(imageModif).getImage().getScaledInstance(300, 400, java.awt.Image.SCALE_DEFAULT));
                        pictureLabel.setIcon(imageIcon);
                        pictureLabel.repaint();

                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erreur valeur contraste d'image", "Images introuvables", JOptionPane.ERROR_MESSAGE);
                    }
                }


                /*BufferedImage imageModif = picture.get(i).getImage();
                imgPanel = new ImagePanel(imageModif);

                pictureLabel.setIcon(new ImageIcon(imgPanel.getImg()));
                pictureLabel.repaint();*/
            }
        });


        annulebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                retourAccueil();
            }
        });
    }


    public void changementContraste() {
        int i = 0;
        picture.get(i).setContraste(constrastSlider.getValue());

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
        headPanel = new JPanel();
        headPanel.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(headPanel, BorderLayout.NORTH);
        nameDoctorLabel = new JLabel();
        nameDoctorLabel.setText("");
        headPanel.add(nameDoctorLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        iconeDoctorLabel = new JLabel();
        iconeDoctorLabel.setHorizontalAlignment(11);
        iconeDoctorLabel.setHorizontalTextPosition(11);
        iconeDoctorLabel.setIcon(new ImageIcon(getClass().getResource("/iconeMedecin.png")));
        iconeDoctorLabel.setText("");
        headPanel.add(iconeDoctorLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nbImageLabel = new JLabel();
        nbImageLabel.setText("Numéro d'archivage : ");
        headPanel.add(nbImageLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dateLabel = new JLabel();
        dateLabel.setText("Date : ");
        headPanel.add(dateLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        westPanel = new JPanel();
        westPanel.setLayout(new GridLayoutManager(10, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(westPanel, BorderLayout.WEST);
        contrasteLabel = new JLabel();
        contrasteLabel.setText("Contraste");
        westPanel.add(contrasteLabel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        constrastSlider.setMaximum(5);
        constrastSlider.setMinimum(1);
        constrastSlider.setPaintLabels(true);
        constrastSlider.setPaintTicks(true);
        constrastSlider.setValue(1);
        constrastSlider.setValueIsAdjusting(true);
        westPanel.add(constrastSlider, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eclaircissementLabel = new JLabel();
        eclaircissementLabel.setText("Eclaircissement");
        westPanel.add(eclaircissementLabel, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ecalircissementSlider.setDoubleBuffered(false);
        ecalircissementSlider.setInheritsPopupMenu(false);
        ecalircissementSlider.setInverted(false);
        ecalircissementSlider.setMinimum(-100);
        ecalircissementSlider.setOpaque(true);
        ecalircissementSlider.setPaintLabels(true);
        ecalircissementSlider.setPaintTicks(true);
        ecalircissementSlider.setSnapToTicks(false);
        ecalircissementSlider.setValue(0);
        ecalircissementSlider.setValueIsAdjusting(true);
        ecalircissementSlider.putClientProperty("JSlider.isFilled", Boolean.FALSE);
        ecalircissementSlider.putClientProperty("Slider.paintThumbArrowShape", Boolean.FALSE);
        ecalircissementSlider.putClientProperty("html.disable", Boolean.FALSE);
        westPanel.add(ecalircissementSlider, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rotationPanel = new JPanel();
        rotationPanel.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        westPanel.add(rotationPanel, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rotationLabel = new JLabel();
        rotationLabel.setText("Rotation :\n");
        rotationPanel.add(rotationLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rightRotationLabel = new JLabel();
        rightRotationLabel.setText("");
        rotationPanel.add(rightRotationLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        leftRotationLabel = new JLabel();
        leftRotationLabel.setText("");
        rotationPanel.add(leftRotationLabel, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonRight = new JButton();
        buttonRight.setIcon(new ImageIcon(getClass().getResource("/iconeRotationDroite.png")));
        buttonRight.setText("");
        rotationPanel.add(buttonRight, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonLeft = new JButton();
        buttonLeft.setIcon(new ImageIcon(getClass().getResource("/iconeRotationGauche.png")));
        buttonLeft.setInheritsPopupMenu(true);
        buttonLeft.setText("");
        rotationPanel.add(buttonLeft, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inversionCheckBox = new JCheckBox();
        inversionCheckBox.setText("Inversion");
        westPanel.add(inversionCheckBox, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        retournementCheckBox = new JCheckBox();
        retournementCheckBox.setText("Retournement");
        westPanel.add(retournementCheckBox, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        annotationTextArea = new JTextArea();
        annotationTextArea.setText("");
        westPanel.add(annotationTextArea, new GridConstraints(9, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        annotationLabel = new JLabel();
        annotationLabel.setText("Annotation :");
        westPanel.add(annotationLabel, new GridConstraints(8, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        progressionContrasteLabel = new JLabel();
        progressionContrasteLabel.setText("");
        westPanel.add(progressionContrasteLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel2, BorderLayout.CENTER);
        pictureLabel = new JLabel();
        pictureLabel.setText("");
        panel2.add(pictureLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pictureSlider.setPaintLabels(true);
        pictureSlider.setValueIsAdjusting(true);
        panel2.add(pictureSlider, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    public void retourAccueil() {
        try {
            this.mainWindow.setContentPane(accueil.getMainPanel());
            this.mainWindow.revalidate();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problème d'interface");
        }
    }

    public void refresh() {

    }

    public void contraste() {

    }

    public void eclaircissement() {

    }

    public void rotation(ActionEvent e) {

    }

    public void inversion() {

    }

    public void retournement() {

    }

    public void ajouterAnnotation() {

    }

    public JPanel getGeneralPanel() {
        return this.mainPanel;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here

        pictureSlider = new JSlider(0, picture.size() - 1, 0);
        Hashtable<Integer, JLabel> positionCursorPctr = new Hashtable<Integer, JLabel>();
        positionCursorPctr.put(0, new JLabel("1"));
        //position.put(CONTRASTE_MAX / 2, new JLabel("2,5"));
        positionCursorPctr.put(picture.size() - 1, new JLabel(String.valueOf(picture.size())));
        pictureSlider.setLabelTable(positionCursorPctr);
        pictureSlider.setPaintLabels(true);
        pictureSlider.setMajorTickSpacing(10);
        pictureSlider.setMinorTickSpacing(1);
        pictureSlider.setPaintTicks(true);


        constrastSlider = new JSlider(CONTRASTE_MIN, CONTRASTE_MAX, CONTRASTE_INIT);
        Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
        position.put(CONTRASTE_MIN, new JLabel("0"));
        //position.put(CONTRASTE_MAX / 2, new JLabel("2,5"));
        position.put(CONTRASTE_MAX - 1, new JLabel("5"));
        constrastSlider.setLabelTable(position);
        constrastSlider.setPaintLabels(true);
        constrastSlider.setMajorTickSpacing(10);
        constrastSlider.setMinorTickSpacing(1);
        constrastSlider.setPaintTicks(true);

        ecalircissementSlider = new JSlider(LUMINOSITE_MIN, LUMINOSITE_MAX, LUMINOSITE_INIT);

        Hashtable<Integer, JLabel> positionCursor = new Hashtable<Integer, JLabel>();
        positionCursor.put(LUMINOSITE_MIN, new JLabel("-100"));
        positionCursor.put(LUMINOSITE_MIN / 2, new JLabel("-50"));
        positionCursor.put(LUMINOSITE_INIT, new JLabel("0"));
        positionCursor.put(LUMINOSITE_MAX / 2, new JLabel("50"));
        positionCursor.put(LUMINOSITE_MAX, new JLabel("100"));
        ecalircissementSlider.setLabelTable(positionCursor);
        ecalircissementSlider.setPaintLabels(true);

        ecalircissementSlider.setMajorTickSpacing(10);
        ecalircissementSlider.setMinorTickSpacing(1);
        ecalircissementSlider.setPaintTicks(true);
    }
}
