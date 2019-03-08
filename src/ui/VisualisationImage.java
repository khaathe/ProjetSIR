package  ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import nf.AbstractImage;
import nf.Examen;
import nf.Image;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;

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
    private JPanel picturePanel;
    private MainWindow mainWindow;
    private List<AbstractImage> picture;
    private Accueil accueil;
    static final int CONTRASTE_MIN = 1;
    static final int CONTRASTE_MAX = 5;
    static final int CONTRASTE_INIT = 1;
    static final int LUMINOSITE_MIN = -100;
    static final int LUMINOSITE_MAX = 100;
    static final int LUMINOSITE_INIT = 0;
    private ImagePanel imgPanel;
    private Graphics2D g;
    private ImageIcon imageIcon;


    public VisualisationImage(MainWindow mainWindow, Accueil accueil, java.util.List<AbstractImage> picture) {
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

        Examen examSelect = accueil.getNodeToExam().get(accueil.getExamTree().getLastSelectedPathComponent());
        picture = examSelect.getImages();
        nbImageLabel.setText("Numéro d'archivage : " + accueil.getNodeToExam().get(accueil.getExamTree().getLastSelectedPathComponent()).getNumArchivage());

        try {
            imgPanel = new ImagePanel(picture.get(pictureSlider.getValue()).getImage());
            picturePanel.add(imgPanel, BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problème de détection d'image", "Error accès image", JOptionPane.ERROR_MESSAGE);
        }
        revalidate();
    }

    public void initListener() {

        validatebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { //NE FONCTIONNE PAS : PB DE NUMARCHIVAGE, NE VEUT PAS QUE CE SOIT LE MEME
                validation();
            }
        });

        retournementCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                retournement();
            }
        });

        inversionCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                inversion();
            }
        });

        buttonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rotation(actionEvent);
            }
        });

        buttonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rotation(actionEvent);
            }
        });

        ecalircissementSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                eclaircissement();
            }
        });

        pictureSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                pictureChanged();
            }
        });

        constrastSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                contraste();
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
        picturePanel = new JPanel();
        picturePanel.setLayout(new BorderLayout(0, 0));
        mainPanel.add(picturePanel, BorderLayout.CENTER);
        pictureSlider.setPaintLabels(true);
        pictureSlider.setValueIsAdjusting(true);
        picturePanel.add(pictureSlider, BorderLayout.SOUTH);
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
        int i = pictureSlider.getValue();
        if (constrastSlider.getValueIsAdjusting()) {
            try {
                AbstractImage ig = picture.get(i);
                ig.setContraste(constrastSlider.getValue());
                BufferedImage imageModif = ig.getImage();
                imgPanel.setImg(imageModif);
                imgPanel.repaint();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur valeur contraste d'image", "Images introuvables", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void eclaircissement() {
        int i = pictureSlider.getValue();
        if (ecalircissementSlider.getValueIsAdjusting()) {
            try {
                AbstractImage ig = picture.get(i);
                BufferedImage imageModif = ig.getImage();
                ig.setLuminosite(ecalircissementSlider.getValue());
                imgPanel.setImg(imageModif);
                imgPanel.repaint();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur valeur luminosité d'image", "Images introuvables", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void rotation(ActionEvent e) {
        int i = pictureSlider.getValue();
        int rotation = 0;
        if (e.getSource().equals(buttonLeft))
            rotation = Image.ROTATE_RIGHT;
        else if (e.getSource().equals(buttonRight))
            rotation = Image.ROTATE_LEFT;
        try {
            AbstractImage ig = picture.get(i);
            ig.setRotation(rotation);
            BufferedImage imageModif = ig.getImage();
            imgPanel.setImg(imageModif);
            imgPanel.repaint();
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problème de rotation d'image", "Error rotation image", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void inversion() {
        int i = pictureSlider.getValue();
        try {
            AbstractImage ig = picture.get(i);
            ig.setInverser();
            BufferedImage imageModif = ig.getImage();
            imgPanel.setImg(imageModif);
            imgPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problème d'inversement d'image", "Error inversement image", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ajouterAnnotation() {

    }

    public void retournement() {
        int i = pictureSlider.getValue();
        try {
            AbstractImage ig = picture.get(i);
            ig.setRetourner();
            BufferedImage imageModif = ig.getImage();
            imgPanel.setImg(imageModif);
            imgPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problème d'inversement d'image", "Error inversement image", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void pictureChanged() {
        if (pictureSlider.getValueIsAdjusting())
            try {
                int i = pictureSlider.getValue();
                picture.get(i).getImage();
                BufferedImage imageModif = picture.get(i).getImage();
                imgPanel.setImg(imageModif);
                imgPanel.repaint();
                constrastSlider.setValue(picture.get(i).getContraste());


            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Problème de détection d'image", "Images introuvables", JOptionPane.ERROR_MESSAGE);
            }

        constrastSlider.setValue(picture.get(pictureSlider.getValue()).getContraste());
        ecalircissementSlider.setValue(picture.get(pictureSlider.getValue()).getLuminosite());
        inversionCheckBox.setSelected(picture.get(pictureSlider.getValue()).isInverser());
        retournementCheckBox.setSelected(picture.get(pictureSlider.getValue()).isRetourner());
        revalidate();
    }

    public void validation() {
        String annotation = annotationTextArea.getText();
        Image newImg = new Image(picture.get(pictureSlider.getValue()).getNumArchivage());
        newImg.setNumInstance(picture.get(pictureSlider.getValue()).getNumInstance() + 10);
        newImg.setAnnotation(annotation);
        picture.add(newImg);
        constrastSlider.setValue(picture.get(pictureSlider.getValue()).getContraste());
        ecalircissementSlider.setValue(picture.get(pictureSlider.getValue()).getLuminosite());
        inversionCheckBox.setSelected(picture.get(pictureSlider.getValue()).isInverser());
        retournementCheckBox.setSelected(picture.get(pictureSlider.getValue()).isRetourner());
        revalidate();
        try {
            mainWindow.getSir().getConn().insertImage(picture);
        } catch (Exception e) {
            e.printStackTrace();
        }
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