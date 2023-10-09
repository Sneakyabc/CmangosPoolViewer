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

public class HerbalismListTab extends JPanel
{
	private JCheckBox chkbxMiningNodes;
	private JCheckBox chkbxStandardMiningNodes;
	private JCheckBox chkbxCopper1731;
	private JCheckBox chkbxTin1732;
	private JCheckBox chkbxSilver1733;
	private JCheckBox chkbxGold1734;
	private JCheckBox chkbxIron1735;
	private JCheckBox chkbxMithril2040;
	private JCheckBox chkbxTruesilver2047;
	private JCheckBox chkbxSmallThorium324;
	private JCheckBox chkbxRichThorium175404;
	private JCheckBox chkbxOozeMiningNodes;
	private JCheckBox chkbxOozeSilver73940;
	private JCheckBox chkbxOozeGold73941;
	private JCheckBox chkbxOozeMithril123310;
	private JCheckBox chkbxOozeTruesilver123309;
	private JCheckBox chkbxOozeThorium123848;
	private JCheckBox chkbxOozeRichThorium177388;
	private JCheckBox chkbxAlternativeMiningNodes;
	private JCheckBox chkbxCopper2055;
	private JCheckBox chkbxCopper3763;
	private JCheckBox chkbxCopper103713;
	private JCheckBox chkbxTin2054;
	private JCheckBox chkbxTin3764;
	private JCheckBox chkbxTin103711;
	private JCheckBox chkbxSilver105569;
	private JCheckBox chkbxGold150080;
	private JCheckBox chkbxGold181109;
	private JCheckBox chkbxMithril150079;
	private JCheckBox chkbxMithril176645;
	private JCheckBox chkbxTruesilver150081;
	private JCheckBox chkbxTruesilver181108;
	private JCheckBox chkbxSmallThorium150082;
	private JCheckBox chkbxSmallThorium176643;
	
	private ArrayList<JCheckBox> boxes;
	
	public HerbalismListTab()
	{
		this.setSize(400, 1000);
		boxes = new ArrayList<JCheckBox>();
		
		chkbxMiningNodes = new JCheckBox("Mining Nodes");
		chkbxMiningNodes.setSelected(true);
		chkbxStandardMiningNodes = new JCheckBox("Standard Nodes");
		chkbxStandardMiningNodes.setSelected(true);
		chkbxCopper1731 = new JCheckBox("Copper (1731)");
		chkbxCopper1731.setSelected(true);
		chkbxTin1732 = new JCheckBox("Tin (1732)");
		chkbxTin1732.setSelected(true);
		chkbxSilver1733 = new JCheckBox("Silver (1733)");
		chkbxSilver1733.setSelected(true);
		chkbxMithril2040 = new JCheckBox("Mithril (2040)");
		chkbxMithril2040.setSelected(true);
		chkbxGold1734 = new JCheckBox("Gold (1734)");
		chkbxGold1734.setSelected(true);
		chkbxIron1735 = new JCheckBox("Iron (1735)");
		chkbxIron1735.setSelected(true);
		chkbxTruesilver2047 = new JCheckBox("Truesilver (2047)");
		chkbxTruesilver2047.setSelected(true);
		chkbxSmallThorium324 = new JCheckBox("Small Thorium (324)");
		chkbxSmallThorium324.setSelected(true);
		chkbxRichThorium175404 = new JCheckBox("Rich Thorium (175404)");
		chkbxRichThorium175404.setSelected(true);
		
		chkbxAlternativeMiningNodes = new JCheckBox("Alternative Nodes");
		chkbxAlternativeMiningNodes.setSelected(true);
		chkbxCopper2055 = new JCheckBox("Copper (2055) Redridge");
		chkbxCopper2055.setSelected(true);
		chkbxCopper3763 = new JCheckBox("Copper (3763) The Barrens");
		chkbxCopper3763.setSelected(true);
		chkbxCopper103713 = new JCheckBox("Copper (103713) The Barrens");
		chkbxCopper103713.setSelected(true);
		chkbxTin2054 = new JCheckBox("Tin (2054) Redridge");
		chkbxTin2054.setSelected(true);
		chkbxTin3764 = new JCheckBox("Tin (3764) The Barrens");
		chkbxTin3764.setSelected(true);
		chkbxTin103711 = new JCheckBox("Tin (103711) Hillsbrad");
		chkbxTin103711.setSelected(true);
		chkbxSilver105569 = new JCheckBox("Silver (105569) Hillsbrad");
		chkbxSilver105569.setSelected(true);
		chkbxSmallThorium150082 = new JCheckBox("Small Thorium (150082) Blasted Lands");
		chkbxSmallThorium150082.setSelected(true);
		chkbxTruesilver181108 = new JCheckBox("Truesilver (181108) Felwood");
		chkbxTruesilver181108.setSelected(true);
		chkbxTruesilver150081 = new JCheckBox("Truesilver (150081) Blasted Lands");
		chkbxTruesilver150081.setSelected(true);
		chkbxMithril176645 = new JCheckBox("Mithril (176645) Felwood");
		chkbxMithril176645.setSelected(true);
		chkbxMithril150079 = new JCheckBox("Mithril (150079) Blasted Lands");
		chkbxMithril150079.setSelected(true);
		chkbxGold181109 = new JCheckBox("Gold (181109) Felwood");
		chkbxGold181109.setSelected(true);
		chkbxGold150080 = new JCheckBox("Gold (150080) Blasted Lands");
		chkbxGold150080.setSelected(true);
		chkbxSmallThorium176643 = new JCheckBox("Small Thorium (176643) Felwood");
		chkbxSmallThorium176643.setSelected(true);
		
		chkbxOozeMiningNodes = new JCheckBox("Ooze Nodes");
		chkbxOozeMiningNodes.setSelected(true);
		chkbxOozeSilver73940 = new JCheckBox("Ooze-Covered-Silver (73940)");
		chkbxOozeSilver73940.setSelected(true);
		chkbxOozeGold73941 = new JCheckBox("Ooze-Covered-Gold (73941)");
		chkbxOozeGold73941.setSelected(true);
		chkbxOozeMithril123310 = new JCheckBox("Ooze-Covered-Mithril (123310)");
		chkbxOozeMithril123310.setSelected(true);
		chkbxOozeTruesilver123309 = new JCheckBox("Ooze-Covered-Truesilver (123309)");
		chkbxOozeTruesilver123309.setSelected(true);
		chkbxOozeThorium123848 = new JCheckBox("Ooze-Covered-Thorium (123848)");
		chkbxOozeThorium123848.setSelected(true);
		chkbxOozeRichThorium177388 = new JCheckBox("Ooze-Covered-Rich-Thorium (177388)");
		chkbxOozeRichThorium177388.setSelected(true);
		
		boxes.add(chkbxCopper1731);
		boxes.add(chkbxTin1732);
		boxes.add(chkbxSilver1733);
		boxes.add(chkbxMithril2040);
		boxes.add(chkbxGold1734);
		boxes.add(chkbxIron1735);
		boxes.add(chkbxTruesilver2047);
		boxes.add(chkbxSmallThorium324);
		boxes.add(chkbxRichThorium175404);
		boxes.add(chkbxCopper2055);
		boxes.add(chkbxCopper3763);
		boxes.add(chkbxCopper103713);
		boxes.add(chkbxTin2054);
		boxes.add(chkbxTin3764);
		boxes.add(chkbxTin103711);
		boxes.add(chkbxSilver105569);
		boxes.add(chkbxSmallThorium150082);
		boxes.add(chkbxTruesilver181108);
		boxes.add(chkbxTruesilver150081);
		boxes.add(chkbxMithril176645);
		boxes.add(chkbxMithril150079);
		boxes.add(chkbxGold181109);
		boxes.add(chkbxGold150080);
		boxes.add(chkbxSmallThorium176643);
		boxes.add(chkbxOozeSilver73940);
		boxes.add(chkbxOozeGold73941);
		boxes.add(chkbxOozeMithril123310);
		boxes.add(chkbxOozeTruesilver123309);
		boxes.add(chkbxOozeThorium123848);
		boxes.add(chkbxOozeRichThorium177388);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(42)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(chkbxRichThorium175404, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
										.addComponent(chkbxSmallThorium324, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(chkbxTruesilver2047, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(chkbxMithril2040, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(chkbxIron1735, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(chkbxGold1734, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(chkbxSilver1733, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(chkbxTin1732, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(chkbxCopper1731, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addComponent(chkbxMiningNodes, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(21)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(chkbxStandardMiningNodes, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
										.addComponent(chkbxAlternativeMiningNodes, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
										.addComponent(chkbxOozeMiningNodes, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(42)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(chkbxCopper103713, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
										.addComponent(chkbxCopper3763, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(chkbxCopper2055, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(42)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(chkbxTin103711, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
										.addComponent(chkbxSilver105569, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)))))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(48)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(chkbxOozeThorium123848, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
								.addComponent(chkbxOozeRichThorium177388, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
								.addComponent(chkbxOozeSilver73940, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(48, Short.MAX_VALUE)
							.addComponent(chkbxOozeGold73941, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(48, Short.MAX_VALUE)
							.addComponent(chkbxOozeMithril123310, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(48, Short.MAX_VALUE)
							.addComponent(chkbxOozeTruesilver123309, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(48, Short.MAX_VALUE)
							.addComponent(chkbxTin2054, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(48, Short.MAX_VALUE)
							.addComponent(chkbxTin3764, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(48, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(chkbxGold150080, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
									.addComponent(chkbxGold181109, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
									.addComponent(chkbxMithril150079, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(chkbxMithril176645, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
										.addComponent(chkbxTruesilver150081, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
										.addComponent(chkbxTruesilver181108, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
										.addComponent(chkbxSmallThorium150082, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
									.addComponent(chkbxSmallThorium176643, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(chkbxMiningNodes)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxStandardMiningNodes)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxCopper1731)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxTin1732)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxSilver1733)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxGold1734)
					.addGap(2)
					.addComponent(chkbxIron1735)
					.addGap(2)
					.addComponent(chkbxMithril2040)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxTruesilver2047)
					.addGap(2)
					.addComponent(chkbxSmallThorium324)
					.addGap(2)
					.addComponent(chkbxRichThorium175404)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxOozeMiningNodes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chkbxOozeSilver73940)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxOozeGold73941)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxOozeMithril123310)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxOozeTruesilver123309)
					.addGap(2)
					.addComponent(chkbxOozeThorium123848)
					.addGap(2)
					.addComponent(chkbxOozeRichThorium177388)
					.addGap(4)
					.addComponent(chkbxAlternativeMiningNodes)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxCopper2055)
					.addGap(2)
					.addComponent(chkbxCopper3763)
					.addGap(2)
					.addComponent(chkbxCopper103713)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxTin2054)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxTin3764)
					.addGap(2)
					.addComponent(chkbxTin103711)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxSilver105569)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxGold150080)
					.addGap(2)
					.addComponent(chkbxGold181109)
					.addGap(2)
					.addComponent(chkbxMithril150079)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxMithril176645)
					.addGap(2)
					.addComponent(chkbxTruesilver150081)
					.addGap(2)
					.addComponent(chkbxTruesilver181108)
					.addGap(2)
					.addComponent(chkbxSmallThorium150082)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkbxSmallThorium176643)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	
		CreateActionHandlers();
	}
	
	private void CreateActionHandlers()
	{
		ActionListener allMineralNodesChkbxAH = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				chkbxStandardMiningNodes.setSelected(chkbxMiningNodes.isSelected());
				chkbxOozeMiningNodes.setSelected(chkbxMiningNodes.isSelected());
				chkbxAlternativeMiningNodes.setSelected(chkbxMiningNodes.isSelected());
				
				chkbxCopper1731.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxTin1732.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxSilver1733.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxMithril2040.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxGold1734.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxIron1735.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxTruesilver2047.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxSmallThorium324.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxRichThorium175404.setSelected(chkbxStandardMiningNodes.isSelected());
				
				chkbxOozeSilver73940.setSelected(chkbxOozeMiningNodes.isSelected());
				chkbxOozeGold73941.setSelected(chkbxOozeMiningNodes.isSelected());
				chkbxOozeMithril123310.setSelected(chkbxOozeMiningNodes.isSelected());
				chkbxOozeTruesilver123309.setSelected(chkbxOozeMiningNodes.isSelected());
				chkbxOozeThorium123848.setSelected(chkbxOozeMiningNodes.isSelected());
				chkbxOozeRichThorium177388.setSelected(chkbxOozeMiningNodes.isSelected());
				
				chkbxCopper2055.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxCopper3763.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxCopper103713.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxTin2054.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxTin3764.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxTin103711.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxSilver105569.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxSmallThorium150082.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxTruesilver181108.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxTruesilver150081.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxMithril176645.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxMithril150079.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxGold181109.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxGold150080.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxSmallThorium176643.setSelected(chkbxAlternativeMiningNodes.isSelected());
			}
		};
		chkbxMiningNodes.addActionListener(allMineralNodesChkbxAH);
		
		ActionListener standardNodesChkbxAH = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				chkbxCopper1731.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxTin1732.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxSilver1733.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxMithril2040.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxGold1734.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxIron1735.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxTruesilver2047.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxSmallThorium324.setSelected(chkbxStandardMiningNodes.isSelected());
				chkbxRichThorium175404.setSelected(chkbxStandardMiningNodes.isSelected());
			}
		};
		chkbxStandardMiningNodes.addActionListener(standardNodesChkbxAH);

		ActionListener oozeNodesChkbxAH = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				chkbxOozeSilver73940.setSelected(chkbxOozeMiningNodes.isSelected());
				chkbxOozeGold73941.setSelected(chkbxOozeMiningNodes.isSelected());
				chkbxOozeMithril123310.setSelected(chkbxOozeMiningNodes.isSelected());
				chkbxOozeTruesilver123309.setSelected(chkbxOozeMiningNodes.isSelected());
				chkbxOozeThorium123848.setSelected(chkbxOozeMiningNodes.isSelected());
				chkbxOozeRichThorium177388.setSelected(chkbxOozeMiningNodes.isSelected());
			}
		};
		chkbxOozeMiningNodes.addActionListener(oozeNodesChkbxAH);
		
		ActionListener alternativeNodesChkbxAH = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				chkbxCopper2055.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxCopper3763.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxCopper103713.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxTin2054.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxTin3764.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxTin103711.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxSilver105569.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxSmallThorium150082.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxTruesilver181108.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxTruesilver150081.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxMithril176645.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxMithril150079.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxGold181109.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxGold150080.setSelected(chkbxAlternativeMiningNodes.isSelected());
				chkbxSmallThorium176643.setSelected(chkbxAlternativeMiningNodes.isSelected());
			}
		};
		chkbxAlternativeMiningNodes.addActionListener(alternativeNodesChkbxAH);
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
