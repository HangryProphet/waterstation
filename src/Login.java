
import java.awt.Color;
import static java.awt.Color.blue;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class Login extends javax.swing.JFrame {

    public class DatabaseConnection {
        // Database URL, username, and password

        private static final String URL = "jdbc:mysql://localhost:3306/waterstation";
        private static final String USER = "root";
        private static final String PASSWORD = "";

        // Method to establish a connection to the database
        public static Connection getConnection() {
            Connection connection = null;
            try {
                // Register the JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Open a connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully.");
            } catch (ClassNotFoundException e) {
                System.out.println("MySQL JDBC Driver not found.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Failed to connect to the database.");
                e.printStackTrace();
            }
            return connection;
        }

        public static void closeConnection(Connection connection) {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    System.out.println("Failed to close the database connection.");
                    e.printStackTrace();
                }
            }
        }

    }

    public Login() {
        initComponents();
        setTitle("Jentaime Water Station");
        setResizable(false);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(LoginButton);

        this.icon2.setVisible(false);
        this.LoginButton.setOpaque(false);
        this.LoginButton.setContentAreaFilled(false); //to make the content area transparent

        this.SignUpButton.setOpaque(false);
        this.SignUpButton.setContentAreaFilled(false); //to make the content area transparent
        this.SignUpButton.setBorderPainted(true);

        String SignUp = "<HTML><U>SIGN UP</U></HTML>";
        SignUpButton.setText(SignUp);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoginLayeredPane = new javax.swing.JLayeredPane();
        ForgetPasswordButton = new javax.swing.JButton();
        UsernameField = new javax.swing.JTextField();
        icon2 = new javax.swing.JLabel();
        icon1 = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        LoginButton = new javax.swing.JButton();
        SignUpLabel = new javax.swing.JLabel();
        SignUpButton = new javax.swing.JButton();
        Loginbg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LoginLayeredPane.setMaximumSize(new java.awt.Dimension(1920, 1080));
        LoginLayeredPane.setMinimumSize(new java.awt.Dimension(1280, 720));

        ForgetPasswordButton.setBackground(new java.awt.Color(51, 51, 51));
        ForgetPasswordButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ForgetPasswordButton.setForeground(new java.awt.Color(255, 255, 255));
        ForgetPasswordButton.setText("Forget Password");
        ForgetPasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ForgetPasswordButtonActionPerformed(evt);
            }
        });
        LoginLayeredPane.add(ForgetPasswordButton);
        ForgetPasswordButton.setBounds(500, 440, 130, 20);

        UsernameField.setBackground(new java.awt.Color(223, 223, 223));
        UsernameField.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        UsernameField.setForeground(new java.awt.Color(102, 102, 102));
        LoginLayeredPane.add(UsernameField);
        UsernameField.setBounds(500, 300, 290, 50);

        icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/hide.png"))); // NOI18N
        icon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                icon2MousePressed(evt);
            }
        });
        LoginLayeredPane.add(icon2);
        icon2.setBounds(750, 404, 24, 20);

        icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/seen.png"))); // NOI18N
        icon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                icon1MousePressed(evt);
            }
        });
        LoginLayeredPane.add(icon1);
        icon1.setBounds(750, 400, 30, 30);

        PasswordField.setBackground(new java.awt.Color(223, 223, 223));
        PasswordField.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        PasswordField.setForeground(new java.awt.Color(51, 51, 51));
        LoginLayeredPane.add(PasswordField);
        PasswordField.setBounds(500, 390, 290, 50);

        LoginButton.setBackground(new java.awt.Color(0, 74, 173));
        LoginButton.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        LoginButton.setForeground(new java.awt.Color(250, 250, 250));
        LoginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/enter (2).png"))); // NOI18N
        LoginButton.setBorder(null);
        LoginButton.setBorderPainted(false);
        LoginButton.setOpaque(true);
        LoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LoginButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LoginButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LoginButtonMousePressed(evt);
            }
        });
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });
        LoginLayeredPane.add(LoginButton);
        LoginButton.setBounds(610, 470, 60, 50);

        SignUpLabel.setBackground(new java.awt.Color(223, 223, 223));
        SignUpLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        SignUpLabel.setForeground(new java.awt.Color(223, 223, 223));
        SignUpLabel.setText("Don't have an account?");
        LoginLayeredPane.add(SignUpLabel);
        SignUpLabel.setBounds(504, 560, 200, 60);

        SignUpButton.setBackground(new java.awt.Color(51, 51, 51));
        SignUpButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        SignUpButton.setForeground(new java.awt.Color(255, 255, 255));
        SignUpButton.setText("SIGN UP");
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpButtonActionPerformed(evt);
            }
        });
        LoginLayeredPane.add(SignUpButton);
        SignUpButton.setBounds(670, 563, 120, 50);

        Loginbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/LogBG1.png"))); // NOI18N
        LoginLayeredPane.add(Loginbg);
        Loginbg.setBounds(0, 0, 1280, 720);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LoginLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LoginLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        String username = UsernameField.getText();
        String password = new String(PasswordField.getPassword()); // Assuming you use a JPasswordField for the password input

        // Establish a connection to the database
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // Login successful
                    JOptionPane.showMessageDialog(this, "Login successful.");
                    // Proceed to the next window or functionality
                    redirectToHomePage();

                } else {
                    // Login failed
                    JOptionPane.showMessageDialog(this, "Invalid username or password.");
                }

                // Close resources
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
                e.printStackTrace();
            } finally {
                DatabaseConnection.closeConnection(connection);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.");
        }
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpButtonActionPerformed
        redirectToSignUpPage();
    }//GEN-LAST:event_SignUpButtonActionPerformed

    private void icon1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon1MousePressed

        icon2.setVisible(true);
        icon1.setVisible(false);
        PasswordField.setEchoChar((char) 0);

    }//GEN-LAST:event_icon1MousePressed

    private void LoginButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginButtonMouseEntered

    }//GEN-LAST:event_LoginButtonMouseEntered

    private void LoginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginButtonMouseClicked

    }//GEN-LAST:event_LoginButtonMouseClicked

    private void LoginButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginButtonMousePressed

        LoginButton.setBorder(BorderFactory.createLineBorder(blue, 2, true));
    }//GEN-LAST:event_LoginButtonMousePressed

    private void LoginButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginButtonMouseExited
        LoginButton.setBorder(null);
    }//GEN-LAST:event_LoginButtonMouseExited

    private void ForgetPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ForgetPasswordButtonActionPerformed
       redirectoForgetPassForm();
    }//GEN-LAST:event_ForgetPasswordButtonActionPerformed

    private void icon2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon2MousePressed

        icon1.setVisible(true);
        icon2.setVisible(false);
        PasswordField.setEchoChar('*');
    }//GEN-LAST:event_icon2MousePressed

    private void redirectoForgetPassForm() {
        ForgetPass ForgetPassForm = new ForgetPass();
        ForgetPassForm.setVisible(true);
        this.dispose();

    }

    private void redirectToHomePage() {
        Home HomeForm = new Home();
        HomeForm.setVisible(true);
        this.dispose();
    }

    private void redirectToSignUpPage() {
        SignUp SignUpForm = new SignUp();
        SignUpForm.setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ForgetPasswordButton;
    private javax.swing.JButton LoginButton;
    private javax.swing.JLayeredPane LoginLayeredPane;
    private javax.swing.JLabel Loginbg;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JButton SignUpButton;
    private javax.swing.JLabel SignUpLabel;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel icon1;
    private javax.swing.JLabel icon2;
    // End of variables declaration//GEN-END:variables
}
