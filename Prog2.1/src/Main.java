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

            HashMap<String, Node> map = new HashMap<String, Node>();

            for(int k = 0; k < numberOfPapers; k++) {
                String next = input.next();

                String first = next.substring(0, 2);
                String second = next.substring(1, 3);

                Node firstNode = null;
                Node secondNode = null;

                if(map.containsKey(first)) {
                    firstNode = map.get(first);
                }
                else {
                    firstNode = new Node(0,0);
                    map.put(first, firstNode);
                }
                if(map.containsKey(second)) {
                    secondNode = map.get(second);
                }
                else {
                    secondNode = new Node(0,0);
                    map.put(second, secondNode);
                }
                firstNode.outDeg += 1;
                secondNode.inDeg += 1;
            }

            // TODO: Check if graph is euler graph
        }
    }
}


class Node {

    int inDeg;
    int outDeg;

    public Node(int newIn, int newOut) {
        inDeg = newIn;
        outDeg = newOut;
    }

}
