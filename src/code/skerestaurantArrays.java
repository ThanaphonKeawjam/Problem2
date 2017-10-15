package code;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * User interface for a menu and ordering system,
 * using console interface.
 * 
 * @author Thanaphon Keawjam
 */

public class skerestaurantArrays{
	
	public static Scanner sc = new Scanner(System.in);
	
	
	private static String[] isMenu = {"Pizza","Chickens","Coke","Water"};
	private static double[] isPrice = {250.00,120.00,45.00,7.00};
	private static int[] order = new int[isPrice.length];
	private static int[] realQuantity = new int[isPrice.length];
	
	public static void printMenuList(){
		System.out.println("--------- Welcome to SKE Restaurant ---------");
		for (int i = 0; i<isMenu.length; i++){
			System.out.printf("%d.) %s",i+1,isMenu[i]);
			System.out.printf("\t\t%6.2f Baht.",isPrice[i]);
			System.out.println("");
		}
		System.out.println("[T] Total");
		System.out.println("[P] Pay Money");
		System.out.println("[E] Exit");
	}
	
	public static double printOrder(String choice,double sum){
		int lengthPrice = isPrice.length;
		int Total = 0;
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		if (choice.equalsIgnoreCase("T")){
			System.out.println("\t\tSKE Restaurant");
			System.out.println("Date: " + date + "  Time: " + time);
			System.out.println("+------ Menu --------------+-- Qty --+-- Price --+");
			for (int j = 0; j<lengthPrice; j++){
				if (isPrice[j]*order[j] != 0){
				System.out.printf("|%-8s\t\t   |\t%d    |\t%7.2f  |\n",isMenu[j],order[j],isPrice[j]*order[j]);
				}
			}
			if (sum >= 1200){
				System.out.println("+------------------------------------+-----------+");
				System.out.println("|Price over 1200 Baht.\t\t\t\t |");
				System.out.println("|You got 5% discount.\t\t\t\t |");
				sum = sum*95/100;
			}
			for (int i = 0; i<order.length; i++){
			Total = Total + order[i];
			}
			System.out.println("+--------------------------+---------+-----------+");
			System.out.printf("|Total\t\t\t   |\t%d    |\t%7.2f  |\n",Total,sum);
			System.out.println("+--------------------------+---------+-----------+");
		}
		return sum;
	}
	
	public static void payMoney(double realPrice){
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
		int choice2 = Integer.parseInt(choice);
		for (int i = 0; i < isPrice.length; i++){
			if (choice2 == i+1){
				order[i] = order[i] + quantity;
				realQuantity[i] = order[i] - realQuantity[i];
				price =  realQuantity[i] * isPrice[i];
				break;
			}
		}
		return price;
	}

	public static String checkInt(String choice){
		String str;
		for (int ch = 1; ch <= isPrice.length; ch++){
			str = Integer.toString(ch);
			if (choice.equals(str)){
				return str;
			}
		}
		return "0";
	}
	
	public static void makeOrder(){
		printMenuList();
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
				payMoney(realPrice);
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
	
	public static void main(String[] args) {
		makeOrder();
	}
}