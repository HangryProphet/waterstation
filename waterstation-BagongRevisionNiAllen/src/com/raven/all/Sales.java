package com.raven.all;

import com.formdev.flatlaf.IntelliJTheme;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;


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
        populateCustomerComboBox();

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

        panelBorder1 = new com.raven.swing.PanelBorder();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ProductTable = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        CheckOutButton2 = new javax.swing.JButton();
        RemoveFromCartButton1 = new javax.swing.JButton();
        ClearCartButton1 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        SelectCustomerComboBox = new javax.swing.JComboBox<>();
        CustomerLabel1 = new javax.swing.JLabel();
        MethodComboBox1 = new javax.swing.JComboBox<>();
        MethodLabel1 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        AddToCartButton1 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        CartTable = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        TotalPriceLabel1 = new javax.swing.JLabel();
        DiscountedPriceLabel = new javax.swing.JLabel();
        DiscountLabel1 = new javax.swing.JLabel();
        TotalPrice = new javax.swing.JLabel();
        DiscountTextField = new javax.swing.JTextField();
        DiscountedPrice = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1192, 713));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1192, 713));

        ProductTable.setBackground(new java.awt.Color(153, 204, 255));
        ProductTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ProductTable.setForeground(new java.awt.Color(0, 74, 173));
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
        jScrollPane2.setViewportView(ProductTable);
        if (ProductTable.getColumnModel().getColumnCount() > 0) {
            ProductTable.getColumnModel().getColumn(0).setResizable(false);
            ProductTable.getColumnModel().getColumn(1).setResizable(false);
            ProductTable.getColumnModel().getColumn(2).setResizable(false);
            ProductTable.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 74, 173));
        jLabel3.setText("AVAILABLE PRODUCT");
        jPanel11.add(jLabel3);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setPreferredSize(new java.awt.Dimension(424, 102));

        CheckOutButton2.setBackground(new java.awt.Color(0, 74, 173));
        CheckOutButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CheckOutButton2.setForeground(new java.awt.Color(153, 204, 255));
        CheckOutButton2.setText("Check Out");
        CheckOutButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        CheckOutButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckOutButton2ActionPerformed(evt);
            }
        });

        RemoveFromCartButton1.setBackground(new java.awt.Color(153, 204, 255));
        RemoveFromCartButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        RemoveFromCartButton1.setForeground(new java.awt.Color(0, 74, 173));
        RemoveFromCartButton1.setText("Remove");
        RemoveFromCartButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        RemoveFromCartButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveFromCartButton1ActionPerformed(evt);
            }
        });

        ClearCartButton1.setBackground(new java.awt.Color(0, 74, 173));
        ClearCartButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ClearCartButton1.setForeground(new java.awt.Color(153, 204, 255));
        ClearCartButton1.setText("Clear Cart");
        ClearCartButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ClearCartButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearCartButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ClearCartButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(80, 80, 80)
                .addComponent(RemoveFromCartButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(66, 66, 66)
                .addComponent(CheckOutButton2)
                .addGap(132, 132, 132))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CheckOutButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClearCartButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RemoveFromCartButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        SelectCustomerComboBox.setBackground(new java.awt.Color(153, 204, 255));
        SelectCustomerComboBox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SelectCustomerComboBox.setForeground(new java.awt.Color(0, 74, 173));
        SelectCustomerComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Test", "TEST", "test", " " }));
        SelectCustomerComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectCustomerComboBoxActionPerformed(evt);
            }
        });

        CustomerLabel1.setBackground(new java.awt.Color(0, 74, 173));
        CustomerLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CustomerLabel1.setForeground(new java.awt.Color(0, 74, 173));
        CustomerLabel1.setText("Select Customer");

        MethodComboBox1.setBackground(new java.awt.Color(153, 204, 255));
        MethodComboBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        MethodComboBox1.setForeground(new java.awt.Color(0, 74, 173));
        MethodComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Delivery", "Pick-up" }));

        MethodLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        MethodLabel1.setForeground(new java.awt.Color(0, 74, 173));
        MethodLabel1.setText("Fullfilment Method:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(SelectCustomerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(CustomerLabel1))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(MethodLabel1))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(MethodComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CustomerLabel1)
                .addGap(18, 18, 18)
                .addComponent(SelectCustomerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(MethodLabel1)
                .addGap(18, 18, 18)
                .addComponent(MethodComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        AddToCartButton1.setBackground(new java.awt.Color(0, 74, 173));
        AddToCartButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        AddToCartButton1.setForeground(new java.awt.Color(153, 204, 255));
        AddToCartButton1.setText("Add To Cart");
        AddToCartButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        AddToCartButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToCartButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddToCartButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddToCartButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(153, 204, 255));

        CartTable.setBackground(new java.awt.Color(153, 204, 255));
        CartTable.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CartTable.setForeground(new java.awt.Color(0, 74, 173));
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
        jScrollPane4.setViewportView(CartTable);
        if (CartTable.getColumnModel().getColumnCount() > 0) {
            CartTable.getColumnModel().getColumn(0).setResizable(false);
            CartTable.getColumnModel().getColumn(1).setResizable(false);
            CartTable.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        TotalPriceLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TotalPriceLabel1.setForeground(new java.awt.Color(0, 74, 173));
        TotalPriceLabel1.setText("Total Price:");

        DiscountedPriceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DiscountedPriceLabel.setForeground(new java.awt.Color(0, 74, 173));
        DiscountedPriceLabel.setText("Discounted Price:");

        DiscountLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DiscountLabel1.setForeground(new java.awt.Color(0, 74, 173));
        DiscountLabel1.setText("Discount:");

        TotalPrice.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TotalPrice.setForeground(new java.awt.Color(0, 74, 173));
        TotalPrice.setText("0");

        DiscountTextField.setBackground(new java.awt.Color(153, 204, 255));
        DiscountTextField.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DiscountTextField.setForeground(new java.awt.Color(0, 74, 173));
        DiscountTextField.setText("0");
        DiscountTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DiscountTextFieldKeyReleased(evt);
            }
        });

        DiscountedPrice.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DiscountedPrice.setForeground(new java.awt.Color(0, 74, 173));
        DiscountedPrice.setText("0");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(DiscountedPriceLabel)
                    .addComponent(TotalPriceLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DiscountedPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addComponent(DiscountLabel1)
                .addGap(18, 18, 18)
                .addComponent(DiscountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalPriceLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DiscountedPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DiscountedPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DiscountLabel1)
                    .addComponent(DiscountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 74, 173));
        jLabel4.setText("SALES");
        jPanel18.add(jLabel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(59, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1041, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(panelBorder1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void CheckOutButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckOutButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckOutButton2ActionPerformed

    private void RemoveFromCartButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveFromCartButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RemoveFromCartButton1ActionPerformed

    private void ClearCartButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearCartButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClearCartButton1ActionPerformed

    private void SelectCustomerComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectCustomerComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SelectCustomerComboBoxActionPerformed

    private void AddToCartButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToCartButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddToCartButton1ActionPerformed

    private void DiscountTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiscountTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_DiscountTextFieldKeyReleased

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
    private void populateCustomerComboBox() {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement("SELECT customername FROM customertable");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String customerName = rs.getString("customername");
                comboBoxModel.addElement(customerName); // Add customer name to combo box model
            }

            // Set the updated model to the combo box
            SelectCustomerComboBox.setModel(comboBoxModel);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to populate customer names: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Log or handle the exception as needed
        } finally {
            // Close resources in the reverse order of their creation
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Do not close the connection here; leave it open for further use
        }
    }

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
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddToCartButton1;
    private javax.swing.JTable CartTable;
    private javax.swing.JButton CheckOutButton2;
    private javax.swing.JButton ClearCartButton1;
    private javax.swing.JLabel CustomerLabel1;
    private javax.swing.JLabel DiscountLabel1;
    private javax.swing.JTextField DiscountTextField;
    private javax.swing.JLabel DiscountedPrice;
    private javax.swing.JLabel DiscountedPriceLabel;
    private javax.swing.JComboBox<String> MethodComboBox1;
    private javax.swing.JLabel MethodLabel1;
    private javax.swing.JTable ProductTable;
    private javax.swing.JButton RemoveFromCartButton1;
    private javax.swing.JComboBox<String> SelectCustomerComboBox;
    private javax.swing.JLabel TotalPrice;
    private javax.swing.JLabel TotalPriceLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private com.raven.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
