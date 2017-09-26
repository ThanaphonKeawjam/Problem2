package code;

import java.util.Scanner;

public class skeres {
	
	static int sum = 0,menu,quan,quanpizza,pricepizza,pricechicken,quanchicken,pricecoke,quancoke;
	static int p = 0,ch = 0,co = 0,quan1 = 0,quan2 = 0,quan3 = 0;
	
	static Scanner sc = new Scanner(System.in);
	
	static void ske(){
		System.out.println("--------- Welcome to SKE Restaurant ---------");
		System.out.printf("1.) Pizza\t%5d Baht.\n",250);
		System.out.printf("2.) Chickens\t%5d Baht.\n",120);
		System.out.printf("3.) Coke\t%5d Baht.\n",45);
		System.out.println("4.) Total");
		System.out.println("5.) Exit");
	}
	
	static void printbill(int menu,int quan,int sum){
		if (menu == 4){
			System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
			if (p > 0){
				System.out.printf("|Pizza\t\t   |\t%d    |\t%5d    |\n",quanpizza,pricepizza);
			}if (ch > 0){
				System.out.printf("|Chickens\t   |\t%d    |\t%5d    |\n",quanchicken,pricechicken);
			}if (co > 0){
				System.out.printf("|Coke\t\t   |\t%d    |\t%5d    |\n",quancoke,pricecoke);
			}
			System.out.println("+------------------+---------+-----------+");
			System.out.printf("|Total\t\t\t     |\t%5d\t |\n",sum);
			System.out.println("+----------------------------+-----------+");
			System.out.println();
		}
	}
	
	static int pizza(int menu,int quan){
		if (menu == 1){
			quan1 = quan1+quan;
			pricepizza = quan1*250;
			quanpizza = quan1;
			p++;
		}
		return pricepizza;
	}
	
	static int chicken(int menu,int quan){
		if (menu == 2){
			quan2 = quan2+quan;
			pricechicken = quan2*120;
			quanchicken = quan2;
			ch++;
		}
		return pricechicken;
	}
	
	static int coke(int menu,int quan){
		if (menu == 3){
			quan3 = quan3+quan;
			pricecoke = quan3*45;
			quancoke = quan3;
			co++;
		}
		return pricecoke;
	}
	
	public static void main(String[] args) {
		ske();
		do {
			do {
				do {
		System.out.print("Enter your Choice: ");
		menu = sc.nextInt();
		if (menu > 5){
			System.out.println("Incorrect menu!!\nTry again.");
			System.out.println();
		}
				}
				while(menu > 5);
		printbill(menu,quan,sum);
			}
			while (menu == 4);
		if (menu == 5){
			System.out.print("==== Thank you ====");
			break;
		}
		System.out.print("Enter Quantity: ");
		quan = sc.nextInt();
		sum = pizza(menu,quan)+chicken(menu,quan)+coke(menu,quan);
		System.out.println();
		}
		while (menu != 5 || menu < 5);
	}
}
