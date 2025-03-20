/*TODO: improve layout
        add options menu
 *      add functionality to login 
 */
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{

    JButton loginButton;
    JTextField userName;
    JTextField password;
    JLabel message;
    GUI(){
        
        //TEMP CODE
        message = new JLabel();
        message.setText("login feature to be implemented");
        message.setVisible(false);
        
        //title
        JLabel title = new JLabel();//create label
        title.setText("Employee Management System");//set text for label
        
        //Credentials text felids
        userName = new JTextField();
        userName.setPreferredSize(new Dimension(300,40));
        password = new JTextField();
        password.setPreferredSize(new Dimension(300,40));

        //login button
        loginButton = new JButton();
        loginButton.addActionListener(this);
        loginButton.setText("login");
        loginButton.setFocusable(false);
        
        //Creates title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(2000, 30)); //TODO: make width responsive (react when window is resized)
        titlePanel.setBackground(Color.GRAY);
        titlePanel.add(title);
        
        //Creates login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension (300,200)); 
        loginPanel.add(new JLabel("username"));
        loginPanel.add(userName);
        loginPanel.add(new JLabel("password"));
        loginPanel.add(password);
        loginPanel.add(loginButton);
        
        //Creates the window
        this.setTitle("CCS Employee Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(true);
        this.setLayout(new FlowLayout());
        this.add(titlePanel);
        this.add(loginPanel);
        this.add(message);//TEMP CODE
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        /* TODO: disable button if input field for login credentials is empty
        *  loginButton.setEnabled(false);
        */
        if(e.getSource()==loginButton){
            //TEMP CODE
            message.setVisible(true);
        }
    }
}