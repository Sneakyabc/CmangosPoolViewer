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

public class MotherPoolListComponent extends JComponent
{
	public int mother_pool;
	public Color draw_color;
	
	private MainWindow creator;
	private JLabel lblColorPreview;
	private JLabel lblID;
	
	private JColorChooser colorChooser = new JColorChooser();
	private JCheckBox chkbx;
	
	public MotherPoolListComponent(MainWindow creator, int mother_pool, int count_pools, int count_gobjects, Color draw_color)
	{
		this.creator = creator;
		this.mother_pool = mother_pool;
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
		
		lblID = new JLabel(String.format("%d (%d, %d)", mother_pool, count_pools, count_gobjects));
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
					.addComponent(lblID, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(chkbx, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
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

			public void mouseReleased(MouseEvent e) {
//				System.out.println("2");
			}

			public void mouseEntered(MouseEvent e)
			{
				creator.HighlightMotherPool(mother_pool);
			}

			public void mouseExited(MouseEvent e)
			{
				creator.UnhighlightMotherPool(mother_pool);
			}

			public void mousePressed(MouseEvent e) {
//				System.out.println("5");
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
					
					creator.UpdateMotherPoolColor(mother_pool, draw_color);
				}
			}

			public void mouseReleased(MouseEvent e) {
//				System.out.println("2");
			}

			public void mouseEntered(MouseEvent e) {
//				System.out.println("3");
			}

			public void mouseExited(MouseEvent e) {
//				System.out.println("4");
			}

			public void mousePressed(MouseEvent e) {
//				System.out.println("5");
			}
		});
		
		chkbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				creator.SetDrawMotherPool(mother_pool, chkbx.isSelected());
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
