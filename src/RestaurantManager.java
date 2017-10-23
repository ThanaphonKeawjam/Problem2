package code;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * User interface for a menu and ordering system,
 * using console interface.
 * 
 * @author Thanaphon Keawjam;
 */

public class RestaurantManager{
	
	public static Scanner sc = new Scanner(System.in);
	
	public static ArrayList<String> isMenu = new ArrayList<String>();
	public static ArrayList<Double> isPrice = new ArrayList<Double>();
	public static int[] order = new int[1000];
	public static int[] realQuantity = new int[1000];
	
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
		loadMenu();
	//	ArrayList<String> listMenu = isMenu;
		String[] itemMenu = isMenu.toArray(new String[isMenu.size()]);
		return itemMenu;
	}
	
	public static double[] getPrices(){
	//	ArrayList<Double> listPrice = isPrice;
		double[] priceMenu = new double[isPrice.size()];
		for (int x = 0; x< priceMenu.length; x++){
			priceMenu[x] = isPrice.get(x);
		}
		return priceMenu;
	}
		
	public static void printMenuList(){
		String[] menu = getMenuItems();
		double[] price = getPrices();
		System.out.println("--------- Welcome to SKE Restaurant ---------");
		for (int i = 0; i<isMenu.size(); i++){
			System.out.printf("%d.) %s",i+1,menu[i]);
			System.out.printf("\t\t%6.2f Baht.",price[i]);
			System.out.println("");
		}
		System.out.println("[T] Total");
		System.out.println("[P] Payment");
		System.out.println("[E] Exit");
	}
	
	public static double printOrder(String choice,double sum){
		int Total = 0;
		double[] price3 = getPrices();
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		if (choice.equalsIgnoreCase("T")){
			System.out.println("\t\tSKE Restaurant");
			System.out.println("Date: " + date + "  Time: " + time);
			System.out.println("+------ Menu --------------+-- Qty --+-- Price --+");
			for (int j = 0; j<isPrice.size(); j++){
				if (price3[j]*order[j] != 0){
				System.out.printf("|%-8s\t\t   |\t%d    |\t%7.2f  |\n",isMenu.get(j),order[j],price3[j]*order[j]);
				}
			}
			if (sum >= 1200){
				System.out.println("+------------------------------------+-----------+");
				System.out.println("|Price over 1200 Baht.\t\t\t\t |");
				System.out.println("|You got 5% discount.\t\t\t\t |");
				sum = sum*95/100;
			}
			for (int i = 0; i<isMenu.size(); i++){
			Total = Total + order[i];
			}
			System.out.println("+--------------------------+---------+-----------+");
			System.out.printf("|Total\t\t\t   |\t%d    |\t%7.2f  |\n",Total,sum);
			System.out.println("+--------------------------+---------+-----------+");
		}
		return sum;
	}
	
	public static void payMent(double realPrice){
		double getMoney;
		do{
			if (realPrice >= 1200) realPrice = realPrice*95/100;
			
			System.out.print("Get money(Baht): ");
			getMoney = sc.nextDouble();
			if (getMoney < realPrice){
				System.out.println("Not enough money.Try again.");
				System.out.println();
			}
			}
			while (getMoney < realPrice);
			System.out.printf("Change(Baht): %.2f\n",getMoney-realPrice);
	}
	
/**
 * Calculate prices in each menu
 * @param choice
 * @param quantity
 * @return
 */
	public static double calculatePrice(String choice, int quantity){
		double price = 0;
		double[] price2 = getPrices();
		int choice2 = Integer.parseInt(choice);
		for (int i = 0; i < isMenu.size(); i++){
			if (choice2 == i+1){
				order[i] = order[i] + quantity;
				realQuantity[i] = order[i] - realQuantity[i];
				price =  realQuantity[i] * price2[i];
				break;
			}
		}
		return price;
	}

	public static String checkInt(String choice){
		String str;
		for (int ch = 1; ch <= isPrice.size(); ch++){
			str = Integer.toString(ch);
			if (choice.equals(str)){
				return str;
			}
		}
		return " ";
	}
	
/**
 * Require input choice to get quantity or print order or payment
 * or end the program. 	
 */
	
	public static void recordOrder(){
		double sum = 0,realPrice = 0;
		int quantity = 0;
		String choice;
		do {
			System.out.print("Enter your Choice: ");
			choice = sc.next();
			realPrice = printOrder(choice,sum);
			if ((!choice.equalsIgnoreCase("E") && !choice.equalsIgnoreCase("T") && !choice.equalsIgnoreCase("P")) && !choice.equals(checkInt(choice))){
				System.out.println("Incorrect menu!!\nTry again.");
				continue;
			}
			if (choice.equalsIgnoreCase("E")){
				System.out.print("==== Thank you ====");
				System.exit(0);
			}else 
			if (choice.equalsIgnoreCase("P")){
				payMent(realPrice);
				System.out.print("==== Thank you ====");
				System.exit(0);
			}else 
			if (!choice.equalsIgnoreCase("E") && !choice.equalsIgnoreCase("T") && !choice.equalsIgnoreCase("P")){
			System.out.print("Enter Quantity: ");
			quantity = sc.nextInt();
			}else {
				continue;
				}
			sum = sum + calculatePrice(choice,quantity);
		} while(!choice.equalsIgnoreCase("E"));
	}
	
	public static void init(){
		printMenuList();
		recordOrder();
	}
	
	public static void main(String[] args) {
		RestaurantManager.init();
	}
}