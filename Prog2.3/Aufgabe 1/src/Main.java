import java.util.*;

class Main {

    static Datapoint dp[][][] = new Datapoint[101][101][101];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        for(int i = 1; i < 101; i++) {
            for(int j = 1; j < 101; j++) {
                Rational zero = new Rational(0, 1);
                Rational one = new Rational(1, 1);

                dp[0][i][j] = new Datapoint(zero, zero, one);
                dp[i][0][j] = new Datapoint(one, zero, zero);
                dp[i][j][0] = new Datapoint(zero, one, zero);
            }
        }

        int testCases = input.nextInt();

        for(int i = 0; i < testCases; i++) {
            int numberOfBears = input.nextInt();
            int numberOfHunters = input.nextInt();
            int numberOfNinjas = input.nextInt();

            calculate(numberOfBears, numberOfHunters, numberOfNinjas);

            Datapoint result = dp[numberOfBears][numberOfHunters][numberOfNinjas];

            System.out.print(result.bearsWin + " | " + result.huntersWin + " | " + result.ninjasWin);
            System.out.println();
            System.out.println("----------------------------------");

            /*Rational tree = new Rational(6, 2);
            Rational four = new Rational(4, 1);

            Rational seven = tree.add(four);

            seven.reduce();

            System.out.println(seven); */

        }
    }

    public static void calculate(int b, int h, int n) {
        if(dp[b][h][n] != null) {
            return;
        }
        if(b == 0 || h == 0 || n == 0) {
            return;
        }

        if(dp[b - 1][h][n] == null) {
            calculate(b - 1, h, n);
        }
        if(dp[b][h - 1][n] == null) {
            calculate(b, h - 1, n);
        }
        if(dp[b][h][n - 1] == null) {
            calculate(b, h, n - 1);
        }

        Rational bears = calculateProbability(b, h, n);
        Rational hunters = calculateProbability(h, n, b);
        Rational ninjas = calculateProbability(n, b, h);

        Rational pB = new Rational(0, 1);
        Rational pH = new Rational(0, 1);
        Rational pN = new Rational(0, 1);

        pB.add(bears.multiply(dp[b - 1][h][n].bearsWin));
        pB.add(hunters.multiply(dp[b][h - 1][n].bearsWin));
        pB.add(ninjas.multiply(dp[b][h][n - 1].bearsWin));

        pH.add(bears.multiply(dp[b - 1][h][n].huntersWin));
        pH.add(hunters.multiply(dp[b][h - 1][n].huntersWin));
        pH.add(ninjas.multiply(dp[b][h][n - 1].huntersWin));

        pN.add(bears.multiply(dp[b - 1][h][n].ninjasWin));
        pN.add(hunters.multiply(dp[b][h - 1][n].ninjasWin));
        pN.add(ninjas.multiply(dp[b][h][n - 1].ninjasWin));

        System.out.println(bears);
        System.out.println(hunters);
        System.out.println(ninjas);

        System.out.println(pB);
        System.out.println(pH);
        System.out.println(pN);

        System.out.println("XXXXXXXXXXXXXXXXXXXXX");

        dp[b][h][n] = new Datapoint(pB, pH, pN);

    }

    public static Rational calculateProbability(int x, int y, int z) {
        int counter = x * y;
        int denominator = (x*y) + (x * z) + (y * z);

        return new Rational(counter, denominator);
    }

}

class Datapoint {
    Rational bearsWin;
    Rational huntersWin;
    Rational ninjasWin;

    public Datapoint(Rational bears, Rational hunters, Rational ninjas) {
        bearsWin = bears;
        huntersWin = hunters;
        ninjasWin = ninjas;
    }

}

class Rational {
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

    public double toDouble() {
        return ((double)counter) / ((double)denominator);
    }

}