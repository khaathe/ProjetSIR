package nf;

import eu.gnome.morena.Device;
import eu.gnome.morena.Manager;
import eu.gnome.morena.TransferListener;
import ui.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class Numeriseur extends JDialog implements TransferListener {
    ImagePanel imagePanel;
    JPanel main, south, panel_button;
    JButton quitter, numeriser, choixDevice, valider;
    JLabel info;
    Manager manager;
    Device device;
    AbstractImage image;

    public Numeriseur() {
        initComponent();

        initListener();
    }

    public void initComponent() {
        image = null;
        info = new JLabel("Aucun Peripherique selectionne");
        manager = Manager.getInstance();
        device = null;
        imagePanel = new ImagePanel(null);
        imagePanel.setPreferredSize(new Dimension(500, 500));
        south = new JPanel();

        south.setLayout(new BorderLayout());
        south.add(info, BorderLayout.NORTH);

        panel_button = new JPanel();
        quitter = new JButton("Quitter");
        numeriser = new JButton("Numeriser");
        choixDevice = new JButton("Choisir un scanner");
        valider = new JButton("Valider");
        panel_button.add(valider);
        panel_button.add(numeriser);
        panel_button.add(choixDevice);
        panel_button.add(quitter);
        south.add(panel_button, BorderLayout.SOUTH);

        main = new JPanel();
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
                image = new PGM(Examen.generateNumArchivage());
                image.setImage(file);
            } else {
                image = new Image(Examen.generateNumArchivage());
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
