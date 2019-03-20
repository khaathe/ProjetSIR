package ui;

import eu.gnome.morena.Device;
import eu.gnome.morena.Manager;
import eu.gnome.morena.TransferListener;
import nf.AbstractImage;
import nf.Image;
import nf.PGM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class Numeriseur extends JDialog implements TransferListener {

    private static final Color default_background = new Color(190, 193, 195);
    private static final Color button_background = new Color(68, 140, 255);
    private static final Font default_font = new Font("DejaVu Sans", Font.PLAIN, 16);

    ImagePanel imagePanel;
    JPanel main, south, panel_button;
    JButton quitter, numeriser, choixDevice, valider;
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

        panel_button = new JPanel();
        panel_button.setBackground(default_background);
        panel_button.setForeground(Color.WHITE);
        panel_button.setFont(default_font);

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


        panel_button.add(valider);
        panel_button.add(numeriser);
        panel_button.add(choixDevice);
        panel_button.add(quitter);
        south.add(panel_button, BorderLayout.SOUTH);

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
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valider();
            }
        });

        numeriser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numerise();
            }
        });

        choixDevice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChoixDevice();
            }
        });

        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitter();
            }
        });
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

    public synchronized void numerise() {
        try {
            if (device != null) {
                device.startTransfer(this);
            } else throw new Exception("Aucun peripherique connecte");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Peripherique non trouve", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setChoixDevice() {
        device = manager.selectDevice(this);
        info.setText("Scanner choisi : " + device);
    }

    public void quitter() {
        image = null;
        manager.close();
        this.setVisible(false);
    }

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
    public void transferProgress(int progress) {
        info.setText("Numerisation à " + progress + "%");
        info.repaint();
    }

    @Override
    public void transferDone(File file) {
        try {
            if (file.getName().matches(".pgm")) {
                image = new PGM(numArchivage);
                image.setImage(file);
            } else {
                image = new Image(numArchivage);
                image.setImage(file);
            }
            imagePanel.setImg(image.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        info.setText("Numerisation finie");
        repaint();
    }

    @Override
    public void transferFailed(int code, String messsage) {
        info.setText(messsage + "[0x" + Integer.toHexString(code) + "]");
        info.repaint();
    }
}
