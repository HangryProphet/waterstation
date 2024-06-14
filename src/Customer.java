
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class Customer extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> tableRowSorter;

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

    private void loadCustomerManagementTable() {
        // Initialize Connection, PreparedStatement, and ResultSet objects
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection
            connection = DatabaseConnection.getConnection();

            // Define the SQL query to retrieve all customer records
            String query = "SELECT * FROM customertable";
            preparedStatement = connection.prepareStatement(query);

            // Execute the query and get the result set
            resultSet = preparedStatement.executeQuery();

            // Update the table model with the retrieved data
            updateTableModel(resultSet);
        } catch (SQLException e) {
            // Print error message if loading customer data fails
            System.out.println("Failed to load customer data from the database.");
            e.printStackTrace();
        } finally {
            // Close the ResultSet, PreparedStatement, and Connection objects in reverse order
            closeResources(resultSet, preparedStatement, connection);
        }
    }

    private void updateTableModel(ResultSet resultSet) throws SQLException {
        // Get the table model for CustomerManagementTable
        DefaultTableModel model = (DefaultTableModel) CustomerManagementTable.getModel();
        model.setRowCount(0); // Clear existing rows

        // Iterate through the result set and add each customer record to the table model
        while (resultSet.next()) {
            int id = resultSet.getInt("UID");
            String customername = resultSet.getString("customername");
            String contactnumber = resultSet.getString("contactnumber");
            String address = resultSet.getString("address");
            String comments = resultSet.getString("comments");
            String borrowedItems = resultSet.getString("borroweditems");

            // Add a new row to the table model with the customer data
            model.addRow(new Object[]{id, customername, contactnumber, address, comments, borrowedItems});
        }
    }

    private void closeResources(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        // Close the ResultSet, PreparedStatement, and Connection objects in reverse order
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
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Customer() {
        initComponents();
        loadCustomerManagementTable();
        tableModel = (DefaultTableModel) CustomerManagementTable.getModel();
        CustomerManagementTable.getColumnModel().getColumn(4).setCellRenderer(new CustomCellRenderer());
        CustomerManagementTable.getColumnModel().getColumn(3).setCellRenderer(new CustomCellRenderer());

        // Adjust row height based on content in the "Customer Name" column
        for (int row = 0; row < CustomerManagementTable.getRowCount(); row++) {
            int rowHeight = CustomerManagementTable.getRowHeight();
            Component comp = CustomerManagementTable.prepareRenderer(CustomerManagementTable.getCellRenderer(row, 1), row, 1);
            rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            CustomerManagementTable.setRowHeight(row, rowHeight);
        }

        // Initialize table row sorter
        tableRowSorter = new TableRowSorter<>(tableModel);
        CustomerManagementTable.setRowSorter(tableRowSorter);

        // Add DocumentListener to CustomerSearchTextField for live filtering
        CustomerSearchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterCustomerTable(CustomerSearchTextField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterCustomerTable(CustomerSearchTextField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterCustomerTable(CustomerSearchTextField.getText());
            }
        });
    }

    private void filterCustomerTable(String searchText) {
        // If the search text is empty, show all rows
        if (searchText.trim().length() == 0) {
            tableRowSorter.setRowFilter(null);
        } else {
            // Filter rows based on the search text (ignore case)
            tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
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
        PhoneNumberTextField = new javax.swing.JTextField();
        CustomerNameTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        CommentsTextField = new javax.swing.JTextArea();
        AddressTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        SaveBttn = new javax.swing.JButton();
        UpdateBttn = new javax.swing.JButton();
        DeleteBttn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        CommentsLabel1 = new javax.swing.JLabel();
        BorrowedItemsComboBox = new javax.swing.JComboBox<>();
        CustomerListPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        CustomerManagementTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        CustomerSearchLabel = new javax.swing.JLabel();
        CustomerSearchTextField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();

        CustomerTabbedPane.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        AddCustPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        SearchLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        SearchLabel.setText("Search ID:");

        SearchIDTextField.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        SearchIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchIDTextFieldActionPerformed(evt);
            }
        });
        SearchIDTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SearchIDTextFieldKeyTyped(evt);
            }
        });

        Search_Bttn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Search_Bttn.setText("Search");
        Search_Bttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search_BttnActionPerformed(evt);
            }
        });

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

        PhoneNumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneNumberTextFieldActionPerformed(evt);
            }
        });
        PhoneNumberTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PhoneNumberTextFieldKeyTyped(evt);
            }
        });

        CustomerNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerNameTextFieldActionPerformed(evt);
            }
        });

        CommentsTextField.setColumns(20);
        CommentsTextField.setRows(5);
        jScrollPane2.setViewportView(CommentsTextField);

        AddressTextField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        AddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddressTextFieldActionPerformed(evt);
            }
        });

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
                    .addComponent(PhoneNumberTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CustomerNameTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AddressTextField)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
                .addGap(139, 139, 139))
        );
        AddCustPanel2Layout.setVerticalGroup(
            AddCustPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddCustPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(AddCustPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(AddCustPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PhoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(AddCustPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddCustPanel2Layout.createSequentialGroup()
                        .addGroup(AddCustPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddressLabel)
                            .addComponent(AddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        SaveBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveBttnActionPerformed(evt);
            }
        });

        UpdateBttn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        UpdateBttn.setText("Update");
        UpdateBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBttnActionPerformed(evt);
            }
        });

        DeleteBttn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        DeleteBttn.setText("Delete");
        DeleteBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBttnActionPerformed(evt);
            }
        });

        CommentsLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        CommentsLabel1.setText("Borrowed Items");

        BorrowedItemsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Slim", "Round", "Slim/Round" }));
        BorrowedItemsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrowedItemsComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BorrowedItemsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(CommentsLabel1)
                .addGap(70, 70, 70))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(CommentsLabel1)
                .addGap(32, 32, 32)
                .addComponent(BorrowedItemsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(SaveBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UpdateBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DeleteBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        CustomerManagementTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CustomerManagementTable.setModel(new javax.swing.table.DefaultTableModel(
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
        CustomerManagementTable.setRowHeight(50);
        jScrollPane3.setViewportView(CustomerManagementTable);

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout CustomerListPanelLayout = new javax.swing.GroupLayout(CustomerListPanel);
        CustomerListPanel.setLayout(CustomerListPanelLayout);
        CustomerListPanelLayout.setHorizontalGroup(
            CustomerListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CustomerListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CustomerListPanelLayout.setVerticalGroup(
            CustomerListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void SearchIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchIDTextFieldActionPerformed
        String searchID = SearchIDTextField.getText().trim();

        if (searchID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a customer ID to search.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM customertable WHERE customerID = ?")) {
            pstmt.setString(1, searchID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                CustomerNameTextField.setText(rs.getString("customerName"));
                PhoneNumberTextField.setText(rs.getString("phoneNumber"));
                AddressTextField.setText(rs.getString("address"));
            } else {
                JOptionPane.showMessageDialog(this, "Customer not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error searching customer: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SearchIDTextFieldActionPerformed

    private void CustomerNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CustomerNameTextFieldActionPerformed

    private void PhoneNumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneNumberTextFieldActionPerformed

    }//GEN-LAST:event_PhoneNumberTextFieldActionPerformed

    private void AddressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddressTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddressTextFieldActionPerformed

    private void SaveBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveBttnActionPerformed
        String customerName = CustomerNameTextField.getText().trim();
        String phoneNumber = PhoneNumberTextField.getText().trim();
        String address = AddressTextField.getText().trim();
        String comments = CommentsTextField.getText().trim(); // Assuming empty string for now
        String borrowedItems = (String) BorrowedItemsComboBox.getSelectedItem(); // Get selected item from combo box

        if (customerName.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Find the current maximum UID in the customertable
            int nextUID = findNextUID(conn);

            // Insert the new customer record with the incremented UID
            String insertQuery = "INSERT INTO customertable (UID, customername, contactnumber, address, comments, borroweditems) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setInt(1, nextUID);
                pstmt.setString(2, customerName);
                pstmt.setString(3, phoneNumber);
                pstmt.setString(4, address);
                pstmt.setString(5, comments); // Set comments field

                // Set borroweditems field based on JComboBox selection
                pstmt.setString(6, borrowedItems);

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    // Customer inserted successfully
                    JOptionPane.showMessageDialog(this, "Customer added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Clear input fields after successful insertion
                    CustomerNameTextField.setText("");
                    PhoneNumberTextField.setText("");
                    AddressTextField.setText("");

                    // Refresh the customer management table
                    loadCustomerManagementTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add customer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding customer: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Log or handle the exception as needed
        }
        loadCustomerManagementTable();
        clearCustomerFields();
    }

    private int findNextUID(Connection conn) throws SQLException {
        int nextUID = 1; // Default start value for UID

        String query = "SELECT MAX(UID) FROM customertable";
        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                nextUID = rs.getInt(1) + 1; // Get maximum UID and increment by 1
            } else {
                // If no records exist (result set is empty), nextUID remains 1
                nextUID = 1;
            }
        }

        return nextUID;
    }//GEN-LAST:event_SaveBttnActionPerformed

    private void UpdateBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBttnActionPerformed
        String customerID = SearchIDTextField.getText().trim();
        String customerName = CustomerNameTextField.getText().trim();
        String phoneNumber = PhoneNumberTextField.getText().trim();
        String address = AddressTextField.getText().trim();
        String comments = CommentsTextField.getText().trim();
        String borrowedItems = BorrowedItemsComboBox.getSelectedItem().toString();

        // Validate input fields
        if (customerID.isEmpty() || customerName.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Prepare SQL statement for updating customer details
            String query = "UPDATE customertable SET customername = ?, contactnumber = ?, address = ?, comments = ?, borroweditems = ? WHERE UID = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, customerName);
                pstmt.setString(2, phoneNumber);
                pstmt.setString(3, address);
                pstmt.setString(4, comments);
                pstmt.setString(5, borrowedItems);
                pstmt.setString(6, customerID);

                // Execute update
                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated > 0) {
                    // Update table model if update was successful
                    DefaultTableModel model = (DefaultTableModel) CustomerManagementTable.getModel();
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if (model.getValueAt(i, 0).equals(customerID)) {
                            model.setValueAt(customerName, i, 1);
                            model.setValueAt(phoneNumber, i, 2);
                            model.setValueAt(address, i, 3);
                            model.setValueAt(comments, i, 4);
                            model.setValueAt(borrowedItems, i, 5);
                            break;
                        }
                    }

                    JOptionPane.showMessageDialog(this, "Customer details updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update customer details.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating customer details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Log or handle the exception as needed
        }
        loadCustomerManagementTable();
        Search_BttnActionPerformed(evt);
        clearCustomerFields();

    }//GEN-LAST:event_UpdateBttnActionPerformed

    private void DeleteBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBttnActionPerformed
        String searchID = SearchIDTextField.getText().trim();

        if (searchID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a customer ID to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int customerUID = Integer.parseInt(searchID); // Assuming the UID is entered as an integer

        int confirmation = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this customer?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                // Step 1: Delete the customer record based on UID
                String deleteQuery = "DELETE FROM customertable WHERE UID = ?";
                try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
                    deleteStmt.setInt(1, customerUID);
                    int rowsAffected = deleteStmt.executeUpdate();

                    if (rowsAffected > 0) {
                        // Step 2: Decrement UID for subsequent records
                        decrementUIDs(conn, customerUID);

                        JOptionPane.showMessageDialog(this, "Customer deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                        // Optionally, update UI or perform other actions after successful deletion
                        loadCustomerManagementTable();
                        clearCustomerFields();
                    } else {
                        JOptionPane.showMessageDialog(this, "No customer found with the given UID.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting customer: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace(); // Log or handle the exception as needed
            }
        }
    }

    private void decrementUIDs(Connection conn, int deletedUID) throws SQLException {
        // Step to decrement UIDs for subsequent records
        String updateQuery = "UPDATE customertable SET UID = UID - 1 WHERE UID > ?";
        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
            updateStmt.setInt(1, deletedUID);
            updateStmt.executeUpdate();
        }
    }//GEN-LAST:event_DeleteBttnActionPerformed

    private void Search_BttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search_BttnActionPerformed
        // Get the customer ID from the SearchIDTextField
        String customerID = SearchIDTextField.getText().trim();

        if (customerID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a customer ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM customertable WHERE UID = ?")) {
            pstmt.setString(1, customerID);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    // Populate text fields with retrieved data
                    CustomerNameTextField.setText(resultSet.getString("customername"));
                    PhoneNumberTextField.setText(resultSet.getString("contactnumber"));
                    AddressTextField.setText(resultSet.getString("address"));
                    CommentsTextField.setText(resultSet.getString("comments"));

                    // Set BorrowedItemsComboBox based on retrieved borroweditems value
                    String borrowedItems = resultSet.getString("borroweditems");
                    if (borrowedItems != null && !borrowedItems.isEmpty()) {
                        BorrowedItemsComboBox.setSelectedItem(borrowedItems);
                    } else {
                        BorrowedItemsComboBox.setSelectedItem("None");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Customer not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error searching customer: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_Search_BttnActionPerformed

    private void clearCustomerFields() {
        CustomerNameTextField.setText("");
        PhoneNumberTextField.setText("");
        AddressTextField.setText("");
        CommentsTextField.setText("");
    }
    private void BorrowedItemsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrowedItemsComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BorrowedItemsComboBoxActionPerformed

    private void SearchIDTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchIDTextFieldKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume(); // Disallow non-numeric characters
        }
    }//GEN-LAST:event_SearchIDTextFieldKeyTyped

    private void PhoneNumberTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PhoneNumberTextFieldKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume(); // Disallow non-numeric characters
        }
    }//GEN-LAST:event_PhoneNumberTextFieldKeyTyped

    public class CustomCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            JTextArea textArea = new JTextArea();
            textArea.setText(value != null ? value.toString() : "");

            // Set properties for text area
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);

            // Set background and foreground colors based on selection
            if (isSelected) {
                textArea.setBackground(table.getSelectionBackground());
                textArea.setForeground(table.getSelectionForeground());
            } else {
                textArea.setBackground(table.getBackground());
                textArea.setForeground(table.getForeground());
            }

            return textArea;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddCustPanel1;
    private javax.swing.JPanel AddCustPanel2;
    private javax.swing.JLabel AddressLabel;
    private javax.swing.JTextField AddressTextField;
    private javax.swing.JComboBox<String> BorrowedItemsComboBox;
    private javax.swing.JLabel CommentsLabel;
    private javax.swing.JLabel CommentsLabel1;
    private javax.swing.JTextArea CommentsTextField;
    private javax.swing.JPanel CreateCustomerPanel;
    private javax.swing.JPanel CustomerListPanel;
    private javax.swing.JTable CustomerManagementTable;
    private javax.swing.JTextField CustomerNameTextField;
    private javax.swing.JLabel CustomerSearchLabel;
    private javax.swing.JTextField CustomerSearchTextField;
    private javax.swing.JTabbedPane CustomerTabbedPane;
    private javax.swing.JButton DeleteBttn;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JLabel PhoneNumber;
    private javax.swing.JTextField PhoneNumberTextField;
    private javax.swing.JButton SaveBttn;
    private javax.swing.JTextField SearchIDTextField;
    private javax.swing.JLabel SearchLabel;
    private javax.swing.JButton Search_Bttn;
    private javax.swing.JButton UpdateBttn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
