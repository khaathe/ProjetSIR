package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import nf.AbstractImage;
import nf.Examen;
import nf.Image;
import nf.Profession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisualisationImage extends JPanel {
    private JPanel mainPanel;
    private JSlider constrastSlider;
    private JSlider ecalircissementSlider;
    private JCheckBox inversionCheckBox;
    private JCheckBox retournementCheckBox;
    private JTextArea annotationTextArea;
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
    private JPanel actionPanel;
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
    private int rotation;

    /**
     * Constructeur de la classe initialisant les composants de l'interface, les listener, et l'accas differentiel
     * @param mainWindow
     * Le panel qui contiendra le contenu de la fenetre
     * @param accueil
     * Permet d'afficher les images de l'examen selectionne dans l'interface accueil qui precede
     * @param picture
     * Liste d'images qui seront affichees dans l'interface
     */
    public VisualisationImage(MainWindow mainWindow, Accueil accueil, List<AbstractImage> picture) {
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

/**
 * Initialise l'acces differentiel pour le PH et le manipulateur
 * Ne modifie que l'image
 */
    public void initDifferentialAccess() {
        Profession profession = mainWindow.getSir().getPersonneConnecte().getProfession();
        if (profession == Profession.PH)
                iconeDoctorLabel.setIcon(new ImageIcon("resources/iconeMedecin.png"));
        else if (profession == Profession.MANIPULATEUR)
            iconeDoctorLabel.setIcon(new ImageIcon("resources/iconeManipulateur.png"));
        mainWindow.revalidate();
    }


    /**
     * Initialise tous les composants de la fenetre
     * Identite de l'utilisateur connecte, date courante, Numero d'archivage de l'examen selectionne
     * Renvoie la premiere image de la liste
     */
    public void initComponent() {
        rotation = AbstractImage.NO_ROTATE;
        nameDoctorLabel.setText("Mr/Mme " + mainWindow.getSir().getPersonneConnecte().getNom()
                + " " + mainWindow.getSir().getPersonneConnecte().getPrenom()
                + " (" + mainWindow.getSir().getPersonneConnecte().getIdMedical() + ")"
        );
        dateLabel.setText("Date : " + LocalDate.now().toString());

        Examen examSelect = accueil.getNodeToExam().get(accueil.getExamTree().getLastSelectedPathComponent());
        picture = examSelect.getImages();
        nbImageLabel.setText("Numero d'archivage : " + accueil.getNodeToExam().get(accueil.getExamTree().getLastSelectedPathComponent()).getNumArchivage());

        imgPanel = new ImagePanel(picture.get(pictureSlider.getValue()).getImage());
        picturePanel.add(imgPanel, BorderLayout.CENTER);
        String annotation = picture.get(pictureSlider.getValue()).getAnnotation();
        annotationTextArea.setText(annotation);
        revalidate();
    }

    /**
     * Reunis l'initialisation de tous les listeners
     */
    public void initListener() {

        validatebutton.addActionListener(actionEvent -> validation());

        retournementCheckBox.addActionListener(actionEvent -> retournement());

        inversionCheckBox.addActionListener(actionEvent -> inversion());

        buttonRight.addActionListener(actionEvent -> rotation(actionEvent));

        buttonLeft.addActionListener(actionEvent -> rotation(actionEvent));

        ecalircissementSlider.addChangeListener(changeEvent -> eclaircissement());

        pictureSlider.addChangeListener(changeEvent -> pictureChanged());

        constrastSlider.addChangeListener(changeEvent -> contraste());

        annulebutton.addActionListener(actionEvent -> retourAccueil());
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
        nbImageLabel.setText("Numero d'archivage : ");
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
        constrastSlider.setInverted(false);
        constrastSlider.setMaximum(5);
        constrastSlider.setMinimum(1);
        constrastSlider.setPaintLabels(true);
        constrastSlider.setPaintTicks(true);
        constrastSlider.setValue(1);
        constrastSlider.setValueIsAdjusting(true);
        westPanel.add(constrastSlider, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        eclaircissementLabel = new JLabel();
        eclaircissementLabel.setText("Eclaircissement");
        westPanel.add(eclaircissementLabel, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ecalircissementSlider.setDoubleBuffered(false);
        ecalircissementSlider.setInheritsPopupMenu(false);
        ecalircissementSlider.setInverted(false);
        ecalircissementSlider.setMaximum(100);
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
        buttonRight.setText("");
        rotationPanel.add(buttonRight, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonLeft = new JButton();
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
        validatebutton = new JButton();
        validatebutton.setText("Ajouter annotation");
        panel1.add(validatebutton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        annulebutton = new JButton();
        annulebutton.setText("Retour");
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

    /**
     * Methode permettant de modifier le contenu du Panel courant par l'interface d'accueil
     * apres avoir clique sur le bouton retour
     */
    public void retourAccueil() {
        try {
            this.mainWindow.setContentPane(accueil.getMainPanel());
            this.mainWindow.revalidate();
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
            JOptionPane.showMessageDialog(null, "Probleme d'interface");
        }
    }

    /**
     * Methode permettant de modifier le contraste de l'image courante en utilisant
     * le curseur prevu a cet effet
     */
    public void contraste() {
        int i = pictureSlider.getValue();
        AbstractImage ig = picture.get(i);
        ig.setContraste(constrastSlider.getValue());
        BufferedImage imageModif = ig.getImage();
        imgPanel.setImg(imageModif);
        imgPanel.repaint();
    }

    /**
     * Methode permettant de modifier l'eclaircissement de l'image courante en utilisant
     * le curseur prevu a cet effet
     */
    public void eclaircissement() {
        int i = pictureSlider.getValue();
        AbstractImage ig = picture.get(i);
        BufferedImage imageModif = ig.getImage();
        ig.setLuminosite(ecalircissementSlider.getValue());
        imgPanel.setImg(imageModif);
        imgPanel.repaint();
    }

    /**
     * Methode permettant de changer de faire pivoter l'image courante de 90° a droite ou
     * a gauche selon le bouton selectionne
     * @param e
     * Recupere la source du bouton clique, pour affecter la valeur rotation a droite ou a gauche
     * a un nouvel attribut pour appeler les methodes implementees dans le moyau fonctionnel
     */
    public void rotation(ActionEvent e) {
        int i = pictureSlider.getValue();
        int rotation = 0;
        if (e.getSource().equals(buttonLeft)) {
            rotation = Image.ROTATE_RIGHT;
            if (this.rotation == AbstractImage.NO_ROTATE)
                this.rotation = AbstractImage.ROTATE_RIGHT;
            else if (this.rotation == AbstractImage.ROTATE_LEFT)
                this.rotation = AbstractImage.NO_ROTATE;
        }
        else if (e.getSource().equals(buttonRight)){
            rotation = Image.ROTATE_LEFT;
            if (this.rotation == AbstractImage.NO_ROTATE)
                this.rotation = AbstractImage.ROTATE_LEFT;
            else if (this.rotation == AbstractImage.ROTATE_RIGHT)
                this.rotation = AbstractImage.NO_ROTATE;
        }
        AbstractImage ig = picture.get(i);
        ig.rotate(rotation);
        BufferedImage imageModif = ig.getImage();
        imgPanel.setImg(imageModif);
        imgPanel.repaint();
    }

    public void inversion() {
        int i = pictureSlider.getValue();
        AbstractImage ig = picture.get(i);
        ig.setInverser();
        BufferedImage imageModif = ig.getImage();
        imgPanel.setImg(imageModif);
        imgPanel.repaint();
    }

    /**
     * Methode permettant de retourner l'image courante, en faisant appel a la methode prevue
     * a cet effet dans le noyau fonctionnel
     */
    public void retournement() {
        int i = pictureSlider.getValue();
        AbstractImage ig = picture.get(i);
        ig.setRetourner();
        BufferedImage imageModif = ig.getImage();
        imgPanel.setImg(imageModif);
        imgPanel.repaint();
    }

    /**
     * Methode permettant l'affichage de toutes les images de l'examen une a une
     * selon la position du curseur prevue pour les faire defiler
     * Reinitialise les positions des curseurs (de luminosite, contraste) a 0 et autres modifications
     * realisees sur l'image precedente
     */
    public void pictureChanged() {
        int i = pictureSlider.getValue();
        picture.get(i).setContraste(constrastSlider.getValue());
        picture.get(i).setLuminosite(ecalircissementSlider.getValue());
        picture.get(i).setRotation(rotation);
        picture.get(i).setInverser(inversionCheckBox.isSelected());
        picture.get(i).setRetourner(retournementCheckBox.isSelected());
        annotationTextArea.setText(picture.get(i).getAnnotation());
        BufferedImage imageModif = picture.get(i).getImage();
        imgPanel.setImg(imageModif);
        imgPanel.repaint();
        revalidate();
    }

    /**
     * Methode permettant d'enresgistrer l'annotation ajoutee dans la zone de texte prevue a cet effet, si il n'y en
     * avait pas deja une. N'enregistre pas les modifications apportees aux images
     */
    public void validation() {
        int i = pictureSlider.getValue();
        String annotation = annotationTextArea.getText();
        if (picture.get(i).getAnnotation().equals("")) {
            picture.get(i).setAnnotation(annotation);
            try {
                mainWindow.getSir().getConn().addAnnotation(picture);
            } catch (Exception e) {
                Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
            }
            revalidate();
        } else
            JOptionPane.showMessageDialog(null, "Modification des annotations non autorise", "Interdiction de modifier", JOptionPane.ERROR_MESSAGE);
    }

    public JPanel getGeneralPanel() {
        return this.mainPanel;
    }


    /**
     * Permet d'initialiser les JSlider de luminosite, contraste et de defilement des images a des
     * valeurs qui leur sont adaptees
     */
    private void createUIComponents() {
        pictureSlider = new JSlider(0, picture.size() - 1, 0);
        Hashtable<Integer, JLabel> positionCursorPctr = new Hashtable<>();
        positionCursorPctr.put(0, new JLabel("1"));
        positionCursorPctr.put(picture.size() - 1, new JLabel(String.valueOf(picture.size())));
        pictureSlider.setLabelTable(positionCursorPctr);
        pictureSlider.setPaintLabels(true);
        pictureSlider.setMajorTickSpacing(10);
        pictureSlider.setMinorTickSpacing(1);
        pictureSlider.setPaintTicks(true);

        constrastSlider = new JSlider(CONTRASTE_MIN, CONTRASTE_MAX, CONTRASTE_INIT);
        Hashtable<Integer, JLabel> position = new Hashtable<>();
        position.put(CONTRASTE_MIN, new JLabel("0"));
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