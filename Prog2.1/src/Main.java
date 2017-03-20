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
            int numberOfPapers = input.nextInt();
            boolean printYes = false;

            if(numberOfPapers == 0) {
                System.out.println("no");
                continue;
            }
            else if(numberOfPapers == 1) {
                printYes = true;
            }

            Paper[] papers = new Paper[numberOfPapers];

            for(int j = 0; j < numberOfPapers; j++) {
                char[] letters = input.next().toCharArray();
                papers[j] = new Paper(letters[0], letters[1], letters[2]);
            }

            for(int first = 0; first < numberOfPapers; first++) {
                for (int second = first + 1; second < numberOfPapers; second++) {
                    if (papers[first].comesBefore(papers[second])) {
                        papers[first].outDeg += 1;
                        papers[second].inDeg += 1;
                    }

                    if (papers[first].comesAfter(papers[second])) {
                        papers[first].inDeg += 1;
                        papers[second].outDeg += 1;
                    }
                }
            }

            int numberOfStartNodes = 0;
            int numberOfCenterNodes = 0;
            int numberOfEndNodes = 0;

            boolean errorFound = false;

            for(int j = 0; j < numberOfPapers; j++) {
                if((papers[j].inDeg == 0) && (papers[j].outDeg == 0)) {
                    errorFound = true;
                    break;
                }
                else if(papers[j].inDeg == papers[j].outDeg) {
                    numberOfCenterNodes += 1;
                    continue;
                }
                else if(papers[j].inDeg == papers[j].outDeg + 1) {
                    numberOfStartNodes += 1;
                    continue;
                }
                else if(papers[j].inDeg + 1 == papers[j].outDeg) {
                    numberOfEndNodes += 1;
                    continue;
                }
            }

            if(printYes) {
                System.out.println("yes");
            }
            else if(errorFound) {
                System.out.println("no: Error found");
            }
            else if((numberOfStartNodes > 1) || (numberOfEndNodes > 1)) {
                System.out.println("no: too many start / end nodes, startnodes: " + numberOfStartNodes + ", Endnodes: " + numberOfEndNodes);
            }
            else if(numberOfCenterNodes == numberOfPapers) {
                System.out.println("yes");
            }
            else if((numberOfCenterNodes == numberOfPapers - 2) && (numberOfStartNodes == 1) && (numberOfEndNodes == 1)) {
                System.out.println("yes");
            }
            else {
                System.out.println("no: Failed for some reason");
            }
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


    // returns true if the current Zettel can be placed before the other Paper
    public boolean comesBefore(Paper other) {
        if((second == other.first) && (third == other.second)) {
            return true;
        }
        else{
            return false;
        }
    }

    // returns true if the current Zettel can be placed after the other Paper
    public boolean comesAfter(Paper other) {
        if ((first == other.second) && (second == other.third)) {
            return true;
        } else {
            return false;
        }
    }

}
