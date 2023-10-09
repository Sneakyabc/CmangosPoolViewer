package common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameZone
{
	// Eastern Kingdoms
	public static GameZone AlteracMountains;
	public static GameZone ArathiHighlands;
	public static GameZone Badlands;
	public static GameZone BlastedLands;
	public static GameZone BurningSteppes;
	public static GameZone DeadwindPass;
	public static GameZone DunMorogh;
	public static GameZone Duskwood;
	public static GameZone EasternPlaguelands;
	public static GameZone ElwynnForest;
	public static GameZone HillsbradFoothills;
	public static GameZone LochModan;
	public static GameZone RedridgeMountains;
	public static GameZone SearingGorge;
	public static GameZone SilverpineForest;
	public static GameZone StranglethornVale;
	public static GameZone SwampOfSorrows;
	public static GameZone TheHinterlands;
	public static GameZone TirisfalGlades;
	public static GameZone WesternPlaguelands;
	public static GameZone Westfall;
	public static GameZone Wetlands;
	
	// Kalimdor
	public static GameZone Ashenvale;
	public static GameZone Azshara;
	public static GameZone Darkshore;
	public static GameZone Desolace;
	public static GameZone Durotar;
	public static GameZone DustwallowMarsh;
	public static GameZone Felwood;
	public static GameZone Feralas;
	public static GameZone Moonglade;
	public static GameZone Mulgore;
	public static GameZone Silithus;
	public static GameZone StonetalonMountains;
	public static GameZone Tanaris;
	public static GameZone Teldrassil;
	public static GameZone TheBarrens;
	public static GameZone ThousandNeedles;
	public static GameZone UngoroCrater;
	public static GameZone Winterspring;
	
	public static void InitializeGameZoneData()
	{
		BufferedImage bimg;
		try {
			// Eastern Kingdoms
			String folder = "B:\\Documents\\eclipse-workspace\\CmangosPoolViewer\\src\\resources\\";
			bimg = ImageIO.read(new File(folder + "Alterac Mountains.jpg"));
			AlteracMountains = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0150), (int) (bimg.getWidth()  * 1.0000), (int) (bimg.getHeight() * 0.9950), 1497.0, 780.0, -363.0, -2016.0);
			
			bimg = ImageIO.read(new File(folder + "Arathi Highlands.jpg"));
			ArathiHighlands = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 1.0000), (int) (bimg.getHeight() * 0.9950), -134.0, -869.0, -2528.0, -4462.0);
			
			bimg = ImageIO.read(new File(folder + "Badlands.jpg"));
			Badlands = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0150), (int) (bimg.getWidth()  * 0.9950), (int) (bimg.getHeight() * 0.9900), -5892.0, -2079.0, -7547.0, -4561.0);
			
			bimg = ImageIO.read(new File(folder + "Blasted Lands.jpg"));
			BlastedLands = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0050), (int) (bimg.getWidth()  * 1.0000), (int) (bimg.getHeight() * 1.0000), -10569.0, -1245.0, -12798.0, -4587.0);
			
			bimg = ImageIO.read(new File(folder + "Burning Steppes.jpg"));
			BurningSteppes = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0020), (int) (bimg.getHeight() * 0.0050), (int) (bimg.getWidth()  * 0.9965), (int) (bimg.getHeight() * 0.9935), -7032.0, -270.0, -8987.0, -3195.0);
			
			bimg = ImageIO.read(new File(folder + "Deadwind Pass.jpg"));
			DeadwindPass = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 1.0000), (int) (bimg.getHeight() * 1.0000), 4326.0, 1443.0, 1201.0, -3968.0);
			
			bimg = ImageIO.read(new File(folder + "Dun Morogh.jpg"));
			DunMorogh = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 1.0000), (int) (bimg.getHeight() * 1.0000), -3880.0, 1798.0, -7158.0, -3120.0);
			
			bimg = ImageIO.read(new File(folder + "Duskwood.jpg"));
			Duskwood = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 0.9970), (int) (bimg.getHeight() * 0.9950), -9717.0, 832.0, -11515.0, -1865.0);
			
			bimg = ImageIO.read(new File(folder + "Eastern Plaguelands.jpg"));
			EasternPlaguelands = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 1.0000), (int) (bimg.getHeight() * 1.0000), 3795.0, -2190.0, 1220.0, -6055.0);
			
			bimg = ImageIO.read(new File(folder + "Elwynn Forest.jpg"));
			ElwynnForest = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 1.0000), (int) (bimg.getHeight() * 1.0000), -7941.0, 1527.0, -10252.0, -1993.0);
			
			bimg = ImageIO.read(new File(folder + "Hillsbrad Foothills.jpg"));
			HillsbradFoothills = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 0.9980), (int) (bimg.getHeight() * 0.9980), 400.0, 1065.0, -1730.0, -2132.0);
			
			bimg = ImageIO.read(new File(folder + "Loch Modan.jpg"));
			LochModan = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 0.9980), (int) (bimg.getHeight() * 0.9980), -4490.0, -1995.0, -6325.0, -4750.0);
			
			bimg = ImageIO.read(new File(folder + "Redridge Mountains.jpg"));
			RedridgeMountains = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 0.9980), (int) (bimg.getHeight() * 1.0000), -8578.0, -1572.0, -10020.0, -3741.0);
			
			bimg = ImageIO.read(new File(folder + "Searing Gorge.jpg"));
			SearingGorge = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 0.9960), (int) (bimg.getHeight() * 1.0000), -6110.0, -325.0, -7585.0, -2552.0);
			
			bimg = ImageIO.read(new File(folder + "Silverpine Forest.jpg"));
			SilverpineForest = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 1.0000), (int) (bimg.getHeight() * 1.0250), 1666.0, 3448.0, -1228.0, -749.0);
			
			bimg = ImageIO.read(new File(folder + "Stranglethorn Vale.jpg"));
			StranglethornVale = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 1.000), (int) (bimg.getHeight() * 1.0000), -11172.0, 2215.0, -15422.0, -4160.0);
			
			bimg = ImageIO.read(new File(folder + "Swamp of Sorrows.jpg"));
			SwampOfSorrows = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0025), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 0.9960), (int) (bimg.getHeight() * 0.9980), -9620.0, -2225.0, -11149.0, -4515.0);
			
			bimg = ImageIO.read(new File(folder + "The Hinterlands.jpg"));
			TheHinterlands = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0010), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 0.9960), (int) (bimg.getHeight() * 0.9960), 1465.0, -1577.0, -1099.0, -5414.0);
			
			bimg = ImageIO.read(new File(folder + "Tirisfal Glades.jpg"));
			TirisfalGlades = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 1.0000), (int) (bimg.getHeight() * 0.9980), 3836.0, 3033.0, 825.0, -1482.0);
			
			bimg = ImageIO.read(new File(folder + "Western Plaguelands.jpg"));
			WesternPlaguelands = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0015), (int) (bimg.getHeight() * -0.1000), (int) (bimg.getWidth()  * 0.9975), (int) (bimg.getHeight() * 1.0000), 3662.0, 415.0, 501.0, -3879.0);
			
			bimg = ImageIO.read(new File(folder + "Westfall.jpg"));
			Westfall = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 1.0000), (int) (bimg.getHeight() * 1.0000), -9400.0, 3014.0, -11730.0, -481.0);
			
			bimg = ImageIO.read(new File(folder + "Wetlands.jpg"));
			Wetlands = new GameZone(bimg, 0, (int) (bimg.getWidth()  * 0.0000), (int) (bimg.getHeight() * 0.0000), (int) (bimg.getWidth()  * 1.0000), (int) (bimg.getHeight() * 1.0020), -2148.0, -389.0, -4900.0, -4520.0);
			
			// Kalimdor
			bimg = ImageIO.read(new File(folder + "Ashenvale.jpg"));
			Ashenvale = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.0480), (int) (bimg.getHeight() * 0.0950), (int) (bimg.getWidth()  * 0.9800), (int) (bimg.getHeight() * 0.9000), 4326.0, 1443.0, 1201.0, -3968.0);
			
			bimg = ImageIO.read(new File(folder + "Azshara.jpg"));
			Azshara = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.1100), (int) (bimg.getHeight() * 0.0550), (int) (bimg.getWidth()  * 0.9620), (int) (bimg.getHeight() * 0.9825), 5176.0, -3826.0, 2020.0, -8165.0);
			
			bimg = ImageIO.read(new File(folder + "Darkshore.jpg"));
			Darkshore = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.2750), (int) (bimg.getHeight() * 0.0115), (int) (bimg.getWidth()  * 0.6550), (int) (bimg.getHeight() * 0.9900), 8294.0, 1142.0, 3969.0, -1404.0);
			
			bimg = ImageIO.read(new File(folder + "Desolace.jpg"));
			Desolace = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.1900), (int) (bimg.getHeight() * 0.0045), (int) (bimg.getWidth()  * 0.8085), (int) (bimg.getHeight() * 0.9850), 448.0, 3380.0, -2522.0, 578.0);
			
			bimg = ImageIO.read(new File(folder + "Durotar.jpg"));
			Durotar = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.3530), (int) (bimg.getHeight() * 0.0575), (int) (bimg.getWidth()  * 0.7325), (int) (bimg.getHeight() * 0.9900), 1615.0, -3819.0, -1681.0, -5823.0);
			
			bimg = ImageIO.read(new File(folder + "Dustwallow Marsh.jpg"));
			DustwallowMarsh = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.2900), (int) (bimg.getHeight() * 0.0425), (int) (bimg.getWidth()  * 0.8500), (int) (bimg.getHeight() * 0.8850), -2154.0, -2477.0, -5160.0, -5452.0);
			
			bimg = ImageIO.read(new File(folder + "Felwood.jpg"));
			Felwood = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.2975), (int) (bimg.getHeight() * 0.0475), (int) (bimg.getWidth()  * 0.6775), (int) (bimg.getHeight() * 1.0100), 6970.0, -66.0, 3251, -2262.0);
			
			bimg = ImageIO.read(new File(folder + "Feralas.jpg"));
			Feralas = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.2150), (int) (bimg.getHeight() * 0.0150), (int) (bimg.getWidth()  * 0.9150), (int) (bimg.getHeight() * 0.8050), -2438.0, 3948.0, -6100.0, -945.0);
			
			bimg = ImageIO.read(new File(folder + "Moonglade.jpg"));
			Moonglade = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.2150), (int) (bimg.getHeight() * 0.0150), (int) (bimg.getWidth()  * 0.9150), (int) (bimg.getHeight() * 0.8050), 0.0, 0.0, 0.0, 0.0);
			
			bimg = ImageIO.read(new File(folder + "Mulgore.jpg"));
			Mulgore = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.2775), (int) (bimg.getHeight() * 0.0650), (int) (bimg.getWidth()  * 0.7350), (int) (bimg.getHeight() * 0.9450), -491.0, 617.0, -3533.0, -1755.0);
			
			bimg = ImageIO.read(new File(folder + "Silithus.jpg"));
			Silithus = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.0975), (int) (bimg.getHeight() * 0.0225), (int) (bimg.getWidth()  * 0.8400), (int) (bimg.getHeight() * 1.0575), -6000.0, 2200.0, -8420.0, -403.0);
			
			bimg = ImageIO.read(new File(folder + "Stonetalon Mountains.jpg"));
			StonetalonMountains = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.2585), (int) (bimg.getHeight() * 0.0500), (int) (bimg.getWidth()  * 0.8300), (int) (bimg.getHeight() * 0.9900), 2770.0, 1989.0, -337.0, -812.0);
			
			bimg = ImageIO.read(new File(folder + "Tanaris.jpg"));
			Tanaris = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.2475), (int) (bimg.getHeight() * 0.1115), (int) (bimg.getWidth()  * 0.8100), (int) (bimg.getHeight() * 0.9880), -6408.0, -1931.0, -10434.0, -5781.0);
			
			bimg = ImageIO.read(new File(folder + "Teldrassil.jpg"));
			Teldrassil = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.2775), (int) (bimg.getHeight() * 0.0650), (int) (bimg.getWidth()  * 0.7350), (int) (bimg.getHeight() * 0.9450), 11357.0, 3160.0, 8560.0, -552.0);
			
			bimg = ImageIO.read(new File(folder + "The Barrens.jpg"));
			TheBarrens = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.3525), (int) (bimg.getHeight() * 0.0300), (int) (bimg.getWidth()  * 0.7150), (int) (bimg.getHeight() * 0.9000), 1475.0, -925.0, -4532.0, -4662.0);
			
			bimg = ImageIO.read(new File(folder + "Thousand Needles.jpg"));
			ThousandNeedles = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.0425), (int) (bimg.getHeight() * 0.0450), (int) (bimg.getWidth()  * 0.9250), (int) (bimg.getHeight() * 0.9950), -4102.0, -618.0, -6872.0, -4500.0);
			
			bimg = ImageIO.read(new File(folder + "Ungoro Crater.jpg"));
			UngoroCrater = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.1825), (int) (bimg.getHeight() * 0.0400), (int) (bimg.getWidth()  * 0.8450), (int) (bimg.getHeight() * 0.9300), -6059.0, -150.0, -8273.0, -2574.0);
			
			bimg = ImageIO.read(new File(folder + "Winterspring.jpg"));
			Winterspring = new GameZone(bimg, 1, (int) (bimg.getWidth()  * 0.2600), (int) (bimg.getHeight() * 0.0600), (int) (bimg.getWidth()  * 0.7690), (int) (bimg.getHeight() * 0.9525), 8262.0, -2153.0, 4000.0, -5800.0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GameZone(BufferedImage bimg, int map, int mapImageOffsetX, int mapImageOffsetY
						, int mapImageWidth, int mapImageHeight, double xTopLeft
						, double yTopLeft, double xBotRight, double yBotRight)
	{
		this.bimg = bimg;
		this.map = map;
		this.mapImageOffsetX = mapImageOffsetX;
		this.mapImageOffsetY = mapImageOffsetY;
		this.mapImageWidth = mapImageWidth - mapImageOffsetX;
		this.mapImageHeight = mapImageHeight - mapImageOffsetY;
		
		this.xTopLeft = xTopLeft;
		this.yTopLeft = yTopLeft;
		this.xBotRight = xBotRight; // X is vertical in WoW
		this.yBotRight = yBotRight; // Y is horizontal in WoW
		this.width = xTopLeft - xBotRight;
		this.height = yTopLeft - yBotRight;
	}
	
	public BufferedImage bimg;
	public int map;
	public int mapImageOffsetX;
	public int mapImageOffsetY;
	public int mapImageWidth;
	public int mapImageHeight;
	
	public double xTopLeft;
	public double yTopLeft;
	public double xBotRight;
	public double yBotRight;
	public double width;
	public double height;
}
