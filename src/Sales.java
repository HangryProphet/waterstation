import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class Sales extends javax.swing.JPanel {
    
    
    public class DatabaseConnection {
        // Database URL, username, and password

        private static final String URL = "jdbc:mysql://localhost:3306/waterstation";
        private static final String USER = "root";
        private static final String PASSWORD = "";

        // Single instance of the connection
        private static Connection connection = null;

        // Method to establish a connection to the database
        public static Connection getConnection() {
            if (connection == null) {
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
            }
            return connection;
        }

        // Method to close the connection (if needed)
        public static void closeConnection() {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    System.out.println("Failed to close the database connection.");
                    e.printStackTrace();
                }
            }
        }

        // Method to manually reconnect if needed
        public static void reconnect() {
            closeConnection();
            getConnection();
        }
    }

    private void loadCartTable() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM carttable";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            DefaultTableModel model = (DefaultTableModel) CartTable.getModel();

            while (resultSet.next()) {
                String name = resultSet.getString("productname");
                String qty = resultSet.getString("qty");
                String price = resultSet.getString("price");

                model.addRow(new Object[]{name, qty, price});
            }
        } catch (SQLException e) {
            System.out.println("Failed to load cart items from database.");
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
  public static class NumberOnlyFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) {
            return;
        }
        String newValue = getNewValue(fb, offset, string, attr);
        if (isNumericAndInRange(newValue)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) {
            return;
        }
        String newValue = getNewValue(fb, offset, text, attrs);
        if (isNumericAndInRange(newValue)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
        Document doc = fb.getDocument();
        if (doc.getLength() == 0) {
            fb.insertString(0, "0", null); // Set to 0 if the field is empty
        }
    }

    private boolean isNumericAndInRange(String text) {
        if (text.isEmpty()) {
            return true;
        }
        try {
            int value = Integer.parseInt(text);
            return value >= 0 && value <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String getNewValue(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, text);
        return sb.toString();
    }
}

    public Sales() {
        initComponents();
        loadProductTable();
        loadCartTable();
        updateTotalAndDiscountedPrice();
        
        DiscountTextField.setText("0");
        ((AbstractDocument) DiscountTextField.getDocument()).setDocumentFilter(new NumberOnlyFilter());
   
    }

     public void updateProductTableQuantity(String productName, int newQty) {
        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement("UPDATE productTable SET quantity =? WHERE productName =?")) {
            pstmt.setInt(1, newQty);
            pstmt.setString(2, productName);
            pstmt.executeUpdate();
            System.out.println("Quantity updated successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to update the quantity.");
            e.printStackTrace();
        }
    }
     
    private void loadProductTable() {
        DefaultTableModel model = (DefaultTableModel) ProductTable.getModel();
        // Clear existing data in the table
        //UPDATE THE PRODUCT JTABLE
        model.setRowCount(0);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Inventory.DatabaseConnection.getConnection();
            String query = "SELECT * FROM inventory";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("productid");
                String name = resultSet.getString("productname");
                String qty = resultSet.getString("qty");
                String price = resultSet.getString("price");

                model.addRow(new Object[]{id, name, qty, price});
            }
        } catch (SQLException e) {
            System.out.println("Failed to load inventory from database.");
            e.printStackTrace();
        } finally {
            // Close database resources
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProductTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        CheckOutButton1 = new javax.swing.JButton();
        RemoveFromCartButton = new javax.swing.JButton();
        ClearCartButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        SelectCustomerComboBox = new javax.swing.JComboBox<>();
        CustomerLabel = new javax.swing.JLabel();
        MethodComboBox = new javax.swing.JComboBox<>();
        MethodLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        AddToCartButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        CartTable = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        TotalPriceLabel = new javax.swing.JLabel();
        DiscountedPriceLabel = new javax.swing.JLabel();
        DiscountLabel = new javax.swing.JLabel();
        TotalPrice = new javax.swing.JLabel();
        DiscountTextField = new javax.swing.JTextField();
        DiscountedPrice = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1094, 720));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        ProductTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Stocks", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ProductTable.setRowHeight(50);
        ProductTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(ProductTable);
        if (ProductTable.getColumnModel().getColumnCount() > 0) {
            ProductTable.getColumnModel().getColumn(0).setResizable(false);
            ProductTable.getColumnModel().getColumn(1).setResizable(false);
            ProductTable.getColumnModel().getColumn(2).setResizable(false);
            ProductTable.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("AVAILABLE PRODUCT");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel1);

        CheckOutButton1.setText("Check Out");
        CheckOutButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        CheckOutButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckOutButton1ActionPerformed(evt);
            }
        });

        RemoveFromCartButton.setText("Remove");
        RemoveFromCartButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        RemoveFromCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveFromCartButtonActionPerformed(evt);
            }
        });

        ClearCartButton.setText("Clear Cart");
        ClearCartButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ClearCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearCartButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(ClearCartButton, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addGap(89, 89, 89)
                .addComponent(RemoveFromCartButton, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addGap(105, 105, 105)
                .addComponent(CheckOutButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addGap(88, 88, 88))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CheckOutButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(RemoveFromCartButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ClearCartButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));

        SelectCustomerComboBox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SelectCustomerComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Test", "TEST", "test", " " }));
        SelectCustomerComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectCustomerComboBoxActionPerformed(evt);
            }
        });

        CustomerLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CustomerLabel.setText("Select Customer");

        MethodComboBox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        MethodComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Delivery", "Pick-up" }));

        MethodLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        MethodLabel.setText("Fullfilment Method:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(SelectCustomerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(CustomerLabel))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(MethodLabel))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(MethodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CustomerLabel)
                .addGap(18, 18, 18)
                .addComponent(SelectCustomerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(MethodLabel)
                .addGap(18, 18, 18)
                .addComponent(MethodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));

        AddToCartButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        AddToCartButton.setText("Add To Cart");
        AddToCartButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        AddToCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToCartButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddToCartButton, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddToCartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        CartTable.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Quantity", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        CartTable.setRowHeight(50);
        CartTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(CartTable);
        if (CartTable.getColumnModel().getColumnCount() > 0) {
            CartTable.getColumnModel().getColumn(0).setResizable(false);
            CartTable.getColumnModel().getColumn(1).setResizable(false);
            CartTable.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addContainerGap())
        );

        TotalPriceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TotalPriceLabel.setText("Total Price:");

        DiscountedPriceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DiscountedPriceLabel.setText("Discounted Price:");

        DiscountLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DiscountLabel.setText("Discount:");

        TotalPrice.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TotalPrice.setText("0");

        DiscountTextField.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DiscountTextField.setText("0");
        DiscountTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DiscountTextFieldKeyReleased(evt);
            }
        });

        DiscountedPrice.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DiscountedPrice.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(DiscountedPriceLabel)
                    .addComponent(TotalPriceLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DiscountedPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(TotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addComponent(DiscountLabel)
                .addGap(18, 18, 18)
                .addComponent(DiscountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DiscountedPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DiscountedPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DiscountLabel)
                    .addComponent(DiscountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void ClearCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearCartButtonActionPerformed
        int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear the cart?", "Clear Cart", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            DefaultTableModel cartModel = (DefaultTableModel) CartTable.getModel();

            // Update the carttable database
            Connection connection = null;
            PreparedStatement deleteStatement = null;
            PreparedStatement updateInventoryStatement = null;

            try {
                connection = DatabaseConnection.getConnection();
                if (connection != null) {
                    connection.setAutoCommit(false); // Start transaction

                    String updateInventoryQuery = "UPDATE inventory SET qty = qty +? WHERE productname =?";
                    updateInventoryStatement = connection.prepareStatement(updateInventoryQuery);

                    // Update inventory for each item in the cart
                    for (int row = 0; row < cartModel.getRowCount(); row++) {
                        String productName = (String) cartModel.getValueAt(row, 0);
                        int quantity = Integer.parseInt(cartModel.getValueAt(row, 1).toString()); // Convert to Integer

                        updateInventoryStatement.setInt(1, quantity);
                        updateInventoryStatement.setString(2, productName);
                        updateInventoryStatement.executeUpdate();
                    }

                    // Clear the carttable database
                    String deleteQuery = "DELETE FROM carttable";
                    deleteStatement = connection.prepareStatement(deleteQuery);
                    deleteStatement.executeUpdate();

                    connection.commit(); // Commit transaction

                    // Clear the cart table in the UI
                    cartModel.setRowCount(0);

                    // Refresh the ProductTable
                    loadProductTable();

                    JOptionPane.showMessageDialog(this, "Cart cleared successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println("Failed to establish database connection.");
                }
            } catch (SQLException e) {
                try {
                    if (connection != null) {
                        connection.rollback(); // Rollback transaction on error
                    }
                } catch (SQLException ex) {
                    System.out.println("Failed to rollback: " + ex.getMessage());
                    ex.printStackTrace();
                }
                System.out.println("Failed to clear cart: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // Close database resources
                if (deleteStatement != null) {
                    try {
                        deleteStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (updateInventoryStatement != null) {
                    try {
                        updateInventoryStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            // Update the total and discounted prices
            updateTotalAndDiscountedPrice();
        } else {
            JOptionPane.showMessageDialog(null, "No items were removed from the cart.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_ClearCartButtonActionPerformed
private int generateReceiptId() {
    // You can store the current receipt ID in a file or database to ensure uniqueness
    // For now, let's assume the next ID is 1 greater than the maximum existing ID in the database
    int nextId = 1; // Default to 1 if no records exist

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        connection = DatabaseConnection.getConnection();
        if (connection != null) {
            String query = "SELECT MAX(receiptid) FROM reports";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nextId = resultSet.getInt(1) + 1;
            }
        } else {
            System.out.println("Failed to establish database connection.");
        }
    } catch (SQLException e) {
        System.out.println("Failed to generate receipt ID: " + e.getMessage());
        e.printStackTrace();
    } finally {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return nextId;
}
    // CHECK OUT BUTTON INCOMPLETE
    private void CheckOutButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckOutButton1ActionPerformed
    DefaultTableModel cartModel = (DefaultTableModel) CartTable.getModel();
    int rowCount = cartModel.getRowCount();

    if (rowCount == 0) {
        JOptionPane.showMessageDialog(this, "Cart is empty.", "Checkout Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Connection connection = null;
    PreparedStatement insertStatement = null;
    PreparedStatement deleteStatement = null;

    try {
        connection = DatabaseConnection.getConnection();
        if (connection != null) {
            connection.setAutoCommit(false); // Start transaction

            // Delete existing cart items from the database
            String deleteQuery = "DELETE FROM carttable";
            deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.executeUpdate();

            // Insert new cart items into the database
            String insertQuery = "INSERT INTO reports (receiptid, productname, qty, discount, price, date, customer, method) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            insertStatement = connection.prepareStatement(insertQuery);

            // Get current date and format it
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
            String currentDate = sdf.format(now);

            // Concatenate product names with quantities and newline character
            StringBuilder productNamesBuilder = new StringBuilder();
            int totalQty = 0;

            for (int row = 0; row < rowCount; row++) {
                String productName = (String) cartModel.getValueAt(row, 0);
                int qty = Integer.parseInt(cartModel.getValueAt(row, 1).toString());
                productNamesBuilder.append(productName).append(" (").append(qty).append(")").append("\n");
                totalQty += qty;
            }

            String productNames = productNamesBuilder.toString().trim();
            double totalPrice = Double.parseDouble(TotalPrice.getText().replace("₱", "").trim());
            double discount = Double.parseDouble(DiscountTextField.getText()); // Fetch the current number on DiscountTextField
            String methods = MethodComboBox.getSelectedItem().toString();

            // Set parameters for the prepared statement
            insertStatement.setInt(1, generateReceiptId());
            insertStatement.setString(2, productNames);
            insertStatement.setInt(3, totalQty);
            insertStatement.setDouble(4, discount); // Store the discount value in the database
            insertStatement.setDouble(5, totalPrice);
            insertStatement.setString(6, currentDate);
            insertStatement.setString(7, ""); // Leave customer blank for now
            insertStatement.setString(8, methods);

            // Execute the insertion query
            insertStatement.executeUpdate();
            connection.commit(); // Commit transaction

            JOptionPane.showMessageDialog(this, "Checkout successful. Reports updated.", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear the cart table after successful checkout
            cartModel.setRowCount(0);

            // Refresh the ProductTable (if applicable)
            loadProductTable();

            // Reset total and discounted prices
            updateTotalAndDiscountedPrice();
        } else {
            System.out.println("Failed to establish database connection.");
        }
    } catch (SQLException e) {
        try {
            if (connection != null) {
                connection.rollback(); // Rollback transaction on error
            }
        } catch (SQLException ex) {
            System.out.println("Failed to rollback: " + ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("Failed to checkout: " + e.getMessage());
        e.printStackTrace();
    } finally {
        // Close database resources
        if (insertStatement != null) {
            try {
                insertStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (deleteStatement != null) {
            try {
                deleteStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    }//GEN-LAST:event_CheckOutButton1ActionPerformed
    
    private void RemoveFromCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveFromCartButtonActionPerformed
        // Get the selected rows from the CartTable
        int[] selectedRows = CartTable.getSelectedRows();

        if (selectedRows.length > 0) {
            // Confirm with the user if they want to remove the selected items
            int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the selected item(s) from the cart?", "Remove From Cart", JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
                DefaultTableModel cartModel = (DefaultTableModel) CartTable.getModel();
                Connection connection = null;
                PreparedStatement deleteStatement = null;
                PreparedStatement updateInventoryStatement = null;

                try {
                    // Open the database connection
                    connection = DatabaseConnection.getConnection();
                    connection.setAutoCommit(false); // Start transaction

                    if (connection != null) {
                        String deleteQuery = "DELETE FROM carttable WHERE productname =? AND qty =? AND price =?";
                        deleteStatement = connection.prepareStatement(deleteQuery);

                        String updateInventoryQuery = "UPDATE inventory SET qty = qty +? WHERE productname =?";
                        updateInventoryStatement = connection.prepareStatement(updateInventoryQuery);

                        // Check if the connection is established
                        if (!connection.isClosed()) {
                            for (int row : selectedRows) {
                                String name = (String) cartModel.getValueAt(row, 0);
                                int qty = Integer.parseInt(cartModel.getValueAt(row, 1).toString()); // Convert to Integer
                                double price = Double.parseDouble(cartModel.getValueAt(row, 2).toString()); // Convert to Double

                                // Set parameters for the prepared statement
                                deleteStatement.setString(1, name);
                                deleteStatement.setInt(2, qty);
                                deleteStatement.setDouble(3, price);

                                // Execute the deletion query
                                deleteStatement.executeUpdate();

                                // Update inventory
                                updateInventoryStatement.setInt(1, qty);
                                updateInventoryStatement.setString(2, name);
                                updateInventoryStatement.executeUpdate();
                            }

                            connection.commit(); // Commit transaction

                            // Remove the selected rows from the table (in reverse order to avoid index issues)
                            for (int i = selectedRows.length - 1; i >= 0; i--) {
                                cartModel.removeRow(selectedRows[i]);
                            }

                            // Refresh the ProductTable
                            loadProductTable();

                            JOptionPane.showMessageDialog(this, "Selected items removed from cart successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            System.out.println("Failed to establish database connection.");
                        }
                    } else {
                        System.out.println("Failed to establish database connection.");
                    }
                } catch (SQLException e) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        System.out.println("Failed to rollback: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                    System.out.println("Failed to remove item(s) from cart: " + e.getMessage());
                    e.printStackTrace();
                } finally {
                    // Close database resources
                    if (deleteStatement != null) {
                        try {
                            deleteStatement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (updateInventoryStatement != null) {
                        try {
                            updateInventoryStatement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            // Update the total and discounted prices
            updateTotalAndDiscountedPrice();
        } else {
            JOptionPane.showMessageDialog(null, "No item selected to remove from the cart.", "Error", JOptionPane.ERROR_MESSAGE);
        }
     }//GEN-LAST:event_RemoveFromCartButtonActionPerformed

    private void SelectCustomerComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectCustomerComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SelectCustomerComboBoxActionPerformed

    private void AddToCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToCartButtonActionPerformed
       int selectedRow = ProductTable.getSelectedRow();

    if (selectedRow != -1) {
        // Retrieve product details from the selected row
        DefaultTableModel productModel = (DefaultTableModel) ProductTable.getModel();
        DefaultTableModel cartModel = (DefaultTableModel) CartTable.getModel();

        String name = (String) productModel.getValueAt(selectedRow, 1); // Assuming column 1 is product name
        double price = Double.parseDouble(productModel.getValueAt(selectedRow, 3).toString()); // Assuming column 4 is product price
        int availableStock = Integer.parseInt(productModel.getValueAt(selectedRow, 2).toString()); // Assuming column 3 is available stock

        // Check if the product is already in the cart
        boolean productInCart = false;
        int cartRowIndex = -1;
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            if (cartModel.getValueAt(i, 0).equals(name)) {
                productInCart = true;
                cartRowIndex = i;
                break;
            }
        }

        // Prompt the user to input the quantity of stock to add
        String stockInput = JOptionPane.showInputDialog(this, "Enter the quantity of stock to add for " + name + ":", "Add Stock", JOptionPane.QUESTION_MESSAGE);
        if (stockInput != null && !stockInput.isEmpty()) {
            try {
                int qtyToAdd = Integer.parseInt(stockInput.trim());

                // Check if the quantity to add is valid (positive) and available
                if (qtyToAdd > 0 && qtyToAdd <= availableStock) {
                    Connection connection = null;
                    PreparedStatement updateCartStatement = null;
                    PreparedStatement insertCartStatement = null;
                    PreparedStatement updateInventoryStatement = null;

                    try {
                        connection = DatabaseConnection.getConnection();
                        connection.setAutoCommit(false); // Start transaction

                        if (productInCart) {
                            // Update the quantity in the cart table
                            int currentQty = Integer.parseInt(cartModel.getValueAt(cartRowIndex, 1).toString());
                            cartModel.setValueAt(currentQty + qtyToAdd, cartRowIndex, 1);

                            // Update the quantity in the carttable database table
                            String updateCartQuery = "UPDATE carttable SET qty = qty + ? WHERE productname = ?";
                            updateCartStatement = connection.prepareStatement(updateCartQuery);
                            updateCartStatement.setInt(1, qtyToAdd);
                            updateCartStatement.setString(2, name);
                            updateCartStatement.executeUpdate();
                        } else {
                            // Add the product to the CartTable
                            cartModel.addRow(new Object[]{name, qtyToAdd, price});

                            // Insert the product into the carttable database table
                            String insertCartQuery = "INSERT INTO carttable (productname, qty, price) VALUES (?, ?, ?)";
                            insertCartStatement = connection.prepareStatement(insertCartQuery);
                            insertCartStatement.setString(1, name);
                            insertCartStatement.setInt(2, qtyToAdd);
                            insertCartStatement.setDouble(3, price);
                            insertCartStatement.executeUpdate();
                        }

                        // Update inventory
                        String updateInventoryQuery = "UPDATE inventory SET qty = qty - ? WHERE productname = ?";
                        updateInventoryStatement = connection.prepareStatement(updateInventoryQuery);
                        updateInventoryStatement.setInt(1, qtyToAdd);
                        updateInventoryStatement.setString(2, name);
                        updateInventoryStatement.executeUpdate();

                        connection.commit(); // Commit transaction

                        // Inform the user about the stock update
                        JOptionPane.showMessageDialog(this, qtyToAdd + " units of " + name + " added to the cart.", "Stock Added", JOptionPane.INFORMATION_MESSAGE);
                        // Refresh the product table to reflect the updated stock
                        loadProductTable();

                        // Update the total price and discounted price
                        updateTotalAndDiscountedPrice();

                    } catch (SQLException e) {
                        if (connection != null) {
                            try {
                                connection.rollback();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                        JOptionPane.showMessageDialog(this, "Failed to execute SQL query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    } finally {
                        if (updateCartStatement != null) {
                            try {
                                updateCartStatement.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        if (insertCartStatement != null) {
                            try {
                                insertCartStatement.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        if (updateInventoryStatement != null) {
                            try {
                                updateInventoryStatement.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (qtyToAdd <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid positive quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough stock available.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid quantity entered.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "No product selected to add.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_AddToCartButtonActionPerformed

   private void updateTotalAndDiscountedPrice() {
        DefaultTableModel cartModel = (DefaultTableModel) CartTable.getModel();
    double totalPrice = 0.0;

    for (int i = 0; i < cartModel.getRowCount(); i++) {
        int qty = Integer.parseInt(cartModel.getValueAt(i, 1).toString());
        double price = Double.parseDouble(cartModel.getValueAt(i, 2).toString());
        totalPrice += qty * price;
    }

     double discount = 0.0;
    if (!DiscountTextField.getText().isEmpty()) {
        try {
            String discountText = DiscountTextField.getText();
            if (discountText.endsWith("%")) {
                discountText = discountText.replace("%", "");
            }
            discount = Double.parseDouble(discountText) / 100.0;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid discount entered.", "Error", JOptionPane.ERROR_MESSAGE);
            DiscountTextField.setText("0");
            discount = 0.0;
        }
    }

    if (discount == 0.0) {
        DiscountedPrice.setText("₱0.00");
        TotalPrice.setText(String.format("₱%.2f", totalPrice));
    } else {
        double discountedPrice = totalPrice - (totalPrice * discount);
        DiscountedPrice.setText(String.format("₱%.2f", discountedPrice));
        TotalPrice.setText(String.format("₱%.2f", discountedPrice));
    }
}

    private void DiscountTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiscountTextFieldKeyReleased
          updateTotalAndDiscountedPrice();
    
    }//GEN-LAST:event_DiscountTextFieldKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddToCartButton;
    private javax.swing.JTable CartTable;
    private javax.swing.JButton CheckOutButton1;
    private javax.swing.JButton ClearCartButton;
    private javax.swing.JLabel CustomerLabel;
    private javax.swing.JLabel DiscountLabel;
    private javax.swing.JTextField DiscountTextField;
    private javax.swing.JLabel DiscountedPrice;
    private javax.swing.JLabel DiscountedPriceLabel;
    private javax.swing.JComboBox<String> MethodComboBox;
    private javax.swing.JLabel MethodLabel;
    private javax.swing.JTable ProductTable;
    private javax.swing.JButton RemoveFromCartButton;
    private javax.swing.JComboBox<String> SelectCustomerComboBox;
    private javax.swing.JLabel TotalPrice;
    private javax.swing.JLabel TotalPriceLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
