package com.example.cassandra.simple_client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;;

public class View extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Cluster cluster;
	private Session session;
	private JButton button;
	private JTable table;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblView = new JLabel("View");
		lblView.setBounds(244, 12, 70, 15);
		layeredPane.add(lblView);
		
		
		
		button = new JButton("");
		button.addActionListener(this);
		button.setIcon(new ImageIcon("/home/omkar/Downloads/undo.png"));
		button.setToolTipText("Return Home");
		button.setBounds(497, 7, 49, 33);
		layeredPane.add(button);
		
		table = new JTable();
		table.setBounds(40, 47, 457, 214);
		layeredPane.add(table);
	}

	public void display()
	{
		String age;
		String aps, job, house ,ms, name;
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect();
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		model.addColumn("name");
        model.addColumn("age");
		model.addColumn("job");
		model.addColumn("house");
		model.addColumn("ms");
		model.addColumn("aps");
		model.addRow(new Object[] {"Name","Age","Has Job","Own House","Marital Status","Approval Status"});
		ResultSet results = session.execute("SELECT * FROM nb.bankdata;");
		for (Row row : results) 
        {
        	name = row.getString("name");
        	age=row.getString("age");
            job=row.getString("job");
            house=row.getString("house");
            ms=row.getString("ms");
            aps=row.getString("aps");
            model.addRow(new Object[] {name, age,job ,house ,ms,aps});        
        }
        session.close();
        cluster.close();
	}
	
	public void actionPerformed(ActionEvent e) 
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
}