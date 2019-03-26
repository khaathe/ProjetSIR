package ui;

import eu.gnome.morena.Device;
import eu.gnome.morena.Manager;
import eu.gnome.morena.TransferListener;
import nf.AbstractImage;
import nf.Dicom;
import nf.Image;
import nf.PGM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Numeriseur extends JDialog implements TransferListener {

    private static final Color default_background = new Color(190, 193, 195);
    private static final Color button_background = new Color(68, 140, 255);
    private static final Font default_font = new Font("DejaVu Sans", Font.PLAIN, 16);

    ImagePanel imagePanel;
    JPanel main;
    JPanel south;
    JPanel panelButton;
    JButton quitter;
    JButton numeriser;
    JButton choixDevice;
    JButton valider;
    JLabel info;
    Manager manager;
    Device device;
    AbstractImage image;
    String numArchivage;

    public Numeriseur(String numArchivage) {
        this.numArchivage = numArchivage;

        initComponent();

        initListener();
    }

    public void initComponent() {
        image = null;
        info = new JLabel("Aucun Peripherique selectionne");
        info.setBackground(default_background);
        info.setForeground(Color.WHITE);
        info.setFont(default_font);

        manager = Manager.getInstance();
        device = null;
        imagePanel = new ImagePanel(null);
        imagePanel.setPreferredSize(new Dimension(500, 500));

        south = new JPanel();
        south.setBackground(default_background);
        south.setForeground(Color.WHITE);
        south.setFont(default_font);
        south.setLayout(new BorderLayout());
        south.add(info, BorderLayout.NORTH);

        panelButton = new JPanel();
        panelButton.setBackground(default_background);
        panelButton.setForeground(Color.WHITE);
        panelButton.setFont(default_font);

        quitter = new JButton("Quitter");
        quitter.setBackground(button_background);
        quitter.setForeground(Color.WHITE);
        quitter.setFont(default_font);

        numeriser = new JButton("Numeriser");
        numeriser.setBackground(button_background);
        numeriser.setForeground(Color.WHITE);
        numeriser.setFont(default_font);

        choixDevice = new JButton("Choisir un scanner");
        choixDevice.setBackground(button_background);
        choixDevice.setForeground(Color.WHITE);
        choixDevice.setFont(default_font);

        valider = new JButton("Valider");
        valider.setBackground(button_background);
        valider.setForeground(Color.WHITE);
        valider.setFont(default_font);


        panelButton.add(valider);
        panelButton.add(numeriser);
        panelButton.add(choixDevice);
        panelButton.add(quitter);
        south.add(panelButton, BorderLayout.SOUTH);

        main = new JPanel();
        main.setBackground(default_background);
        main.setForeground(Color.WHITE);
        main.setFont(default_font);
        main.setLayout(new BorderLayout());
        main.add(south, BorderLayout.SOUTH);
        main.add(imagePanel, BorderLayout.CENTER);
        this.setContentPane(main);
    }

    public void initListener() {
        valider.addActionListener( actionEvent -> valider() );

        numeriser.addActionListener( actionEvent ->  numerise() );

        choixDevice.addActionListener( actionEvent -> setChoixDevice() );

        quitter.addActionListener( actionEvent -> quitter() );
    }

    public void valider() {
        if (image == null)
            JOptionPane.showMessageDialog(this, "Aucune image n'a ete numerise", "Pas d'image numerise", JOptionPane.INFORMATION_MESSAGE);
        else {
            int res = JOptionPane.showConfirmDialog(this, "Voulez-vous validez ?", "Validation", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.OK_OPTION){
                manager.close();
                this.setVisible(false);
            }
        }
    }

    /**
     * Methode qui demarre la numerisation avec le scanner choisi.
     */
    public synchronized void numerise() {
        try {
            if (device != null) {
                device.startTransfer(this);
            } else throw new NullPointerException("Aucun peripherique connecte");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Peripherique non trouve", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Affiche une JOptionPane avec les peripheriques actuellement connectees.
     */
    public void setChoixDevice() {
        device = manager.selectDevice(this);
        info.setText("Scanner choisi : " + device);
    }

    /**
     * Libere toute les ressources et ferme la Dialog si l'utilisateur souhaite quitter.
     */
    public void quitter() {
        image = null;
        manager.close();
        this.setVisible(false);
    }

    /**
     * Methode qui rend la dialog visible et retourne l'image numerise.
     * @return Image numerise
     */
    public AbstractImage run() {
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                quitter();
            }
        });
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        return image;
    }

    @Override
    /**
     * Methode de l'interface TransfertListener appelee pendant la numerisation.
     * Cette methode affiche l'etat d'avancement de la numerisation dans un label.
     */
    public void transferProgress(int progress) {
        info.setText("Numerisation à " + progress + "%");
        info.repaint();
    }

    @Override
    /**
     * Methode de l'interface TransfertListener appelee une fois la numerisation terminee.
     * Un test est realise sur l'extension du fichier pour creer une instance de l'image correspondante.
     * @param file
     *      Fichier image
     */
    public void transferDone(File file) {
        try {
            String[] regrex = file.getName().split("\\.");
            String extension = regrex[regrex.length - 1].toUpperCase();
            AbstractImage image = null;
            switch (extension) {
                case "PGM":
                    image = new PGM(numArchivage);
                    break;
                case "DCM":
                    image = new Dicom(numArchivage);
                    break;
                default:
                    image = new Image(numArchivage);
                    break;
            }
            image.setImage(file);
            imagePanel.setImg(image.getImage());
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
        }
        info.setText("Numerisation finie");
        repaint();
    }

    @Override
    /**
     * Methode de l'interface TransfertListener appelee lors d'un echec de la numerisation.
     * Cette methode affiche le code de l'erreur survenue lors du transfert.
     */
    public void transferFailed(int code, String messsage) {
        info.setText(messsage + "[0x" + Integer.toHexString(code) + "]");
        info.repaint();
    }
}
