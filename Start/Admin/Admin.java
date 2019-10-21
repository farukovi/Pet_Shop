package Admin;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.font.*;

public class Admin extends JFrame implements ActionListener
{
	private JLabel title;
	private JButton add_employee,employee_details, update_details,delete_employee;
	private JButton add_pet,pet_details, update_pet,delete_pet,sold_list;
	private JButton View;
	private JPanel panel;
	
	public Admin()
	{
		super("Admin Panel");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,100,350,400);
		
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		Font font2 = new Font("SansSerif", Font.BOLD, 15);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GREEN);
		
		title = new JLabel("Welcome To Admin Panel");
		title.setBounds(80,-50,400,200);
		title.setForeground(Color.BLACK);
		title.setFont(font1);
		panel.add(title);
		
		add_employee = new JButton("Add Employee");
		add_employee.setBounds(20,120,120,50);
		add_employee.setBackground(Color.BLUE);
		add_employee.addActionListener(this);
		panel.add(add_employee);
		
		employee_details = new JButton("Employee Details");
		employee_details.setBackground(Color.BLUE);
		employee_details.addActionListener(this);
		employee_details.setBounds(160,120,150,50);
		panel.add(employee_details);
		
		add_pet = new JButton("Add Pet");
		add_pet.setBounds(20,180,120,50);
		add_pet.addActionListener(this);
		add_pet.setBackground(Color.BLUE);
		panel.add(add_pet);
		
		pet_details = new JButton("Pet Details");
		pet_details.setBackground(Color.BLUE);
		pet_details.addActionListener(this);
		pet_details.setBounds(160,180,150,50);
		panel.add(pet_details);
		
		
		sold_list = new JButton("Sold_List");
		sold_list.addActionListener(this);
		sold_list.setBounds(20,240,120,50);
		sold_list.setBackground(Color.BLUE);
		panel.add(sold_list);
		
		View = new JButton("View Message");
		View.addActionListener(this);
		View.setBounds(160,240,120,50);
		View.setBackground(Color.BLUE);
		panel.add(View);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String clicked = ae.getActionCommand();
		if(clicked.equals(add_employee.getText()))
		{
			Adding a = new Adding();
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(clicked.equals(employee_details.getText()))
		{
			Details e = new Details();
			e.setVisible(true);
			this.setVisible(false);
		}
		else if(clicked.equals(add_pet.getText()))
		{
			AddingPet a = new AddingPet();
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(clicked.equals(pet_details.getText()))
		{
			detailsPet p = new detailsPet();
			p.setVisible(true);
			this.setVisible(false);
		}
		else if(clicked.equals(sold_list.getText()))
		{
			Sold_list s = new Sold_list();
			s.setVisible(true);
			this.setVisible(false);
		}
		else if(clicked.equals(View.getText()))
		{
			String sql = "SELECT COUNT(Mail) FROM mail";
			System.out.println(sql);
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				//System.out.println("Driver loaaded");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pet_shop","root", "");
				//System.out.println("Connection Done");
				st = con.createStatement();
				//System.out.println("Statement Created");
				rs = st.executeQuery(sql);
				//System.out.println("result received");
				while(rs.next())
				{
					int x = rs.getInt("COUNT(Mail)");
					System.out.println(x);
					if(x > 0)
					{
						//JOptionPane.showMessageDialog(panel,"No Reply");
						View viewmessage = new View();
						viewmessage.setVisible(true);
						this.setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(panel,"No Reply");
					}
				}
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
			}
		}
	}
}

	