package com.example.cassandra.simple_client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.*;

import javax.swing.JLayeredPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;


public class Delete extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Cluster cluster;
	private Session session;
	private JTable table;
	private JButton button, btnRemove;
	Object[][] data=null;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public void connect() 
	{
	 cluster = Cluster.builder()
	 .addContactPoint("127.0.0.1")
	 .build();
	 session = cluster.connect();
	}
	
	public Delete() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblDelete = new JLabel("Delete");
		lblDelete.setBounds(268, 12, 70, 15);
		layeredPane.add(lblDelete);
		
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(this);
		btnRemove.setBounds(227, 293, 117, 25);
		layeredPane.add(btnRemove);
		
		table = new JTable();
		table.setBounds(35, 39, 469, 242);
		layeredPane.add(table);
		
		button = new JButton("");
		button.addActionListener(this);
		button.setIcon(new ImageIcon("/home/omkar/Downloads/undo.png"));
		button.setToolTipText("Return Home");
		button.setBounds(507, 7, 49, 33);
		layeredPane.add(button);
	}
	
	public void display()
	{
		connect();
		String age;
		String aps, job, house ,ms, name;
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
        close();
	}
	
	public void close() 
    {
        session.close();
        cluster.close();
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
		final String name,aps;
		int s=table.getSelectedRow();
		name=(String)table.getModel().getValueAt(s, 0);
		aps=(String)table.getModel().getValueAt(s, 5);
		String cqlStatementD = "DELETE FROM nb.bankdata " + 
                "WHERE name = '"+name+"' aps = '"+aps+"';";

		session.execute(cqlStatementD);
		
		close();
		}
	}
}
