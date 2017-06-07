import java.util.Scanner;


public class Factorial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner (System.in);
		
		int startNumber = sc.nextInt();
		
			System.out.println (fact(startNumber));
			
			sc.close();
		
	}

	
	public static int fact(int startNumber){
		
		if (startNumber == 1){
			return startNumber;
			
		}
		else {
			return startNumber*fact(startNumber-1);
		}
		
	}
	
	
	
	
}
