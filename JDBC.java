package simple;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class JDBC {
	  Connection conn;
	    PreparedStatement stmt;

	    // GUI components
	    Frame frame;
	    TextField tfId, tfName, tfEmail;
	    Button btnCreateDB, btnInsert, btnUpdate, btnDelete, btnClear;

	    public JDBC() {
	        try {
	            // Connect to MySQL server
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
	            System.out.println("Connected to MySQL server!");

	            // Create GUI
	            frame = new Frame("Simple AWT Website with JDBC");
	            frame.setSize(600, 400);
	            frame.setLayout(null);

	            // Labels
	            Label lblTitle = new Label("Simple AWT Website");
	            lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
	            lblTitle.setBounds(200, 40, 200, 30);

	            Label lblId = new Label("ID:");
	            lblId.setBounds(50, 100, 100, 30);
	            Label lblName = new Label("Name:");
	            lblName.setBounds(50, 150, 100, 30);
	            Label lblEmail = new Label("Email:");
	            lblEmail.setBounds(50, 200, 100, 30);

	            // Text fields
	            tfId = new TextField();
	            tfId.setBounds(150, 100, 200, 30);
	            tfName = new TextField();
	            tfName.setBounds(150, 150, 200, 30);
	            tfEmail = new TextField();
	            tfEmail.setBounds(150, 200, 200, 30);

	            // Buttons
	            btnCreateDB = new Button("Create DB & Table");
	            btnCreateDB.setBounds(400, 100, 150, 30);

	            btnInsert = new Button("Insert");
	            btnInsert.setBounds(50, 250, 80, 30);
	            btnUpdate = new Button("Update");
	            btnUpdate.setBounds(140, 250, 80, 30);
	            btnDelete = new Button("Delete");
	            btnDelete.setBounds(230, 250, 80, 30);
	            btnClear = new Button("Clear");
	            btnClear.setBounds(320, 250, 80, 30);

	            // Add components to frame
	            frame.add(lblTitle);
	            frame.add(lblId);
	            frame.add(lblName);
	            frame.add(lblEmail);
	            frame.add(tfId);
	            frame.add(tfName);
	            frame.add(tfEmail);
	            frame.add(btnCreateDB);
	            frame.add(btnInsert);
	            frame.add(btnUpdate);
	            frame.add(btnDelete);
	            frame.add(btnClear);

	            frame.setVisible(true);

	            // Button actions
	            btnCreateDB.addActionListener(e -> createDatabaseAndTable());
	            btnInsert.addActionListener(e -> insertRecord());
	            btnUpdate.addActionListener(e -> updateRecord());
	            btnDelete.addActionListener(e -> deleteRecord());
	            btnClear.addActionListener(e -> clearFields());

	            // Close window
	            frame.addWindowListener(new WindowAdapter() {
	                public void windowClosing(WindowEvent e) {
	                    try {
	                        if (conn != null) conn.close();
	                        System.out.println("Database connection closed!");
	                    } catch (SQLException ex) {
	                        ex.printStackTrace();
	                    }
	                    frame.dispose();
	                }
	            });
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Create database and table
	    void createDatabaseAndTable() {
	        try {
	            String createDB = "CREATE DATABASE IF NOT EXISTS awtdb";
	            stmt = conn.prepareStatement(createDB);
	            stmt.executeUpdate();
	            System.out.println("Database created or already exists!");

	            conn.setCatalog("awtdb"); // Switch to the created database
	            String createTable = "CREATE TABLE IF NOT EXISTS users (" +
	                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
	                    "name VARCHAR(50), " +
	                    "email VARCHAR(50))";
	            stmt = conn.prepareStatement(createTable);
	            stmt.executeUpdate();
	            System.out.println("Table created or already exists!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Insert record
	    void insertRecord() {
	        try {
	            String query = "INSERT INTO users (name, email) VALUES (?, ?)";
	            stmt = conn.prepareStatement(query);
	            stmt.setString(1, tfName.getText());
	            stmt.setString(2, tfEmail.getText());
	            stmt.executeUpdate();
	            System.out.println("Record inserted!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Update record
	    void updateRecord() {
	        try {
	            String query = "UPDATE users SET name = ?, email = ? WHERE id = ?";
	            stmt = conn.prepareStatement(query);
	            stmt.setString(1, tfName.getText());
	            stmt.setString(2, tfEmail.getText());
	            stmt.setInt(3, Integer.parseInt(tfId.getText()));
	            stmt.executeUpdate();
	            System.out.println("Record updated!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Delete record
	    void deleteRecord() {
	        try {
	            String query = "DELETE FROM users WHERE id = ?";
	            stmt = conn.prepareStatement(query);
	            stmt.setInt(1, Integer.parseInt(tfId.getText()));
	            stmt.executeUpdate();
	            System.out.println("Record deleted!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Clear text fields
	    void clearFields() {
	        tfId.setText("");
	        tfName.setText("");
	        tfEmail.setText("");
	    }

	    public static void main(String[] args) {
	        new JDBC();
	    }
	}