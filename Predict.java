package com.example.cassandra.simple_client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.lang.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class Predict extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel label_4;
	private Cluster cluster;
	private Session session;
	private JButton button, btnPredict;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Predict() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblPredict = new JLabel("Predict");
		lblPredict.setBounds(248, 12, 70, 15);
		layeredPane.add(lblPredict);
		
		btnPredict = new JButton("Predict");
		btnPredict.addActionListener(this);
		btnPredict.setBounds(221, 213, 117, 25);
		layeredPane.add(btnPredict);
		
		label_4 = new JLabel("");
		label_4.setBounds(167, 266, 215, 20);
		layeredPane.add(label_4);
		
		JLabel lblAge = new JLabel("Age: ");
		lblAge.setBounds(141, 64, 102, 15);
		layeredPane.add(lblAge);
		
		JLabel lblJob = new JLabel("Has Job");
		lblJob.setBounds(141, 91, 114, 15);
		layeredPane.add(lblJob);
		
		JLabel lblOwnHouse = new JLabel("Own House? ");
		lblOwnHouse.setBounds(141, 118, 149, 15);
		layeredPane.add(lblOwnHouse);
		
		JLabel lblMaritalStatus = new JLabel("Marital Status: ");
		lblMaritalStatus.setBounds(141, 142, 138, 15);
		layeredPane.add(lblMaritalStatus);
		
		button = new JButton("");
		button.addActionListener(this);
		button.setIcon(new ImageIcon("/home/omkar/Downloads/undo.png"));
		button.setToolTipText("Return Home");
		button.setBounds(495, 7, 49, 33);
		layeredPane.add(button);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Child", "Young", "Adult"}));
		comboBox.setBounds(273, 59, 109, 24);
		layeredPane.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addItem("Yes");
		comboBox_1.addItem("No");
		comboBox_1.setBounds(273, 86, 109, 24);
		layeredPane.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.addItem("Yes");
		comboBox_2.addItem("No");
		comboBox_2.setBounds(273, 113, 109, 24);
		layeredPane.add(comboBox_2);
		
		comboBox_3 = new JComboBox();
		comboBox_3.addItem("Married");
		comboBox_3.addItem("Unmarried");
		comboBox_3.setBounds(273, 137, 109, 24);
		layeredPane.add(comboBox_3);
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
		
			naivebayes();
		
		}
	}
	
	public void naivebayes()
	{
		
		long yn=1,nn=1,n=1,ay=1,an=1,hy=1,hn=1,my=1,mn=1,jy=1,jn=1;
		double pry, prn, poy, pon,  pay, pan, phy, phn, pmy, pmn, pjy, pjn, ly, ln, pe;
		ResultSet results;
		String ms, job, age, house;
		
		age=(String)comboBox.getSelectedItem();
		job=(String)comboBox_1.getSelectedItem();
		house=(String)comboBox_2.getSelectedItem();
		ms=(String)comboBox_3.getSelectedItem();
		
		//Building connection to the database
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect();
		
		//Counting the number of Rows (N)
		results = session.execute("select count(*) from nb.bankdata where aps='Yes' allow filtering;");        
		for (Row row : results) 
			yn = row.getLong(0);
		
		results = session.execute("select count(*) from nb.bankdata where aps='No' allow filtering;");        
		for (Row row : results) 
			nn = row.getLong(0);
		
		results = session.execute("select count(*) from nb.bankdata;");        
		for (Row row : results) 
			n = row.getLong(0);
		
		//Calculating prior probability
		pry = yn/n;
		prn = nn/n;
		
		//Calculating likelihood
		results = session.execute("select count(*) from nb.bankdata where aps='Yes' AND age='"+age+"' allow filtering;");        
		for (Row row : results) 
			ay = row.getInt(0);
		pay = ay/yn;
		
		results = session.execute("select count(*) from nb.bankdata where aps='No' AND age='"+age+"' allow filtering;");        
		for (Row row : results) 
			an = row.getInt(0);
		pan = an/nn;
		
		results = session.execute("select count(*) from nb.bankdata where aps='Yes' AND ms='"+ms+"' allow filtering;");        
		for (Row row : results) 
			my = row.getInt(0);
		pmy = my/yn;
		
		results = session.execute("select count(*) from nb.bankdata where aps='No' AND ms='"+ms+"' allow filtering;");        
		for (Row row : results) 
			mn = row.getInt(0);
		pmn = mn/nn;
		
		results = session.execute("select count(*) from nb.bankdata where aps='Yes' AND house='"+house+"' allow filtering;");        
		for (Row row : results) 
			hy = row.getInt(0);
		phy = hy/yn;
		
		results = session.execute("select count(*) from nb.bankdata where aps='No' AND house='"+house+"' allow filtering;");        
		for (Row row : results) 
			hn = row.getInt(0);
		phn = hn/nn;
		
		results = session.execute("select count(*) from nb.bankdata where aps='Yes' AND job='"+job+"' allow filtering;");        
		for (Row row : results) 
			jy = row.getInt(0);
		pjy = jy/yn;
		
		results = session.execute("select count(*) from nb.bankdata where aps='No' AND job='"+job+"' allow filtering;");        
		for (Row row : results) 
			jn = row.getInt(0);
		pjn = jn/nn;
		
		ly = pay*phy*pjy*pmy;
		ln = pan*phn*pjn*pmn;
		
		//Calculating evidence
		pe = ((ay+an)/n) * ((hy+hn)/n) * ((jy+jn)/n) * ((my+mn)/n);
		
		//Calculating posterior probability
		poy = pry * ly / pe;
		pon = prn * ln / pe;
		
		int r = Double.compare(poy, pon);
				if(r>0)
					label_4.setText("Predicted Approval Status : Yes");
				else
					label_4.setText("Predicted Approval Status : No");
				
		//Closing the database connection
		session.close();
        cluster.close();
        
	}
}

