package code;

import java.util.Scanner;

public class skerestaurantImprove {
	
	static int sum = 0,menu,quan,quanpizza,pricepizza,pricechicken,quanchicken,pricecoke,quancoke,pricewater,quanwater;
	static int p = 0,ch = 0,co = 0,wa = 0,quan1 = 0,quan2 = 0,quan3 = 0,quan4 = 0;
	static String checkbill = "";
	
	static Scanner sc = new Scanner(System.in);
	
	//menu//
	static void skeMenu(){
		System.out.println("--------- Welcome to SKE Restaurant ---------");
		System.out.printf("1.) Pizza\t%5d Baht.\n",250);
		System.out.printf("2.) Chickens\t%5d Baht.\n",120);
		System.out.printf("3.) Coke\t%5d Baht.\n",45);
		System.out.printf("4.) Water\t%5d Baht.\n",7);
		System.out.println("5.) Total");
		System.out.println("6.) Reset Menu");
		System.out.println("7.) Exit");
	}
	
	//printbill//
	static void printBill(int menu,int quan,int sum){
		if (menu == 5){
			System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
			if (p > 0){
				System.out.printf("|Pizza\t\t   |\t%d    |\t%5d    |\n",quanpizza,pricepizza);
			}if (ch > 0){
				System.out.printf("|Chickens\t   |\t%d    |\t%5d    |\n",quanchicken,pricechicken);
			}if (co > 0){
				System.out.printf("|Coke\t\t   |\t%d    |\t%5d    |\n",quancoke,pricecoke);
			}if (wa > 0){
				System.out.printf("|Water\t\t   |\t%d    |\t%5d    |\n",quanwater,pricewater);
			}if (sum >= 1200){
				System.out.println("+----------------------------+-----------+");
				System.out.println("|Price over than 1200 Baht.\t\t |");
				System.out.println("|You got 5% discount.\t\t\t |");
				sum = sum*95/100;
			}
			int quanall = quanpizza+quanchicken+quancoke+quanwater;
			System.out.println("+------------------+---------+-----------+");
			System.out.printf("|Total\t\t   |\t%d    |\t%5d    |\n",quanall,sum);
			System.out.println("+------------------+---------+-----------+");
			checkBill(checkbill,sum);
			System.out.println();
		}
	}
	
	//checkbill//
	static void checkBill(String checkbill, int sum){
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
	
	//pizza//
	static int pizzaMenu(int menu,int quan){
		if (menu == 1){
			quan1 = quan1+quan;
			pricepizza = quan1*250;
			quanpizza = quan1;
			p++;
		}
		return pricepizza;
	}
	
	//chicken//
	static int chickenMenu(int menu,int quan){
		if (menu == 2){
			quan2 = quan2+quan;
			pricechicken = quan2*120;
			quanchicken = quan2;
			ch++;
		}
		return pricechicken;
	}
	
	//coke//
	static int cokeMenu(int menu,int quan){
		if (menu == 3){
			quan3 = quan3+quan;
			pricecoke = quan3*45;
			quancoke = quan3;
			co++;
		}
		return pricecoke;
	}
	
	//water//
	static int waterMenu(int menu,int quan){
		if (menu == 4){
			quan4 = quan4+quan;
			pricewater = quan4*7;
			quanwater = quan4;
			wa++;
		}
		return pricewater;
	}
	
	//reset menu//
	static void resetMenu(int menu){
		if (menu == 6){
		sum = 0;
		quan1 = 0;
		quan2 = 0;
		quan3 = 0;
		quan4 = 0;
		co = 0;
		wa = 0;
		ch = 0;
		p = 0;
		quanpizza = 0;
		quanchicken = 0;
		quancoke = 0;
		quanwater = 0;
		pricepizza = 0;
		pricechicken = 0;
		pricecoke = 0;
		pricewater = 0;
		System.out.println("Reset menu!!!!");
		System.out.println();
		}
	}
	
	static void setMenu(){
		do {
			do {
				do {
		System.out.print("Enter your Choice: ");
		menu = sc.nextInt();
		resetMenu(menu);
		if (menu > 7){
			System.out.println("Incorrect menu!!\nTry again.");
			System.out.println();
		}
				}
				while(menu > 7 || menu == 6);
		printBill(menu,quan,sum);
			}
			while (menu == 5);
		if (menu == 7){
			System.out.print("==== Thank you ====");
			break;
		}
		System.out.print("Enter Quantity: ");
		quan = sc.nextInt();
		sum = pizzaMenu(menu,quan)+chickenMenu(menu,quan)+cokeMenu(menu,quan)+waterMenu(menu,quan);
		System.out.println();
		}
		while (menu != 7 || menu < 7);
	}
	
	public static void main(String[] args) {
		skeMenu();
		setMenu();
	}
}