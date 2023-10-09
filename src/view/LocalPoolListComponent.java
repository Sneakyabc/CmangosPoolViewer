package view;

import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JColorChooser;

public class LocalPoolListComponent extends JComponent
{
	public int pool_entry;
	public Color draw_color;
	
	private MainWindow creator;
	private JLabel lblColorPreview;
	private JLabel lblID;
	
	private JColorChooser colorChooser = new JColorChooser();
	private JCheckBox chkbx;
	
	public LocalPoolListComponent(MainWindow creator, int pool_entry, int count, Color draw_color)
	{
		this.creator = creator;
		this.pool_entry = pool_entry;
		this.draw_color = draw_color;
		this.setSize(200, 30);
		this.setMaximumSize(new Dimension(200, 30));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE)
					.addContainerGap())
		);

		BufferedImage bimg = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bimg.createGraphics();
		g2d.setColor(draw_color);
		g2d.fillRect(0, 0, 100, 100);
		g2d.dispose();
		lblColorPreview = new JLabel(new ImageIcon(bimg));
		
		lblID = new JLabel(String.format("%d (%d)", pool_entry, count));
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		chkbx = new JCheckBox("");
		chkbx.setSelected(true);
		chkbx.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(chkbx)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblColorPreview, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblID, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addGap(43))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(chkbx, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
				.addComponent(lblColorPreview, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
				.addComponent(lblID, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		CreateActionEvents();
	}
	
	private void CreateActionEvents()
	{
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e)
			{
//				creator.FilterToMotherPool(mother_pool);
			}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}

			public void mouseEntered(MouseEvent e)
			{
				creator.HighlightLocalPool(pool_entry);
			}

			public void mouseExited(MouseEvent e)
			{
				creator.UnhighlightLocalPool(pool_entry);
			}
		});
		
		lblColorPreview.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e){
				draw_color = JColorChooser.showDialog(lblColorPreview, "Choose a color", draw_color);
				
				if (draw_color != null)
				{
					BufferedImage bimg = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
					Graphics2D g2d = bimg.createGraphics();
					g2d.setColor(draw_color);
					g2d.fillRect(0, 0, 100, 100);
					lblColorPreview.setIcon(new ImageIcon(bimg));
					g2d.dispose();
					
					creator.UpdateLocalPoolColor(pool_entry, draw_color);
				}
			}

			public void mouseReleased(MouseEvent e) {
//				System.out.println("2");
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
//				System.out.println("5");
			}
		});
		
		chkbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				creator.SetDrawLocalPool(pool_entry, chkbx.isSelected());
			}
		});
	}
	
	public boolean IsEnabled()
	{
		return chkbx.isSelected();
	}
	
	public void SetChecked(boolean checked)
	{
		chkbx.setSelected(checked);
	}
}
