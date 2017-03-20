import java.util.*;

class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        for(int i = 0; i < testCases; i++) {
            int numberOfPapers = input.nextInt();
            boolean printYes = false;

            if(numberOfPapers == 0) {
                System.out.println("no");
                continue;
            }
            else if(numberOfPapers == 1) {
                System.out.println("no");
                String throwAway = input.nextLine();
                continue;
            }



        }
    }
}
