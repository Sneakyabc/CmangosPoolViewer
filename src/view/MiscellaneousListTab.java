package view;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.eclipse.jface.commands.ActionHandler;

public class MiscellaneousListTab extends JPanel
{
	private JCheckBox chkbxSpawnEntries;
	
	private ArrayList<JCheckBox> boxes;
	
	public MiscellaneousListTab()
	{
		this.setSize(400, 1000);
		boxes = new ArrayList<JCheckBox>();
		
		chkbxSpawnEntries = new JCheckBox("Spawn Entries");
		chkbxSpawnEntries.setSelected(false);
		
		// Spawn Entries
		// Chests
		// Food Crates
		// Water Crates
		// Clams and stuff
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(chkbxSpawnEntries, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
					.addGap(48))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(chkbxSpawnEntries)
					.addContainerGap(818, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	
		CreateActionHandlers();
	}
	
	private void CreateActionHandlers()
	{
/*		ActionListener allMineralNodesChkbxAH = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		};
		
		ActionListener standardNodesChkbxAH = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		};*/
	}
	
	public String GetEntries()
	{
		String entries = "";
		for (int i = 0; i < boxes.size(); ++i)
			if (boxes.get(i).isSelected())
				entries += ", " + boxes.get(i).getText().substring(boxes.get(i).getText().indexOf("(") + 1, boxes.get(i).getText().indexOf(')'));
		
		return entries.substring(2);
	}
}
