package view.MotherPoolBuilder;

import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

import common.GameObjectPool;
import common.PoolPool;
import view.MainWindow;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JColorChooser;

public class MotherPoolBuilderListComponent extends JComponent
{
	public int mother_pool;
	public int count;
	public Color draw_color;
	
	private MotherPoolBuilderListComponent self;
	private MainWindow mw;
	private MotherPoolBuilder creator;
	private JLabel lblColorPreview;
	private JLabel lblID;
	
	private static JColorChooser colorChooser = new JColorChooser();
	
	private NewMotherPoolDialog objectCompiler;
	
	private JLabel lblStateChange;
	
	public ArrayList<Integer> originalEntries;
	
	public boolean isNew = false;
	public boolean isDeleted = false;
	private JButton btnEdit;
	
	/**
	 * @wbp.parser.constructor
	 */
	public MotherPoolBuilderListComponent(MainWindow mw, MotherPoolBuilder creator, int mother_pool, int count, Color draw_color, NewMotherPoolDialog compiler)
	{
		this.mw = mw;
		this.creator = creator;
		this.mother_pool = mother_pool;
		this.count = count;
		this.draw_color = draw_color;
		this.originalEntries = new ArrayList<Integer>();
		this.objectCompiler = compiler;
		
		CreateComponents();
	}
	
	public MotherPoolBuilderListComponent(MainWindow mw, MotherPoolBuilder creator, int mother_pool, int count, Color draw_color, NewMotherPoolDialog compiler, boolean isNew)
	{
		this.mw = mw;
		this.creator = creator;
		this.mother_pool = mother_pool;
		this.count = count;
		this.draw_color = draw_color;
		this.originalEntries = new ArrayList<Integer>();
		this.objectCompiler = compiler;
		this.isNew = isNew;
		
		CreateComponents();
	}
	
	private void CreateComponents()
	{
		this.self = this;
		this.setSize(250, 30);
		this.setMaximumSize(new Dimension(200, 30));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
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
		
		lblID = new JLabel(String.format("%d (%d)", mother_pool, count));
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblStateChange = new JLabel("");
		if (mother_pool != 0)
			lblStateChange.setText("X");
		lblStateChange.setHorizontalAlignment(SwingConstants.CENTER);
		lblStateChange.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		btnEdit = new JButton("Edit");
		if (mother_pool == 0)
			btnEdit.setEnabled(false);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblStateChange, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblColorPreview, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEdit)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblID, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblID, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblStateChange, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
							.addComponent(lblColorPreview, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
				mw.HighlightMotherPool(mother_pool);
			}
			
			public void mouseExited(MouseEvent e)
			{
				mw.UnhighlightMotherPool(mother_pool);
			}
		});
		
		lblColorPreview.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e)
			{
				draw_color = JColorChooser.showDialog(lblColorPreview, "Choose a color", draw_color);
				
				if (draw_color != null)
				{
					BufferedImage bimg = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
					Graphics2D g2d = bimg.createGraphics();
					g2d.setColor(draw_color);
					g2d.fillRect(0, 0, 100, 100);
					lblColorPreview.setIcon(new ImageIcon(bimg));
					g2d.dispose();
					
					mw.UpdateMotherPoolColor(mother_pool, draw_color);
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
				if (isNew)
				{
					creator.RemoveNewMotherPool(self);
				}
				else if (isDeleted)
				{
					isDeleted = false;
					creator.MoveToExistingList(self);
					self.ChangeButtonText("X");
				}
				else if (mother_pool != 0)
				{
					isDeleted = true;
					creator.MoveToDeleteList(self);
					self.ChangeButtonText("+");
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
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (objectCompiler == null)
				{
					objectCompiler = new NewMotherPoolDialog(mw, creator, mother_pool);
				}
				
				objectCompiler.ShowCompiler();
				
				if (objectCompiler.OkClicked())
				{
					PoolPool existingPool = mw.objectData.motherPoolsMap.get(mother_pool);
					existingPool.max_limit = (int) objectCompiler.spnrMaxSpawns.getValue();
					existingPool.comment = objectCompiler.frmtxtComment.getText();
					
					int[] entries = new int[existingPool.pools.size()];
					for (int i = 0; i < entries.length; ++i) entries[i] = existingPool.pools.get(i).pool_entry;
					
					for (var entry : entries) // array is to avoid concurrent modification
						// Remove any needed
						if (!objectCompiler.pools.contains(mw.objectData.gobjectPoolsMap.get(entry)))
							mw.objectData.MovePoolToMotherPool(mw.objectData.motherPoolsMap.get(0), mw.objectData.gobjectPoolsMap.get(entry));
					
					for (var lPool : objectCompiler.pools)
					{
						// Add new ones needed
						if (!existingPool.pools.contains(lPool))
							mw.objectData.MovePoolToMotherPool(existingPool, lPool);
						lPool.chance = objectCompiler.GetChanceFor(lPool.pool_entry);
					}
					
					mw.DrawImage();
				}
			}
		});
	}
	
	public void SetCompilerDialog(NewMotherPoolDialog newDialog)
	{
		this.objectCompiler = newDialog;
	}
	
	public void NoteCurrentObjects()
	{
		originalEntries.clear();
		for (var lPool : mw.objectData.motherPoolsMap.get(mother_pool).pools)
			originalEntries.add(lPool.pool_entry);
	}
	
	public void ChangeButtonText(String text)
	{
		lblStateChange.setText(text);
	}
}
