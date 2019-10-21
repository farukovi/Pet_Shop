package Admin;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.font.*;
import java.sql.*;
import java.util.regex.Pattern;

public class Adding extends JFrame implements ActionListener
{
	JLabel name,email,cell,title,address,pet_type,age,password,work_type;
	JTextField namefiled,mailfield,cellfield,addField,petField,ageField,foodField;
	JPanel panel;
	JPasswordField passField;
	JRadioButton r1,r2;
	ButtonGroup bg1;
	JButton insert,back,row_insert;
	
	public Adding()
	{
		super("Pet Shop");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,100,450,550);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GREEN);
		
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		Font font2 = new Font("SansSerif", Font.BOLD, 15);
			
			title = new JLabel("Add Employee");
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
			
			email = new JLabel("Email: ");
			email.setBounds(60,120,200,30);
			email.setForeground(Color.BLACK);
			email.setFont(font2);
			panel.add(email);
			
			mailfield = new JTextField();
			mailfield.setBounds(130,120,200,30);
			panel.add(mailfield);
			
			cell = new JLabel("Cell: ");
			cell.setFont(font2);
			cell.setBounds(50,170,200,30);
			cell.setForeground(Color.BLACK);
			panel.add(cell);
			
			cellfield = new JTextField();
			cellfield.setBounds(130,170,200,30);
			panel.add(cellfield);
			
			address = new JLabel("Address: ");
			address.setFont(font2);
			address.setBounds(50,220,200,30);
			address.setForeground(Color.BLACK);
			panel.add(address);
			
			addField = new JTextField();
			addField.setBounds(130,220,200,30);
			panel.add(addField);
			
			pet_type = new JLabel("Pet_Type: ");
			pet_type.setFont(font2);
			pet_type.setBounds(50,270,200,30);
			pet_type.setForeground(Color.BLACK);
			panel.add(pet_type);
			
			petField = new JTextField();
			petField.setBounds(130,270,200,30);
			panel.add(petField);
			
			age = new JLabel("Age: ");
			age.setFont(font2);
			age.setBounds(50,320,200,30);
			age.setForeground(Color.BLACK);
			panel.add(age);
			
			ageField = new JTextField();
			ageField.setBounds(130,320,200,30);
			panel.add(ageField);
			
			password = new JLabel("Password: ");
			password.setFont(font2);
			password.setBounds(50,370,200,30);
			password.setForeground(Color.BLACK);
			panel.add(password);
			
			passField = new JPasswordField();
			passField.setBounds(130,370,200,30);
			panel.add(passField);
			
			work_type = new JLabel("Choose type: ");
			work_type.setFont(font2);
			work_type.setBounds(50,430,100,30);
			panel.add(work_type);
			
			r1 = new JRadioButton("Fulltime");
			r1.setBackground(Color.GREEN);
			r1.setActionCommand("Fulltime");
			r1.setBounds(150,430,80,30);
			panel.add(r1);
			
			r2 = new JRadioButton("Part Time");
			r2.setBackground(Color.GREEN);
			r2.setActionCommand("PartTime");
			r2.setBounds(240,430,80,30);
			panel.add(r2);
			
			bg1 = new ButtonGroup();
			bg1.add(r1);
			bg1.add(r2);
			
			insert = new JButton("Insert");
			insert.setBounds(100,470,100,40);
			insert.addActionListener(this);
			panel.add(insert);
			
			back = new JButton("Back");
			back.setBounds(250,470,100,40);
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
		else if(click.equals(insert.getText()))
		{
			Row_Insert();
			Insert();
		}
	}
	
	public void Row_Insert()
	{
		boolean inter = true;
		if(namefiled.getText().equals("") || mailfield.getText().equals("") || cellfield.getText().equals("") || addField.getText().equals("") || petField.getText().equals("") || ageField.getText().equals("") || passField.getText().equals(""))
		{
			JOptionPane.showMessageDialog(panel,"Field Can't be empty");
			inter = false;
		}
		if(bg1.getSelection() == null)
		{
			JOptionPane.showMessageDialog(panel,"Select a gender");
			inter = false;
		}
		else
		{	if(inter == true)
			{	
				String query = "INSERT INTO employee_info(Name,Email,Cell,Address,Pet_type,Work_type,Age,Password) values('"+namefiled.getText()+"','"+mailfield.getText()+"',"+cellfield.getText()+",'"+addField.getText()+"','"+petField.getText()+"','"+bg1.getSelection().getActionCommand()+"',"+ageField.getText()+",'"+passField.getText()+"')";
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
						JOptionPane.showMessageDialog(panel,"success");
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
	public void Insert()
	{
		String query = "INSERT INTO login(Email,Password,Type) values('"+mailfield.getText()+"','"+passField.getText()+"','Employee')";
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

}
 
