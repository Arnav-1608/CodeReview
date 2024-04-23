//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package jaime;

import java.util.HashMap;

public class User {
    private String username;
    private String displayName;
    private String state;

    // HashMap<String, User> makes it easier to not only have immediate access to the user via "Username" but also their whole profile
    private HashMap<String, User> friends = new HashMap<>();

    public User(String username, String displayName, String state) {
        this.setUsername(username);
        this.setDisplayName(displayName);
        this.setState(state);
    }

    // Used to parse the .txt data
    public void addFriend(User friend) {
        if (friend == null) {
            throw new IllegalArgumentException("Friend cannot be null");
        } else {
            this.friends.put(friend.getUsername(), friend);
        }
    }

    public String getUsername() {
        return this.username;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getState() {
        return this.state;
    }

    public HashMap<String, User> getFriends() {
        return this.friends;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setState(String stateAbbr) {
        if (!States.isValidState(stateAbbr)) {
            throw new IllegalArgumentException("Invalid state abbreviation");
        } else {
            this.state = stateAbbr.toUpperCase();
        }
    }

    public void setFriends(HashMap<String, User> friends) {
        this.friends = friends;
    }

    public String toString() {
        String result = "@" + this.username;
        if (this.displayName != null) {
            result += " / " + this.displayName;
        } else {
            result += " / No Display Name";
        }

        if (this.state != null) {
            result += " / from " + this.state;
        } else {
            result += " / State Unknown";
        }

        if (this.friends != null) {
            result += " [Followers: " + this.friends.size() + "]";
        } else {
            result += " [Followers: 0]";
        }

        return result;
    }
}
