package view.MotherPoolBuilder;

import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.MainWindow;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JColorChooser;

public class GameObjectPoolListCompilerComponent extends JComponent
{
	public int pool_entry;
	public Color draw_color;
	
	private GameObjectPoolListCompilerComponent self;
	private MainWindow mw;
	private NewMotherPoolDialog creator;
	
	private JLabel lblColorPreview;
	private JLabel lblID;
	
	private JColorChooser colorChooser = new JColorChooser();
	
	private JLabel lblStateChange;
	
	public boolean isIncluded = false;
	public JSpinner spnChance;
	
	public GameObjectPoolListCompilerComponent(MainWindow mw, NewMotherPoolDialog creator, int pool_entry, int chance, int maxSpawns, String comment, Color draw_color, boolean editable)
	{
		this.self = this;
		this.mw = mw;
		this.creator = creator;
		this.pool_entry = pool_entry;
		this.draw_color = draw_color;
		this.setSize(250, 75);
		this.setMaximumSize(new Dimension(250, 75));
		this.setToolTipText(!comment.isEmpty() ? comment : "[No Description]");
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
		);

		BufferedImage bimg = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bimg.createGraphics();
		g2d.setColor(draw_color);
		g2d.fillRect(0, 0, 100, 100);
		g2d.dispose();
		lblColorPreview = new JLabel(new ImageIcon(bimg));
		lblColorPreview.setBounds(0, 34, 40, 40);
		
		lblID = new JLabel(String.format("%d", pool_entry));
		lblID.setBounds(50, 5, 57, 31);
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblStateChange = new JLabel("+");
		lblStateChange.setBounds(0, 1, 40, 30);
		lblStateChange.setHorizontalAlignment(SwingConstants.CENTER);
		lblStateChange.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		spnChance = new JSpinner();
		spnChance.setModel(new SpinnerNumberModel(chance, 0, 100, 1));
		spnChance.setBounds(100, 46, 50, 20);
		spnChance.setEnabled(editable);
		
		JLabel lblChance = new JLabel("Chance:");
		lblChance.setBounds(50, 38, 57, 36);
		lblChance.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.setLayout(null);
		panel.add(lblStateChange);
		panel.add(lblColorPreview);
		panel.add(lblID);
		panel.add(lblChance);
		panel.add(spnChance);
		
		JLabel lblMaxSpawns = new JLabel(String.format("Max: %d", maxSpawns));
		lblMaxSpawns.setBounds(100, 15, 84, 13);
		panel.add(lblMaxSpawns);
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
				mw.HighlightLocalPool(pool_entry);
			}

			public void mouseExited(MouseEvent e)
			{
				mw.UnhighlightLocalPool(pool_entry);
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
					
					mw.UpdateLocalPoolColor(pool_entry, draw_color);
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
		
		lblStateChange.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e)
			{
				if (isIncluded)
				{
					isIncluded = false;
					creator.MoveToExcludeList(self);
					self.ChangeButtonText("+");
					spnChance.setValue(0);
					spnChance.setEnabled(false);
				}
				else
				{
					isIncluded = true;
					creator.MoveToIncludeList(self);
					self.ChangeButtonText("X");
					spnChance.setEnabled(true);
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
		
		/*spnChance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e)
			{
				
			}
		});*/
	}
	
	public void ChangeButtonText(String text)
	{
		lblStateChange.setText(text);
	}
}
