import java.util.Scanner;

public class crc {

	public static void main(String[] args) {
		int data_bits, gen_bits;
		Scanner s = new Scanner(System.in);
		// size of data
		System.out.println("Enter the Number Of Data bits");
		data_bits = s.nextInt();
		// size of
		System.out.println("Enter the number of generator bits");
		gen_bits = s.nextInt();

		int[] data = new int[data_bits];
		int[] generator = new int[gen_bits];

		System.out.println("Enter the data bits");
		for (int i = 0; i < data_bits; i++)
			data[i] = s.nextInt();

		System.out.println("Enter the Generator bits");
		for (int i = 0; i < gen_bits; i++)
			generator[i] = s.nextInt();

		int total_len = data_bits + gen_bits - 1;
		int dataword[] = new int[total_len];
		int dummy[] = new int[total_len];
		int codeword[] = new int[total_len];

		for (int i = 0; i < data_bits; i++)
			dataword[i] = data[i];

		System.out.println("after appending");
		for (int i = 0; i < total_len; i++)
			System.out.println(dataword[i]);

		dummy = dataword;
		codeword = divide(dummy, generator);
		System.out.println("the codeword is ");

		for (int i = 0; i < total_len; i++)
			System.out.println(codeword[i]);

		// receiver
		int recv_data[] = new int[total_len];
		int syndrome[] = new int[total_len];
		int flag = 0;
		System.out.println("Enter the received word data");

		for (int i = 0; i < total_len; i++)
			recv_data[i] = s.nextInt();

		syndrome = divide(recv_data, generator);

		System.out.println("the syndrome is ");

		for (int i = 0; i < total_len; i++)
			System.out.println(syndrome[i]);

		for (int i = data_bits; i < total_len; i++) {
			if (syndrome[i] != 0) {
				flag = 1;
				break;
			}
		}
		if (flag == 0)
			System.out.println("No Error");

		else
			System.out.println("Error");

		s.close();

	}

	private static int[] divide(int[] d, int[] g) {
		int crc[] = new int[d.length];

		for (int i = 0; i < d.length; i++)
			crc[i] = d[i];

		int n = d.length - g.length + 1;

		for (int i = 0; i < n; i++) {
			int msb = crc[i];
			int k = 0;

			for (int j = i; j < i + g.length; j++) {
				if (msb == 0)
					crc[j] = crc[j] ^ 0;
				else
					crc[j] = crc[j] ^ g[k];

				k++;

			}
		}

		for (int i = 0; i < n; i++)
			crc[i] = d[i];

		return crc;
	}

}
