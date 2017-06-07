import java.util.Scanner;


public class Fibonacci {

	public static void main(String[] args){
		Scanner sc = new Scanner (System.in);
		int fiboNumber = sc.nextInt();
		sc.close();
		System.out.println (fibo(fiboNumber));
		
	}
	
	public static int  fibo (int fiboNumber){
		if (fiboNumber==0){
			return fiboNumber;
		
		}
		else if (fiboNumber == 1 ){
			return fiboNumber;
		}
		
		else {
			return fibo(fiboNumber-1)+fibo(fiboNumber-2);
		}
	}
	
}
