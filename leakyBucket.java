import java.util.Scanner;
import java.util.function.IntPredicate;


public class leakyBucket {

	
	public static void main(String[] args) {
		int in_size, bb_size, out_size, i;
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter the no of inputs:");
		in_size = s.nextInt();
		System.out.println("Enter the size of Bucket");
		bb_size = s.nextInt();
		System.out.println("Enter the size of outputs");
		out_size =s.nextInt();
		
		int [] inputArray = new int[in_size];
		System.out.println("Enter the inputs:");
		for(i=0;i<in_size;i++)
			inputArray[i] = s.nextInt();
		
		System.out.print("Input_rate\tBefore_Bucket\tStatus\tafter_Bucket\n");
		int ib = bb_size;
		int res = 0;
		for(i=0;i<in_size;i++){
			
			ib = res;
			
			System.out.print(inputArray[i]+"\t\t");
			
			System.out.print(ib+"\t\t");
			
			if(inputArray[i] + ib <= bb_size)
				System.out.print("Accept\t\t");
			else
				System.out.print("Reject\t\t");
			
			
			res = ib-out_size;
			if (res>0){
				System.out.print(res+"\n");
			}else{
				res = 0;
				System.out.print(res+"\n");
			}
			
			
		}
		

	}

}
