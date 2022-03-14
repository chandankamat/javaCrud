package com.jspiders.pkg1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaCrud {

	private JFrame frame;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTextField txtbname;
	private JTable table;
	private JTextField txtbid;
	protected Object pstmt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCrud() {
		initialize();
		Connect();
		table_load();
	}

	private final String dbUrl = "jdbc:mysql://localhost:3306/javacrud";
	private final String dbUsername = "root";
	private final String dbPassword = "kumar123";
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	 public void Connect()
	    {
	        try {
	        	con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); // open connection
				//System.out.println("Connected to MySQL DB");
	        }
	        catch (SQLException ex) 
	        {
	          ex.printStackTrace();
	        }
	       
	        }
	        
	 public void table_load()
	    {
	     
    	  String selectQuery ="select * from javacrud.book";

		 try
	     {

	    pst = con.prepareStatement(selectQuery);
	    rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	}
	     catch (SQLException e)
	     {
	     e.printStackTrace();
	  }

	    }


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 25));
		frame.getContentPane().setBackground(Color.GREEN);
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 676, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setBounds(252, 0, 132, 31);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel Panel = new JPanel();
		Panel.setBackground(new Color(255, 0, 255));
		Panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Panel.setBounds(10, 53, 305, 208);
		frame.getContentPane().add(Panel);
		Panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Price");
		lblNewLabel_1.setBounds(27, 122, 84, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		Panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Name");
		lblNewLabel_1_1.setBounds(27, 34, 84, 14);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		Panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Edition");
		lblNewLabel_1_2.setBounds(27, 73, 84, 14);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		Panel.add(lblNewLabel_1_2);
		
		txtedition = new JTextField();
		txtedition.setBounds(121, 71, 171, 20);
		txtedition.setFont(new Font("Tahoma", Font.BOLD, 12));
		Panel.add(txtedition);
		txtedition.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setBounds(121, 120, 171, 20);
		txtprice.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtprice.setColumns(10);
		Panel.add(txtprice);
		
		txtbname = new JTextField();
		txtbname.setBounds(121, 32, 171, 20);
		txtbname.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtbname.setColumns(10);
		Panel.add(txtbname);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBackground(new Color(255, 255, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				txtbname.setText("");
	            txtedition.setText("");
	            txtprice.setText("");
	            txtbname.requestFocus();
	                
				
			}
		});
		btnNewButton.setBounds(226, 272, 89, 50);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{			
				String bname,edition,price;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
						
				 String insertQuery = "insert into javacrud.book values(0,?,?,?);";

				 try {
					pst = con.prepareStatement(insertQuery);
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
						           
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
			}

			
				
			});
		btnNewButton_1.setBounds(10, 272, 89, 50);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBackground(new Color(240, 255, 240));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_2.setBounds(127, 272, 89, 50);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(336, 52, 314, 265);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 333, 305, 50);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Book ID");
		lblNewLabel_1_2_1.setBounds(20, 22, 83, 17);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblNewLabel_1_2_1);
		
		txtbid = new JTextField();
		txtbid.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtbid.setForeground(new Color(0, 0, 0));
		txtbid.setBackground(new Color(255, 255, 255));
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
			          
                 String selectQuery ="select name,edition,price from javacrud.book where id = ?";
					 
		         String id = txtbid.getText();
                 pst = con.prepareStatement(selectQuery);
			     pst.setString(1, id);
			     ResultSet rs = pst.executeQuery();

			     if(rs.next()==true)
			     {
			              
			     String name = rs.getString(1);
			     String edition = rs.getString(2);
			      String price = rs.getString(3);
			                
			        txtbname.setText(name);
			        txtedition.setText(edition);
			        txtprice.setText(price);
			                
			                
			            }   
			            else
			            {
			            	txtbname.setText("");
			            	txtedition.setText("");
			                txtprice.setText("");
			                 
			            }
			            


			        } 
				
				 catch (SQLException ex) {
			           
			        }
				
				
				
				
			}
		});
		txtbid.setBounds(103, 21, 150, 20);
		txtbid.setColumns(10);
		panel.add(txtbid);
		
		JButton btnNewButton_3_1 = new JButton("Update");
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3_1.setBackground(new Color(153, 50, 204));
		btnNewButton_3_1.setForeground(new Color(0, 0, 0));
		btnNewButton_3_1.addActionListener(new ActionListener() {
      
			public void actionPerformed(ActionEvent e) {
				
				
				String bname,edition,price,bid;
				
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid  = txtbid.getText();
				
				 try {
					    String updateQuery =("update javacrud.book set name= ?,edition=?,price=? where id =?");
						
					    pst = con.prepareStatement(updateQuery);
						pst.setString(1, bname);
			            pst.setString(2, edition);
			            pst.setString(3, price);
			            pst.setString(4, bid);
			            pst.executeUpdate();
					    JOptionPane.showMessageDialog(null, "Record Update!!!!!");

			           // System.out.println("Record Update!!!!!");
			            
			            table_load();
			           
			            txtbname.setText("");
			            txtedition.setText("");
			            txtprice.setText("");
			            txtbname.requestFocus();
					}

		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
	
			}
		});
		btnNewButton_3_1.setBounds(366, 328, 89, 50);
		frame.getContentPane().add(btnNewButton_3_1);
		
		JButton btnNewButton_3_2 = new JButton("Delete");
		btnNewButton_3_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3_2.setBackground(new Color(0, 0, 255));
		btnNewButton_3_2.setForeground(new Color(0, 0, 0));
		btnNewButton_3_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				   String bid;
					bid  = txtbid.getText();
					
					 try {
						    String deleteQuery =  "delete from javacrud.book where id =?"; 
						  
							pst = con.prepareStatement(deleteQuery);
					
				            pst.setString(1, bid);
				            pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Record Delete!!!!!");

				           // System.out.println("Record Delete!!!!!!"); 
				            
				            table_load();
				         
				            txtbname.setText("");
				            txtedition.setText("");
				            txtprice.setText("");
				            txtbname.requestFocus();
						}

			            catch (SQLException e1) {
							
							e1.printStackTrace();
						}
			}
		});
		btnNewButton_3_2.setBounds(539, 328, 89, 50);
		frame.getContentPane().add(btnNewButton_3_2);
	}
	}

