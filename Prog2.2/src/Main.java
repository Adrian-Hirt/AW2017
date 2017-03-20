import java.util.*;

class Main {

    static boolean error;
    static LinkedList<Friend> red;
    static LinkedList<Friend> blue;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int testCases = input.nextInt();

        for(int i = 0; i < testCases; i++) {
            int numberOfPeople = input.nextInt();
            int numberOfFriendships = input.nextInt();
            int nameOfBestFriend = input.nextInt();


            Friend[] friends = new Friend[numberOfPeople];

            for(int k = 0; k < numberOfPeople; k++) {
                friends[k] = new Friend(k);
            }

            for(int k = 0; k < numberOfFriendships; k++) {
                int firstFriend = input.nextInt();
                int secondFriend = input.nextInt();

                friends[firstFriend].friends.add(friends[secondFriend]);
                friends[secondFriend].friends.add(friends[firstFriend]);
            }

            error = false;
            int color = 1;

            red = new LinkedList<Friend>();
            blue = new LinkedList<Friend>();

            for(int k = 0; k < numberOfPeople; k++) {
                if(friends[k].visited) {

                }
                else {
                    dfs(friends[k], color);
                }
            }

            if(error) {
                System.out.print("no");
            }
            else {
                if(friends[nameOfBestFriend].color == -1) {

                    Collections.sort(red);

                    for(Friend next: red) {
                        System.out.print(next.id + " ");
                    }
                }
                else {
                    Collections.sort(blue);

                    for(Friend next: blue) {
                        System.out.print(next.id + " ");
                    }
                }
            }
            System.out.println();

        }
    }

    public static void dfs(Friend current, int color) {
        current.visited = true;
        current.color = color;

        if(color == -1) {
            red.add(current);
        }
        else {
            blue.add(current);
        }

        color = color * (-1);

        for(Friend friend: current.friends) {
            if(friend.visited) {
                if(friend.color == current.color) {
                    error = true;
                }
            }
            else {
                dfs(friend, color);
            }
        }
    }
}

class Friend implements Comparable<Friend> {
    boolean visited;
    int id;
    LinkedList<Friend> friends;
    int color;

    public Friend(int newId) {
        id = newId;
        color = 0;
        friends = new LinkedList<Friend>();
    }

    public int compareTo(Friend other)
    {
        return(id - other.id);
    }

}
