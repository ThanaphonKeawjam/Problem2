package code;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used for reading files menu.txt
 * and then store it in each variable.
 * 
 * @author Thanaphon Keawjam
 */

public class RestaurantManager {
	
	private static ArrayList<String> isMenu = new ArrayList<String>();
	private static ArrayList<Double> isPrice = new ArrayList<Double>();
	
	public static void loadMenu(){
		String filename = "data/menu.txt";
		ClassLoader loader = RestaurantManager.class.getClassLoader();
		InputStream in = loader.getResourceAsStream(filename);
		if (in == null){
			System.out.println("Could not access file " + filename);
			return;
		}
		Scanner reader = new Scanner(in);
		while (reader.hasNextLine()){
			String line = reader.nextLine();
			if (line.startsWith("#")){
				continue;
			}
			String[] array = line.split("; ");
			isMenu.add(array[0]);
			isPrice.add(Double.parseDouble(array[1]));
		}
		reader.close();
	}
	
	public static String[] getMenuItems(){
		String[] itemMenu  = isMenu.toArray(new String[isMenu.size()]);
		return itemMenu;
	}
	
	public static double[] getPrices(){
		double[] priceMenu = new double[isPrice.size()];
		for (int x = 0; x<priceMenu.length; x++){
			priceMenu[x] = isPrice.get(x);
		}
		return priceMenu;
	}
	
	public static void init(){
		loadMenu();
	}

}
