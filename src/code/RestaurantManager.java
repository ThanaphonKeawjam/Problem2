package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used for reading files from menu.txt
 * and record a completed order to file.
 * 
 * @author Thanaphon Keawjam
 */

public class RestaurantManager {
	
	private static ArrayList<String> isMenu = new ArrayList<String>();
	private static ArrayList<Double> isPrice = new ArrayList<Double>();
	
/**
 * This method will load menu file from folder data.	
 */
	
	public static void setMenu(String filename){
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
			String[] array = line.trim().split("; ");
			isMenu.add(array[0]);
			isPrice.add(Double.parseDouble(array[1]));
		}
		reader.close();
	}

/**
 * This method will record menu vale from ArrayList to Array.	
 * @return
 */
	
	public static String[] getMenuItems(){
		String[] itemMenu  = isMenu.toArray(new String[isMenu.size()]);
		return itemMenu;
	}
	
/**
 * This method will record price vale from ArrayList to Array.	
 * @return
 */
	
	public static double[] getPrices(){
		double[] priceMenu = new double[isPrice.size()];
		for (int x = 0; x<priceMenu.length; x++){
			priceMenu[x] = isPrice.get(x);
		}
		return priceMenu;
	}
	
/**
 * This method will record all order from user
 * when user select [P] Payment.	
 * @return
 */

	public static String recordAllOrder(int[] order, double sum) {
		String[] menu = getMenuItems();
		String temp = "";
		int i = 0;
		LocalTime time = LocalTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		String timenow = dtf.format(time);
		for (String name : menu){
			if (order[i] != 0){
				temp = String.format(temp + "%-15s%5d\n", menu[i],order[i]);
			}
			i++;
		}
		return String.format("\nDate: " + LocalDate.now() + "\nTime: " + timenow + 
				"\n\n%s\n%20.2f Baht\n==========================",temp,sum);
		}
	
/**
 * This method is write all order to file when user
 * select [P] Payment.		
 * @param allOrder
 * @throws IOException
 */
	
	public static void writeToFile(String allOrder) throws IOException{
		File out = new File("src/data/RecordOrder.txt");
		FileOutputStream output;
		try{
			output = new FileOutputStream(out,true);
			output.write(allOrder.getBytes());
		}catch (FileNotFoundException ex){
			System.out.println("Couldn't open file " + out);
			return;
		}
		output.close();
	}
	
/**
 * This method will call load menu method.	
 */
	
	public static void init(){
		String filename = "data/menu.txt";
		setMenu(filename);
	}

}
