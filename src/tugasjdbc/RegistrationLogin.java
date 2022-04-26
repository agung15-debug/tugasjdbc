package tugasjdbc;

import java.awt.*; 
import java.awt.event.*; 
import java.sql.*; 
import javax.swing.*;

public class RegistrationLogin extends JFrame {
    JLabel lUsernameReg, lPasswordReg, lUsernameLog, lPasswordLog;
    JTextField tUsernameReg, tPasswordReg, tUsernameLog, tPasswordLog;
    JButton bRegister, bLogin;
    JPanel panelForm, panelTombol;
    Connector connector = new Connector();
    
    public RegistrationLogin(){
        setTitle("Tugas JDBC_123200085_Agung Prayogi"); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750, 500);
        
        setLocationRelativeTo(null);
        panelForm= new JPanel();
        setContentPane(panelForm);
        panelForm.setLayout(null);
                 
        //registration section
        JLabel lblNewUserRegister = new JLabel("Registration");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewUserRegister.setBounds(380, 52, 325, 50);
        panelForm.add(lblNewUserRegister);
        
        lUsernameReg = new JLabel("Username :");
        lUsernameReg.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lUsernameReg.setBounds(380, 80, 350, 60);
        panelForm.add(lUsernameReg);
        
        lPasswordReg = new JLabel("Password :");
        lPasswordReg.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lPasswordReg.setBounds(380, 170, 350, 60);
        panelForm.add(lPasswordReg);
        
        tUsernameReg = new JTextField(15);
        tUsernameReg.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tUsernameReg.setBounds(380, 130, 200, 30);
        panelForm.add(tUsernameReg);
        
        tPasswordReg = new JTextField(15);
        tPasswordReg.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tPasswordReg.setBounds(380, 220, 200, 30);
        panelForm.add(tPasswordReg);
        
        bRegister = new JButton("Register");
        bRegister.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        bRegister.setBounds(380, 300, 220, 50);
        panelForm.add(bRegister);
        bRegister.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
            RegistrasiData();
	}	});
        
        //login section
        JLabel lblLogin = new JLabel("Login");
        lblLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblLogin.setBounds(20, 52, 325, 50);
        panelForm.add(lblLogin);
        
        lUsernameLog = new JLabel("Username :");
        lUsernameLog.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lUsernameLog.setBounds(20, 80, 350, 60);
        panelForm.add(lUsernameLog);
        
        lPasswordLog = new JLabel("Password :");
        lPasswordLog.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lPasswordLog.setBounds(20, 170, 350, 60);
        panelForm.add(lPasswordLog);
        
        tUsernameLog = new JTextField(15);
        tUsernameLog.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tUsernameLog.setBounds(20, 130, 200, 30);
        panelForm.add(tUsernameLog);
        
        tPasswordLog = new JTextField(15);
        tPasswordLog.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tPasswordLog.setBounds(20, 220, 200, 30);
        panelForm.add(tPasswordLog);
        
        bLogin = new JButton("Login");
        bLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        bLogin.setBounds(20, 300, 220, 50);
        panelForm.add(bLogin);
        bLogin.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
            Login();
	}	});
        
        setVisible(true);
    }
    public void RegistrasiData(){
       try{
            String query = "INSERT INTO `users`(`username`,`password`) VALUES ('"+ tUsernameReg.getText() + "','" + tPasswordReg.getText() + "')";
	connector.statement = connector.koneksi.createStatement();
        int x = connector.statement.executeUpdate(query);
        if (x == 0) {
            JOptionPane.showMessageDialog(null, "Username Already Exist!", "Hasil", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Registration Successfully!", "Hasil",JOptionPane.INFORMATION_MESSAGE);
        }
        connector.statement.close();
	connector.koneksi.close();
       } catch(Exception ex){
           JOptionPane.showMessageDialog(null, "Username Already Exist!", "Hasil", JOptionPane.ERROR_MESSAGE);
       }   
    }
    
    public void Login(){
        try {
            String query = "SELECT * FROM `users` WHERE username='"+tUsernameLog.getText()+"' OR password='"+tPasswordLog.getText()+"'";
            connector.statement = connector.koneksi.createStatement();
            ResultSet rs = connector.statement.executeQuery(query);
            if(!(tUsernameLog.getText().equals("")) && !(tPasswordLog.getText().equals(""))){
                 if(rs.next()){
                if(tUsernameLog.getText().equals(rs.getString("username"))){
                    if(tPasswordLog.getText().equals(rs.getString("password"))){
                         JOptionPane.showMessageDialog(null, "Login Successfully!", "Login",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Wrong Password!", "Hasil", JOptionPane.ERROR_MESSAGE);
                    }
                } else{
                        JOptionPane.showMessageDialog(null, "Username Not Found!", "Hasil", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                   JOptionPane.showMessageDialog(null, "Account not Registered!", "Hasil", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Fill the Blank!", "Hasil", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}