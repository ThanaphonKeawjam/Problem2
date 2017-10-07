package code;

import java.util.Scanner;

public class skerestaurantArrays{
	
	static Scanner sc = new Scanner(System.in);
	
	static int quanPizza,quanChicken,quanCoke,quanWater;
	static double sum = 0,pricePizza,priceChicken,priceCoke,priceWater;
	static int pizza = 0,chicken = 0,coke = 0,water = 0;
	static int count1 = 0,count2 = 0,count3 = 0,count4 = 0;
	static String checkbill = "";
	
	static String[] isMenu = {"Pizza","Chickens","Coke","Water","Total","Reset Menu","Exit"};
	static double[] isPrice = {250,120,45,7};
	static int lengthPrice = isPrice.length;
	
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
	
	static void printBill(int choice,int quantity,double sum){
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
			int Total = quanPizza+quanChicken+quanCoke+quanWater;
			System.out.println("+--------------------------+---------+-----------+");
			System.out.printf("|Total\t\t\t   |\t%d    |\t%7.2f  |\n",Total,sum);
			System.out.println("+--------------------------+---------+-----------+");
			checkBill(checkbill,sum);
			System.out.println();
		}
	}
	
	static String printMenu(int j){
		if (j == 0 && pizza > 0) return isMenu[j];
		if (j == 1 && chicken > 0) return isMenu[j];
		if (j == 2 && coke > 0) return isMenu[j];
		if (j == 3 && water > 0) return isMenu[j];
		return "";
	}
	
	static int printQuantity(int j){
		if (j == 0 && pizza > 0) return quanPizza;
		if (j == 1 && chicken > 0) return quanChicken;
		if (j == 2 && coke > 0) return quanCoke;
		if (j == 3 && water > 0) return quanWater;
		return 0;
	}
	
	static double printPrice(int j){
		if (j == 0 && pizza > 0) return isPrice[j]*quanPizza;
		if (j == 1 && chicken > 0) return isPrice[j]*quanChicken;
		if (j == 2 && coke > 0) return isPrice[j]*quanCoke;
		if (j == 3 && water > 0) return isPrice[j]*quanWater;
		return 0;
	}
	
	static void checkBill(String checkbill, double sum){
		double getmoney;
		System.out.print("Do you want to check bill?(y/n) ");
		checkbill = sc.next();
		if (checkbill.equals("y")){
			do{
			System.out.print("Get money(Baht): ");
			getmoney = sc.nextDouble();
			if (getmoney < sum){
				System.out.println("Not enough money.Try again.");
				System.out.println();
			}
			}
			while (getmoney < sum);
			System.out.printf("Change(Baht): %.2f\n",getmoney-sum);
		} if (!checkbill.equals("y") && !checkbill.equals("n")){
			System.out.println("Wrong word!!");
			System.out.println();
			checkBill(checkbill,sum);
		}
	}

	static double pizzaMenu(int choice,int quantity){
		if (choice == 1){
			count1 = count1+quantity;
			pricePizza = count1*250;
			quanPizza = count1;
			pizza++;
		}
		return pricePizza;
	}
	
	static double chickenMenu(int choice,int quantity){
		if (choice == 2){
			count2 = count2+quantity;
			priceChicken = count2*120;
			quanChicken = count2;
			chicken++;
		}
		return priceChicken;
	}
	
	static double cokeMenu(int choice,int quantity){
		if (choice == 3){
			count3 = count3+quantity;
			priceCoke = count3*45;
			quanCoke = count3;
			coke++;
		}
		return priceCoke;
	}
	
	static double waterMenu(int choice,int quantity){
		if (choice == 4){
			count4 = count4+quantity;
			priceWater = count4*7;
			quanWater = count4;
			water++;
		}
		return priceWater;
	}
	
	static void resetMenu(int menu){
		if (menu == 6){
		sum = 0;
		count1 = 0;count2 = 0;count3 = 0;count4 = 0;
		coke = 0;chicken = 0;coke = 0;water = 0;
		quanPizza = 0;quanChicken = 0;quanCoke = 0;quanWater = 0;
		pricePizza = 0;priceChicken = 0;priceCoke = 0;priceWater = 0;
		System.out.println("Reset menu!!!!");
		System.out.println();
		}
	}
	
	static void makeOrder(){
		printMenuList();
		int choice,quantity = 0;
		do {
			do {
				do {
		System.out.print("Enter your Choice: ");
		choice = sc.nextInt();
		resetMenu(choice);
		if (choice > 7){
			System.out.println("Incorrect menu!!\nTry again.");
			System.out.println();
		}
				}
				while(choice > 7 || choice == 6);
		printBill(choice,quantity,sum);
			}
			while (choice == 5);
		if (choice == 7){
			System.out.print("==== Thank you ====");
			break;
		}
		System.out.print("Enter Quantity: ");
		quantity = sc.nextInt();
		sum = pizzaMenu(choice,quantity)+chickenMenu(choice,quantity)+cokeMenu(choice,quantity)+waterMenu(choice,quantity);
		System.out.println();
		}
		while (choice != 7 || choice < 7);
	}
	
	public static void main(String[] args) {
		makeOrder();
	}
}