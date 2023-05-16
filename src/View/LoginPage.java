package View;

import Loading.ImageLoader;
import Loading.UserLoader;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginPage extends MainFrame {
    // Constructor
    public LoginPage(){
        super(500,625,ImageLoader.getMainMenuImage());
        initComponents();
    }

    // Methods
    @Override
    void initComponents() {
        // Initializing and configuring the components
        JLabel usernameLabel = new JLabel("Username :");
        JLabel passwordLabel = new JLabel("Password :");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back");
        JLabel wrongLabel = new JLabel();

        usernameLabel.setFont(new Font("Forte",Font.PLAIN,18));
        passwordLabel.setFont(new Font("Forte",Font.PLAIN,18));
        loginButton.setFont(new Font("Forte",Font.PLAIN,18));
        backButton.setFont(new Font("Forte",Font.PLAIN,18));

        loginButton.setFocusPainted(false);
        backButton.setFocusPainted(false);

        // Setting listeners
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FirstPage();
                LoginPage.super.dispose();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameField.getText().equals("") || new String(passwordField.getPassword()).equals("")){
                    JOptionPane.showMessageDialog(null,"Empty inputs are not acceptable. Try again.","Empty Input Error",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    try {
                        UserLoader.loadUsers();
                    } catch (IOException ignored) {}
                    User thisUser=null;
                    boolean found = false;
                    User[] users = UserLoader.getUsers();
                    if (users!=null){
                        for (User user : users){
                            if (user.getUsername().equals(usernameField.getText())){
                                if (user.getPassword().equals(new String(passwordField.getPassword()))){
                                    thisUser = user;
                                    new MainPage(thisUser);
                                    LoginPage.super.dispose();
                                }
                                else{
                                    JOptionPane.showMessageDialog(null,"Password is wrong. Try again.","Wrong Password",JOptionPane.INFORMATION_MESSAGE);
                                }
                                found=true;
                                break;
                            }
                        }
                    }
                    if (!found){
                        JOptionPane.showMessageDialog(null,"Username not found. Try again.","Username Not Found",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        // Adding the components and setting the locations
        super.mainPanel.add(usernameLabel);
        super.mainPanel.add(passwordLabel);
        super.mainPanel.add(usernameField);
        super.mainPanel.add(passwordField);
        super.mainPanel.add(loginButton);
        super.mainPanel.add(backButton);
        super.mainPanel.add(wrongLabel);

        usernameLabel.setBounds(172,225,150,50);
        usernameField.setBounds(170,265,150,25);
        passwordLabel.setBounds(172,295,150,50);
        passwordField.setBounds(170,335,150,25);
        loginButton.setBounds(255,400,80,30);
        backButton.setBounds(155,400,80,30);
    }
}
