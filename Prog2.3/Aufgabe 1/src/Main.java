import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

class Main {

    static Datapoint dp[][][] = new Datapoint[101][101][101];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        for(int i = 1; i < 101; i++) {
            for(int j = 1; j < 101; j++) {

                dp[0][i][j] = new Datapoint(0.0, 0.0, 1.0);
                dp[i][0][j] = new Datapoint(1.0, 0.0, 0.0);
                dp[i][j][0] = new Datapoint(0.0, 1.0, 0.0);
            }

            dp[i][0][0] = new Datapoint(1.0, 0.0, 0.0);
            dp[0][i][0] = new Datapoint(0.0, 1.0, 0.0);
            dp[0][0][i] = new Datapoint(0.0, 0.0, 1.0);
        }

        int testCases = input.nextInt();

        for(int i = 0; i < testCases; i++) {
            int numberOfBears = input.nextInt();
            int numberOfHunters = input.nextInt();
            int numberOfNinjas = input.nextInt();

            calculate(numberOfBears, numberOfHunters, numberOfNinjas);

            Datapoint result = dp[numberOfBears][numberOfHunters][numberOfNinjas];

            DecimalFormat df = new DecimalFormat("0.0######");
            df.setRoundingMode(RoundingMode.HALF_DOWN);

            System.out.print(df.format(result.bearsWin) + " " + df.format(result.huntersWin) + " " + df.format(result.ninjasWin));
            System.out.println();
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

        double bears = calculateProbability(b, h, n);
        double hunters = calculateProbability(h, n, b);
        double ninjas = calculateProbability(n, b, h);

        double pB = 0.0;
        double pH = 0.0;
        double pN = 0.0;

        pB += bears * dp[b - 1][h][n].bearsWin;
        pB += hunters * dp[b][h - 1][n].bearsWin;
        pB += ninjas * dp[b][h][n - 1].bearsWin;

        pH += bears * dp[b - 1][h][n].huntersWin;
        pH += hunters * dp[b][h - 1][n].huntersWin;
        pH += ninjas * dp[b][h][n - 1].huntersWin;

        pN += bears * dp[b - 1][h][n].ninjasWin;
        pN += hunters * dp[b][h - 1][n].ninjasWin;
        pN += ninjas * dp[b][h][n - 1].ninjasWin;


        /*System.out.println(bears);
        System.out.println(hunters);
        System.out.println(ninjas);

        System.out.println(pB);
        System.out.println(pH);
        System.out.println(pN);

        System.out.println("XXXXXXXXXXXXXXXXXXXXX");*/

        dp[b][h][n] = new Datapoint(pB, pH, pN);

    }

    public static double calculateProbability(int x, int y, int z) {
        return ((double)(x * y)) / ((double)(x*y) + (x * z) + (y * z));
    }

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

/*class Rational {
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

}*/