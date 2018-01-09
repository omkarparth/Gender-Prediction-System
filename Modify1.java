package com.example.cassandra.simple_client;

import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Modify1 extends JFrame implements ActionListener,Runnable {

	private JPanel contentPane;
	private Cluster cluster;
	private Session session;
	private JButton button, btnModify;
	private JTable table;
	String age, aps, job, house ,ms, name;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Modify1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblModify = new JLabel("Modify");
		lblModify.setBounds(249, 12, 70, 15);
		layeredPane.add(lblModify);
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(this);
		btnModify.setBounds(224, 290, 117, 25);
		layeredPane.add(btnModify);
		
		
		
		
		button = new JButton("");
		button.addActionListener(this);
		button.setIcon(new ImageIcon("/home/omkar/Downloads/undo.png"));
		button.setToolTipText("Return Home");
		button.setBounds(500, 7, 49, 33);
		layeredPane.add(button);
		
		table = new JTable();
		table.setBounds(38, 42, 466, 236);
		layeredPane.add(table);
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
		Object[][] data=null;
		int s=table.getSelectedRow();
		name=(String)table.getModel().getValueAt(s, 0);
		age=(String)table.getModel().getValueAt(s, 1);
		job=(String)table.getModel().getValueAt(s, 2);
		house=(String)table.getModel().getValueAt(s, 3);
		ms=(String)table.getModel().getValueAt(s, 4);
		aps=(String)table.getModel().getValueAt(s, 5);
		EventQueue.invokeLater(this);
		}
	}
	
	public void run() {
		try {
			Modify2 frame = new Modify2(name,age,job,house,ms,aps);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
}
