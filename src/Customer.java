
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
public class Customer extends javax.swing.JPanel {

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
    public Customer() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CustomerTabbedPane = new javax.swing.JTabbedPane();
        CreateCustomerPanel = new javax.swing.JPanel();
        AddCustPanel1 = new javax.swing.JPanel();
        SearchLabel = new javax.swing.JLabel();
        SearchIDTextField = new javax.swing.JTextField();
        Search_Bttn = new javax.swing.JButton();
        AddCustPanel2 = new javax.swing.JPanel();
        NameLabel = new javax.swing.JLabel();
        PhoneNumber = new javax.swing.JLabel();
        AddressLabel = new javax.swing.JLabel();
        CommentsLabel = new javax.swing.JLabel();
        PNumberTextField = new javax.swing.JTextField();
        NameTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        AddressField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        SaveBttn = new javax.swing.JButton();
        UpdateBttn = new javax.swing.JButton();
        DeleteBttn = new javax.swing.JButton();
        CustomerListPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ManagementTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        CustomerSearchLabel = new javax.swing.JLabel();
        CustomerSearchTextField = new javax.swing.JTextField();
        BorrowedItemLabel = new javax.swing.JLabel();
        ItemsBorrowedComboBox = new javax.swing.JComboBox<>();

        CustomerTabbedPane.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        AddCustPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        SearchLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        SearchLabel.setText("Search ID:");

        SearchIDTextField.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        Search_Bttn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Search_Bttn.setText("Search");

        javax.swing.GroupLayout AddCustPanel1Layout = new javax.swing.GroupLayout(AddCustPanel1);
        AddCustPanel1.setLayout(AddCustPanel1Layout);
        AddCustPanel1Layout.setHorizontalGroup(
            AddCustPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddCustPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SearchLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SearchIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Search_Bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AddCustPanel1Layout.setVerticalGroup(
            AddCustPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddCustPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(AddCustPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Search_Bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        AddCustPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        NameLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        NameLabel.setText("Name:");

        PhoneNumber.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        PhoneNumber.setText("Phone Number:");

        AddressLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        AddressLabel.setText("Address:");

        CommentsLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        CommentsLabel.setText("Comments:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        AddressField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout AddCustPanel2Layout = new javax.swing.GroupLayout(AddCustPanel2);
        AddCustPanel2.setLayout(AddCustPanel2Layout);
        AddCustPanel2Layout.setHorizontalGroup(
            AddCustPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddCustPanel2Layout.createSequentialGroup()
                .addGroup(AddCustPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddCustPanel2Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(NameLabel))
                    .addGroup(AddCustPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(AddCustPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CommentsLabel)
                            .addComponent(PhoneNumber)
                            .addComponent(AddressLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddCustPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PNumberTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NameTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AddressField)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
                .addGap(139, 139, 139))
        );
        AddCustPanel2Layout.setVerticalGroup(
            AddCustPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddCustPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(AddCustPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(AddCustPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(AddCustPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddCustPanel2Layout.createSequentialGroup()
                        .addGroup(AddCustPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddressLabel)
                            .addComponent(AddressField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(99, 99, 99)
                        .addComponent(CommentsLabel)
                        .addGap(110, 110, 110))
                    .addGroup(AddCustPanel2Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jScrollPane2)
                        .addGap(75, 75, 75))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        SaveBttn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        SaveBttn.setText("Save");

        UpdateBttn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        UpdateBttn.setText("Update");

        DeleteBttn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        DeleteBttn.setText("Delete");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DeleteBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(SaveBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UpdateBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(SaveBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(UpdateBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(DeleteBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CreateCustomerPanelLayout = new javax.swing.GroupLayout(CreateCustomerPanel);
        CreateCustomerPanel.setLayout(CreateCustomerPanelLayout);
        CreateCustomerPanelLayout.setHorizontalGroup(
            CreateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateCustomerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CreateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddCustPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(CreateCustomerPanelLayout.createSequentialGroup()
                        .addComponent(AddCustPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        CreateCustomerPanelLayout.setVerticalGroup(
            CreateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateCustomerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddCustPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CreateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddCustPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        CustomerTabbedPane.addTab("Add Customer", CreateCustomerPanel);

        ManagementTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ManagementTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer's ID", "Customer's Name", "Contact Number", "Address", "Alias/Comments", "Borrowed Items"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(ManagementTable);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        CustomerSearchLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        CustomerSearchLabel.setText("Search:");

        CustomerSearchTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(CustomerSearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CustomerSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomerSearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomerSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        BorrowedItemLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        BorrowedItemLabel.setText("Set Borrowed Items");

        ItemsBorrowedComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Slim", "Round", "Both" }));

        javax.swing.GroupLayout CustomerListPanelLayout = new javax.swing.GroupLayout(CustomerListPanel);
        CustomerListPanel.setLayout(CustomerListPanelLayout);
        CustomerListPanelLayout.setHorizontalGroup(
            CustomerListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CustomerListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
                    .addGroup(CustomerListPanelLayout.createSequentialGroup()
                        .addComponent(BorrowedItemLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(CustomerListPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(ItemsBorrowedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CustomerListPanelLayout.setVerticalGroup(
            CustomerListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BorrowedItemLabel)
                .addGap(18, 18, 18)
                .addComponent(ItemsBorrowedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );

        CustomerTabbedPane.addTab("Customer Management", CustomerListPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CustomerTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CustomerTabbedPane)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddCustPanel1;
    private javax.swing.JPanel AddCustPanel2;
    private javax.swing.JTextField AddressField;
    private javax.swing.JLabel AddressLabel;
    private javax.swing.JLabel BorrowedItemLabel;
    private javax.swing.JLabel CommentsLabel;
    private javax.swing.JPanel CreateCustomerPanel;
    private javax.swing.JPanel CustomerListPanel;
    private javax.swing.JLabel CustomerSearchLabel;
    private javax.swing.JTextField CustomerSearchTextField;
    private javax.swing.JTabbedPane CustomerTabbedPane;
    private javax.swing.JButton DeleteBttn;
    private javax.swing.JComboBox<String> ItemsBorrowedComboBox;
    private javax.swing.JTable ManagementTable;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JTextField NameTextField;
    private javax.swing.JTextField PNumberTextField;
    private javax.swing.JLabel PhoneNumber;
    private javax.swing.JButton SaveBttn;
    private javax.swing.JTextField SearchIDTextField;
    private javax.swing.JLabel SearchLabel;
    private javax.swing.JButton Search_Bttn;
    private javax.swing.JButton UpdateBttn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
