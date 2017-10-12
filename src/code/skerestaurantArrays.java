package code;

import java.util.Scanner;

/**
 * User interface for a menu and ordering system,
 * using console interface.
 * You can delete your order or add by delete its quantity
 * @author Thanaphon Keawjam
 */

public class skerestaurantArrays{
	
	static Scanner sc = new Scanner(System.in);
	
	static String[] isMenu = {"Pizza","Chickens","Coke","Water","Total","Pay Money","Exit"};
	static double[] isPrice = {250,120,45,7};
	static int[] order = {0,0,0,0};
	
	static void printMenuList(){
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
	
	static void printBill(int choice,double sum){
		int lengthPrice = isPrice.length;
		if (choice == 5){
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
			int Total = order[0]+order[1]+order[2]+order[3];
			System.out.println("+--------------------------+---------+-----------+");
			System.out.printf("|Total\t\t\t   |\t%d    |\t%7.2f  |\n",Total,sum);
			System.out.println("+--------------------------+---------+-----------+");
		}
	}
	
	static String printMenu(int j){
		if (j == 0) return isMenu[j];
		if (j == 1) return isMenu[j];
		if (j == 2) return isMenu[j];
		if (j == 3) return isMenu[j];
		return "";
	}
	
	static int printQuantity(int j){
		if (j == 0) return order[j];
		if (j == 1) return order[j];
		if (j == 2) return order[j];
		if (j == 3) return order[j];
		return 0;
	}
	
	static double printPrice(int j){
		if (j == 0) return isPrice[j]*order[j];
		if (j == 1) return isPrice[j]*order[j];
		if (j == 2) return isPrice[j]*order[j];
		if (j == 3) return isPrice[j]*order[j];
		return 0;
	}
	
	static void payMoney(double sum){
		double getMoney;
		do{
			System.out.print("Get money(Baht): ");
			getMoney = sc.nextDouble();
			if (getMoney < sum){
				System.out.println("Not enough money.Try again.");
				System.out.println();
			}
			}
			while (getMoney < sum);
			System.out.printf("Change(Baht): %.2f\n",getMoney-sum);
	}

	static double pizzaMenu(int choice,int quantity){
		double price = 0;
		if (choice == 1){
			order[0] = order[0] + quantity;
			price = order[0]*isPrice[0];
		}
		return price;
	}
	
	static double chickenMenu(int choice,int quantity){
		double price = 0;
		if (choice == 2){
			order[1] = order[1] + quantity;
			price = order[1]*isPrice[1];
		}
		return price;
	}
	
	static double cokeMenu(int choice,int quantity){
		double price = 0;
		if (choice == 3){
			order[2] = order[2] + quantity;
			price = order[2]*isPrice[2];
		}
		return price;
	}
	
	static double waterMenu(int choice,int quantity){
		double price = 0; 
		if (choice == 4){
			order[3] = order[3] + quantity;
			price = order[3]*isPrice[3];
		}
		return price;
	}
	
	static void makeOrder(){
		printMenuList();
		double sum = 0;
		int choice,quantity = 0;
		do {
			System.out.print("Enter your Choice: ");
			choice = sc.nextInt();
			printBill(choice,sum);
			if (choice > isMenu.length){
				System.out.println("Incorrect menu!!\nTry again.");
				continue;
			}else if (choice == isMenu.length){
				System.out.print("==== Thank you ====");
				System.exit(0);
			}else if (choice == isMenu.length-1){
				payMoney(sum);
				System.out.print("==== Thank you ====");
				System.exit(0);
			}else if (choice < isPrice.length+1){
			System.out.print("Enter Quantity: ");
			quantity = sc.nextInt();
			}
			sum = sum+pizzaMenu(choice,quantity)+chickenMenu(choice,quantity)+cokeMenu(choice,quantity)+waterMenu(choice,quantity);
		} while(choice != isMenu.length || choice < isMenu.length);
	}
	
	public static void main(String[] args) {
		makeOrder();
	}
}