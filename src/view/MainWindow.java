package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.GameObject;
import common.GameObjectPool;
import common.GameObjectType;
import common.GameZone;
import common.MySQLConnection;
import common.PoolPool;
import common.QueriedGobjectData;
import common.WorldMap;
import view.LocalPoolBuilder.LocalPoolBuilder;
import view.MotherPoolBuilder.MotherPoolBuilder;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JSlider;

public class MainWindow extends JFrame
{
	public static MainWindow mw;
	
	private JPanel contentPane;
	public JLabel wIcon;
	
	public QueriedGobjectData objectData = null;
	private LocalPoolBuilder lpoolBuilder;
	private MotherPoolBuilder mpoolBuilder;
	
	int lastX = 0;
	int lastY = 0;
	private int rdioColorOption = 1;
	private double zoom = 1.0;
	
	private JPanel motherPoolsViewport;
	private JPanel localPoolsViewport;
	private JPanel gameObjectsViewport;
	
	private WorldMap map;
	private GameZone zone;
	private JRadioButton rdioMotherPools;
	private JRadioButton rdioLocalPools;
	private JPanel typesViewport;
	private JComboBox cbxMap;
	private JComboBox cbxZone;

	private static ArrayList<WorldMap> maps;
	private static ArrayList<GameZone> zonesKalimdor;
	private static ArrayList<GameZone> zonesEasternKingdoms;
	private JMenuItem mitmCopyGosToClipboard;
	private JMenuItem mitmResetView;
	private JRadioButton rdioGobjectType;
	private JMenuItem mitmLocalPoolBuilder;
	private JMenuItem mitmMotherPoolBuilder;
	private JMenuItem mitmBoundingBoxes;
	private JMenuItem mitmCopyLocalToClipboard;
	private JMenuItem mitmCopyMotherToClipboard;
	
	private ObjectQuerySelector querySelector = new ObjectQuerySelector();
	private JMenuItem mitmSelectObjectEntries;
	
	private String query_entries = "";
	private JLabel lblMaxData;
	private JMenuItem mitmSimulateSelection;
	private JSlider sliderZoom;
	
	public static void main(String[] args)
	{
		GameZone.InitializeGameZoneData();
		
		maps = new ArrayList<WorldMap>();
		maps.add(WorldMap.EASTERN_KINGDOMS);
		maps.add(WorldMap.KALIMDOR);
		
		zonesKalimdor = new ArrayList<GameZone>();
		zonesKalimdor.add(GameZone.Ashenvale);
		zonesKalimdor.add(GameZone.Azshara);
		zonesKalimdor.add(GameZone.Darkshore);
		zonesKalimdor.add(GameZone.Desolace);
		zonesKalimdor.add(GameZone.Durotar);
		zonesKalimdor.add(GameZone.DustwallowMarsh);
		zonesKalimdor.add(GameZone.Felwood);
		zonesKalimdor.add(GameZone.Feralas);
		zonesKalimdor.add(GameZone.Moonglade);
		zonesKalimdor.add(GameZone.Mulgore);
		zonesKalimdor.add(GameZone.Silithus);
		zonesKalimdor.add(GameZone.StonetalonMountains);
		zonesKalimdor.add(GameZone.Tanaris);
		zonesKalimdor.add(GameZone.Teldrassil);
		zonesKalimdor.add(GameZone.TheBarrens);
		zonesKalimdor.add(GameZone.ThousandNeedles);
		zonesKalimdor.add(GameZone.UngoroCrater);
		zonesKalimdor.add(GameZone.Winterspring);
		
		zonesEasternKingdoms = new ArrayList<GameZone>();
		zonesEasternKingdoms.add(GameZone.AlteracMountains);
		zonesEasternKingdoms.add(GameZone.ArathiHighlands);
		zonesEasternKingdoms.add(GameZone.Badlands);
		zonesEasternKingdoms.add(GameZone.BlastedLands);
		zonesEasternKingdoms.add(GameZone.BurningSteppes);
		zonesEasternKingdoms.add(GameZone.DeadwindPass);
		zonesEasternKingdoms.add(GameZone.DunMorogh);
		zonesEasternKingdoms.add(GameZone.Duskwood);
		zonesEasternKingdoms.add(GameZone.EasternPlaguelands);
		zonesEasternKingdoms.add(GameZone.ElwynnForest);
		zonesEasternKingdoms.add(GameZone.HillsbradFoothills);
		zonesEasternKingdoms.add(GameZone.LochModan);
		zonesEasternKingdoms.add(GameZone.RedridgeMountains);
		zonesEasternKingdoms.add(GameZone.SearingGorge);
		zonesEasternKingdoms.add(GameZone.SilverpineForest);
		zonesEasternKingdoms.add(GameZone.StranglethornVale);
		zonesEasternKingdoms.add(GameZone.SwampOfSorrows);
		zonesEasternKingdoms.add(GameZone.TheHinterlands);
		zonesEasternKingdoms.add(GameZone.TirisfalGlades);
		zonesEasternKingdoms.add(GameZone.WesternPlaguelands);
		zonesEasternKingdoms.add(GameZone.Westfall);
		zonesEasternKingdoms.add(GameZone.Wetlands);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mw = new MainWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		if (MySQLConnection.Connect())
			mw.PrepareDataAndShow();
	}
	
	public MainWindow()
	{
		setTitle("WoW Resource Pool Visualizer");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/resources/wow-icon2.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		map = WorldMap.EASTERN_KINGDOMS;
		zone = GameZone.AlteracMountains;
		
		query_entries = "go.id IN (" + querySelector.GetEntries() + ") AND ";
		
		lpoolBuilder = new LocalPoolBuilder(this);
		mpoolBuilder = new MotherPoolBuilder(this);
		
		JPanel mapPanel = new JPanel();
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(mapPanel, GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(controlsPanel, GroupLayout.PREFERRED_SIZE, 498, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(mapPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
						.addComponent(controlsPanel, GroupLayout.PREFERRED_SIZE, 671, Short.MAX_VALUE))
					.addGap(10))
		);
		
		JPanel objectPanel = new JPanel();
		objectPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Game Objects", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel typeListPanel = new JPanel();
		typeListPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Game Object Types", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel colorByPanel = new JPanel();
		colorByPanel.setBorder(new TitledBorder(null, "Color By", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		cbxMap = new JComboBox();
		cbxMap.setModel(new DefaultComboBoxModel(common.WorldMap.values()));
		cbxMap.setSelectedIndex(0);
		
		cbxZone = new JComboBox();
		cbxZone.setModel(new DefaultComboBoxModel(common.EasternKingdomZone.values()));
		cbxZone.setSelectedIndex(0);
		
		sliderZoom = new JSlider();
		sliderZoom.setValue(80);
		sliderZoom.setSnapToTicks(true);
		sliderZoom.setPaintTicks(true);
		sliderZoom.setMajorTickSpacing(10);
		sliderZoom.setMinimum(40);
		
		GroupLayout gl_controlsPanel = new GroupLayout(controlsPanel);
		gl_controlsPanel.setHorizontalGroup(
			gl_controlsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_controlsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_controlsPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(typeListPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
						.addComponent(objectPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_controlsPanel.createSequentialGroup()
							.addGroup(gl_controlsPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(cbxMap, 0, 130, Short.MAX_VALUE)
								.addComponent(cbxZone, Alignment.TRAILING, 0, 130, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(colorByPanel, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
						.addComponent(sliderZoom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_controlsPanel.setVerticalGroup(
			gl_controlsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_controlsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_controlsPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_controlsPanel.createSequentialGroup()
							.addComponent(cbxMap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cbxZone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(colorByPanel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sliderZoom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(typeListPanel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(objectPanel, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
					.addGap(71))
		);
		typeListPanel.setLayout(new BoxLayout(typeListPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		typeListPanel.add(scrollPane_4);
		
		typesViewport = new JPanel();
		scrollPane_4.setViewportView(typesViewport);
		typesViewport.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		rdioMotherPools = new JRadioButton("Mother Pools");
		rdioMotherPools.setFont(new Font("Tahoma", Font.PLAIN, 9));
		rdioMotherPools.setSelected(true);
		
		rdioLocalPools = new JRadioButton("Local Pools");
		rdioLocalPools.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		rdioGobjectType = new JRadioButton("Object Type");
		rdioGobjectType.setFont(new Font("Tahoma", Font.PLAIN, 9));
		GroupLayout gl_colorByPanel = new GroupLayout(colorByPanel);
		gl_colorByPanel.setHorizontalGroup(
			gl_colorByPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_colorByPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdioMotherPools, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdioLocalPools, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdioGobjectType, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_colorByPanel.setVerticalGroup(
			gl_colorByPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_colorByPanel.createSequentialGroup()
					.addGroup(gl_colorByPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdioMotherPools)
						.addComponent(rdioLocalPools)
						.addComponent(rdioGobjectType))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		colorByPanel.setLayout(gl_colorByPanel);
		
		JPanel motherPoolsPanel = new JPanel();
		motherPoolsPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Mother Pools", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel localPoolsPanel = new JPanel();
		localPoolsPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Local Pools", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel gameObjectsPanel = new JPanel();
		gameObjectsPanel.setBorder(new TitledBorder(null, "Game Objects", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		lblMaxData = new JLabel("New label");
		GroupLayout gl_objectPanel = new GroupLayout(objectPanel);
		gl_objectPanel.setHorizontalGroup(
			gl_objectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_objectPanel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_objectPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblMaxData, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(motherPoolsPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(localPoolsPanel, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(gameObjectsPanel, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_objectPanel.setVerticalGroup(
			gl_objectPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_objectPanel.createSequentialGroup()
					.addComponent(lblMaxData)
					.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
					.addGroup(gl_objectPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(motherPoolsPanel, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
						.addComponent(localPoolsPanel, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
						.addComponent(gameObjectsPanel, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gameObjectsPanel.setLayout(new BoxLayout(gameObjectsPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		gameObjectsPanel.add(scrollPane_2);
		
		gameObjectsViewport = new JPanel();
		scrollPane_2.setViewportView(gameObjectsViewport);
		gameObjectsViewport.setLayout(new BoxLayout(gameObjectsViewport, BoxLayout.Y_AXIS));
		localPoolsPanel.setLayout(new BoxLayout(localPoolsPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		localPoolsPanel.add(scrollPane_1);
		
		localPoolsViewport = new JPanel();
		scrollPane_1.setViewportView(localPoolsViewport);
		localPoolsViewport.setLayout(new BoxLayout(localPoolsViewport, BoxLayout.Y_AXIS));
		motherPoolsPanel.setLayout(new BoxLayout(motherPoolsPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		motherPoolsPanel.add(scrollPane);
		
		motherPoolsViewport = new JPanel();
		scrollPane.setViewportView(motherPoolsViewport);
		motherPoolsViewport.setLayout(new BoxLayout(motherPoolsViewport, BoxLayout.Y_AXIS));
		objectPanel.setLayout(gl_objectPanel);
		controlsPanel.setLayout(gl_controlsPanel);
		contentPane.setLayout(gl_contentPane);
		
		SetupImageLabel();
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		mapPanel.add(scrollPane_3);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Query");
		menuBar.add(mnNewMenu);
		
		mitmSelectObjectEntries = new JMenuItem("Select Object Entries");
		mnNewMenu.add(mitmSelectObjectEntries);
		
		JMenu mnNewMenu_1 = new JMenu("Data");
		menuBar.add(mnNewMenu_1);
		
		mitmResetView = new JMenuItem("Reset View");
		mnNewMenu_1.add(mitmResetView);
		
		mitmLocalPoolBuilder = new JMenuItem("Local Pool Builder");
		mnNewMenu_1.add(mitmLocalPoolBuilder);
		
		mitmMotherPoolBuilder = new JMenuItem("Mother Pool Builder");
		mnNewMenu_1.add(mitmMotherPoolBuilder);
		
		mitmBoundingBoxes = new JMenuItem("Bounding Boxes");
		mnNewMenu_1.add(mitmBoundingBoxes);
		
		mitmCopyGosToClipboard = new JMenuItem("Copy Objects to Clipboard");
		mnNewMenu_1.add(mitmCopyGosToClipboard);
		
		mitmCopyLocalToClipboard = new JMenuItem("Copy Local Pools to Clipboard");
		mnNewMenu_1.add(mitmCopyLocalToClipboard);
		
		mitmCopyMotherToClipboard = new JMenuItem("Copy Mother Pools to Clipboard");
		mnNewMenu_1.add(mitmCopyMotherToClipboard);
		
		mitmSimulateSelection = new JMenuItem("Simulate Selection");
		mnNewMenu_1.add(mitmSimulateSelection);
		
		setContentPane(contentPane);
		
		//Image img = GenerateZoneImage();
		wIcon = new JLabel();
/*		wIcon.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e)
			{
				
			}

			@Override
			public void mouseMoved(MouseEvent e)
			{
//				System.out.println(wIcon.getMousePosition());
//				wIcon.setToolTipText(String.format("(%d, %d)", wIcon.getMousePosition().x, wIcon.getMousePosition().y));
				if (wIcon.getMousePosition() != null)
					wIcon.setToolTipText(String.format("(%.1f, %.1f)", 100.0 * wIcon.getMousePosition().x / wIcon.getWidth(), 100.0 * wIcon.getMousePosition().y / wIcon.getHeight()));
			}
		});*/
		
		JPanel mainImageViewport = new JPanel();
		scrollPane_3.setViewportView(mainImageViewport);
		mainImageViewport.setLayout(new BoxLayout(mainImageViewport, BoxLayout.X_AXIS));
		mainImageViewport.add(wIcon);
		
		CreateActionEvents();
	}
	
	public void PrepareDataAndShow()
	{
		QueryData();
		
		DrawImage();
		UpdateViewports();
		RefreshDataLabels();
		
		setVisible(true);
	}
	
	private void CreateActionEvents()
	{
		ActionListener rdioColorAL = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				switch (++rdioColorOption)
				{
					case 1:
					case 2:
						rdioMotherPools.setSelected(false);
						rdioLocalPools.setSelected(true);
						rdioGobjectType.setSelected(false);
						break;
					case 3:
						rdioMotherPools.setSelected(false);
						rdioLocalPools.setSelected(false);
						rdioGobjectType.setSelected(true);
						break;
					case 4:
						rdioMotherPools.setSelected(true);
						rdioLocalPools.setSelected(false);
						rdioGobjectType.setSelected(false);
						rdioColorOption = 1;
						break;
				}
				
				DrawImage();
			}
		};
		
		rdioMotherPools.addActionListener(rdioColorAL);
		rdioLocalPools.addActionListener(rdioColorAL);
		rdioGobjectType.addActionListener(rdioColorAL);
		
		cbxMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (map != maps.get(cbxMap.getSelectedIndex()))
				{
					map = maps.get(cbxMap.getSelectedIndex());
					
					switch (cbxMap.getSelectedIndex())
					{
						case 0:
							cbxZone.setModel(new DefaultComboBoxModel(common.EasternKingdomZone.values()));
							zone = zonesEasternKingdoms.get(cbxZone.getSelectedIndex());
							break;
						case 1:
							cbxZone.setModel(new DefaultComboBoxModel(common.KalimdorZone.values()));
							zone = zonesKalimdor.get(cbxZone.getSelectedIndex());
							break;
					}
					
					ChangeZoneMap();
				}
			}
		});
		
		cbxZone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (map == WorldMap.EASTERN_KINGDOMS)
				{
					if (zone != zonesEasternKingdoms.get(cbxZone.getSelectedIndex()))
					{
						zone = zonesEasternKingdoms.get(cbxZone.getSelectedIndex());
						ChangeZoneMap();
					}
				}
				else if (map == WorldMap.KALIMDOR)
				{
					if (zone != zonesKalimdor.get(cbxZone.getSelectedIndex()))
					{
						zone = zonesKalimdor.get(cbxZone.getSelectedIndex());
						ChangeZoneMap();
					}
				}
			}
		});
		
		sliderZoom.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e)
			{
				zoom = (sliderZoom.getValue() + 20) / 100.0;
				DrawImage();
			}

			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		mitmCopyGosToClipboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String text = "";
				for (GameObject obj : objectData.gobjectsList)
//					if (obj.draw)
					if (ObjectIsDrawn(obj.guid))
						text += obj.guid + ",";
				
				StringSelection stringSelection = new StringSelection(text);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
		
		mitmCopyLocalToClipboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String text = "";
				for (var lPool : objectData.gobjectPoolsList)
					if (lPool.pool_entry != 0 && ObjectPoolIsDrawn(lPool.pool_entry))
						text += lPool.pool_entry + ",";
				
				StringSelection stringSelection = new StringSelection(text);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
		
		mitmCopyMotherToClipboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String text = "";
				for (var mPool : objectData.motherPoolsList)
					if (mPool.mother_pool != 0 && mPool.draw)
						text += mPool.mother_pool + ",";
				
				StringSelection stringSelection = new StringSelection(text);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
		
		mitmResetView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				for (GameObject obj : objectData.gobjectsList)
					obj.draw = true;
				DrawImage();
				RefreshDataLabels();
			}
		});
		
		mitmLocalPoolBuilder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				lpoolBuilder.ShowBuilder();
			}
		});
		
		mitmMotherPoolBuilder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				mpoolBuilder.ShowBuilder();
			}
		});
		
		mitmSelectObjectEntries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				querySelector.ShowDialog();
				
				if (querySelector.ok)
				{
					query_entries = "go.id IN (" + querySelector.GetEntries() + ") AND ";
					QueryData();
					DrawImage();
					RefreshDataLabels();
				}
			}
		});
		
		mitmSimulateSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Random rand = new Random();
				
				for (GameObject obj : objectData.gobjectsList)
					obj.draw = false;
				
				for (var mPool : objectData.motherPoolsList)
					if (mPool.draw)
					{
						ArrayList<Integer> possiblePools = new ArrayList<Integer>();
						ArrayList<Integer> usedPools = new ArrayList<Integer>();
						double mSum = 0.0;
						for (int i = 0; i < mPool.pools.size(); ++i)
						{
							possiblePools.add(i);
							if (!mPool.pools.get(i).draw)
							{
								usedPools.add(i);
								mSum += mPool.CalculateChance(mPool.pools.get(i));
							}
						}
						
						int numPools = mPool.mother_pool == 0 
										? possiblePools.size() 
												: mPool.max_limit;
						
						for (int i = usedPools.size(); i < numPools; ++i)
						{
							int rIndex = -1, z = 0;
							double chance = 100.0, sum = 0.0;
							while (rIndex == -1)
							{
								if (usedPools.contains(z))
								{
									++z;
									continue;
								}
								
								double objChance = mPool.CalculateChance(mPool.pools.get(possiblePools.get(z)));
								
								if (rand.nextDouble(chance - mSum - sum) <= objChance)
								{
									rIndex = z;
									mSum += objChance;
								}
								else
								{
									++z;
									sum += objChance;
								}
							}
							
							var lPool = mPool.pools.get(possiblePools.get(rIndex));
							usedPools.add(rIndex);
							
							ArrayList<Integer> possibleGameObjects = new ArrayList<Integer>();
							ArrayList<Integer> usedGameObjects = new ArrayList<Integer>();
							for (int j = 0; j < lPool.objects.size(); ++j)
								possibleGameObjects.add(j);
							
							int numGobjects = lPool.pool_entry == 0 
												? possibleGameObjects.size() 
													: lPool.max_limit;
							double lSum = 0.0;
							for (int j = 0; j < numGobjects; ++j)
							{
								rIndex = -1;
								z = 0;
								chance = 100.0;
								sum = 0.0;
								while (rIndex == -1)
								{
									if (usedGameObjects.contains(z))
									{
										++z;
										continue;
									}
									
									double objChance = lPool.CalculateChance(lPool.objects.get(possibleGameObjects.get(z)));
									
									if (rand.nextDouble(chance - lSum - sum) <= objChance)
									{
										rIndex = z;
										lSum += objChance;
									}
									else
									{
										++z;
										sum += objChance;
									}
								}
								
								var gObject = lPool.objects.get(possibleGameObjects.get(rIndex));
								usedGameObjects.add(rIndex);
								
								gObject.draw = true;
							}
						}
				}
				
				DrawImage();
			}
		});
	}
	
	private void ChangeZoneMap()
	{
		QueryData();
		DrawImage();
		RefreshDataLabels();
	}
	
	private void SetupImageLabel()
	{
		this.addComponentListener(new ComponentAdapter() 
		{  
		        public void componentResized(ComponentEvent e)
		        {
		        	if (lastX != wIcon.getWidth() || lastY != wIcon.getHeight())
		        	{
//			        	System.out.println(e.getID());
//			        	System.out.println(String.format("Resized! %d, %d", wIcon.getWidth(), wIcon.getHeight()));
			        	
//		        		DrawImage();
//			        	System.out.println(String.format("Icon: %d, %d", wIcon.getIcon().getIconWidth(), wIcon.getIcon().getIconHeight()));
		        		
			        	lastX = wIcon.getWidth();
			        	lastY = wIcon.getHeight();
		        	}
		        }
		});
	}
	
	private Image GenerateZoneImage()
	{
		BufferedImage bimg = CopyImage(zone.bimg);
		
		double xRatio = zone.mapImageWidth / zone.height;
		double yRatio = zone.mapImageHeight / zone.width;
		
		Graphics2D g2d = bimg.createGraphics();
//		g2d.setPaint(Color.white);
//		g2d.drawRect(mapImageOffsetX, mapImageOffsetY, mapImageWidth, mapImageHeight);
		
    	for (PoolPool poolPool : objectData.motherPoolsList)
    	{
    		if (poolPool.draw)
	    		for (GameObjectPool goPool : poolPool.pools)
				{
	    			if (goPool.draw)
						for (GameObject obj : goPool.objects)
							if (objectData.gobjectsMap.get(obj.guid).draw
									&& objectData.goTypesMap.get(obj.id).draw)
							{
								double nodeX = zone.mapImageOffsetX + (zone.yTopLeft - obj.pos_y) * xRatio;
								double nodeY = zone.mapImageOffsetY + (zone.xTopLeft - obj.pos_x) * yRatio;
								
								int outerSize = 14;
								int innerSize = outerSize - 4;
			//					g2d.setPaint(motherPoolColor);
			//					g2d.fillOval((int)nodeX - outerSize / 2, (int)nodeY - outerSize / 2, outerSize, outerSize);
			//					g2d.setPaint(objectData.gobjectPoolColors.get(goPool.pool_entry));
								
								if (obj.highlight)
								{
									g2d.setPaint(Color.white);
									g2d.fillOval((int)nodeX - (innerSize + 4) / 2, (int)nodeY - (innerSize + 4) / 2, innerSize + 4, innerSize + 4);
								}
								
								if (rdioMotherPools.isSelected())
								{
									g2d.setPaint(objectData.motherPoolColors.get(poolPool.mother_pool));
									g2d.fillOval((int)nodeX - innerSize / 2, (int)nodeY - innerSize / 2, innerSize, innerSize);
								}
								else if (rdioLocalPools.isSelected())
								{
									g2d.setPaint(objectData.gobjectPoolColors.get(obj.pool_entry));
									g2d.fillOval((int)nodeX - innerSize / 2, (int)nodeY - innerSize / 2, innerSize, innerSize);
								}
								else
								{
									g2d.setPaint(objectData.gobjectTypeColors.get(obj.id));
									g2d.fillOval((int)nodeX - innerSize / 2, (int)nodeY - innerSize / 2, innerSize, innerSize);
								}
							}
				}
    	}
		g2d.dispose();
		
//        try {
//			ImageIO.write(Durotar.bimg, "png", new File("c:\\newimage.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        
//		BufferedImage scaled = ScaleImage(bimg, 0.5); this works
		return ScaleImage(bimg, zoom);
//        return bimg.getScaledInstance(getWidth(), getHeight(),
//		        Image.SCALE_SMOOTH);
	}
	
	private void QueryData()
	{
		objectData = MySQLConnection.CALL_GET_GOBJECTS_NODES_IN_ZONE(zone, query_entries);
		
		if (!objectData.gobjectsList.isEmpty())
		{
			String foundTypes = "";
			for (GameObjectType type : objectData.gobjectTypesList)
				foundTypes += ", " + type.name;
			foundTypes = foundTypes.substring(2);
			
/*			int mp = 1, gp = 1;
			System.out.println(String.format("Printing Pool Data.\nThere are %d mother pools, %d game object pools and %d game objects including these types:\n%s\n", objectData.GetNumMotherPools(), objectData.GetNumGameObjectPools(), objectData.GetNumGameObjects(), foundTypes));
			for (PoolPool poolPool : objectData.motherPoolsList)
			{
				System.out.println(String.format("Printing Mother Pool %d (%d, size: %d):", mp++, poolPool.mother_pool, poolPool.GetNumPools()));
				for (GameObjectPool goPool : poolPool.pools)
				{
					System.out.println(String.format("\tPrinting Gobject Pool %d (%d, size: %d):", gp++, goPool.pool_entry, goPool.GetNumGameObjects()));
					for (GameObject obj : goPool.objects)
						System.out.println("\t\t" + obj.toString());
				}
			}*/
		}
		
		if (motherPoolsViewport != null)
			UpdateViewports();
	}
	
	private void UpdateViewports()
	{
		motherPoolsViewport.removeAll();
		for (var moPool : objectData.motherPoolsList)
			motherPoolsViewport.add(new MotherPoolListComponent(this, moPool.mother_pool, moPool.GetNumPools(), moPool.GetNumGameObjects(), objectData.motherPoolColors.get(moPool.mother_pool)));
		motherPoolsViewport.revalidate();
		
		localPoolsViewport.removeAll();
		for (var goPool : objectData.gobjectPoolsList)
			localPoolsViewport.add(new LocalPoolListComponent(this, goPool.pool_entry, goPool.GetNumGameObjects(), objectData.gobjectPoolColors.get(goPool.pool_entry)));
		localPoolsViewport.revalidate();
		
		gameObjectsViewport.removeAll();
		for (var go : objectData.gobjectsList)
			gameObjectsViewport.add(new GameObjectListComponent(this, go.guid, go.name));
		gameObjectsViewport.revalidate();
		
		typesViewport.removeAll();
		for (var type : objectData.gobjectTypesList)
			typesViewport.add(new GameObjectTypeListComponent(this, type.id, type.count, type.name, objectData.gobjectTypeColors.get(type.id)));
		typesViewport.revalidate();
	}
	
	public void UpdateMotherPoolColor(int entry, Color color)
	{
		objectData.motherPoolColors.remove(entry);
		objectData.motherPoolColors.put(entry, color);
		DrawImage();
	}
	
	public void UpdateLocalPoolColor(int entry, Color color)
	{
		objectData.gobjectPoolColors.remove(entry);
		objectData.gobjectPoolColors.put(entry, color);
		DrawImage();
	}
	
	public void UpdateGobjectTypeColor(int entry, Color color)
	{
		objectData.gobjectTypeColors.remove(entry);
		objectData.gobjectTypeColors.put(entry, color);
		DrawImage();
	}
	
	public void HighlightMotherPool(int entry)
	{
		for (var mo : objectData.motherPoolsMap.get(entry).pools)
			for (var go : objectData.gobjectPoolsMap.get(mo.pool_entry).objects)
				objectData.gobjectsMap.get(go.guid).highlight = true;
		DrawImage();
	}
	
	public void UnhighlightMotherPool(int entry)
	{
		for (var mo : objectData.motherPoolsMap.get(entry).pools)
			for (var go : objectData.gobjectPoolsMap.get(mo.pool_entry).objects)
				objectData.gobjectsMap.get(go.guid).highlight = false;
		DrawImage();
	}
	
	public void HighlightLocalPool(int entry)
	{
		for (var go : objectData.gobjectPoolsMap.get(entry).objects)
			objectData.gobjectsMap.get(go.guid).highlight = true;
		DrawImage();
	}
	
	public void UnhighlightLocalPool(int entry)
	{
		for (var go : objectData.gobjectPoolsMap.get(entry).objects)
			objectData.gobjectsMap.get(go.guid).highlight = false;
		DrawImage();
	}

	public void HighlightGameObject(int entry)
	{
		objectData.gobjectsMap.get(entry).highlight = true;
		DrawImage();
	}
	
	public void UnhighlightGameObject(int entry)
	{
		objectData.gobjectsMap.get(entry).highlight = false;
		DrawImage();
	}

	public void HighlightGameObjectType(int entry)
	{
		for (GameObject obj : objectData.gobjectsList)
			if (obj.id == entry)
				obj.highlight = true;
		DrawImage();
	}
	
	public void UnhighlightGameObjectType(int entry)
	{
		for (GameObject obj : objectData.gobjectsList)
			if (obj.id == entry)
				obj.highlight = false;
		DrawImage();
	}
	
	public void SetDrawMotherPool(int mother_pool, boolean draw)
	{
		objectData.motherPoolsMap.get(mother_pool).draw = draw;
		DrawImage();
		RefreshDataLabels();
	}
	
	public void SetDrawLocalPool(int pool_entry, boolean draw)
	{
		objectData.gobjectPoolsMap.get(pool_entry).draw = draw;
		DrawImage();
		RefreshDataLabels();
	}
	
	public void SetDrawGameObject(int guid, boolean draw)
	{
		objectData.gobjectsMap.get(guid).draw = draw;
		DrawImage();
		RefreshDataLabels();
	}
	
	public void SetDrawGameObjectType(int id, boolean draw)
	{
		objectData.goTypesMap.get(id).draw = draw;
		RefreshDataLabels();
	}
	
	public boolean ObjectPoolIsDrawn(int entry)
	{
		GameObjectPool pool = objectData.gobjectPoolsMap.get(entry);
		return pool.draw
				&& objectData.motherPoolsMap.get(pool.mother_pool).draw;
	}
	
	public boolean ObjectIsDrawn(int guid)
	{
		GameObject obj = objectData.gobjectsMap.get(guid);
		return obj.draw
				&& objectData.gobjectPoolsMap.get(obj.pool_entry).draw
				&& objectData.motherPoolsMap.get(obj.mother_pool).draw
				&& objectData.goTypesMap.get(obj.id).draw;
	}
	
	public void FilterToMotherPool(int mother_pool)
	{
		for (GameObject obj : objectData.gobjectsList)
			obj.draw = false;
		
		for (GameObjectPool goPool : objectData.motherPoolsMap.get(mother_pool).pools)
			for (GameObject obj : goPool.objects)
				obj.draw = true;
		DrawImage();
		RefreshDataLabels();
	}
	
	public void DrawImage()
	{
		wIcon.setIcon(new ImageIcon(GenerateZoneImage()));
	}
	
	public void RefreshDataLabels()
	{
		int minSpawns = 0;
		int maxSpawns = 0;
		for (var mPool : objectData.motherPoolsList)
			if (mPool.draw)
			{
				ArrayList<Integer> poolLimits = new ArrayList<Integer>(mPool.pools.size());
				
				for (var lPool : mPool.pools)
					if (lPool.draw && lPool.pool_entry != 0)
						poolLimits.add(lPool.max_limit);
				Collections.sort(poolLimits);
				
				int mPoolLimit = mPool.mother_pool != 0 ? mPool.max_limit : mPool.pools.size();
				mPoolLimit = Math.min(mPoolLimit, poolLimits.size());
				
				for (int i = 0; i < mPoolLimit; ++i)
				{
					minSpawns += poolLimits.get(i);
					maxSpawns += poolLimits.get(poolLimits.size() - (i+1));
				}
			}
		
		lblMaxData.setText(String.format("Min Spawns: %d, Max Spawns: %d", minSpawns, maxSpawns));
	}
	
	public static BufferedImage CopyImage(BufferedImage source)
	{
	    BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	    Graphics g = b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
	
	public static BufferedImage ScaleImage(BufferedImage before, double scale)
	{
		BufferedImage after = new BufferedImage(before.getWidth(), before.getHeight(), BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(scale, scale);
		AffineTransformOp scaleOp = 
		   new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(before, after);
		return after;
	}
}
