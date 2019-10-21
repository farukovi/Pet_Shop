import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.font.*;
import java.sql.*;
import java.util.regex.Pattern;

public class Registration extends JFrame implements ActionListener
{
	JLabel name,email,cell,title,gender,day,mash,year;
	JTextField namefiled,mailfield, agefield;
	JButton submit;
	JPasswordField cellfield;
	JPanel panel;
	JComboBox combo,combo1,combo2;
	JRadioButton r1,r2;
	ButtonGroup bg1;
	JButton insert,back;
	
	public Registration()
	{
		super("Registration Form");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,100,450,400);
			
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GREEN);
		
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		Font font2 = new Font("SansSerif", Font.BOLD, 15);
		
		title = new JLabel("Registration Form");
		title.setBounds(80,0,400,50);
		title.setForeground(Color.BLACK);
		title.setFont(font1);
		panel.add(title);
		
		name = new JLabel("Name: ");
		name.setBounds(60,70,200,100);
		name.setForeground(Color.BLACK);
		name.setFont(font2);
		panel.add(name);
		
		namefiled = new JTextField();
		namefiled.setBounds(130,110,200,30);
		panel.add(namefiled);
		
		email = new JLabel("Email: ");
		email.setBounds(50,100,200,100);
		email.setForeground(Color.BLACK);
		email.setFont(font2);
		panel.add(email);
		
		mailfield = new JTextField();
		mailfield.setBounds(130,150,200,30);
		panel.add(mailfield);
		
		cell = new JLabel("Password: ");
		cell.setFont(font2);
		cell.setBounds(50,150,200,100);
		cell.setForeground(Color.BLACK);
		panel.add(cell);
		
		cellfield = new JPasswordField();
		cellfield.setBounds(130,190,200,30);
		panel.add(cellfield);
		
		day = new JLabel("Month: ");
		day.setBounds(50,230,100,30);
		day.setForeground(Color.BLACK);
		panel.add(day);
		
		String[] mon=new String[12];
		for(int i=0,month=1;i<12;i++)
		{
			mon[i] = month + "";
			month++;
		}
		
		combo = new JComboBox(mon);
		combo.setBounds(100,235,50,20);
		panel.add(combo);
		
		day = new JLabel("Date: ");
		day.setBounds(160,230,100,30);
		day.setForeground(Color.BLACK);
		panel.add(day);
		
		String[] din = new String[31];
		for(int i=0,j=1;i<31;i++)
		{
			din[i] =  j + "";
			j++;
		}
		
		combo1 = new JComboBox(din);
		combo1.setBounds(200,235,50,20);
		panel.add(combo1);
		
		day = new JLabel("Year: ");
		day.setBounds(270,230,100,30);
		day.setForeground(Color.BLACK);
		panel.add(day);
		
		String[] birthyear = new String[60];
		for(int i=0,j=1960;i<55;i++)
		{
			birthyear[i] =  j + "";
			j++;
		}
		
		combo2 = new JComboBox(birthyear);
		combo2.setBounds(320,235,50,20);
		panel.add(combo2);
		
		gender = new JLabel("Choose Gender: ");
		gender.setFont(font2);
		gender.setBounds(50,230,150,100);
		panel.add(gender);
		
		r1 = new JRadioButton("Male");
		r1.setBackground(Color.GREEN);
		r1.setActionCommand("Male");
		r1.setBounds(180,260,60,30);
		panel.add(r1);
		
		r2 = new JRadioButton("Female");
		r2.setBackground(Color.GREEN);
		r2.setActionCommand("Female");
		r2.setBounds(240,260,80,30);
		panel.add(r2);
		
		bg1 = new ButtonGroup();
		bg1.add(r1);
		bg1.add(r2);
		
		insert = new JButton("Reister");
		insert.setBounds(100,300,100,50);
		insert.addActionListener(this);
		panel.add(insert);
		
		back = new JButton("Back");
		back.setBounds(250,300,100,50);
		back.addActionListener(this);
		panel.add(back);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String click = ae.getActionCommand();
		if(click.equals(back.getText()))
		{
			Home h = new Home();
			this.setVisible(false);
			h.setVisible(true);
		}
		else if(click.equals(insert.getText()))
		{
			Row_Insert();
			Login_Insert();
			//Your_Id();
		}
	}
	
	public void Row_Insert()
	{
		boolean inter = true;
		if(namefiled.getText().equals("") || mailfield.getText().equals("") || cellfield.getText().equals(""))
		{
			JOptionPane.showMessageDialog(panel,"Field Can't be empty");
			inter = false;
		}
		int validmail = mailfield.getText().length();
		String submail = mailfield.getText().substring(validmail-4,validmail);
		// if(submail != ".com")
		// {
			// JOptionPane.showMessageDialog(panel,"Mail must be valid");
		// }
		String checkname = namefiled.getText();
		char[] chars = checkname.toCharArray();
		String regex = "(.)*(\\d)(.)*";      
		Pattern pattern = Pattern.compile(regex);
		boolean containsNumber = pattern.matcher(checkname).matches();
		if(containsNumber == true)
		{
			JOptionPane.showMessageDialog(panel, "Valid Name Plz");
			inter = false;
		}
		int pass = cellfield.getText().length();
		if(pass < 5)
		{
			JOptionPane.showMessageDialog(panel,"At least 5 char long");
			inter = false;
		}
		
		String gender = bg1.getSelection().getActionCommand();
		String bdaymonth = combo.getSelectedItem().toString();
		String bday = combo1.getSelectedItem().toString();
		String byear = combo2.getSelectedItem().toString();
		String birthday = bdaymonth + " / " + bday + " / "+ byear;
		
		
		if(bg1.getSelection() == null)
		{
			JOptionPane.showMessageDialog(panel,"Select a gender");
			inter = false;
		}
		else
		{	if(inter == true)
			{	
				String query = "INSERT INTO customer_info(Name,Email,Password,Date_Of_Birth,Gender) values('"+namefiled.getText()+"','"+mailfield.getText()+"','"+cellfield.getText()+"','"+ combo.getSelectedItem().toString()+"/"+combo1.getSelectedItem().toString()+combo2.getSelectedItem().toString() +"', '"+bg1.getSelection().getActionCommand()+"')";
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
	public void Login_Insert()
	{
		String query = "INSERT INTO login(Email,Password,Type) values('"+mailfield.getText()+"','"+cellfield.getText()+"','Customer')";
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
						JOptionPane.showMessageDialog(panel,"You can now login in our website with Your Email and Password");
					}
				}
				catch(Exception ex)
				{
					System.out.println("Exception : " +ex.getMessage());
				}
	}
} 
