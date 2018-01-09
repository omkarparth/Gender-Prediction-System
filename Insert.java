package com.example.cassandra.simple_client;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

public class Insert extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Cluster cluster;
	private Session session;
	private JComboBox comboBox, comboBox_1, comboBox_2, comboBox_4, comboBox_5, comboBox_3;
	private JLabel label;
	private JButton btnReturnHome, btnAdd;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public Insert() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setToolTipText("");
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblInsert = new JLabel("Insert");
		lblInsert.setBounds(177, 28, 70, 15);
		layeredPane.add(lblInsert);
		
		JLabel lblName = new JLabel("Age: ");
		lblName.setBounds(98, 66, 102, 15);
		layeredPane.add(lblName);
		
		JLabel lblHeight = new JLabel("Has Job?");
		lblHeight.setBounds(98, 93, 102, 15);
		layeredPane.add(lblHeight);
		
		JLabel lblWeight = new JLabel("Own House?");
		lblWeight.setBounds(98, 120, 114, 15);
		layeredPane.add(lblWeight);
		
//		JLabel lblFootSize = new JLabel("Credit Rating: ");
//		lblFootSize.setBounds(98, 147, 149, 15);
//		layeredPane.add(lblFootSize);
		
		JLabel lblSex = new JLabel("Approval Status");
		lblSex.setBounds(98, 203, 126, 15);
		layeredPane.add(lblSex);
		
		comboBox = new JComboBox();
		comboBox.addItem("Yes");
		comboBox.addItem("No");
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(242, 198, 114, 24);
		layeredPane.add(comboBox);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(this); 
		btnAdd.setBounds(155, 241, 117, 25);
		layeredPane.add(btnAdd);
		
		label = new JLabel("");
		label.setBounds(98, 278, 258, 19);
		layeredPane.add(label);
		
		JLabel lblHairLength = new JLabel("Maritial Status: ");
		lblHairLength.setBounds(98, 171, 138, 15);
		layeredPane.add(lblHairLength);
		
		btnReturnHome = new JButton("Back");
		btnReturnHome.addActionListener(this);
		btnReturnHome.setToolTipText("Return Home");
		btnReturnHome.setBounds(392, 12, 77, 38);
		layeredPane.add(btnReturnHome);
		
		comboBox_2 = new JComboBox();
		comboBox_2.addItem("Yes");
		comboBox_2.addItem("No");
		comboBox_2.setSelectedIndex(0);
		comboBox_2.setBounds(242, 115, 114, 24);
		layeredPane.add(comboBox_2);
		
		comboBox_4 = new JComboBox();
		comboBox_4.addItem("Married");
		comboBox_4.addItem("Unmarried");
		//comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"Married", "Unmarried"}));
		comboBox_4.setSelectedIndex(0);
		comboBox_4.setBounds(240, 166, 116, 24);
		layeredPane.add(comboBox_4);
		
		comboBox_5 = new JComboBox();
		comboBox_5.addItem("Yes");
		comboBox_5.addItem("No");
		//comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
		comboBox_5.setSelectedIndex(0);
		comboBox_5.setBounds(242, 88, 114, 24);
		layeredPane.add(comboBox_5);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Child", "Young", "Adult"}));
		comboBox_3.setSelectedIndex(0);
		comboBox_3.setBounds(240, 61, 116, 24);
		layeredPane.add(comboBox_3);
		
		JLabel lblName_1 = new JLabel("Name : ");
		lblName_1.setBounds(98, 147, 70, 15);
		layeredPane.add(lblName_1);
		
		textField = new JTextField();
		textField.setBounds(242, 145, 114, 19);
		layeredPane.add(textField);
		textField.setColumns(10);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnReturnHome)
		{
			setVisible(false);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Home Home = new Home();
						Home.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			this.dispose();
		}
		else
		{
		String age = (String) comboBox_3.getSelectedItem();
		String job = (String) comboBox_5.getSelectedItem();
	    String house = (String) comboBox_2.getSelectedItem();
		String ms = (String) comboBox_4.getSelectedItem();
		String aps = (String) comboBox.getSelectedItem();

			cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
			session = cluster.connect();

			session.execute("CREATE KEYSPACE IF NOT EXISTS nb WITH replication" +"= {'class':'SimpleStrategy', 'replication_factor':3};");
			session.execute(
				      "CREATE TABLE IF NOT EXISTS nb.bankdata (" +
				    		  "name text," + 
				            "age text," +
				            "job text," +
				            "house text," +
				            "ms text," +
						 "aps text,"+
				            "PRIMARY KEY(name,aps) );");

session.execute("INSERT INTO nb.bankdata (name,age, job, house, ms, aps) " +
"VALUES ( '"+textField.getText()+"', '"+age+"'   ,'"+job+"' ,  '"+house+"' ,   '"+ms+"' ,  '"+aps+"' ) ;");
			session.execute("use naivebayes;");
			
		label.setText("Record Successfully Inserted");
		session.close();
        cluster.close();
		}
		}
}