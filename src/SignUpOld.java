import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class SignUpOld extends javax.swing.JFrame {

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
    public SignUpOld() {
        initComponents();
        setTitle("Jentaime Water Station");
        setResizable(false);
        setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SignUpLayeredPane = new javax.swing.JLayeredPane();
        whitebg = new javax.swing.JPanel();
        LoginLabel = new javax.swing.JLabel();
        ConfirmPasswordLabel = new javax.swing.JLabel();
        SignUpUsernameLabel = new javax.swing.JLabel();
        SignUpUsernameField = new javax.swing.JTextField();
        SignUpPasswordField = new javax.swing.JPasswordField();
        SignUpButton = new javax.swing.JButton();
        SignUpPassLabel = new javax.swing.JLabel();
        ConfirmPassField = new javax.swing.JPasswordField();
        AccountReadyLabel = new javax.swing.JLabel();
        LogInRedirectButton = new javax.swing.JButton();
        ShowPassSignUp = new javax.swing.JCheckBox();
        SignUpbg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setMinimumSize(new java.awt.Dimension(1280, 720));

        SignUpLayeredPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SignUpLayeredPane.setMaximumSize(new java.awt.Dimension(1920, 1080));
        SignUpLayeredPane.setMinimumSize(new java.awt.Dimension(1280, 720));

        whitebg.setBackground(new java.awt.Color(255, 255, 255));
        whitebg.setForeground(new java.awt.Color(255, 255, 255));
        whitebg.setMaximumSize(new java.awt.Dimension(640, 1080));
        whitebg.setMinimumSize(new java.awt.Dimension(640, 720));
        whitebg.setPreferredSize(new java.awt.Dimension(640, 720));

        LoginLabel.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        LoginLabel.setText("Create Account");

        ConfirmPasswordLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        ConfirmPasswordLabel.setText("Confirm Password:");

        SignUpUsernameLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        SignUpUsernameLabel.setText("Enter Username:");

        SignUpButton.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        SignUpButton.setText("SIGN UP");
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpButtonActionPerformed(evt);
            }
        });

        SignUpPassLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        SignUpPassLabel.setText("Enter Password:");

        AccountReadyLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        AccountReadyLabel.setText("Have an account?");

        LogInRedirectButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LogInRedirectButton.setText("LOGIN");
        LogInRedirectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogInRedirectButtonActionPerformed(evt);
            }
        });

        ShowPassSignUp.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ShowPassSignUp.setText("Show Password");
        ShowPassSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowPassSignUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout whitebgLayout = new javax.swing.GroupLayout(whitebg);
        whitebg.setLayout(whitebgLayout);
        whitebgLayout.setHorizontalGroup(
            whitebgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(whitebgLayout.createSequentialGroup()
                .addGroup(whitebgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(whitebgLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(LoginLabel))
                    .addGroup(whitebgLayout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(SignUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(whitebgLayout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(AccountReadyLabel))
                    .addGroup(whitebgLayout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(LogInRedirectButton))
                    .addGroup(whitebgLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(whitebgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ShowPassSignUp)
                            .addGroup(whitebgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(SignUpPassLabel)
                                .addGroup(whitebgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(SignUpPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(whitebgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(SignUpUsernameLabel)
                                        .addComponent(SignUpUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(ConfirmPasswordLabel)
                                .addComponent(ConfirmPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        whitebgLayout.setVerticalGroup(
            whitebgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(whitebgLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(LoginLabel)
                .addGap(30, 30, 30)
                .addComponent(SignUpUsernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SignUpUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(SignUpPassLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SignUpPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ConfirmPasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ConfirmPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ShowPassSignUp)
                .addGap(19, 19, 19)
                .addComponent(SignUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(AccountReadyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LogInRedirectButton)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        SignUpLayeredPane.add(whitebg);
        whitebg.setBounds(320, 40, 640, 680);

        SignUpbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg.PNG"))); // NOI18N
        SignUpLayeredPane.add(SignUpbg);
        SignUpbg.setBounds(0, 0, 1280, 720);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SignUpLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SignUpLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpButtonActionPerformed
        String username = SignUpUsernameField.getText();
        String password1 = new String(SignUpPasswordField.getPassword());
        String password2 = new String(ConfirmPassField.getPassword());

        if (username.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password1.equals(password2)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO accounts (username, password) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password1);
                pstmt.executeUpdate();
            }
            JOptionPane.showMessageDialog(this, "Sign up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SignUpButtonActionPerformed

    private void ShowPassSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowPassSignUpActionPerformed
       if (ShowPassSignUp.isSelected()) {
            SignUpPasswordField.setEchoChar((char) 0);
            ConfirmPassField.setEchoChar((char) 0);// Show password
        } else {
            SignUpPasswordField.setEchoChar('•');
            ConfirmPassField.setEchoChar('•');// Hide password (default char, can be '*')
        }
    }//GEN-LAST:event_ShowPassSignUpActionPerformed

    private void LogInRedirectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogInRedirectButtonActionPerformed
        redirectToLogInPage();
    }//GEN-LAST:event_LogInRedirectButtonActionPerformed
    private void redirectToLogInPage() {
        Login LoginForm = new Login();
        LoginForm.setVisible(true);
        this.dispose();
    }
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(SignUpOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUpOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUpOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUpOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUpOld().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AccountReadyLabel;
    private javax.swing.JPasswordField ConfirmPassField;
    private javax.swing.JLabel ConfirmPasswordLabel;
    private javax.swing.JButton LogInRedirectButton;
    private javax.swing.JLabel LoginLabel;
    private javax.swing.JCheckBox ShowPassSignUp;
    private javax.swing.JButton SignUpButton;
    private javax.swing.JLayeredPane SignUpLayeredPane;
    private javax.swing.JLabel SignUpPassLabel;
    private javax.swing.JPasswordField SignUpPasswordField;
    private javax.swing.JTextField SignUpUsernameField;
    private javax.swing.JLabel SignUpUsernameLabel;
    private javax.swing.JLabel SignUpbg;
    private javax.swing.JPanel whitebg;
    // End of variables declaration//GEN-END:variables
}
