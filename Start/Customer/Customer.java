package Customer;
import java.lang.Package;
import javax.swing.*;
//package Customer;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.font.*;
import java.util.*;
import java.sql.*;
import java.util.regex.Pattern;

public class Customer extends JFrame implements ActionListener
{
	JLabel name,email,cell,title,address,pet_type,age,password,work_type,picture;
	JTextField namefiled,mailfield,cellfield,addField,petField,ageField,foodField;
	JPanel panel;
	JPasswordField passField;
	JRadioButton r1,r2;
	ButtonGroup bg1;
	JButton insert,back,row_insert;
	private ImageIcon img;
	public int x,z;
	public String mail;
	
	public Customer(String e_mail)
	{
		super("Pet Shop");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,100,450,550);
		
		mail = e_mail;
		
		panel = new JPanel();
		//panel.setLayout(true);
		panel.setBackground(Color.GREEN);
		
		String sql = "SELECT COUNT(DISTINCT Pet_Type) FROM pet_availiable";
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
					int x = rs.getInt("COUNT(DISTINCT Pet_Type)");
			}
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		
		if(x % 2 == 0)
		{
			z = x / 2;
			panel.setLayout(new GridLayout(z,2));
		}
		else
		{
			z = (x / 2) + 1;
			panel.setLayout(new GridLayout(z,2));
		}
		
		ArrayList ar = new ArrayList();
		//panel.setLayout(new GridLayout(3,2));
		String query = "SELECT DISTINCT Pet_Type,Picture from pet_availiable";
		//Connection con = null;
		//Statement st = null;
		//ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("Driver loaaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pet_shop","root", "");
			//System.out.println("Connection Done");
			st = con.createStatement();
			//System.out.println("Statement Created");
			rs = st.executeQuery(query);
			//System.out.println("result received");
			while(rs.next())
			{
				String x = rs.getString("Pet_Type");
				ImageIcon grassIcon = new ImageIcon(rs.getString("Picture")); 
				//ar.add(rs.getString("Pet_Type"));
				insert = new JButton(grassIcon);
				insert.setText(x);
				insert.addActionListener(this);
				panel.add(insert);
				//picture = new JLabel(grassIcon );
				//picture.addMouseListener(this);
				//panel.add(picture);
				//panel.add(new ImageIcon("E:\\JAVA\\Project\\Image\\Dog"));
				//ImageIcon
			}
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String buttonclicked = ae.getActionCommand();
		Pet pet = new Pet(buttonclicked,mail);
		this.setVisible(false);
		pet.setVisible(true);
		
	}
}
	
	
 
