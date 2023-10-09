package view.LocalPoolBuilder;

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

public class NewObjectPoolDialog extends JDialog
{
	private final JPanel contentPanel = new JPanel();
	
	private MainWindow mw;
	private LocalPoolBuilder creator;
	
	public ArrayList<GameObject> objects;
	public HashMap<GameObject, GameObjectPool> moveUpdates;
	public int pool_entry;
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
	public NewObjectPoolDialog(MainWindow mw, LocalPoolBuilder creator)
	{
		this.mw = mw;
		this.creator = creator;
		this.pool_entry = -1;
		objects = new ArrayList<GameObject>();
		moveUpdates = new HashMap<GameObject, GameObjectPool>();
		
		CreateComponents();
		
		count = 0;
		lblCount.setText("Count: 0");
	}
	
	public NewObjectPoolDialog(MainWindow mw, LocalPoolBuilder creator, int pool_entry)
	{
		this.mw = mw;
		this.creator = creator;
		this.pool_entry = pool_entry;
		objects = new ArrayList<GameObject>();
		moveUpdates = new HashMap<GameObject, GameObjectPool>();
		
		CreateComponents();
		
		for (var obj : mw.objectData.gobjectPoolsMap.get(pool_entry).objects)
		{
			includedViewport.add(new GameObjectListCompilerComponent(mw, this, obj.guid, obj.chance, obj.name, mw.objectData.gobjectTypeColors.get(obj.id), true));
			objects.add(obj);
		}
		
		count = mw.objectData.gobjectPoolsMap.get(pool_entry).objects.size();
		lblCount.setText("Count: " + count);
		excludedViewport.revalidate();
	}
	
	private void CreateComponents()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewObjectPoolDialog.class.getResource("/resources/wow-icon2.jpg")));
		setTitle("Compile a List of Game Objects");
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
		includedPanel.setBorder(new TitledBorder(null, "Included GameObjects", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		includedPanel.setLayout(new BoxLayout(includedPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		includedPanel.add(scrollPane);
		
		includedViewport = new JPanel();
		scrollPane.setViewportView(includedViewport);
		includedViewport.setLayout(new BoxLayout(includedViewport, BoxLayout.Y_AXIS));
		
		JPanel excludedPanel = new JPanel();
		excludedPanel.setBorder(new TitledBorder(null, "Excluded GameObjects", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
				boolean hasZero = false;
				
				// Ensure not empty
				if (includedViewport.getComponentCount() == 0)
				{
					JOptionPane.showConfirmDialog(null, "Pool must have at least one gameobject.", "Pool is Empty", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				// Ensure chance values are valid
				for (var comp : includedViewport.getComponents())
				{
					GameObjectListCompilerComponent listComp = (GameObjectListCompilerComponent) comp;
					int chance = (int) listComp.spnChance.getValue();
					
					if (chance == 0)
						hasZero = true;
					else
						chanceTotal += chance;
				}
				
				if ((!hasZero && chanceTotal != 100)
						|| (hasZero && chanceTotal >= 100))
				{
					JOptionPane.showConfirmDialog(null, "At least one object must have chance = 0 or total chance must equal 100.", "Chance Value Invalid", JOptionPane.WARNING_MESSAGE);
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
	
	public void MoveToIncludeList(GameObjectListCompilerComponent comp)
	{
		excludedViewport.remove(comp);
		excludedViewport.revalidate();
		includedViewport.add(comp);
		includedViewport.revalidate();
		objects.add(mw.objectData.gobjectsMap.get(comp.guid));
		moveUpdates.put(mw.objectData.gobjectsMap.get(comp.guid), mw.objectData.gobjectPoolsMap.get(pool_entry));
		lblCount.setText("Count: " + ++count);
	}
	
	public void MoveToExcludeList(GameObjectListCompilerComponent comp)
	{
		includedViewport.remove(comp);
		includedViewport.revalidate();
		excludedViewport.add(comp);
		excludedViewport.revalidate();
		objects.remove(mw.objectData.gobjectsMap.get(comp.guid));
		moveUpdates.put(mw.objectData.gobjectsMap.get(comp.guid), mw.objectData.gobjectPoolsMap.get(0));
		lblCount.setText("Count: " + --count);
	}
	
	public int GetChanceFor(int guid)
	{
		for (var comp : includedViewport.getComponents())
		{
			GameObjectListCompilerComponent listComp = (GameObjectListCompilerComponent) comp;
			if (listComp.guid == guid)
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
			for (var obj : mw.objectData.gobjectsList)
				if (obj.pool_entry == 0 && mw.ObjectIsDrawn(obj.guid))
					excludedViewport.add(new GameObjectListCompilerComponent(mw, this, obj.guid, obj.chance, obj.name, mw.objectData.gobjectTypeColors.get(obj.id), false));
			
			for (var comp : includedViewport.getComponents())
			{
				GameObjectListCompilerComponent listComp = (GameObjectListCompilerComponent) comp;
				listComp.spnChance.setValue(mw.objectData.gobjectsMap.get(listComp.guid).chance);
			}
			
			if (pool_entry != -1)
			{
				spnrMaxSpawns.setValue(mw.objectData.gobjectPoolsMap.get(pool_entry).max_limit);
				frmtxtComment.setText(mw.objectData.gobjectPoolsMap.get(pool_entry).comment);
			}
			
			excludedViewport.revalidate();
			setVisible(true);
		}
		else
		{
			
		}
	}
}
