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

public class Sendmessage extends JFrame implements ActionListener
{
	private JLabel title,txt2;
	private JTextField text,txt1;
	private JPasswordField pass;
	private JButton login, registration,mail,send;
	private JPanel panel;
	public String mails;
	
	public Sendmessage(String e_mail)
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
		
		txt1 = new JTextField();
		txt1.setBounds(20,10,400,300);
		txt1.setForeground(Color.BLACK);
		//title.setFont(font1);
		panel.add(txt1);
		
		send = new JButton("Send");
		send.addActionListener(this);
		send.setBounds(50,320,100,30);
		panel.add(send);
		
		registration = new JButton("Back");
		registration.setBounds(180,320,100,30);
		registration.addActionListener(this);
		panel.add(registration);
		
		mail = new JButton("View Message");
		mail.setBounds(310,320,100,30);
		mail.addActionListener(this);
		panel.add(mail);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String ButtonClicked = ae.getActionCommand();
		if(ButtonClicked.equals(send.getText()))
		{
			Message();
		}
		else if(ButtonClicked.equals(registration.getText()))
		{
			Customer index = new Customer(mails);
			index.setVisible(true);
			this.setVisible(false);
		}
		else if(ButtonClicked.equals(mail.getText()))
		{
			String sql = "SELECT COUNT(Mail) FROM reply WHERE Mail= '"+mails+"'";
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
						ViewMessage viewmessage = new ViewMessage(mails);
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
	public void Message()
	{
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		String strDate = dateFormat.format(date);
		String query = "INSERT INTO mail(Mail, Time,Text) VALUES ('"+mails+"','"+strDate+"','"+txt1.getText()+"')";
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