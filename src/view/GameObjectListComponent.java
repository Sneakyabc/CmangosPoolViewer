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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameObjectListComponent extends JComponent
{
	private MainWindow creator;
	public int guid;
	
	private JCheckBox chkbx;
	
	public GameObjectListComponent(MainWindow creator, int guid, String name)
	{
		this.creator = creator;
		this.guid = guid;
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
		
		BufferedImage bimg = new BufferedImage(25, 25, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bimg.createGraphics();
		g2d.setColor(Color.gray);
		g2d.fillRect(0, 0, 100, 100);
		g2d.dispose();
		
		JLabel lblObjectName = new JLabel(guid + " " + name);
		lblObjectName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		chkbx = new JCheckBox("");
		chkbx.setSelected(true);
		chkbx.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(chkbx)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblObjectName, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(chkbx, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
				.addComponent(lblObjectName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		CreateActionEvents();
	}
	
	private void CreateActionEvents()
	{
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}

			public void mouseEntered(MouseEvent e)
			{
				creator.HighlightGameObject(guid);
			}

			public void mouseExited(MouseEvent e)
			{
				creator.UnhighlightGameObject(guid);
			}
		});
		
		chkbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				creator.SetDrawGameObject(guid, chkbx.isSelected());
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