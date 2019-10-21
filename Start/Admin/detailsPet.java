package Admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;

public class detailsPet extends JFrame implements ActionListener {
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);
	JButton btn,updtbtn,dltbtn;
    public detailsPet()
	{
			cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
			model.addColumn("Id");
			model.addColumn("Name");
			model.addColumn("Pet_Type");
			model.addColumn("Price");
			model.addColumn("Food");
			model.addColumn("Color");
			model.addColumn("Quantity");
			model.addColumn("Picture");
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pet_shop", "root", "");
				PreparedStatement pstm = con.prepareStatement("SELECT * FROM pets_info");
				ResultSet Rs = pstm.executeQuery();
				while(Rs.next())
				{
					model.addRow(new Object[]{Rs.getString(1),Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),Rs.getString(6),Rs.getString(7),Rs.getString(8)});
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
			UpdatePet ue = new UpdatePet(id);
			this.setVisible(false);
			ue.setVisible(true);
		}
		else if(click.equals(dltbtn.getText()))
		{
			String id = JOptionPane.showInputDialog("Provide Id that you want to delete");
			DeletePet ue = new DeletePet(id);
			this.setVisible(false);
			ue.setVisible(true);
		}
	}
}

