
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

;

public class Reports extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> tableRowSorter;
           
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
    }
    /**
     * Creates new form Reports
     */
    public Reports() {
        initComponents();
        loadReportTable();
        
        tableModel = (DefaultTableModel) ReportTable.getModel();
        tableRowSorter = new TableRowSorter<>(tableModel);
        ReportTable.setRowSorter(tableRowSorter);

        ReportTable.getColumnModel().getColumn(1).setCellRenderer(new CustomCellRenderer());

        // Adjust row height based on content in the "Product Name" column
        for (int row = 0; row < ReportTable.getRowCount(); row++) {
            int rowHeight = ReportTable.getRowHeight();
            Component comp = ReportTable.prepareRenderer(ReportTable.getCellRenderer(row, 1), row, 1);
            rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            ReportTable.setRowHeight(row, rowHeight);
        }

        SearchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable(SearchField.getText());
                updateTotalPrice(ReportTable, 4, TotalPriceValue);
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(SearchField.getText());
                updateTotalPrice(ReportTable, 4, TotalPriceValue);
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable(SearchField.getText());
                updateTotalPrice(ReportTable, 4, TotalPriceValue);
                
            }
                
        });
        tableRowSorter.addRowSorterListener(new RowSorterListener() {
            @Override
            public void sorterChanged(RowSorterEvent e) {
                updateTotalPrice(ReportTable, 4, TotalPriceValue);
        }
    });
   }
            

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ExportPDF_Bttn = new javax.swing.JButton();
        UpdateTable = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        SearchLabel = new javax.swing.JLabel();
        SearchField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ReportTable = new javax.swing.JTable();
        TotalLabel = new javax.swing.JLabel();
        TotalPriceValue = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1094, 720));

        ExportPDF_Bttn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ExportPDF_Bttn.setText("Export As PDF");
        ExportPDF_Bttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportPDF_BttnActionPerformed(evt);
            }
        });

        UpdateTable.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        UpdateTable.setText("Refresh Table");
        UpdateTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateTableActionPerformed(evt);
            }
        });

        SearchLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        SearchLabel.setText("Search:");

        SearchField.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        SearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchFieldActionPerformed(evt);
            }
        });

        ReportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Receipt Number", "Product", "Quantity", "Discount (%)", "Total Price", "Date/Time", "Customer", "Fullfillment Method"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ReportTable.setRowHeight(100);
        jScrollPane1.setViewportView(ReportTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(SearchLabel)
                .addGap(18, 18, 18)
                .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SearchLabel)
                    .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TotalLabel.setText("Total Price:");

        TotalPriceValue.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UpdateTable, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ExportPDF_Bttn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 363, Short.MAX_VALUE)
                .addComponent(TotalLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TotalPriceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(TotalLabel))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(UpdateTable, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ExportPDF_Bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(TotalPriceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void updateReportTable() {
        DefaultTableModel reportTableModel = (DefaultTableModel) ReportTable.getModel();
        reportTableModel.setRowCount(0); // Clear the table before updating

        Connection connection = null;
        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection != null) {
                String selectQuery = "SELECT * FROM reports";
                selectStatement = connection.prepareStatement(selectQuery);
                resultSet = selectStatement.executeQuery();

                while (resultSet.next()) {
                    int receiptId = resultSet.getInt("receiptid");
                    String productName = resultSet.getString("productname");
                    int qty = resultSet.getInt("qty");
                    int discount = resultSet.getInt("discount");
                    double price = resultSet.getInt("price");
                    String date = resultSet.getString("date");
                    String customer = resultSet.getString("customer");
                    String methods = resultSet.getString("method");

                    // Add the fetched data to the table model
                    reportTableModel.addRow(new Object[]{receiptId, productName, qty, discount, price, date, customer, methods});
                }
            } else {
                System.out.println("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to update ReportTable: " + e.getMessage());
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
            if (selectStatement != null) {
                try {
                    selectStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void UpdateTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateTableActionPerformed
        updateReportTable();
    }//GEN-LAST:event_UpdateTableActionPerformed

    private void ExportPDF_BttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportPDF_BttnActionPerformed
        exportTableDataToPDF();
    }//GEN-LAST:event_ExportPDF_BttnActionPerformed

    private void SearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchFieldActionPerformed

    private void exportTableDataToPDF() {
           if (ReportTable.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Error: The table is empty. Please add data to the table before exporting to PDF.");
        return;
    }

    int confirmed = JOptionPane.showConfirmDialog(
        null, 
        "Are you sure you want to export the current table to PDF?", 
        "Confirm Export", 
        JOptionPane.YES_NO_OPTION
    );

    if (confirmed == JOptionPane.YES_OPTION) {
        
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("report.pdf"));
            document.open();

            // Add table headers
            PdfPTable table = new PdfPTable(ReportTable.getColumnCount());
            for (int i = 0; i < ReportTable.getColumnCount(); i++) {
                table.addCell(new PdfPCell(new Paragraph(ReportTable.getColumnName(i))));
            }

            // Add table data
            for (int i = 0; i < ReportTable.getRowCount(); i++) {
                for (int j = 0; j < ReportTable.getColumnCount(); j++) {
                    table.addCell(new PdfPCell(new Paragraph(ReportTable.getValueAt(i, j).toString())));
                }
            }

            document.add(table);
            document.close();

            System.out.println("PDF report generated successfully.");
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    }

    private void loadReportTable() {
        DefaultTableModel model = (DefaultTableModel) ReportTable.getModel();
        // Clear existing data in the table
        //UPDATE THE PRODUCT JTABLE
        model.setRowCount(0);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Inventory.DatabaseConnection.getConnection();
            String query = "SELECT * FROM reports";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("receiptid");
                String name = resultSet.getString("productname");
                String qty = resultSet.getString("qty");
                int discount = resultSet.getInt("discount");
                String price = resultSet.getString("price");
                String date = resultSet.getString("date");
                String customer = resultSet.getString("customer");
                String methods = resultSet.getString("method");
                model.addRow(new Object[]{id, name, qty, discount, price, date, customer, methods});
            }
            updateTotalPrice(ReportTable, 4, TotalPriceValue);
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

    class CustomCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            JTextArea textArea = new JTextArea(value.toString());
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
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
    
    private void filterTable(String searchText) {
     if (tableRowSorter == null) {
        tableRowSorter = new TableRowSorter<>(tableModel);
        ReportTable.setRowSorter(tableRowSorter);
    }

    if (searchText.trim().length() == 0) {
        tableRowSorter.setRowFilter(null);
    } else {
        tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        updateTotalPrice(ReportTable, 4, TotalPriceValue);
    }
}
    public static void updateTotalPrice(JTable table, int columnIndex, JLabel totalPriceValue) {
    double sum = 0.0;
    TableModel model = table.getModel();
    for (int row = 0; row < model.getRowCount(); row++) {
        if (table.convertRowIndexToView(row) != -1) { // Check if row is visible
            Object value = model.getValueAt(row, columnIndex);
            if (value instanceof Number) {
                sum += ((Number) value).doubleValue();
            } else if (value instanceof String) {
                try {
                    sum += Double.parseDouble((String) value);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format at row " + row + ": " + value);
                }
            } else {
                System.err.println("Invalid data type at row " + row + ": " + value);
            }
        }
    }
    totalPriceValue.setText(String.format("%.2f", sum));
}

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExportPDF_Bttn;
    private javax.swing.JTable ReportTable;
    private javax.swing.JTextField SearchField;
    private javax.swing.JLabel SearchLabel;
    private javax.swing.JLabel TotalLabel;
    private javax.swing.JLabel TotalPriceValue;
    private javax.swing.JButton UpdateTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
