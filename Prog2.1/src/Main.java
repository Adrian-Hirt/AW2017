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

            int numberOfPapers = input.nextInt();

            if(numberOfPapers == 0) {
                System.out.println("no");
                continue;
            }
            else if(numberOfPapers == 1) {
                System.out.println("yes");
                continue;
            }

            Paper[] papers = new Paper[numberOfPapers];

            for(int j = 0; j < numberOfPapers; j++) {
                char[] letters = input.next().toCharArray();
                papers[j] = new Paper(letters[0], letters[1], letters[2]);
            }

            for(int first = 0; first < numberOfPapers; first++) {
                for(int second = first + 1; second < numberOfPapers; second++) {
                    if(papers[first].comesBefore(papers[second])) {
                        papers[first].outDeg += 1;
                        papers[second].inDeg += 1;
                    }

                    if(papers[first].comesAfter(papers[second])) {
                        papers[first].inDeg += 1;
                        papers[second].outDeg += 1;
                    }
                }
            }



            // TODO: Prüfen ob der Graph eulersch ist, falls ja: Print "yes" sonst print "no"


            System.out.println("maybe");

        }
    }
}

class Paper {
    char first;
    char second;
    char third;

    int inDeg;
    int outDeg;

    public Paper(char newFirst, char newSecond, char newThird) {
        first = newFirst;
        second = newSecond;
        third = newThird;
        inDeg = 0;
        outDeg = 0;
    }


    // returns true if the current Zettel can be placed before the other Zettel
    public boolean comesBefore(Paper other) {
        if((second == other.first) && (third == other.second))
            return true;
        else
            return false;
    }

    // returns true if the current Zettel can be placed after the other Zettel
    public boolean comesAfter(Paper other) {
        if((first == other.second) && (second == other.third))
            return true;
        else
            return false;
    }


}
