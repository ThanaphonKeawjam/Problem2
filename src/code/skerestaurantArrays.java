package code;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * User interface for a menu and ordering system,
 * using console interface.
 * You can delete your order or add by delete its quantity
 * @author Thanaphon Keawjam
 */

public class skerestaurantArrays{
	
	public static Scanner sc = new Scanner(System.in);
	
	// if you want to add other menu, add it before Total menu
	public static String[] isMenu = {"Pizza","Chickens","Coke","Water","Total","Pay Money","Exit"};
	static double[] isPrice = {250.00,120.00,45.00,7.00};
	public static int[] order = {0,0,0,0};
	
	public static void printMenuList(){
		System.out.println("--------- Welcome to SKE Restaurant ---------");
		int index = 0;
		for (int i = 0; i<isMenu.length; i++){
			System.out.printf("%d.) %s",i+1,isMenu[i]);
			if (index < isPrice.length){
			System.out.printf("\t\t%6.2f Baht.",isPrice[i]);
			}
			System.out.println("");
			index++;
		}
	}
	
	public static double printOrder(int choice,double sum){
		int lengthPrice = isPrice.length;
		int Total = 0;
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		if (choice == 5){
			System.out.println("\t\tSKE Restaurant");
			System.out.println("Date: " + date + "  Time: " + time);
			System.out.println("+------ Menu --------------+-- Qty --+-- Price --+");
			for (int j = 0; j<lengthPrice; j++){
				if (printPrice(j) != 0){
				System.out.printf("|%-8s\t\t   |\t%d    |\t%7.2f  |\n",printMenu(j),printQuantity(j),printPrice(j));
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
	
	public static String printMenu(int j){
		if (j == 0) return isMenu[j];
		if (j == 1) return isMenu[j];
		if (j == 2) return isMenu[j];
		if (j == 3) return isMenu[j];
		return "";
	}
	
	public static int printQuantity(int j){
		if (j == 0) return order[j];
		if (j == 1) return order[j];
		if (j == 2) return order[j];
		if (j == 3) return order[j];
		return 0;
	}
	
	public static double printPrice(int j){
		if (j == 0) return isPrice[j]*order[j];
		if (j == 1) return isPrice[j]*order[j];
		if (j == 2) return isPrice[j]*order[j];
		if (j == 3) return isPrice[j]*order[j];
		return 0;
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

	public static double calculatePrice(int choice, int quantity){
		double price = 0;
		switch (choice){
		case 1 : order[0] = order[0] + quantity;
						 price = order[0]*isPrice[0];
						 break;
		case 2 : order[1] = order[1] + quantity;
				 price = order[1]*isPrice[1];
				 break;
		case 3 : order[2] = order[2] + quantity;
				 price = order[2]*isPrice[2];
				 break;
		case 4 : order[3] = order[3] + quantity;
				 price = order[3]*isPrice[3];
		}
		return price;
	}
	
	public static void makeOrder(){
		printMenuList();
		double sum = 0,realPrice = 0;
		int choice,quantity = 0;
		do {
			System.out.print("Enter your Choice: ");
			choice = sc.nextInt();
			realPrice = printOrder(choice,sum);
			if (choice > isMenu.length){
				System.out.println("Incorrect menu!!\nTry again.");
				continue;
			}else if (choice == isMenu.length){
				System.out.print("==== Thank you ====");
				System.exit(0);
			}else if (choice == isMenu.length-1){
				payMoney(realPrice);
				System.out.print("==== Thank you ====");
				System.exit(0);
			}else if (choice < isPrice.length+1){
			System.out.print("Enter Quantity: ");
			quantity = sc.nextInt();
			}
			sum = sum + calculatePrice(choice,quantity);
		} while(choice != isMenu.length || choice < isMenu.length);
	}
	
	public static void main(String[] args) {
		makeOrder();
	}
}