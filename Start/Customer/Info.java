package Customer;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.font.*;
import java.util.regex.Pattern;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar; 

public class Info extends JFrame implements ActionListener
{
	JLabel name,email,cell,title,address,pet_type,age,password,work_type;
	JLabel namefiled,mailfield,cellfield,addField,petField;
	JTextField ageField,foodField;
	JPanel panel;
	JPasswordField passField;
	JRadioButton r1,r2,r3,r4,r5;
	ButtonGroup bg1;
	JButton insert,back,row_insert,send,view;
	public String e_mail,pet_name;
	
	public Info(String petname,String mail)
	{
		super("Pet Shop");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,100,450,550);
		
		e_mail = mail;
		pet_name = petname;
		
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
			
			namefiled = new JLabel();
			namefiled.setBounds(130,70,200,30);
			panel.add(namefiled);
			
			email = new JLabel("Price: ");
			email.setBounds(40,120,200,30);
			email.setForeground(Color.BLACK);
			email.setFont(font2);
			panel.add(email);
			
			mailfield = new JLabel();
			mailfield.setBounds(130,120,200,30);
			panel.add(mailfield);
			
			cell = new JLabel("Food: ");
			cell.setFont(font2);
			cell.setBounds(50,170,200,30);
			cell.setForeground(Color.BLACK);
			panel.add(cell);
			
			cellfield = new JLabel();
			cellfield.setBounds(130,170,200,30);
			panel.add(cellfield);
			
			address = new JLabel("Color: ");
			address.setFont(font2);
			address.setBounds(50,220,60,30);
			address.setForeground(Color.BLACK);
			panel.add(address);
			
			addField = new JLabel();
			addField.setBounds(130,220,200,30);
			panel.add(addField);
			
			pet_type = new JLabel("Quantity: ");
			pet_type.setFont(font2);
			pet_type.setBounds(50,270,200,30);
			pet_type.setForeground(Color.BLACK);
			panel.add(pet_type);
			
			petField = new JLabel();
			petField.setBounds(130,270,200,30);
			panel.add(petField);
			
			age = new JLabel("How much do u want to buy: ");
			age.setBounds(50,320,200,30);
			age.setForeground(Color.BLACK);
			panel.add(age);
			
			ageField = new JTextField();
			ageField.setBounds(230,320,200,30);
			panel.add(ageField);
			
		String sql = "SELECT * FROM `pets_info` WHERE Name = '"+petname+"'";
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
				namefiled.setText(rs.getString("Name"));
				mailfield.setText(rs.getString("Price"));
				cellfield.setText(rs.getString("Food"));
				addField.setText(rs.getString("Color"));
				//petField.setText(rs.getString("Color"));
				petField.setText(Integer.toString(rs.getInt("Quantity")));
				String[] words = rs.getString("Color").split(",");
				int petColor = words.length;
				//System.out.println(petColor);
				//if(petColor==1){r1.setText(words[0]);r2.setVisible(false);r3.setVisible(false);r4.setVisible(false);}
				//else if(petColor==2){r1.setText(words[0]);r2.setText(words[1]);r3.setVisible(false);r4.setVisible(false);}
				//else if(petColor==3){r1.setText(words[0]);r2.setText(words[1]);r3.setText(words[2]);r4.setVisible(false);}
				//else if(petColor==4){r1.setText(words[0]);r2.setText(words[1]);r3.setText(words[2]);r4.setText(words[3]);}
			}
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
			send = new JButton("Chat");
			send.setBounds(100,370,100,40);
			send.addActionListener(this);
			panel.add(send);
			
			view = new JButton("Reply");
			view.setBounds(250,370,100,40);
			view.addActionListener(this);
			panel.add(view);
			
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
		if(click.equals(send.getText()))
		{
			Sendmessage h = new Sendmessage(e_mail);
			this.setVisible(false);
			h.setVisible(true);
		}
		else if(click.equals(view.getText()))
		{
			String sql = "SELECT COUNT(Mail) FROM reply WHERE Mail= '"+e_mail+"'";
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
						ViewMessage viewmessage = new ViewMessage(e_mail);
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
		else if(click.equals(back.getText()))
		{
			Customer h = new Customer(e_mail);
			this.setVisible(false);
			h.setVisible(true);
		}
		else if(click.equals(row_insert.getText()))
		{
			Insert();
		}
	}
	public void Insert()
	{
		String sql = "SELECT * FROM `pets_info` WHERE Name = '"+pet_name+"'";
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
				int x = rs.getInt("Quantity");
				int price = rs.getInt("Price");
				String color = rs.getString("Color");
				int z = Integer.parseInt(ageField.getText());
				if(z > x || z < 0)
				{
					JOptionPane.showMessageDialog(panel,"Insufficient Amount");
				}
				else
				{
					int sum = z * price;
					Date date = Calendar.getInstance().getTime();  
					DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
					String strDate = dateFormat.format(date);
					String query = "INSERT INTO `sold_list`(`Pet_Name`, `Quantity`, `Price`, `Color`, `Email`, `Time`) VALUES ('"+pet_name+"',"+z+","+sum+",'"+color+"','"+e_mail+"','"+strDate+"');";
					Class.forName("com.mysql.jdbc.Driver");
					//System.out.println("Driver loaaded");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pet_shop","root", "");
					//System.out.println("Connection Done");
					st = con.createStatement();
					//System.out.println("Statement Created");
					//rs = st.executeQuery(sql);
					int row = ((java.sql.Statement)st).executeUpdate(query);
					if(row > 0)
					{
						JOptionPane.showMessageDialog(panel,"Succes.Your Order is Confirmed.Come 2 days Later and take your order");
						Info f = new Info(pet_name,e_mail);f.setVisible(true);this.setVisible(false);
					}
				}
				
			}
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
} 
