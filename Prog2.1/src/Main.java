import java.util.*;

class Main {

    /*
    Idee:
    1) Zettelobjekt dass die 3 Buchstaben sowie in / outdeg speichert
    2) Funktion schreiben die schaut ob man 2 Zettel miteinander verbinden kann
    3) Schauen ob jeder Zettel indeg = outdeg hat
     */


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        for(int i = 0; i < testCases; i++) {
            System.out.println(i);

            int papers = input.nextInt();

            if(papers == 0) {
                System.out.println("no");
                continue;
            }
            else if(papers == 1) {
                System.out.println("yes");
                continue;
            }

            System.out.println("maybe");

        }
    }
}

class Zettel {
    char first;
    char second;
    char third;

    int inDeg;
    int outDeg;

    public Zettel(char newFirst, char newSecond, char newThird) {
        first = newFirst;
        second = newSecond;
        third = newThird;
        inDeg = 0;
        outDeg = 0;
    }


    // returns true if the current Zettel can be placed before the other Zettel
    public boolean comesBefore(Zettel other) {
        if((second == other.first) && (third == other.second))
            return true;
        else
            return false;
    }

    // returns true if the current Zettel can be placed after the other Zettel
    public boolean comesAfter(Zettel other) {
        if((first == other.second) && (second == other.third))
            return true;
        else
            return false;
    }


}