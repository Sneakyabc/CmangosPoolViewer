package view.MotherPoolBuilder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import common.GameObject;
import common.GameObjectPool;
import common.MySQLConnection;
import common.PoolPool;
import view.MainWindow;
import view.LocalPoolBuilder.LocalPoolBuilderListComponent;
import view.LocalPoolBuilder.NewObjectPoolDialog;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

public class MotherPoolBuilder extends JFrame
{
	private JPanel contentPane;
	private MainWindow creator;
	private MotherPoolBuilder self;
	
	private ArrayList<PoolPool> newPools;
	
	private JPanel existingPoolsViewport;
	private JPanel newPoolsViewport;
	private JPanel deletedPoolsViewport;
	private JButton btnNewPool;
	private JButton btnCopyUpdates;
	
	public MotherPoolBuilder(MainWindow creator)
	{
		this.creator = creator;
		this.self = this;
		this.setVisible(false);
		this.setTitle("Mother Pool Builder");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/resources/wow-icon2.jpg")));
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));	
		newPools = new ArrayList<PoolPool>();
		
		setContentPane(contentPane);
		
		JPanel listsPanel = new JPanel();
		
		JPanel controlsPanel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(listsPanel, GroupLayout.PREFERRED_SIZE, 547, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(controlsPanel, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(listsPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
				.addComponent(controlsPanel, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
		);
		
		btnNewPool = new JButton("New Pool");
		
		btnCopyUpdates = new JButton("Copy Updates");
		btnCopyUpdates.setToolTipText("Copy Queries to Clipboard");
		GroupLayout gl_controlsPanel = new GroupLayout(controlsPanel);
		gl_controlsPanel.setHorizontalGroup(
			gl_controlsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_controlsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_controlsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewPool)
						.addComponent(btnCopyUpdates))
					.addContainerGap(128, Short.MAX_VALUE))
		);
		gl_controlsPanel.setVerticalGroup(
			gl_controlsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_controlsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewPool)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCopyUpdates)
					.addContainerGap(495, Short.MAX_VALUE))
		);
		controlsPanel.setLayout(gl_controlsPanel);
		
		JPanel existingPoolsPanel = new JPanel();
		existingPoolsPanel.setBorder(new TitledBorder(null, "Existing Pools", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel newPoolsPanel = new JPanel();
		newPoolsPanel.setBorder(new TitledBorder(null, "New Pools", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel deletedPoolsPanel = new JPanel();
		deletedPoolsPanel.setBorder(new TitledBorder(null, "Deleted Pools", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_listsPanel = new GroupLayout(listsPanel);
		gl_listsPanel.setHorizontalGroup(
			gl_listsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_listsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(existingPoolsPanel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(newPoolsPanel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(deletedPoolsPanel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(64, Short.MAX_VALUE))
		);
		gl_listsPanel.setVerticalGroup(
			gl_listsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_listsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_listsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(newPoolsPanel, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
						.addComponent(existingPoolsPanel, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
						.addComponent(deletedPoolsPanel, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
					.addContainerGap())
		);
		deletedPoolsPanel.setLayout(new BoxLayout(deletedPoolsPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		deletedPoolsPanel.add(scrollPane_2);
		
		deletedPoolsViewport = new JPanel();
		scrollPane_2.setViewportView(deletedPoolsViewport);
		deletedPoolsViewport.setLayout(new BoxLayout(deletedPoolsViewport, BoxLayout.Y_AXIS));
		newPoolsPanel.setLayout(new BoxLayout(newPoolsPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		newPoolsPanel.add(scrollPane_1);
		
		newPoolsViewport = new JPanel();
		scrollPane_1.setViewportView(newPoolsViewport);
		newPoolsViewport.setLayout(new BoxLayout(newPoolsViewport, BoxLayout.Y_AXIS));
		existingPoolsPanel.setLayout(new BoxLayout(existingPoolsPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		existingPoolsPanel.add(scrollPane);
		
		existingPoolsViewport = new JPanel();
		scrollPane.setViewportView(existingPoolsViewport);
		existingPoolsViewport.setLayout(new BoxLayout(existingPoolsViewport, BoxLayout.Y_AXIS));
		listsPanel.setLayout(gl_listsPanel);
		contentPane.setLayout(gl_contentPane);
	
		CreateActionHandlers();
	}
	
	private void CreateActionHandlers()
	{
		btnNewPool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				NewMotherPoolDialog objectCompiler = new NewMotherPoolDialog(creator, self);
				objectCompiler.ShowCompiler();
				
				if (objectCompiler.OkClicked())
				{
					int newEntry = MySQLConnection.CALL_GET_AVAILABLE_POOL_ENTRY();
					MySQLConnection.ReservePoolId(newEntry);
					
					PoolPool newPool = new PoolPool(newEntry, (int) objectCompiler.spnrMaxSpawns.getValue(), objectCompiler.frmtxtComment.getText());
					objectCompiler.mother_pool = newEntry;
					
					for (var lPool : objectCompiler.pools)
					{
						creator.objectData.MovePoolToMotherPool(newPool, lPool);
						lPool.chance = objectCompiler.GetChanceFor(lPool.pool_entry);
					}
					
					newPools.add(newPool);
					
					creator.objectData.NewMotherPool(newPool, objectCompiler.draw_color);
					
					newPoolsViewport.add(new MotherPoolBuilderListComponent(creator, self, newPool.mother_pool, newPool.GetNumGameObjects(), objectCompiler.draw_color, objectCompiler, true));
					newPoolsViewport.revalidate();
					creator.DrawImage();
				}
			}
		});
		
		btnCopyUpdates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Remove Deleted Entries
				String text = "";
				
				for (var comp : deletedPoolsViewport.getComponents())
				{
					MotherPoolBuilderListComponent listComp = (MotherPoolBuilderListComponent) comp;
					
					text += String.format("DELETE FROM `pool_template` WHERE entry = %d;\n", listComp.mother_pool);
					text += String.format("DELETE FROM `pool_pool` WHERE mother_pool = %d;\n", listComp.mother_pool);
				}

				// Build New Entries
				for (var mPool : newPools)
				{
					int mother_pool = mPool.mother_pool;
					
					text += "INSERT INTO `pool_template` (`entry`, `max_limit`, `description`) VALUES ";
					text += String.format("(%d, %d, \"%s\");\n", mother_pool, mPool.max_limit, mPool.comment);
					
					for (int i = 0; i < mPool.pools.size(); ++i)
					{
						int entry = mPool.pools.get(i).pool_entry;
						int chance = mPool.pools.get(i).chance;
						String description = "";
						
						text += "INSERT INTO `pool_pool` (`pool_id`, `mother_pool`, `chance`, `description`) VALUES ";
						text += String.format("(%d, %d, %d, \"%s\");\n", entry, mother_pool, chance, description);
					}
				}
				
				StringSelection stringSelection = new StringSelection(text);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
	}
	
	public void MoveToExistingList(MotherPoolBuilderListComponent comp)
	{
		for (Integer entry : comp.originalEntries)
			creator.objectData.MovePoolToMotherPool(creator.objectData.motherPoolsMap.get(0), creator.objectData.gobjectPoolsMap.get(entry));
		
		deletedPoolsViewport.remove(comp);
		deletedPoolsViewport.revalidate();
		existingPoolsViewport.add(comp);
		existingPoolsViewport.revalidate();
		creator.DrawImage();
	}
	
	public void MoveToDeleteList(MotherPoolBuilderListComponent comp)
	{
		comp.NoteCurrentObjects();
		for (Integer entry : comp.originalEntries)
			creator.objectData.MovePoolToMotherPool(creator.objectData.motherPoolsMap.get(0), creator.objectData.gobjectPoolsMap.get(entry));
		
		existingPoolsViewport.remove(comp);
		existingPoolsViewport.revalidate();
		deletedPoolsViewport.add(comp);
		deletedPoolsViewport.revalidate();
		creator.DrawImage();
	}
	
	public void RemoveNewMotherPool(MotherPoolBuilderListComponent comp)
	{
		PoolPool newPool = creator.objectData.motherPoolsMap.get(comp.mother_pool);
		// Return to 0-pool
		int[] entries = new int[newPool.pools.size()];
		for (int i = 0; i < entries.length; ++i) entries[i] = newPool.pools.get(i).pool_entry;
		
		for (var entry : entries) // array is to avoid concurrent modification
			creator.objectData.MovePoolToMotherPool(creator.objectData.motherPoolsMap.get(0), creator.objectData.gobjectPoolsMap.get(entry));
		
		MySQLConnection.UnreservePoolId(newPool.mother_pool);
		this.newPools.remove(newPool);
		creator.objectData.RemoveMotherPool(newPool);
		newPoolsViewport.remove(comp);
		newPoolsViewport.revalidate();
		creator.DrawImage();
	}
	
	public void ShowBuilder()
	{
		if (!this.isVisible())
		{
			existingPoolsViewport.removeAll();
			for (var mPool : creator.objectData.motherPoolsList)
				existingPoolsViewport.add(new MotherPoolBuilderListComponent(creator, this, mPool.mother_pool, mPool.GetNumGameObjects(), creator.objectData.motherPoolColors.get(mPool.mother_pool), null));
			existingPoolsViewport.revalidate();
			
			this.setVisible(true);
		}
		else
		{
			this.requestFocus();
		}
	}
}
