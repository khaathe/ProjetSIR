package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import nf.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class AjoutExamen extends JPanel {
    private JPanel generalPanel;
    private JButton annulerButton;
    private JButton ajouterButton;
    private JButton choisirImageButton;
    private JComboBox dayComboBox;
    private JComboBox monthComboBox;
    private JComboBox yearComboBox;
    private JComboBox examComboBox;
    private JComboBox serviceComboBox;
    private JTextArea ajoutDExamenTextArea;
    private JTextArea crArea;
    private JLabel ajoutPatientLabel;
    private JLabel imageLabel;
    private JLabel dateLabel;
    private JLabel crLabel;
    private JLabel imageChoisieLabel;
    private MainWindow mainWindow;
    private DefaultComboBoxModel<TypeExamen> examModel;
    private DefaultComboBoxModel<ServiceHosp> serviceModel;
    private Accueil accueil;
    private File[] listeFichierImage;


    public AjoutExamen(MainWindow mainWindow, Accueil accueil) {
        this.mainWindow = mainWindow;
        this.accueil = accueil;
        listeFichierImage = null;


        examModel = new DefaultComboBoxModel();
        examComboBox.setModel(examModel);
        remplissageComboTypeExam();


        serviceModel = new DefaultComboBoxModel();
        serviceComboBox.setModel(serviceModel);
        remplissageComboService();

        initDayComboBox();
        initMonthComboBox();
        initYearComoBox();

        choisirImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choisirImage();
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                retourAccueil();
            }
        });

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ajouter();
            }
        });
    }


    public void ajouter (){
        if (isAllFIeldValid())
            creationExam();
    }

    public boolean isAllFIeldValid (){
        boolean valid = false;
        if (checkDate() && !( crArea.getText().equals("") ) && listeFichierImage != null)
            valid = true;
        else
            JOptionPane.showMessageDialog(this, "Erreur, remplissez tous les champs svp", "Erreur de champs", JOptionPane.ERROR_MESSAGE);
        return valid;
    }

    public boolean checkDate (){
        boolean check = true;
        int day = (int) dayComboBox.getSelectedItem();
        int month = (int) monthComboBox.getSelectedItem();
        if( month==2 && day>28 )
            check = false;
        else if ( month<8 && month%2==0 && day>30)
            check = false;
        else if (month>8 && month%2==1 && day>30)
            check = false;
        return check;
    }

    public void creationExam() {
        GregorianCalendar date = new GregorianCalendar();
        date.set(GregorianCalendar.DAY_OF_MONTH, (int) dayComboBox.getSelectedItem());
        date.set(GregorianCalendar.MONTH, (int) monthComboBox.getSelectedItem()-1);
        date.set(GregorianCalendar.YEAR, (int) yearComboBox.getSelectedItem());

        TypeExamen typeExamen = (TypeExamen) examComboBox.getSelectedItem();
        ServiceHosp serviceHosp = (ServiceHosp) serviceComboBox.getSelectedItem();
        Patient patient = ( (DMR) accueil.getList().getSelectedValue() ).getPatient();
        PersonnelServiceRadio personnelServiceRadio = mainWindow.getSir().getPersonneConnecte();
        String numArchivage = Examen.generateNumArchivage();
        ArrayList<AbstractImage> listeImage = loadImage(numArchivage);
        CompteRendu cr = new CompteRendu(numArchivage, crArea.getText());
        Examen examen = new Examen(date, numArchivage, typeExamen, patient, personnelServiceRadio, serviceHosp, listeImage, cr);
        try {
            mainWindow.getSir().getConn().addExamen(examen);
            DMR dmr = (DMR) accueil.getList().getSelectedValue();
            dmr.ajouterExamen(examen);
            accueil.displayPatient();
            retourAccueil();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void choisirImage () {
        JFileChooser jFileChooser =new JFileChooser();
        jFileChooser.setMultiSelectionEnabled(true);
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            listeFichierImage = jFileChooser.getSelectedFiles();
            imageChoisieLabel.setText("Image choisie");
        }
    }

    public ArrayList<AbstractImage> loadImage (String numArchivage) {
        ArrayList<AbstractImage> listeImage = new ArrayList<>();
        for( File f : listeFichierImage ){
            nf.Image image = new nf.Image(numArchivage);
            try {
                image.setImage(ImageIO.read(f));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
            }
            listeImage.add(image);
        }
        return  listeImage;
    }

    public void remplissageComboTypeExam() {
        for(TypeExamen typeExamen : TypeExamen.values()){
            if( !typeExamen.equals(TypeExamen.UNKNOWN))
                examModel.addElement(typeExamen);
        }
    }


    public void remplissageComboService() {
        for(ServiceHosp serviceHosp : ServiceHosp.values()){
            if( !serviceHosp.equals(ServiceHosp.UNKNOWN))
                serviceModel.addElement(serviceHosp);
        }
    }

    public  void initDayComboBox (){
        for(int i = 1; i<=31; i++){
            dayComboBox.addItem(i);
        }
    }

    public  void initMonthComboBox (){
        for(int i = 1; i<=12; i++){
            monthComboBox.addItem(i);
        }
    }

    public  void initYearComoBox (){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int end = 1950;
        for(int i = currentYear; i>=end; i--){
            yearComboBox.addItem(i);
        }
    }

    public void retourAccueil() {
        this.mainWindow.setContentPane(accueil.getMainPanel());
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
        serviceComboBox = new JComboBox();
        panel5.add(serviceComboBox);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel2.add(panel6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Type d'examen : ");
        panel6.add(label2);
        examComboBox = new JComboBox();
        panel6.add(examComboBox);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel7, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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

}
