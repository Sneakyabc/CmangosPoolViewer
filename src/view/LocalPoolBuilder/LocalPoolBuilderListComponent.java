package view.LocalPoolBuilder;

import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.LineBorder;

import common.GameObject;
import common.GameObjectPool;
import view.MainWindow;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JColorChooser;

public class LocalPoolBuilderListComponent extends JComponent
{
	public int pool_entry;
	public int count;
	public Color draw_color;
	
	private LocalPoolBuilderListComponent thisComponent;
	private MainWindow mw;
	private LocalPoolBuilder creator;
	private JLabel lblColorPreview;
	private JLabel lblID;
	
	private static JColorChooser colorChooser = new JColorChooser();
	
	private NewObjectPoolDialog objectCompiler;
	
	private JLabel lblStateChange;
	
	public ArrayList<Integer> originalGuids;
	
	public boolean isNew = false;
	public boolean isDeleted = false;
	private JButton btnEdit;
	
	/**
	 * @wbp.parser.constructor
	 */
	public LocalPoolBuilderListComponent(MainWindow mw, LocalPoolBuilder creator, int pool_entry, int count, Color draw_color, NewObjectPoolDialog compiler)
	{
		this.mw = mw;
		this.creator = creator;
		this.pool_entry = pool_entry;
		this.count = count;
		this.draw_color = draw_color;
		this.originalGuids = new ArrayList<Integer>();
		this.objectCompiler = compiler;
		
		CreateComponents();
	}
	
	public LocalPoolBuilderListComponent(MainWindow mw, LocalPoolBuilder creator, int pool_entry, int count, Color draw_color, NewObjectPoolDialog compiler, boolean isNew)
	{
		this.mw = mw;
		this.creator = creator;
		this.pool_entry = pool_entry;
		this.count = count;
		this.draw_color = draw_color;
		this.originalGuids = new ArrayList<Integer>();
		this.objectCompiler = compiler;
		this.isNew = isNew;
		
		CreateComponents();
	}
	
	private void CreateComponents()
	{
		this.thisComponent = this;
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
		
		lblID = new JLabel(String.format("%d (%d)", pool_entry, count));
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblStateChange = new JLabel("");
		if (pool_entry != 0)
			lblStateChange.setText("X");
		lblStateChange.setHorizontalAlignment(SwingConstants.CENTER);
		lblStateChange.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		btnEdit = new JButton("Edit");
		if (pool_entry == 0)
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
				mw.HighlightLocalPool(pool_entry);
			}
			
			public void mouseExited(MouseEvent e)
			{
				mw.UnhighlightLocalPool(pool_entry);
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
				if (isNew)
				{
					creator.RemoveNewLocalPool(thisComponent);
				}
				else if (isDeleted)
				{
					isDeleted = false;
					creator.MoveToExistingList(thisComponent);
					thisComponent.ChangeButtonText("X");
				}
				else if (pool_entry != 0)
				{
					isDeleted = true;
					creator.MoveToDeleteList(thisComponent);
					thisComponent.ChangeButtonText("+");
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
					objectCompiler = new NewObjectPoolDialog(mw, creator, pool_entry);
				}
				
				objectCompiler.ShowCompiler();
				
				if (objectCompiler.OkClicked())
				{
					GameObjectPool existingPool = mw.objectData.gobjectPoolsMap.get(pool_entry);
					existingPool.max_limit = (int) objectCompiler.spnrMaxSpawns.getValue();
					existingPool.comment = objectCompiler.frmtxtComment.getText();
					
					int[] guids = new int[existingPool.objects.size()];
					for (int i = 0; i < guids.length; ++i) guids[i] = existingPool.objects.get(i).guid;
					
					for (var guid : guids) // array is to avoid concurrent modification
						// Remove any needed
						if (!objectCompiler.objects.contains(mw.objectData.gobjectsMap.get(guid)))
							mw.objectData.MoveObjectToLocalPool(mw.objectData.gobjectPoolsMap.get(0), mw.objectData.gobjectsMap.get(guid));
					
					for (var obj : objectCompiler.objects)
					{
						// Add new ones needed
						if (!existingPool.objects.contains(obj))
							mw.objectData.MoveObjectToLocalPool(existingPool, obj);
						obj.chance = objectCompiler.GetChanceFor(obj.guid);
					}
					
					mw.DrawImage();
				}
			}
		});
	}
	
	public void SetCompilerDialog(NewObjectPoolDialog newDialog)
	{
		this.objectCompiler = newDialog;
	}
	
	public void NoteCurrentObjects()
	{
		originalGuids.clear();
		for (var obj : mw.objectData.gobjectPoolsMap.get(pool_entry).objects)
			originalGuids.add(obj.guid);
	}
	
	public void ChangeButtonText(String text)
	{
		lblStateChange.setText(text);
	}
	
	public boolean IsCompilerNull()
	{
		return objectCompiler == null;
	}
	
	public HashMap<GameObject, GameObjectPool> GetMoveUpdatesMade()
	{
		return objectCompiler.moveUpdates;
	}
}
