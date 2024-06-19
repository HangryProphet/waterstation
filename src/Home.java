
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.IntelliJTheme;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class Home extends javax.swing.JFrame {

    JpanelLoader jpload = new JpanelLoader();
    private JButton lastSelectedButton;

    public Home() {
        initComponents();
        setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        Logout_Bttn = new javax.swing.JButton();
        SalesBttn = new javax.swing.JButton();
        CustomerBttn = new javax.swing.JButton();
        InventoryBttn = new javax.swing.JButton();
        ReportsBttn = new javax.swing.JButton();
        panel_load = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setMinimumSize(new java.awt.Dimension(1280, 720));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Logout_Bttn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Logout_Bttn.setText("Logout");
        Logout_Bttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout_BttnActionPerformed(evt);
            }
        });

        SalesBttn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SalesBttn.setText("Sales");
        SalesBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalesBttnActionPerformed(evt);
            }
        });

        CustomerBttn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CustomerBttn.setText("Customer");
        CustomerBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerBttnActionPerformed(evt);
            }
        });

        InventoryBttn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        InventoryBttn.setText("Inventory");
        InventoryBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InventoryBttnActionPerformed(evt);
            }
        });

        ReportsBttn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ReportsBttn.setText("Reports");
        ReportsBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportsBttnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(InventoryBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(SalesBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Logout_Bttn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CustomerBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ReportsBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(SalesBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CustomerBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(InventoryBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ReportsBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 301, Short.MAX_VALUE)
                .addComponent(Logout_Bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        panel_load.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panel_loadLayout = new javax.swing.GroupLayout(panel_load);
        panel_load.setLayout(panel_loadLayout);
        panel_loadLayout.setHorizontalGroup(
            panel_loadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1170, Short.MAX_VALUE)
        );
        panel_loadLayout.setVerticalGroup(
            panel_loadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_load, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_load, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Logout_BttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout_BttnActionPerformed
        redirectToLogInPage();
    }//GEN-LAST:event_Logout_BttnActionPerformed

    private void SalesBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalesBttnActionPerformed
        handleButtonSelection(SalesBttn, new Sales());
    }//GEN-LAST:event_SalesBttnActionPerformed

    private void CustomerBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerBttnActionPerformed
        handleButtonSelection(CustomerBttn, new Customer());
    }//GEN-LAST:event_CustomerBttnActionPerformed

    private void InventoryBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InventoryBttnActionPerformed
        handleButtonSelection(InventoryBttn, new Inventory());
    }//GEN-LAST:event_InventoryBttnActionPerformed

    private void ReportsBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportsBttnActionPerformed
        handleButtonSelection(ReportsBttn, new Reports());
    }//GEN-LAST:event_ReportsBttnActionPerformed
    
    private void redirectToLogInPage() {
        int confirmed = JOptionPane.showConfirmDialog(
        null, 
        "Are you sure you want to logout?", 
        "Confirm Logout", 
        JOptionPane.YES_NO_OPTION
    );
    
    if (confirmed == JOptionPane.YES_OPTION) {
        Login LoginForm = new Login();
        LoginForm.setVisible(true);
        this.dispose();
    }
    }

    private void handleButtonSelection(JButton selectedButton, javax.swing.JPanel panel) {
        if (lastSelectedButton != null && lastSelectedButton == selectedButton) {
            // Same button clicked, hide the panel
            panel_load.removeAll();
            panel_load.revalidate();
            panel_load.repaint();
            lastSelectedButton = null; // Reset the last selected button
        } else {
            // Different button clicked, show the corresponding panel
            jpload.jPanelLoader(panel_load, panel);
            lastSelectedButton = selectedButton; // Update the last selected button
        }
    }

    public static void main(String args[]) {
        FlatIntelliJLaf.registerCustomDefaultsSource("<default package>");
        FlatIntelliJLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CustomerBttn;
    private javax.swing.JButton InventoryBttn;
    private javax.swing.JButton Logout_Bttn;
    private javax.swing.JButton ReportsBttn;
    private javax.swing.JButton SalesBttn;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panel_load;
    // End of variables declaration//GEN-END:variables
}
