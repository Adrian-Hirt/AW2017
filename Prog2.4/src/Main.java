import java.math.BigInteger;
import java.util.*;

class Main {

    static BigInteger one = new BigInteger("1");
    static BigInteger two = new BigInteger("2");
    static BigInteger tree = new BigInteger("3");


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        long testCases = input.nextLong();

        for(long i = 0; i < testCases; i++) {
            BigInteger number = input.nextBigInteger();

            if(number.equals(one)) {
                System.out.println("no");
            }
            else if(number.equals(two) || number.equals(tree)) {
                System.out.println("yes");
            }
            else if (checkIfPrime(number))
                System.out.println("yes");
            else
                System.out.println("no");
        }

    }

    public static boolean millerRabinTest(BigInteger number, Random r) {
        BigInteger a = BigInteger.ZERO;


        // Make a new random BigInteger to test the number against that is smaller than number
        while (a.compareTo(one) <= 0) {
            a = new BigInteger(number.bitLength() - 1, r);
        }

        // Make new BigInteger n that is 1 smaller than number
        BigInteger n = number.subtract(BigInteger.ONE);

        BigInteger d = number.subtract(BigInteger.ONE);

        int k = 0;

        while(d.mod(two).equals(BigInteger.ZERO)) {
            d = d.divide(two);
            k += 1;
        }

        // calculate x = (a^d) % number
        BigInteger x = a.modPow(d, number);

        // if x == 1 or x == number - 1 return true because number is a prime
        if ((x.equals(BigInteger.ONE)) || (x.equals(n))) {
            //p("1 ");
            return true;
        }

        // loop k-1 times
        for (int j = 0; j < k; j++) {

            // x = (x^2) % number
            x = x.modPow(two, number);

            // if x == 1 then x is no prime
            if (x.equals(BigInteger.ONE)) {
                //p("2 ");
                return false;
            }

            // if x == number - 1 it is a prime
            if (x.equals(n)) {
                //p("3 ");
                return true;
            }
        }
        // no prime
        //p("4 ");
        return false;

    }

    public static boolean checkIfPrime(BigInteger number) {
        Random r = new Random();

        // Even number, cannot be a prime, return false
        if(number.mod(two).equals(BigInteger.ZERO)) {
            //p("One ");
            return false;
        }

        for(int i = 0; i < 100; i++) {
            if(!millerRabinTest(number, r)) {
                //p("two ");
                return false;
            }
        }

        return true;
    }

    public static void p(Object o) {
        System.out.println(o.toString());
    }
}
