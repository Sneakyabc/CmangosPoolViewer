package view.MotherPoolBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.GameObject;
import common.GameObjectPool;
import view.MainWindow;
import view.LocalPoolBuilder.GameObjectListCompilerComponent;
import view.LocalPoolBuilder.LocalPoolBuilder;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JFormattedTextField;
import javax.swing.border.EtchedBorder;

public class NewMotherPoolDialog extends JDialog
{
	private final JPanel contentPanel = new JPanel();
	
	private MainWindow mw;
	private MotherPoolBuilder creator;
	
	public ArrayList<GameObjectPool> pools;
	public int mother_pool;
	public Color draw_color;
	
	private static JColorChooser colorChooser = new JColorChooser();
	
	private JPanel excludedViewport;
	private JPanel includedViewport;
	private JButton btnOK;
	private JButton btnCancel;
	
	private boolean ok = false;
	private JLabel lblColor;
	public JSpinner spnrMaxSpawns;
	private JLabel lblCount;
	
	private int count = 0;
	public JFormattedTextField frmtxtComment;
	
	/**
	 * @wbp.parser.constructor
	 */
	public NewMotherPoolDialog(MainWindow mw, MotherPoolBuilder creator)
	{
		this.mw = mw;
		this.creator = creator;
		this.mother_pool = -1;
		pools = new ArrayList<GameObjectPool>();
		
		CreateComponents();
		setTitle("Compile a New List of GameObject Pools");
		
		count = 0;
		lblCount.setText("Count: 0");
	}
	
	public NewMotherPoolDialog(MainWindow mw, MotherPoolBuilder creator, int mother_pool)
	{
		this.mw = mw;
		this.creator = creator;
		this.mother_pool = mother_pool;
		pools = new ArrayList<GameObjectPool>();
		
		CreateComponents();
		setTitle(String.format("Modify the List of GameObject Pools (%d)", mother_pool));
		
		for (var lPool : mw.objectData.motherPoolsMap.get(mother_pool).pools)
			includedViewport.add(new GameObjectPoolListCompilerComponent(mw, this, lPool.pool_entry, lPool.chance, lPool.max_limit, lPool.comment, mw.objectData.gobjectPoolColors.get(lPool.pool_entry), true));
		
		count = mw.objectData.motherPoolsMap.get(mother_pool).pools.size();
		lblCount.setText("Count: " + count);
		excludedViewport.revalidate();
	}
	
	private void CreateComponents()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewMotherPoolDialog.class.getResource("/resources/wow-icon2.jpg")));
		setBounds(100, 100, 600, 500);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(false);
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel colorPanel = new JPanel();
		colorPanel.setBorder(new TitledBorder(null, "Draw Color", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel = new JPanel();
		
		JPanel listsPanel = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(listsPanel, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(colorPanel, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addComponent(listsPanel, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addComponent(colorPanel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addGap(177))
		);
		
		JPanel includedPanel = new JPanel();
		includedPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Included GameObject Pools", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		includedPanel.setLayout(new BoxLayout(includedPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		includedPanel.add(scrollPane);
		
		includedViewport = new JPanel();
		scrollPane.setViewportView(includedViewport);
		includedViewport.setLayout(new BoxLayout(includedViewport, BoxLayout.Y_AXIS));
		
		JPanel excludedPanel = new JPanel();
		excludedPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Excluded GameObject Pools", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		excludedPanel.setLayout(new BoxLayout(excludedPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		excludedPanel.add(scrollPane_1);
		
		excludedViewport = new JPanel();
		scrollPane_1.setViewportView(excludedViewport);
		excludedViewport.setLayout(new BoxLayout(excludedViewport, BoxLayout.Y_AXIS));
		GroupLayout gl_listsPanel = new GroupLayout(listsPanel);
		gl_listsPanel.setHorizontalGroup(
			gl_listsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_listsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(includedPanel, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(excludedPanel, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_listsPanel.setVerticalGroup(
			gl_listsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_listsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_listsPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(excludedPanel, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
						.addComponent(includedPanel, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(82, Short.MAX_VALUE))
		);
		listsPanel.setLayout(gl_listsPanel);
		
		JLabel labelComment = new JLabel("DB Comment:");
		labelComment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		frmtxtComment = new JFormattedTextField();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelComment, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(frmtxtComment, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelComment, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
						.addComponent(frmtxtComment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		draw_color = Color.magenta;
		BufferedImage bimg = new BufferedImage(75, 65, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bimg.createGraphics();
		g2d.setColor(draw_color);
		g2d.fillRect(0, 0, 100, 100);
		g2d.dispose();
		lblColor = new JLabel(new ImageIcon(bimg));
		
		JLabel lblClickToChange = new JLabel("Click to change");
		lblClickToChange.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		JLabel lblMaxSpawns = new JLabel("Max Spawns:");
		
		spnrMaxSpawns = new JSpinner();
		spnrMaxSpawns.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		
		lblCount = new JLabel("Count: 0");
		
		GroupLayout gl_colorPanel = new GroupLayout(colorPanel);
		gl_colorPanel.setHorizontalGroup(
			gl_colorPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_colorPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblColor, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_colorPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(spnrMaxSpawns, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_colorPanel.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_colorPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_colorPanel.createSequentialGroup()
							.addComponent(lblCount, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_colorPanel.createSequentialGroup()
							.addComponent(lblMaxSpawns, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_colorPanel.createSequentialGroup()
							.addComponent(lblClickToChange, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
							.addGap(10))))
		);
		gl_colorPanel.setVerticalGroup(
			gl_colorPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_colorPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblClickToChange)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblColor, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblMaxSpawns)
					.addGap(18)
					.addComponent(spnrMaxSpawns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
					.addComponent(lblCount)
					.addContainerGap())
		);
		colorPanel.setLayout(gl_colorPanel);
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		btnOK = new JButton("OK");
		btnOK.setActionCommand("OK");
		buttonPane.add(btnOK);
		getRootPane().setDefaultButton(btnOK);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("Cancel");
		buttonPane.add(btnCancel);

		CreateActionHandlers();
	}
	
	private void CreateActionHandlers()
	{
		lblColor.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e){
				draw_color = JColorChooser.showDialog(lblColor, "Choose a color", draw_color);
				
				if (draw_color != null)
				{
					BufferedImage bimg = new BufferedImage(75, 65, BufferedImage.TYPE_INT_RGB);
					Graphics2D g2d = bimg.createGraphics();
					g2d.setColor(draw_color);
					g2d.fillRect(0, 0, 100, 100);
					lblColor.setIcon(new ImageIcon(bimg));
					g2d.dispose();
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
		
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int chanceTotal = 0;
				
				// Ensure not empty
				if (includedViewport.getComponentCount() == 0)
				{
					JOptionPane.showConfirmDialog(null, "Pool pool must have at least one sub-pool.", "Pool is Empty", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				// Ensure chance values are valid
				for (var comp : includedViewport.getComponents())
				{
					GameObjectPoolListCompilerComponent listComp = (GameObjectPoolListCompilerComponent) comp;
					int chance = (int) listComp.spnChance.getValue();
					
					if (chance == 0)
					{
						chanceTotal = 100;
						break;
					}
					else
						chanceTotal += chance;
				}
				
				if (chanceTotal != 100)
				{
					JOptionPane.showConfirmDialog(null, "At least one pool must have chance = 0 or total chance must equal 100.", "Chance Value Invalid", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				ok = true;
				excludedViewport.removeAll();
				setVisible(false);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ok = false;
				excludedViewport.removeAll();
				setVisible(false);
			}
		});
	}
	
	public boolean OkClicked() { return ok; }
	
	public void MoveToIncludeList(GameObjectPoolListCompilerComponent comp)
	{
		excludedViewport.remove(comp);
		excludedViewport.revalidate();
		includedViewport.add(comp);
		includedViewport.revalidate();
		pools.add(mw.objectData.gobjectPoolsMap.get(comp.pool_entry));
		lblCount.setText("Count: " + ++count);
	}
	
	public void MoveToExcludeList(GameObjectPoolListCompilerComponent comp)
	{
		includedViewport.remove(comp);
		includedViewport.revalidate();
		excludedViewport.add(comp);
		excludedViewport.revalidate();
		pools.remove(mw.objectData.gobjectPoolsMap.get(comp.pool_entry));
		lblCount.setText("Count: " + --count);
	}
	
	public int GetChanceFor(int pool_entry)
	{
		for (var comp : includedViewport.getComponents())
		{
			GameObjectPoolListCompilerComponent listComp = (GameObjectPoolListCompilerComponent) comp;
			if (listComp.pool_entry == pool_entry)
				return (int) listComp.spnChance.getValue();
		}
		
		return -1;
	}
	
	public void ShowCompiler()
	{
		if (!this.isVisible())
		{
			ok = false;
			
			excludedViewport.removeAll();
			for (var lPool : mw.objectData.gobjectPoolsList)
				if (lPool.mother_pool == 0 && lPool.pool_entry != 0 && mw.ObjectPoolIsDrawn(lPool.pool_entry))
					excludedViewport.add(new GameObjectPoolListCompilerComponent(mw, this, lPool.pool_entry, lPool.chance, lPool.max_limit, lPool.comment, mw.objectData.gobjectPoolColors.get(lPool.pool_entry), false));
			
			for (var comp : includedViewport.getComponents())
			{
				GameObjectPoolListCompilerComponent listComp = (GameObjectPoolListCompilerComponent) comp;
				listComp.spnChance.setValue(mw.objectData.gobjectPoolsMap.get(listComp.pool_entry).chance);
			}
			
			if (mother_pool != -1)
			{
				spnrMaxSpawns.setValue(mw.objectData.motherPoolsMap.get(mother_pool).max_limit);
				frmtxtComment.setText(mw.objectData.motherPoolsMap.get(mother_pool).comment);
			}
			
			excludedViewport.revalidate();
			setVisible(true);
		}
		else
		{
			
		}
	}
}
