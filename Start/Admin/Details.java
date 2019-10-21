package Admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;

public class Details extends JFrame implements ActionListener {
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);
	JButton btn,updtbtn,dltbtn;
    public Details()
	{
			cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
			model.addColumn("Id");
			model.addColumn("Name");
			model.addColumn("Email");
			model.addColumn("Cell");
			model.addColumn("Address"); 
			model.addColumn("Pet_Type");
			model.addColumn("Work_Type");
			model.addColumn("Age");
			updtbtn = new JButton("Update");
			dltbtn = new JButton("Delete");
			//model.addColumn("Address"); 
			int i =0;
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pet_shop", "root", "");
				PreparedStatement pstm = con.prepareStatement("SELECT * FROM employee_info");
				ResultSet Rs = pstm.executeQuery();
				while(Rs.next())
				{
					//String z = "btn" + Integer.toString(i);
					model.addRow(new Object[]{Rs.getString(1),Rs.getString(2), Rs.getString(3),Rs.getInt(4),Rs.getString(5),Rs.getString(6),Rs.getString(7),Rs.getInt(8)});
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
			cnt.add(btn);
			
			updtbtn = new JButton("Update");
			updtbtn.setSize(100,40);
			updtbtn.addActionListener(this);
			cnt.add(updtbtn);
			
			dltbtn = new JButton("Delete");
			dltbtn.setSize(100,40);
			dltbtn.addActionListener(this);
			cnt.add(dltbtn);
			
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
		else if(click.equals(updtbtn.getText()))
		{
			String id = JOptionPane.showInputDialog("Provide Id that you want to update");
			Update ue = new Update(id);
			this.setVisible(false);
			ue.setVisible(true);
		}
		else if(click.equals(dltbtn.getText()))
		{
			String id = JOptionPane.showInputDialog("Provide Id that you want to delete");
			Delete ue = new Delete(id);
			this.setVisible(false);
			ue.setVisible(true);
		}
	}
}

