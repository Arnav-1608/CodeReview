package jaime;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] var0) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, User> users = new HashMap<>();
        HashMap<String, Post> posts = new HashMap<>();
        boolean dataLoaded = false;

        int option;
        do {
            printMenu();

            try {
                System.out.print("Enter an option from (1-5): ");
                option = scanner.nextInt();
            } catch (InputMismatchException var13) {
                System.err.println("Invalid input. Please enter a valid option.");
                scanner.nextLine();
                option = 0;
                continue;
            }

            System.out.print("\n");
            switch (option) {
                case 1:
                    System.out.println("Required file types (.txt):\n" +
                                        "- 1. User data file\n" +
                                        "\t* each line contains: <username>;<display_name>;<state>;[<friends_list>]" +
                                        "\n  - 2. Post data file\n" +
                                        "\t* each line contains: <post_id>;<user_id>;<visibility>\n");
                    FileReader fileReader = new FileReader();

                    try {
                        System.out.print("Enter path for user data file: ");
                        String userDataFilePath = scanner.next();

                        users = fileReader.loadUserProfiles(userDataFilePath);

                        System.out.print("Enter path for post data file: ");
                        String postDataFilePath = scanner.next();

                        posts = fileReader.readPostsFromFile(postDataFilePath);

                        System.out.print("\n");
                        System.out.println("Users loaded:");
                        for (User user : users.values()) {
                            System.out.println("  - " + user.toString());
                        }

                        System.out.print("\n");
                        System.out.println("\nPosts loaded:");
                        for (Post post : posts.values()) {
                            System.out.println("  - " + post.toString());
                        }

                        System.out.print("\n");
                        dataLoaded = true;
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    if (!dataLoaded) {
                        System.out.println("Please load input data first (Option 1).\n");
                        break;
                    }

                    System.out.print("Enter the post ID#: ");
                    String postId = scanner.next();

                    if(posts.containsKey(postId)) {
                        Post post = posts.get(postId);

                        System.out.print("Enter username: ");
                        String username = scanner.next();

                        if(!post.getUserId().equals(username)) {
                            if(post.getVisibility().equals("public")) {
                                System.out.println("\nAccess Permitted\n");
                            } else {
                                User user = users.get(post.getUserId());

                                if(user.getFriends().containsKey(username)) {
                                    System.out.println("\nAccess Permitted\n");
                                } else {
                                    System.out.println("\nAccess Denied\n");
                                }
                            }
                        } else {
                            System.out.println("\nThey're the owner of the post\n");
                        }
                    } else {
                        System.err.println(postId + " doesn't exists");
                        break;
                    }

                    break;
                case 3:
                    if (!dataLoaded) {
                        System.out.println("Please load input data first (Option 1).\n");
                        break;
                    }

                    System.out.print("Enter a username: ");
                    String username = scanner.next();

                    if(users.containsKey(username)) {
                        System.out.println("\nCan view the following posts: ");

                        for(Map.Entry<String, Post> entry : posts.entrySet()) {
                            String currPostId = entry.getKey();
                            Post post = entry.getValue();

                            if(!post.getUserId().equals(username)) {
                                if(!post.getVisibility().equals("public")) {
                                    if(users.get(post.getUserId()).getFriends().containsKey(username)) {
                                        System.out.println("  - " + currPostId);
                                    }
                                } else {
                                    System.out.println("  - " + currPostId);
                                }
                            } else {
                                System.out.println("  - " + post.getPostId());
                            }
                        }

                        System.out.print("\n");
                    } else {
                        System.err.println("User with username @" + username + " doesn't exists");
                    }
                    break;
                case 4:
                    if (!dataLoaded) {
                        System.out.println("Please load input data first (Option 1).\n");
                        break;
                    }

                    System.out.print("Enter location: ");
                    String location = scanner.next().toUpperCase();

                    if(States.isValidState(location)) {
                        System.out.println("\nUsers from " + location + ": ");

                        boolean foundUsers = false;

                        for(User user : users.values()) {
                            if(user.getState().equals(location)) {
                                System.out.println("  - " + user.getDisplayName());
                                foundUsers = true;
                            }
                        }

                        if (!foundUsers) {
                            System.out.println("No users found in " + location);
                        }
                    } else {
                        System.err.println("Invalid/Unsupported state abbreviation.");
                    }

                    System.out.print("\n");
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while(option != 5);

        scanner.close();
        System.out.println("Have a good day!");
    }

    public static void printMenu() {
        System.out.println("================================\n"
                         + "============= Menu =============\n"
                         + "================================\n"
                         + "1. Load input data\n"
                         + "2. Check visibility\n"
                         + "3. Retrieve posts\n"
                         + "4. Search users by location\n"
                         + "5. Exit\n"
                         + "================================\n");
    }
}
