package view.LocalPoolBuilder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import common.GameObject;
import common.GameObjectPool;
import common.MySQLConnection;
import view.MainWindow;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

public class LocalPoolBuilder extends JFrame
{
	private JPanel contentPane;
	private MainWindow creator;
	private LocalPoolBuilder self;
	
	private ArrayList<GameObjectPool> newPools;
	public HashMap<Integer, Integer> moveUpdates;
	
	private JPanel existingPoolsViewport;
	private JPanel newPoolsViewport;
	private JPanel deletedPoolsViewport;
	private JButton btnNewPool;
	private JButton btnCopyUpdates;
	
	public LocalPoolBuilder(MainWindow creator)
	{
		this.creator = creator;
		this.self = this;
		this.setVisible(false);
		this.setTitle("Local Pool Builder");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/resources/wow-icon2.jpg")));
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));	
		newPools = new ArrayList<GameObjectPool>();
		moveUpdates = new HashMap<Integer, Integer>();
		
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
				NewObjectPoolDialog objectCompiler = new NewObjectPoolDialog(creator, self);
				objectCompiler.ShowCompiler();
				
				if (objectCompiler.OkClicked())
				{
					int newEntry = MySQLConnection.CALL_GET_AVAILABLE_POOL_ENTRY();
					MySQLConnection.ReservePoolId(newEntry);
					
					GameObjectPool newPool = new GameObjectPool(0, newEntry, 0, (int) objectCompiler.spnrMaxSpawns.getValue(), objectCompiler.frmtxtComment.getText());
					objectCompiler.pool_entry = newEntry;
					
					for (var obj : objectCompiler.objects)
					{
						creator.objectData.MoveObjectToLocalPool(newPool, obj);
						obj.chance = objectCompiler.GetChanceFor(obj.guid);
					}
					
					newPools.add(newPool);
					
					creator.objectData.NewLocalPool(newPool, objectCompiler.draw_color);
					
					newPoolsViewport.add(new LocalPoolBuilderListComponent(creator, self, newPool.pool_entry, newPool.GetNumGameObjects(), objectCompiler.draw_color, objectCompiler, true));
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
				
				// Delete Removed Pools
				for (var comp : deletedPoolsViewport.getComponents())
				{
					LocalPoolBuilderListComponent listComp = (LocalPoolBuilderListComponent) comp;
					
					text += String.format("DELETE FROM `pool_template` WHERE entry = %d;\n", listComp.pool_entry);
					text += String.format("DELETE FROM `pool_gameobject` WHERE pool_entry = %d;\n", listComp.pool_entry);
				}
				
				// Add Updates (move from pool to another)
				for (var comp : existingPoolsViewport.getComponents())
				{
					LocalPoolBuilderListComponent listComp = (LocalPoolBuilderListComponent) comp;
					
					// Handle objects moved
					if (!listComp.IsCompilerNull())
						for (HashMap.Entry<GameObject, GameObjectPool> entry : listComp.GetMoveUpdatesMade().entrySet())
						{
							int guid = entry.getKey().guid;
							text += String.format("DELETE FROM `pool_gameobject` WHERE guid = %d;\n", guid);
							
							int pool_entry = entry.getValue().pool_entry;
							int chance = entry.getKey().chance;
							String description = "";
							
							text += "INSERT INTO `pool_gameobject` (`guid`, `pool_entry`, `chance`, `description`) VALUES ";
							text += String.format("(%d, %d, %d, \"%s\");\n", guid, pool_entry, chance, description);
//							text += String.format("UPDATE `pool_template` SET pool_entry = %d WHERE guid = %d;\n", entry.getValue(), entry.getKey());
						}
				}
				
				// Build New Entries
				for (var lPool : newPools)
				{
					int pool_entry = lPool.pool_entry;
					
					text += "INSERT INTO `pool_template` (`entry`, `max_limit`, `description`) VALUES ";
					text += String.format("(%d, %d, \"%s\");\n", pool_entry, lPool.max_limit, lPool.comment);
					
					for (int i = 0; i < lPool.objects.size(); ++i)
					{
						int guid = lPool.objects.get(i).guid;
						int chance = lPool.objects.get(i).chance;
						String description = "";
						
						text += "INSERT INTO `pool_gameobject` (`guid`, `pool_entry`, `chance`, `description`) VALUES ";
						text += String.format("(%d, %d, %d, \"%s\");\n", guid, pool_entry, chance, description);
					}
				}
				
				StringSelection stringSelection = new StringSelection(text);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
	}
	
	public void MoveToExistingList(LocalPoolBuilderListComponent comp)
	{
		for (Integer guid : comp.originalGuids)
			creator.objectData.MoveObjectToLocalPool(creator.objectData.gobjectPoolsMap.get(comp.pool_entry), creator.objectData.gobjectsMap.get(guid));
		
		deletedPoolsViewport.remove(comp);
		deletedPoolsViewport.revalidate();
		existingPoolsViewport.add(comp);
		existingPoolsViewport.revalidate();
		creator.DrawImage();
	}
	
	public void MoveToDeleteList(LocalPoolBuilderListComponent comp)
	{
		comp.NoteCurrentObjects();
		for (Integer guid : comp.originalGuids)
			creator.objectData.MoveObjectToLocalPool(creator.objectData.gobjectPoolsMap.get(0), creator.objectData.gobjectsMap.get(guid));
		
		existingPoolsViewport.remove(comp);
		existingPoolsViewport.revalidate();
		deletedPoolsViewport.add(comp);
		deletedPoolsViewport.revalidate();
		creator.DrawImage();
	}
	
	public void RemoveNewLocalPool(LocalPoolBuilderListComponent comp)
	{
		GameObjectPool newPool = creator.objectData.gobjectPoolsMap.get(comp.pool_entry);
		// Return to 0-pool
		int[] guids = new int[newPool.objects.size()];
		for (int i = 0; i < guids.length; ++i) guids[i] = newPool.objects.get(i).guid;
		
		for (var guid : guids) // array is to avoid concurrent modification
			creator.objectData.MoveObjectToLocalPool(creator.objectData.gobjectPoolsMap.get(0), creator.objectData.gobjectsMap.get(guid));
		
		MySQLConnection.UnreservePoolId(newPool.pool_entry);
		this.newPools.remove(newPool);
		creator.objectData.RemoveLocalPool(newPool);
		newPoolsViewport.remove(comp);
		newPoolsViewport.revalidate();
		creator.DrawImage();
	}
	
	public void GameObjectMovedFromPool(LocalPoolBuilderListComponent comp)
	{
		
	}
	
	public void ShowBuilder()
	{
		if (!this.isVisible())
		{
			existingPoolsViewport.removeAll();
			for (var goPool : creator.objectData.gobjectPoolsList)
				existingPoolsViewport.add(new LocalPoolBuilderListComponent(creator, this, goPool.pool_entry, goPool.GetNumGameObjects(), creator.objectData.gobjectPoolColors.get(goPool.pool_entry), null));
			existingPoolsViewport.revalidate();
			
			this.setVisible(true);
		}
		else
		{
			this.requestFocus();
		}
	}
}
