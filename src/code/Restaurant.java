package code;

import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * User interface for a menu and ordering system,
 * using console interface.
 * 
 * @author Thanaphon Keawjam
 */

public class Restaurant{
	
	public static Scanner sc = new Scanner(System.in);
	
	private static double sum = 0;
	
	private static String[] menu;
	private static double[] prices;
	
/**
 * This method is print menu list.It will print all menus in SKE-Restaurant.	
 */
		
	public static void printMenuList(){
		menu = RestaurantManager.getMenuItems();  //get menu items from RestaurantManager and save them to attribute.
		prices = RestaurantManager.getPrices();  // get prices from RestaurantManager and save them to attribute.
		String prompt = "[T] Total\n" + "[P] Payment\n" + "[M] DisplayMenus\n" + "[E] Exit";
		System.out.println("--------- Welcome to SKE Restaurant ---------");
		for (int i = 0; i<menu.length; i++){
			System.out.printf("%d.) %s",i+1,menu[i]);
			System.out.printf("\t\t%6.2f Baht.",prices[i]);
			System.out.println("");
		}
		System.out.println(prompt);
	}
	
/**
 * This method is print order.
 * When user select [T] Total option then it will show all order
 * by user.
 * @param choice
 * @param sum
 * @return
 */
	
	public static double printOrder(String choice,double sum, ArrayList<Integer> order){
		int Total = 0;
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		String timenow = dtf.format(time);
		if (choice.equalsIgnoreCase("T")){
			System.out.println("\t\tSKE Restaurant");
			System.out.println("Date: " + date + "  Time: " + timenow);
			System.out.println("+------ Menu --------------+-- Qty --+-- Price --+");
			for (int j = 0; j<prices.length; j++){
				if (prices[j]*order.get(j) != 0){
				System.out.printf("|%-8s\t\t   |\t%d    |\t%7.2f  |\n",menu[j],order.get(j),prices[j]*order.get(j));
				}
			}
			if (sum >= 1200){
				System.out.println("+------------------------------------+-----------+");
				System.out.println("|Price over 1200 Baht.\t\t\t\t |");
				System.out.println("|You got 5% discount.\t\t\t\t |");
				sum = sum*95/100;
			}
			for (int i = 0; i<menu.length; i++){
			Total = Total + order.get(i);
			}
			System.out.println("+--------------------------+---------+-----------+");
			System.out.printf("|Total\t\t\t   |\t%d    |\t%7.2f  |\n",Total,sum);
			System.out.println("+--------------------------+---------+-----------+");
		}
		return sum;
	}
	
/**
 * This method is payment option.
 * It will get money from user calculation and change money.
 * @param realPrice
 */
	
	public static double payMent(double realPrice){
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
		return realPrice;
	}
	
/**
 * Calculate prices in each menu
 * @param choice
 * @param quantity
 * @return
 */
	
	public static double calculatePrice(String choice, int quantity, ArrayList<Integer> order){
		double price = 0;
		int order2,real;
		ArrayList<Integer> realQuantity = new ArrayList<Integer>();
		for (int y = 0; y<prices.length; y++){
			realQuantity.add(0);
		}
		int choice2 = Integer.parseInt(choice);
		for (int i = 0; i < prices.length; i++){
			if (choice2 == i+1){
				order2 = order.get(i) + quantity;
				order.add(i, order2);
				order.remove(i+1);
				
				real = quantity - realQuantity.get(i);
				realQuantity.add(i, real);
				realQuantity.remove(i+1);
				price = real * prices[i];
				break;
			}
		}
		return price;
	}
	
/**
 * This method will check choice.
 * If it is integer choice then user can order other menu.	
 * @param choice
 * @return
 */

	public static String checkInt(String choice){
		String str;
		for (int ch = 1; ch <= RestaurantManager.getPrices().length; ch++){
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
 * @throws IOException 
 */
	
	public static void makeOrder() throws IOException{
		ArrayList<Integer> order = setValueOrder();
		double realPrice = 0;
		int quantity = 0;
		String choice;
		do {
			System.out.print("Enter your Choice: ");
			choice = sc.next();
			realPrice = printOrder(choice,sum,order);
			if ((!choice.equalsIgnoreCase("E") && !choice.equalsIgnoreCase("T") && !choice.equalsIgnoreCase("P") 
				 && !choice.equalsIgnoreCase("M")) && !choice.equals(checkInt(choice))){
				System.out.println("Incorrect menu!!\nTry again.");
				continue;
			}
			if (choice.equalsIgnoreCase("M")){
				System.out.println();
				printMenuList();
				continue;
			}else
			if (choice.equalsIgnoreCase("E")){
				System.out.print("==== Thank you ====");
				System.exit(0);
			}else 
			if (choice.equalsIgnoreCase("P")){
				sum = payMent(realPrice);
				System.out.print("==== Thank you ====");;
				record(order);
				System.exit(0);
			}else 
			if (!choice.equalsIgnoreCase("E") && !choice.equalsIgnoreCase("T") && !choice.equalsIgnoreCase("P")
				 && !choice.equalsIgnoreCase("M")){
			System.out.print("Enter Quantity: ");
			quantity = sc.nextInt();
			}else {
				continue;
				}
			sum = sum + calculatePrice(choice,quantity,order);
		} while(!choice.equalsIgnoreCase("E"));
	}
	
/**
 * This method will send all orders vale to write to file.
 * @throws IOException
 */

	public static void record(ArrayList<Integer> order) throws IOException {
		int[] rOrder = new int[order.size()];
		for (int i = 0; i<rOrder.length; i++){
			rOrder[i] = order.get(i);
		}
		String allOrder = RestaurantManager.recordAllOrder(rOrder,sum);
		RestaurantManager.writeToFile(allOrder);
	}

/**
 * This method will on figure an order variable
 * to apply in calculatePrice method.
 */
	
	public static ArrayList<Integer> setValueOrder() {
		ArrayList<Integer> isOrder = new ArrayList<Integer>();
		for (int z = 0; z<RestaurantManager.getMenuItems().length; z++){
			isOrder.add(0);
		}
		return isOrder;
	}

	public static void main(String[] args) throws IOException {
		RestaurantManager.init();
		printMenuList();
		makeOrder();
	}
}
