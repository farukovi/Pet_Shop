package Admin;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.font.*;
import java.sql.*;
import java.util.regex.Pattern;

public class AddingPet extends JFrame implements ActionListener
{
	JLabel name,email,cell,title,address,pet_type,age,password,work_type;
	JTextField namefiled,mailfield,cellfield,addField,petField,ageField,foodField;
	JPanel panel;
	JPasswordField passField;
	JRadioButton r1,r2;
	ButtonGroup bg1;
	JButton insert,back,row_insert;
	
	public AddingPet()
	{
		super("Pet Shop");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,100,450,550);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GREEN);
		
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		Font font2 = new Font("SansSerif", Font.BOLD, 15);
				
		title = new JLabel("Add Pet");
		title.setBounds(80,20,400,30);
		title.setForeground(Color.BLACK);
		title.setFont(font1);
		panel.add(title);
			
		name = new JLabel("Name: ");
		name.setBounds(60,70,200,30);
		name.setForeground(Color.BLACK);
		name.setFont(font2);
		panel.add(name);
			
			namefiled = new JTextField();
			namefiled.setBounds(130,70,200,30);
			panel.add(namefiled);
			
			email = new JLabel("Pet_Type: ");
			email.setBounds(40,120,200,30);
			email.setForeground(Color.BLACK);
			email.setFont(font2);
			panel.add(email);
			
			mailfield = new JTextField();
			mailfield.setBounds(130,120,200,30);
			panel.add(mailfield);
			
			cell = new JLabel("Price: ");
			cell.setFont(font2);
			cell.setBounds(50,170,200,30);
			cell.setForeground(Color.BLACK);
			panel.add(cell);
			
			cellfield = new JTextField();
			cellfield.setBounds(130,170,200,30);
			panel.add(cellfield);
			
			address = new JLabel("Food: ");
			address.setFont(font2);
			address.setBounds(50,220,200,30);
			address.setForeground(Color.BLACK);
			panel.add(address);
			
			addField = new JTextField();
			addField.setBounds(130,220,200,30);
			panel.add(addField);
			
			pet_type = new JLabel("Color: ");
			pet_type.setFont(font2);
			pet_type.setBounds(50,270,200,30);
			pet_type.setForeground(Color.BLACK);
			panel.add(pet_type);
			
			petField = new JTextField();
			petField.setBounds(130,270,200,30);
			panel.add(petField);
			
			age = new JLabel("Quantity: ");
			age.setFont(font2);
			age.setBounds(50,320,200,30);
			age.setForeground(Color.BLACK);
			panel.add(age);
			
			ageField = new JTextField();
			ageField.setBounds(130,320,200,30);
			panel.add(ageField);
			
			password = new JLabel("Picture: ");
			password.setFont(font2);
			password.setBounds(50,370,200,30);
			password.setForeground(Color.BLACK);
			panel.add(password);
			
			foodField = new JTextField();
			foodField.setBounds(130,370,200,30);
			panel.add(foodField);
			
			passField = new JPasswordField("ovi");
			
			row_insert = new JButton("Insert");
			row_insert.setBounds(100,430,100,40);
			row_insert.addActionListener(this);
			panel.add(row_insert);
			
			back = new JButton("Back");
			back.setBounds(250,430,100,40);
			back.addActionListener(this);
			panel.add(back);
			
			this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String click = ae.getActionCommand();
		if(click.equals(back.getText()))
		{
			Admin h = new Admin();
			this.setVisible(false);
			h.setVisible(true);
		}
		else if(click.equals(row_insert.getText()))
		{
			Insert();
			Row_Insert();
		}
	}
	
	public void Insert()
	{
		boolean inter = true;
		if(namefiled.getText().equals("") || mailfield.getText().equals("") || cellfield.getText().equals("") || addField.getText().equals("") || petField.getText().equals("") || ageField.getText().equals("") || passField.getText().equals(""))
		{
			JOptionPane.showMessageDialog(panel,"Field Can't be empty");
			inter = false;
		}
		else
		{	if(inter == true)
			{	
				String query = "INSERT INTO pets_info(Name,Pet_Type,Price,Food,Color,Quantity,Room_no) values('"+namefiled.getText()+"','"+mailfield.getText()+"','"+cellfield.getText()+"','"+addField.getText()+"','"+petField.getText()+"','"+ageField.getText()+"','"+foodField.getText()+"')";
				Connection con = null;
				Statement st = null;
				ResultSet rs = null,rows = null;
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("Driver loaaded");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pet_shop","root", "");
					System.out.println("Connection Done");
					st = con.createStatement();
					System.out.println("Statement Created");
					int row = ((java.sql.Statement)st).executeUpdate(query);
					System.out.println("result received");
					if(row > 0)
					{
						System.out.println("Success");
					}
				}
				catch(Exception ex)
				{
					System.out.println("Exception : " +ex.getMessage());
				}
			}
			else{}
		}
	}
	public void Row_Insert()
	{
		String sql = "SELECT COUNT(Pet_Type) FROM pet_availiable WHERE Pet_Type='"+mailfield.getText()+"'";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null,rows = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("Driver loaaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pet_shop","root", "");
			//System.out.println("Connection Done");
			st = con.createStatement();
			//System.out.println("Statement Created");
			rs = st.executeQuery(sql);
			while(rs.next())
			{
				int z = rs.getInt("COUNT(Pet_Type)");
				if(z > 0)
				{
					
				}
				else
				{
					String query = "INSERT INTO pet_availiable(Pet_Type,Picture) values('"+mailfield.getText()+"','"+foodField.getText()+"')";
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						System.out.println("Driver loaaded");
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pet_shop","root", "");
						System.out.println("Connection Done");
						st = con.createStatement();
						System.out.println("Statement Created");
						int row = ((java.sql.Statement)st).executeUpdate(query);
						System.out.println("result received");
						if(row > 0)
						{
							System.out.println("Success");
						}
					}
					catch(Exception ex)
					{
						System.out.println("Exception : " +ex.getMessage());
					}
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
		}
		
	}
} 
