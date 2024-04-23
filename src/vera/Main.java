import java.io.*;
import java.util.*;

public class Main {
    static Map<String, User> users = new HashMap<>();
    static Map<String, Post> posts = new HashMap<>();

    //This function loads user data
    public static void loadUserData(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            String username = parts[0];
            String displayName = parts[1];
            String state = parts[2];
            List<String> friends = Arrays.asList(parts[3].replaceAll("[\\[\\]]", "").split(","));
            System.out.println(username + ", " + displayName + ", " + state);
            User user = new User(username, displayName, state, new ArrayList<>(friends));
            users.put(username, user);
        }
        reader.close();
    }

    //This function loads post data
    public static void loadPostData(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            String post_id = parts[0];
            String user_id = parts[1];
            String visibility = parts[2];
            System.out.println(post_id + ", " + user_id + ", " + visibility);
            Post post = new Post(post_id, user_id, visibility);
            posts.put(post_id, post);
        }
        reader.close();
    }

    //This function checks post visibility
    public static void checkPostVisibility(String post_id, String username) {
        Post post = posts.get(post_id);
        User user = users.get(username);
        if (post == null || user == null) {
            System.out.println("Post or user not found.");
            return;
        }
        if (post.getUser_id().equals(username)) {
            System.out.println("Access Permitted");
            return;
        }
        if ("public".equals(post.getVisibility())) {
            System.out.println("Access Permitted");
            return;
        }

        if ("friend".equals(post.getVisibility()) && users.get(post.getUser_id()).getFriends().contains(username)) {
            System.out.println("Access Permitted");
        } else {
            System.out.println("Access Denied");
        }
    }

    //This function retrieves posts
    public static void retrievePosts(String username) {
        List<String> visiblePosts = new ArrayList<>();
        User user = users.get(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        for (Post post : posts.values()) {
            if (post.getUser_id().equals(username) || "public".equals(post.getVisibility())) {
                visiblePosts.add(post.getPost_id());
            } else if ("friend".equals(post.getVisibility())) {
                User postOwner = users.get(post.getUser_id());
                if (postOwner != null && postOwner.getFriends().contains(username)) {
                    visiblePosts.add(post.getPost_id());
                }
            }
        }
        System.out.println(username + "'s posts: " + String.join(", ", visiblePosts));
    }
    //This function searches for users by location
    public static void searchUsersByLocation(String state) {
        List<String> matchedUsers = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getState().equals(state)) {
                matchedUsers.add(user.getDisplayName());
            }
        }
        System.out.println("Users in " + state + ": " + String.join(", ", matchedUsers));
    }


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("=========Welcome=========");
            System.out.println("1. Load input data.");
            System.out.println("2. Check visibility.");
            System.out.println("3. Retrieve posts.");
            System.out.println("4. Search users by location.");
            System.out.println("5. Exit");
            System.out.println("=========================");
            System.out.print("Please enter the number of your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Load input data
                    System.out.println("Please enter path to user info file:");
                    String userFilePath = scanner.next();
                    System.out.println("Please enter path to post info file:");
                    String postFilePath = scanner.next();
                    try {
                        loadUserData(userFilePath);
                        loadPostData(postFilePath);
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;
                case 2:
                    // Check visibility
                    System.out.println("Please enter a post id: ");
                    String postId = scanner.next();

                    System.out.println("Please enter a username: ");
                    String username1 = scanner.next();
                    checkPostVisibility(postId, username1);

                    break;
                case 3:
                    // Retrieve posts
                    System.out.println("Please enter a username to retrieve the user's posts: ");
                    String username2 = scanner.next();
                    retrievePosts(username2);
                    break;
                case 4:
                    // Search users by location
                    System.out.println("Please enter a state name: ");
                    String state = scanner.next();
                    searchUsersByLocation(state);
                    break;
                case 5:
                    // Exit
                    System.out.println("Exiting the program.");
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter a number between 1 and 5.");
                    break;
            }
            System.out.println();
        }


        scanner.close();
    }
}