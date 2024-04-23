//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package jaime;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileReader {
    public HashMap<String, User> loadUserProfiles(String filePath) throws FileNotFoundException, IOException {
        HashMap<String, User> userProfiles = new HashMap<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length >= 3) {
                String username = parts[0];
                String email = parts[1];
                String friendsList = parts[2];
                if (friendsList.startsWith("[") && friendsList.endsWith("]")) {
                    lines.add(line);
                    System.err.println("Invalid user data format SKIPPED");
                } else if (userProfiles.containsKey(username)) {
                    System.err.println("Duplicate username found: " + username + " DELETED");
                } else {
                    lines.add(line);
                    User user = new User(username, email, friendsList);
                    userProfiles.put(username, user);
                }
            } else {
                lines.add(line);
                System.err.println("Doesn't meet the first 3 required user data SKIPPED");
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String updatedLine : lines) {
            writer.write(updatedLine);
            writer.newLine();
        }
        writer.close();
        scanner.close();
        linkFriends(filePath, userProfiles);
        scanner.close();
        return userProfiles;
    }

    public void linkFriends(String filePath, HashMap<String, User> userProfiles) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length == 4) {
                String username = parts[0];
                String friendsString = parts[3];
                User user = userProfiles.get(username);
                if (user != null && friendsString.startsWith("[") && friendsString.endsWith("]")) {
                    friendsString = friendsString.substring(1, friendsString.length() - 1);
                    String[] friends = friendsString.split(",");
                    if (friends.length == 0) {
                        System.err.println("No registered friends for " + username);
                    } else {
                        for (String friend : friends) {
                            User friendUser = userProfiles.get(friend);
                            if (friendUser != null) {
                                user.addFriend(friendUser);
                            }
                        }
                    }
                }
            } else {
                System.err.println("No registered friends for " + parts[0]);
            }
        }

        scanner.close();
    }

    public HashMap<String, Post> readPostsFromFile(String filePath) throws FileNotFoundException {
        HashMap<String, Post> posts = new HashMap<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length >= 3) {
                String postId = parts[0];
                String author = parts[1];
                String content = parts[2];
                Post post = new Post(postId, author, content);
                posts.put(postId, post);
            }
        }

        scanner.close();
        return posts;
    }
}
