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

            double dp[][] = new double[spins][spins];

            double p = ((double) zeroes) / ((double) slots);



            for(int i = 0; i < spins; i++) {
                for(int j = spins; j > 0; j--) {
                    dp[i][j] = 0.0;
                }
            }


        }
    }
}
