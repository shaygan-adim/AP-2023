package View;

import Loading.ImageLoader;
import Loading.UserLoader;
import Model.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RegisterPage extends MainFrame {
    // Constructor
    public RegisterPage(){
        super(500,625,ImageLoader.getMainMenuImage());
        initComponents();
    }

    // Methods
    @Override
    void initComponents() {
        // Initializing and configuring the components
        JLabel usernameLabel = new JLabel("Set username :");
        JLabel passwordLabel = new JLabel("Set password :");
        JLabel passwordConfirmLabel = new JLabel("Confirm password :");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField passwordConfirmField = new JPasswordField();
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");
        JLabel wrongLabel = new JLabel();
        JLabel yUsernameImage = new JLabel(ImageLoader.getyImage());
        JLabel nUsernameImage = new JLabel(ImageLoader.getnImage());
        JLabel yPasswordImage = new JLabel(ImageLoader.getyImage());
        JLabel nPasswordImage = new JLabel(ImageLoader.getnImage());
        JLabel yPasswordConfirmImage = new JLabel(ImageLoader.getyImage());
        JLabel nPasswordConfirmImage = new JLabel(ImageLoader.getnImage());

        yUsernameImage.setVisible(false);
        nUsernameImage.setVisible(true);
        yPasswordImage.setVisible(false);
        nPasswordImage.setVisible(true);
        yPasswordConfirmImage.setVisible(false);
        nPasswordConfirmImage.setVisible(true);

        usernameLabel.setFont(new Font("Forte",Font.PLAIN,15));
        passwordLabel.setFont(new Font("Forte",Font.PLAIN,15));
        passwordConfirmLabel.setFont(new Font("Forte",Font.PLAIN,15));
        registerButton.setFont(new Font("Forte",Font.PLAIN,17));
        backButton.setFont(new Font("Forte",Font.PLAIN,18));

        registerButton.setFocusPainted(false);
        backButton.setFocusPainted(false);

        // Setting listeners
        try {
            UserLoader.loadUsers();
        } catch (IOException e) {}
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FirstPage();
                RegisterPage.super.dispose();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameField.getText().equals("") || new String(passwordField.getPassword()).equals("")){
                    JOptionPane.showMessageDialog(null,"Empty inputs are not acceptable. Try again.","Empty Input Error",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    if (!yUsernameImage.isVisible()){
                        JOptionPane.showMessageDialog(null,"Username is taken. Please choose another username.","Username Error",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        if (!yPasswordConfirmImage.isVisible()){
                            JOptionPane.showMessageDialog(null,"Confirm field is not equal to the password. Try again.","Password Error",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            new User(usernameField.getText(),new String(passwordField.getPassword()));
                            JOptionPane.showMessageDialog(null,"User created successfully.","",JOptionPane.INFORMATION_MESSAGE);
                            new FirstPage();
                            RegisterPage.super.dispose();
                        }
                    }
                }
            }
        });
        usernameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                User[] users = UserLoader.getUsers();
                if (users == null){
                    nUsernameImage.setVisible(false);
                    yUsernameImage.setVisible(true);
                }
                else{
                    nUsernameImage.setVisible(false);
                    yUsernameImage.setVisible(true);
                    for (User user : users){
                        if (user.getUsername().equals(usernameField.getText())){
                            yUsernameImage.setVisible(false);
                            nUsernameImage.setVisible(true);
                        }
                    }
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (usernameField.getText().length()==0){
                    yUsernameImage.setVisible(false);
                    nUsernameImage.setVisible(true);
                }
                else{
                    User[] users = UserLoader.getUsers();
                    if (users == null){
                        nUsernameImage.setVisible(false);
                        yUsernameImage.setVisible(true);
                    }
                    else{
                        nUsernameImage.setVisible(false);
                        yUsernameImage.setVisible(true);
                        for (User user : users){
                            if (user.getUsername().equals(usernameField.getText())){
                                yUsernameImage.setVisible(false);
                                nUsernameImage.setVisible(true);
                            }
                        }
                    }
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                yPasswordImage.setVisible(true);
                nPasswordImage.setVisible(false);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (passwordField.getPassword().length == 0) {
                    yPasswordImage.setVisible(false);
                    nPasswordImage.setVisible(true);
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        passwordConfirmField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (new String(passwordConfirmField.getPassword()).equals(new String(passwordField.getPassword()))){
                    yPasswordConfirmImage.setVisible(true);
                    nPasswordConfirmImage.setVisible(false);
                }
                else{
                    yPasswordConfirmImage.setVisible(false);
                    nPasswordConfirmImage.setVisible(true);
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (passwordConfirmField.getPassword().length==0){
                    yPasswordConfirmImage.setVisible(false);
                    nPasswordConfirmImage.setVisible(true);
                }
                else {
                    if (new String(passwordConfirmField.getPassword()).equals(new String(passwordField.getPassword()))){
                        yPasswordConfirmImage.setVisible(true);
                        nPasswordConfirmImage.setVisible(false);
                    }
                    else{
                        yPasswordConfirmImage.setVisible(false);
                        nPasswordConfirmImage.setVisible(true);
                    }
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });


        // Adding the components and setting the locations
        super.mainPanel.add(usernameLabel);
        super.mainPanel.add(passwordLabel);
        super.mainPanel.add(passwordConfirmLabel);
        super.mainPanel.add(usernameField);
        super.mainPanel.add(passwordField);
        super.mainPanel.add(passwordConfirmField);
        super.mainPanel.add(registerButton);
        super.mainPanel.add(backButton);
        super.mainPanel.add(wrongLabel);
        super.mainPanel.add(yUsernameImage);
        super.mainPanel.add(nUsernameImage);
        super.mainPanel.add(yPasswordImage);
        super.mainPanel.add(nPasswordImage);
        super.mainPanel.add(yPasswordConfirmImage);
        super.mainPanel.add(nPasswordConfirmImage);

        usernameLabel.setBounds(172,205,150,50);
        usernameField.setBounds(170,245,150,25);
        yUsernameImage.setBounds(330,243,25,25);
        nUsernameImage.setBounds(330,243,30,30);
        passwordLabel.setBounds(172,275,150,50);
        passwordField.setBounds(170,315,150,25);
        yPasswordImage.setBounds(330,313,25,25);
        nPasswordImage.setBounds(330,313,30,30);
        passwordConfirmLabel.setBounds(172,345,160,50);
        passwordConfirmField.setBounds(170,385,150,25);
        yPasswordConfirmImage.setBounds(330,383,25,25);
        nPasswordConfirmImage.setBounds(330,383,30,30);
        registerButton.setBounds(250,430,95,30);
        backButton.setBounds(150,430,95,30);
    }
}
