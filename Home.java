package com.example.cassandra.simple_client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class Home extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnInsert, btnDelete, btnModify, btnPredict, btnView;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the Home.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Height     191, 178		lblNewLabel_1.setBounds(127, 85, 97, 15)");
		layeredPane.add(lblNewLabel_1);
		
		btnView = new JButton("View ");
		btnView.addActionListener(this);
		btnView.setBounds(325, 167, 117, 25);
		layeredPane.add(btnView);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setBounds(227, 12, 70, 15);
		layeredPane.add(lblHome);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		btnInsert.setBounds(42, 74, 117, 25);
		layeredPane.add(btnInsert);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this);
		btnDelete.setBounds(42, 167, 117, 25);
		layeredPane.add(btnDelete);
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(this);
		btnModify.setBounds(325, 74, 117, 25);
		layeredPane.add(btnModify);
		
		btnPredict = new JButton("Predict");
		btnPredict.addActionListener(this);
		btnPredict.setBounds(180, 117, 117, 25);
		layeredPane.add(btnPredict);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnInsert)
		{
			setVisible(false);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Insert frame = new Insert();
						frame.setVisible(true);						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			this.dispose();
		}
		else if(e.getSource()==btnDelete)
		{
			setVisible(false);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Delete frame = new Delete();
						frame.setVisible(true);
						frame.display();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			this.dispose();
		}
		else if(e.getSource()==btnView)
		{
			setVisible(false);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						View frame = new View();
						frame.setVisible(true);
						frame.display();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			this.dispose();
		}
		else if(e.getSource()==btnModify)
		{
			setVisible(false);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Modify1 frame = new Modify1();
						frame.setVisible(true);
						frame.display();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			this.dispose();
		}
		else if(e.getSource()==btnPredict)
		{
			setVisible(false);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Predict frame = new Predict();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			this.dispose();
		}
	}
}


/* Modify2.java Main()

public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
	public void run() {
		try {
			Modify2 frame = new Modify2("",0,0,0,0,"");
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
});
}

Insert

public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Insert frame = new Insert();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
Delete

public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete frame = new Delete();
					frame.setVisible(true);
					frame.connect("127.0.0.1");
					frame.display();
					frame.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
View

public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
					frame.display();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

Predict 

public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Predict frame = new Predict();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

Modify1
public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modify1 frame = new Modify1();
					frame.setVisible(true);
					frame.display();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
