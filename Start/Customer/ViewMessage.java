package Customer;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.font.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;
//package Start;

//package Home;

public class ViewMessage extends JFrame implements ActionListener
{
	private JLabel title,txt1;
	private JTextField text,txt2;
	private JPasswordField pass;
	private JButton login,send, registration,mail;
	private JPanel panel;
	public String mails;
	
	public ViewMessage(String e_mail)
	{
		super("Pet Shop");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,100,450,500);
		
		mails = e_mail;
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GREEN);
		
		title = new JLabel("Welcome To Our Pet Shop");
		title.setBounds(80,-10,400,200);
		title.setForeground(Color.BLACK);
		//title.setFont(font1);
		panel.add(title);
		
		txt1 = new JLabel();
		txt1.setBounds(80,10,300,200);
		txt1.setForeground(Color.BLACK);
		panel.add(txt1);
		
		txt2 = new JTextField();
		txt2.setBounds(20,200,350,100);
		panel.add(txt2);
		
		String query = "SELECT * FROM reply WHERE Mail = '"+e_mail+"'";
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
			rs = st.executeQuery(query);
			//System.out.println("result received");
					while(rs.next())
					{
						txt1.setText(rs.getString("Mail"));
					}
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		
		send = new JButton("Reply");
		send.addActionListener(this);
		send.setBounds(100,320,100,50);
		panel.add(send);
		
		mail = new JButton("Back");
		mail.setBounds(220,320,100,50);
		mail.addActionListener(this);
		panel.add(mail);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String ButtonClicked = ae.getActionCommand();
		if(ButtonClicked.equals(mail.getText()))
		{
			Customer c = new Customer(mails);
			c.setVisible(true);
			this.setVisible(false);
		}
		else if(ButtonClicked.equals(send.getText()))
		{
			Chat();
		}
	}
	public void Chat()
	{
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		String strDate = dateFormat.format(date);
		String sql = "INSERT INTO mail(Mail,Time,Text) VALUES ('"+mails+"','"+txt2.getText()+"','"+strDate+"')";
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
			int row = ((java.sql.Statement)st).executeUpdate(sql);
			//System.out.println("result received");
			if(row > 0)
			{
				JOptionPane.showMessageDialog(panel,"Sent");
			}
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
}