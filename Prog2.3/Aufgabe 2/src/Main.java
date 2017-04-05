import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        for(int x = 0; x < testCases; x++) {
            int slots = input.nextInt(); // n
            int spins = input.nextInt(); // m
            int desiredWins = input.nextInt(); // k

            int zeroes = 0;
            int other = 0;

            for(int y = 0; y < slots; y++) {
                int current = input.nextInt();
                if(current == 0) {
                    zeroes += 1;
                }
                else {
                    other += 1;
                }
            }

            double dp[][] = new double[101][101];

            for(int i = 0; i < 101; i++) {
                for(int j = 0; j < 101; j++) {
                    dp[i][j] = -1.0;
                }
            }


            double p = ((double) zeroes) / ((double) slots);

            double prob = calculate(0, spins, p, desiredWins, dp);

            DecimalFormat df = new DecimalFormat("0.0######");
            df.setRoundingMode(RoundingMode.HALF_DOWN);
            System.out.println(df.format(prob));

        }
    }

    public static double calculate(int zeroes, int movesLeft, double p,
                                   int desiredWins, double[][] dp) {

        if(zeroes >= desiredWins) {
            return 1.0;
        }
        if(desiredWins - zeroes > movesLeft) {
            return 0.0;
        }

        if(dp[zeroes][movesLeft] != -1.0) {
            return dp[zeroes][movesLeft];
        }

        double first = p * calculate(zeroes + 1, movesLeft - 1, p, desiredWins, dp);
        double second = (1.0 - p) * calculate(0, movesLeft - 1, p, desiredWins, dp);

        dp[zeroes][movesLeft] = first + second;

        return first + second;
    }
}
