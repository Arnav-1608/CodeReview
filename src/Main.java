import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args)throws IOException {
        Scanner sc = new Scanner(System.in);
        int choice;
        List<Users> allUsers = null;
        List<Posts> allPosts = null;
        do {
            System.out.println("Select :\n1: Load Input Data\n2: Check Visibility\n3: Retrieve Posts\n4: Search Users By Location\n5: Exit");
            System.out.println("Please Enter your selection ");
            choice = sc.nextInt();
            String clean = sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Please Enter Users File Path");
                    String fileUsers = sc.nextLine();
                    allUsers = readUsersFromFile(fileUsers);
                    System.out.println("Please Enter Posts File Path");
                    String filePosts = sc.nextLine();
                    allPosts = readPostsFromFile(filePosts);
                    break;

                case 2:
                    String postOwnerUserName = null;
                    int flag = 0;
                    System.out.println("\nPlease Provide POST ID : ");
                    String postId = sc.nextLine();
                    System.out.println("\nPlease Enter your USERNAME : ");
                    String userName = sc.nextLine();
                    for (Posts post : allPosts) {
                        if (post.getPostId().equals(postId)) {
                            if(post.getUserId().equals(userName)){
                                flag = 1;
                            }
                            else {
                                if (post.getVisibility().equals("public")) {
                                    flag = 1;
                                } else {
                                    postOwnerUserName = post.getUserId();
                                }
                            }
                            break;
                        }
                    }
                    if (flag == 1) {
                        System.out.println("Access Permitted");
                    }
                    else{
                        for(Users user : allUsers){
                            if(user.getUsername().equals(postOwnerUserName)){
                                List<String> friends = user.getFriends();
                                for(String friend : friends){
                                    if(friend.equals(userName)){
                                        flag = 1;
                                        System.out.println("Access Permitted");
                                        break;
                                    }

                                }
                                break;
                            }
                        }
                    }
                    if (flag == 0) {
                        System.out.println("Access Denied");
                    }
                    break;

                case 3:
                    List<String> friends = new ArrayList<>();
                    List<String> viewablePost = new ArrayList<>();
                    System.out.println("\nPlease Enter your USERNAME : ");
                    userName = sc.nextLine();
                    for(Users user : allUsers){
                        if(user.getUsername().equals(userName)){
                            friends = user.getFriends();
                            break;
                        }
                    }
                    for(Posts post : allPosts){
                        if(post.getVisibility().equals("public")){
                            viewablePost.add(post.getPostId());
                        }
                        if(post.getUserId().equals(userName)){
                            if(!viewablePost.contains(post.getPostId())) {
                                viewablePost.add(post.getPostId());
                            }
                        }
                        else {
                            for (String friend : friends) {
                                if (post.getUserId().equals(friend)) {
                                    if(!viewablePost.contains(post.getPostId())) {
                                        viewablePost.add(post.getPostId());
                                    }
                                }
                            }
                        }
                    }
                    System.out.print("Viewable Post: ");
                    for(String post : viewablePost){

                        System.out.print(post+" ");
                    }
                    System.out.println();

                    break;
                case 4:
                    System.out.println("Enter the location");
                    String location = sc.next();
                    boolean statePresent = false;
                    List<String> usersByLocation = new ArrayList<>();
                    for(Users user : allUsers){
                        if(user.getState().equalsIgnoreCase(location)){
                            statePresent = true;
                            usersByLocation.add(user.getDisplayName());
                        }
                    }
                    if(statePresent == false) {
                        System.out.println("No Users from the State " + location);
                        break;
                    }
                    System.out.println("Users from the State "+location+": ");
                    for (String name:usersByLocation){
                        System.out.println(name+" ");
                    }
                    System.out.println();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong Selection");

            }
        }while(true);
    }
    private static List<Users> readUsersFromFile(String filename){
        List<Users> users = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String username = parts[0];
                    String displayName = parts[1];
                    String state = parts[2];
                    String friendsString = parts[3].substring(1, parts[3].length() - 1);
                    String[] friendsArray = friendsString.split(",");
                    List<String> friendsList = new ArrayList<>();
                    for (String friend : friendsArray) {
                        friendsList.add(friend.trim());
                    }
                    users.add(new Users(username, displayName, state, friendsList));
                } else {
                    System.out.println("Invalid user line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Users from File");
            System.exit(0);
        }

        return users;
    }
    private static List<Posts> readPostsFromFile(String filename){
        List<Posts> posts = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    Posts post = new Posts(parts[0], parts[1], parts[2]);
                    posts.add(post);
                } else {
                    System.out.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Posts from File");
            System.exit(0);
        }
        return posts;

    }

}


