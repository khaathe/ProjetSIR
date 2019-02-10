package ui.controller;


import ui.view.essaisConnexion;

import javax.swing.*;

public class MainFrameController {

    private essaisConnexion ec;
    private JPanel headPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JPanel southPanel;
    private JLabel imageCHUPP;
    private JLabel logoH2I;
    private JLabel mdpLabel;
    private JLabel identifiantLabel;
    private JLabel connexionLabel;
    private JTextField identifiantTextField;
    private JPasswordField passwordField;
    private JButton connexionButton;
    private JPanel resultPanel;

    public MainFrameController(){
        initComponents();

    }



    public void showMainFrameController(){
        ec.setVisible(true);
    }


    private void initComponents() {
        ec = new essaisConnexion();
        headPanel = ec.getHeadPanel();
        westPanel = ec.getWestPanel();
        eastPanel = ec.getEastPanel();
        southPanel = ec.getSouthPanel();
        imageCHUPP = ec.getImageCHUPP();
        logoH2I = ec.getLogoH2I();
        mdpLabel = ec.getMdpLabel();
        identifiantLabel = ec.getIdentifiantLabel();
        connexionLabel = ec.getConnexionLabel();
        identifiantTextField = ec.getIdentifiantTextField();
        passwordField = ec.getPasswordField();
        connexionButton = ec.getConnexionButton();
        resultPanel = ec.getResultPanel();
    }


}
