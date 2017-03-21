import java.util.*;

class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        for(int i = 0; i < testCases; i++) {
            int numberOfPapers = input.nextInt();

            if(numberOfPapers == 0) {
                System.out.println("no");
                continue;
            }
            else if(numberOfPapers == 1) {
                String throwAway = input.next();
                System.out.println("yes");
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

                firstNode.neighbors.add(secondNode);
                secondNode.neighbors.add(firstNode);


            }

            int startNodes = 0;
            int centerNodes = 0;
            int endNodes = 0;
            boolean hasError = false;

            int totalNodes = 0;

            Node yolo = null;
            for(Map.Entry<String, Node> entry : map.entrySet()) {
                // System.out.println(entry.getKey() + " " + entry.getValue());
                Node current = entry.getValue();
                yolo = current;

                totalNodes += 1;

                if(current.inDeg == current.outDeg) {
                    centerNodes += 1;
                }
                else if(current.inDeg == current.outDeg + 1) {
                    endNodes += 1;
                }
                else if(current.inDeg + 1 == current.outDeg) {
                    startNodes += 1;
                }
                else {
                    hasError = true;
                }
            }

            if(hasError) {
                System.out.println("no");
                continue;
            }
            else if((startNodes == 0) && (endNodes == 0)) {
                if(centerNodes != totalNodes) {
                    hasError = true;
                }
            }
            else if((startNodes == 1) && (endNodes == 1) && (centerNodes == totalNodes - 2)) {
               // do nothing yolo
            }
            else {
                hasError = true;
            }

            if(hasError) {
                System.out.println("no");
                continue;
            }

            Stack<Node> stack = new Stack<Node>();


            stack.push(yolo);
            int counter = 0;

            while(!stack.empty()) {
                Node currentNode = stack.pop();
                if(!currentNode.visited) {
                    currentNode.visited = true;
                    counter++;
                    for(Node child: currentNode.neighbors) {
                        if(!child.visited) {
                            stack.push(child);
                        }
                    }
                }
            }

            System.out.println(counter == map.size() ? "yes" : "no");

        }
    }
}


class Node {

    int inDeg;
    int outDeg;
    boolean visited;

    LinkedList<Node> neighbors;

    public Node(int newIn, int newOut) {
        inDeg = newIn;
        outDeg = newOut;
        neighbors = new LinkedList<Node>();
        visited = false;
    }

    public String toString() {
      return "Indeg: " + inDeg + ", Outdeg:" + outDeg;
    }

}
