package com.example.cassandra.simple_client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.datastax.driver.core.Cluster;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class Modify2 extends JFrame implements ActionListener{

	private JPanel contentPane;
	String age, aps, job, house ,ms, name;
	private Cluster cluster;
	private Session session;
	private JButton button, btnModify;
	private JTextField textField;
	private JComboBox comboBox, comboBox_1, comboBox_2, comboBox_4, comboBox_5, comboBox_3;
	
	/**
	 * Create the frame.
	 */
	public Modify2(String age,String aps,String job,String house ,String ms,String name) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblModify = new JLabel("Modify");
		lblModify.setBounds(247, 12, 70, 15);
		layeredPane.add(lblModify);
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(this);
		btnModify.setBounds(202, 254, 117, 25);
		layeredPane.add(btnModify);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(164, 291, 218, 23);
		layeredPane.add(label_5);
		
		button = new JButton("");
		button.addActionListener(this);
		button.setIcon(new ImageIcon("/home/omkar/Downloads/undo.png"));
		button.setToolTipText("Return Home");
		button.setBounds(490, 7, 49, 33);
		layeredPane.add(button);
		
		comboBox = new JComboBox();
		comboBox.addItem("Yes");
		comboBox.addItem("No");
		comboBox.setSelectedItem(job);
		comboBox.setBounds(279, 90, 114, 24);
		layeredPane.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Child", "Young", "Adult"}));
		comboBox_1.setSelectedItem(age);
		comboBox_1.setBounds(277, 63, 116, 24);
		layeredPane.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.addItem("Yes");
		comboBox_2.addItem("No");
		comboBox_2.setSelectedItem(house);
		comboBox_2.setBounds(279, 117, 114, 24);
		layeredPane.add(comboBox_2);
		
		textField = new JTextField();
		textField.setText(name);
		textField.setColumns(10);
		textField.setBounds(279, 147, 114, 19);
		layeredPane.add(textField);
		
		comboBox_3 = new JComboBox();
		comboBox_3.addItem("Married");
		comboBox_3.addItem("Unmarried");
		comboBox_3.setSelectedItem(ms);
		comboBox_3.setBounds(277, 168, 116, 24);
		layeredPane.add(comboBox_3);
		
		comboBox_4 = new JComboBox();
		comboBox_4.addItem("Yes");
		comboBox_4.addItem("No");
		comboBox_4.setSelectedItem(aps);
		comboBox_4.setBounds(279, 200, 114, 24);
		layeredPane.add(comboBox_4);
		
		JLabel label = new JLabel("Approval Status");
		label.setBounds(135, 205, 126, 15);
		layeredPane.add(label);
		
		JLabel label_1 = new JLabel("Maritial Status: ");
		label_1.setBounds(135, 173, 138, 15);
		layeredPane.add(label_1);
		
		JLabel label_2 = new JLabel("Name : ");
		label_2.setBounds(135, 149, 70, 15);
		layeredPane.add(label_2);
		
		JLabel label_3 = new JLabel("Own House?");
		label_3.setBounds(135, 122, 114, 15);
		layeredPane.add(label_3);
		
		JLabel label_4 = new JLabel("Has Job?");
		label_4.setBounds(135, 95, 102, 15);
		layeredPane.add(label_4);
		
		JLabel label_6 = new JLabel("Age: ");
		label_6.setBounds(135, 68, 102, 15);
		layeredPane.add(label_6);
		
		this.age=age;
		this.house=house;
		this.aps=aps;
		this.name=name;
		this.job=job;
		this.ms=ms;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==button)
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
		connect();
		String age = (String) comboBox_3.getSelectedItem();
		String job = (String) comboBox_5.getSelectedItem();
	    String house = (String) comboBox_2.getSelectedItem();
		String ms = (String) comboBox_4.getSelectedItem();
		String aps = (String) comboBox.getSelectedItem();
		String name = textField.getText();
			cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
			session = cluster.connect();
			session.execute(
	                "UPDATE nb.bankdata " +
	                "SET age="+age+"," +
	                     "job="+job+"," +
	                     "house="+house+"," +
	                     "ms="+ms+"" +
	                     "WHERE name='"+this.name+"' AND aps='"+this.aps+"';");
		
		session.close();
        cluster.close();
        setVisible(false);
        close();
		}
	}
	public void close() 
    {
        session.close();
        cluster.close();
    }
	public void connect() 
	{
	 cluster = Cluster.builder()
	 .addContactPoint("127.0.0.1")
	 .build();
	 session = cluster.connect();
	}
}
