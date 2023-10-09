package view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameObjectTypeListComponent extends JComponent
{
	private MainWindow creator;
	public Color draw_color;
	public int id;
	
	private JCheckBox chkbx;
	private JLabel lblColorPreview;
	
	public GameObjectTypeListComponent(MainWindow creator, int id, int count, String name, Color draw_color)
	{
		this.creator = creator;
		this.id = id;
		this.draw_color = draw_color;
		this.setSize(200, 30);
		this.setMaximumSize(new Dimension(200, 30));
		
		this.setToolTipText(""+id);
		
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
		
		JLabel lblObjectName = new JLabel(String.format("%s (%d)", name, count));
		lblObjectName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		chkbx = new JCheckBox("");
		chkbx.setSelected(true);
		chkbx.setFont(new Font("Tahoma", Font.PLAIN, 14));

		BufferedImage bimg = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bimg.createGraphics();
		g2d.setColor(draw_color);
		g2d.fillRect(0, 0, 100, 100);
		g2d.dispose();
		lblColorPreview = new JLabel(new ImageIcon(bimg));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(chkbx)
					.addComponent(lblColorPreview, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(lblObjectName, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(chkbx, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
				.addComponent(lblColorPreview, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
				.addComponent(lblObjectName, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		CreateActionEvents();
	}
	
	public boolean IsEnabled()
	{
		return chkbx.isSelected();
	}
	
	private void CreateActionEvents()
	{
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}

			public void mouseEntered(MouseEvent e)
			{
				creator.HighlightGameObjectType(id);
			}

			public void mouseExited(MouseEvent e)
			{
				creator.UnhighlightGameObjectType(id);
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
					
					creator.UpdateGobjectTypeColor(id, draw_color);
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
				creator.SetDrawGameObjectType(id, chkbx.isSelected());
				creator.DrawImage();
			}
		});
	}
}