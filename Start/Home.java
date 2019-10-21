import java.lang.*;
import Customer.*;
import Admin.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.font.*;
//package Start;

//package Home;

public class Home extends JFrame implements ActionListener
{
	private JLabel title,txt1,txt2;
	private JTextField text;
	private JPasswordField pass;
	private JButton login, registration;
	private JPanel panel;
	
	public Home()
	{
		super("Pet Shop");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,100,450,400);
		
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		Font font2 = new Font("SansSerif", Font.BOLD, 15);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GREEN);
		
		title = new JLabel("Welcome To Our Pet Shop");
		title.setBounds(80,-10,400,200);
		title.setForeground(Color.BLACK);
		title.setFont(font1);
		panel.add(title);
		
		txt1 = new JLabel("Enter Your Mail: ");
		txt1.setBounds(58,100,200,100);
		txt1.setForeground(Color.BLACK);
		txt1.setFont(font2);
		panel.add(txt1);
		
		text = new JTextField();
		text.setBounds(200,140,200,30);
		panel.add(text);
		
		txt2 = new JLabel("Enter Your Password: ");
		txt2.setBounds(20,150,200,100);
		txt2.setForeground(Color.BLACK);
		txt2.setFont(font2);
		panel.add(txt2);
		
		pass = new JPasswordField();
		pass.setBounds(200,190,200,30);
		panel.add(pass);
		
		login = new JButton("Login");
		login.setBounds(100,250,100,50);
		login.addActionListener(this);
		panel.add(login);
		
		registration = new JButton("Sign Up");
		registration.setBounds(300,250,100,50);
		registration.addActionListener(this);
		panel.add(registration);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String ButtonClicked = ae.getActionCommand();
		if(ButtonClicked.equals(login.getText()))
		{
			check();
		}
		else if(ButtonClicked.equals(registration.getText()))
		{
			Registration form = new Registration();
			form.setVisible(true);
			setVisible(false);
		}
	}
	
	public void check()
	{
		String x = "";
		String query = "SELECT * FROM login WHERE Email='"+text.getText()+"' and Password="+pass.getText();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		//System.out.println(query);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pet_shop","root", "");
			System.out.println("Connection Done");
			st = con.createStatement();
			System.out.println("Statement Created");
			rs = st.executeQuery(query);
			System.out.println("result received");
			while(rs.next())
			{
				int type = rs.getInt("Type");
				System.out.println(type);
				if(type == 0)
				{
					Admin c = new Admin();
					c.setVisible(true);
					this.setVisible(false);
				}
				else if(type == 1)
				{
					String mail = text.getText();
					Customer c = new Customer(mail);
					c.setVisible(true);
					this.setVisible(false);
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
	}
}