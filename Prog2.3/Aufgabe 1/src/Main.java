import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        // Rational dp[][][] = new Rational[101][101][101];


        for(int i = 0; i < testCases; i++) {
            int numberOfBears = input.nextInt();
            int numberOfHunters = input.nextInt();
            int numberOfNinjas = input.nextInt();

            for(int b = numberOfBears; b >= 0; b--) {
                for(int h = numberOfHunters; h >= 0; h--) {
                    for(int n = numberOfNinjas; n >= 0; n--) {
                        if(dp[b][h][n] != null) {

                        }
                        else {
                           // berechne
                        }
                    }
                }
            }

        }
    }

    /* public static Rational calculateProbability(int x, int y, int z) {
        int counter = x * y;
        int denominator = (x*y) + (x * z) + (y * z);

        return new Rational(counter, denominator);
    } */

}

class Datapoint {
    double bearsWin;
    double huntersWin;
    double ninjasWin;

    public Datapoint(double bears, double hunters, double ninjas) {
        bearsWin = bears;
        huntersWin = hunters;
        ninjasWin = ninjas;
    }

}

/* class Rational {
    private int counter;
    private int denominator;

    public Rational(int c, int d) {
        this.counter = c;
        this.denominator = d;
    }

    public Rational add(Rational other) {
        int common = denominator * other.getDenominator();
        int num1 = counter * other.getDenominator();
        int num2 = other.getCounter() * denominator;
        int sum = num1 + num2;

        return new Rational(sum, common);
    }

    public int getDenominator() {
        return denominator;
    }

    public int getCounter() {
        return counter;
    }

    public Rational multiply(Rational other) {
        int co = counter * other.getCounter();
        int de = denominator * other.getDenominator();

        return new Rational(co, de);
    }

    @Override
    public String toString() {
        return counter + "/" + denominator;
    }

    public void reduce() {
        int common = gcd(Math.abs(counter), Math.abs(denominator));
        counter = counter / common;
        denominator = denominator / common;
    }

    public int gcd(int a, int b) {
        while(a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

}
*/