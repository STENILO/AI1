import java.util.Scanner;


public class GCD {

	
	public static void main( String [] args){
		Scanner sc =new Scanner(System.in);
		System.out.println("Insert first number");
		int firstNumber=sc.nextInt();
		System.out.println("Insert second number");
		int secondNumber=sc.nextInt();
		
		sc.close();
		
		System.out.println(gcd(firstNumber, secondNumber));
	}
		
	public static long gcd (int firstNumber, int secondNumber){
		
		if (secondNumber==0){
			return firstNumber;
		}
		else {
			return gcd(secondNumber, firstNumber%secondNumber);
		}
	}
}
