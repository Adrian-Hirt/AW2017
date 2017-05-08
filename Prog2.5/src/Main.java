import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        for(int j = 0; j < testCases; j++) {
            int d = input.nextInt();
            int n = input.nextInt();

            int a[][] = new int[n][d];
            double b[] = new double[n];

            for(int k = 0; k < n; k++) {
                for(int i = 0; i < d; i++) {
                    a[k][i] = input.nextInt();
                }
                b[k] = input.nextInt();
            }

            double v = input.nextDouble();

            Random rndm = new Random();

            double x[] = new double[d];

            int outside = 0;

            for(int k = 0; k < 500000; k++) {
                for(int i = 0; i < d; i++) {
                    x[i] = rndm.nextDouble();
                }
                for(int i = 0; i < n; i++) {
                    if(calculateInequality(d, x, a[i], b[i])) {
                        continue;
                    }
                    else {
                        outside += 1;
                        break;
                    }
                }
            }

            int inside = 500000 - outside;
            double percentage = (inside / 500000.0);



            String answer = (((percentage >= 0.99 * v) && (percentage <= 1.01 * v)) ? "yes" : "no");

            System.out.println(answer);

        }
    }

    public static boolean calculateInequality(int d, double[] x, int[] a, double b) {
        double left = 0.0;
        for(int i = 0; i < d; i++) {
            left += (a[i] * x[i]);
        }

        return (left <= b);
    }
}
