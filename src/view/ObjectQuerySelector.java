package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class ObjectQuerySelector extends JDialog
{
	private ObjectQuerySelector self;
	
	private final JPanel contentPanel = new JPanel();
	private MineralNodeListTab minerals;
	private HerbalismListTab herbs;
	private MiscellaneousListTab misc;
	
	private JButton btnOK;
	private JButton btnCancel;
	
	public boolean ok = false;
	private JFormattedTextField frmtxtCustomEnties;
	
	public ObjectQuerySelector()
	{
		this.self = this;
		setBounds(100, 100, 600, 400);
		setTitle("Select Game Objects to Query");
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		frmtxtCustomEnties = new JFormattedTextField();
		
		JLabel lblEntries = new JLabel("Entries:");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(359)
							.addComponent(lblEntries, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(frmtxtCustomEnties, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(frmtxtCustomEnties, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEntries))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		
		JScrollPane scrollPane1 = new JScrollPane();
		minerals = new MineralNodeListTab();
		scrollPane1.setViewportView(minerals);

		JScrollPane scrollPane2 = new JScrollPane();
		herbs = new HerbalismListTab();
		scrollPane2.setViewportView(herbs);

		JScrollPane scrollPane3 = new JScrollPane();
		misc = new MiscellaneousListTab();
		scrollPane3.setViewportView(misc);
		
		tabbedPane.addTab("Mineral Nodes", null, scrollPane1, null);
		tabbedPane.addTab("Herbalism Nodes", null, scrollPane2, null);
		tabbedPane.addTab("Miscellaneous", null, scrollPane3, null);
		
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
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				self.ok = true;
				self.setVisible(false);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				self.ok = false;
				self.setVisible(false);
			}
		});
	}
	
	public String GetEntries()
	{
		if (frmtxtCustomEnties.getText().length() > 0)
		{
			return frmtxtCustomEnties.getText();
		}
		else
			return minerals.GetEntries();
	}
	
	public void ShowDialog()
	{
		if (!this.isVisible())
		{
			self.ok = false;
			this.setVisible(true);
		}
		else
		{
			
		}
	}
}