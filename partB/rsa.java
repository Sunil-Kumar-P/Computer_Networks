import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class rsa {

	static BigInteger p, q, n, phi_n, e, d;
	static SecureRandom secureRandom;
	static int biLength = 128;

	static String encrypt(String msg) {
		return new BigInteger(msg.getBytes()).modPow(e, n).toString();
	}

	static String decrypt(String cipher) {
		BigInteger bi = new BigInteger(cipher).modPow(d, n);
		return new String(bi.toByteArray());
	}

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		secureRandom = new SecureRandom();

		p = BigInteger.probablePrime(biLength, secureRandom);
		q = BigInteger.probablePrime(biLength, secureRandom);

		n = p.multiply(q);
		phi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

		e = BigInteger.probablePrime((biLength / 2), secureRandom);

		while (e.gcd(phi_n).compareTo(BigInteger.ONE) != 0 && e.compareTo(phi_n) < 0) {
			e = e.add(BigInteger.ONE);
		}

		d = e.modInverse(phi_n);

		System.out.println("P assigned as: " + p);
		System.out.println("Q assigned as: " + q);
		System.out.println("N assigned as: " + n);
		System.out.println("PHI_N assigned as: " + phi_n);

		System.out.println("\nEnter Message ");
		String msg = s.nextLine();

		String encryptedMessage = encrypt(msg);
		System.out.println("Encrypted Message: " + encryptedMessage);

		String decryptedMessage = decrypt(encryptedMessage);
		System.out.println("Decrypted Message: " + decryptedMessage);
		s.close();
	}

}
