package Admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;

public class Sold_list extends JFrame implements ActionListener {
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);
	JButton btn,updtbtn,dltbtn;
    public Sold_list()
	{
			cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
			model.addColumn("Id");
			model.addColumn("Pet_Name");
			model.addColumn("Quantity");
			model.addColumn("Price");
			model.addColumn("Color"); 
			model.addColumn("Email");
			model.addColumn("Time");
			//model.addColumn("Address"); 
			int i =0;
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pet_shop", "root", "");
				PreparedStatement pstm = con.prepareStatement("SELECT * FROM sold_list");
				ResultSet Rs = pstm.executeQuery();
				while(Rs.next())
				{
					//String z = "btn" + Integer.toString(i);
					model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getInt(3),Rs.getInt(4),Rs.getString(5),Rs.getString(6),Rs.getString(7)});
				}
			} 
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
			JScrollPane pg = new JScrollPane(jtbl);
			cnt.add(pg);
			
			btn = new JButton("Back");
			btn.setSize(100,40);
			btn.addActionListener(this);
			cnt.add(btn);
			
			this.pack();
        
    }
	public void actionPerformed(ActionEvent ae)
	{
		String click = ae.getActionCommand();
		if(click.equals(btn.getText()))
		{
			Admin a = new Admin();
			a.setVisible(true);
			this.setVisible(false);
		}
	}
}

