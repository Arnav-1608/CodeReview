import java.util.*;

public class User {
    String username;
    String displayName;
    String state;
    List<String> friends = new ArrayList<>();

    public User(String username, String displayname, String state, ArrayList<String> friends) {
        this.username = username;
        this.displayName = displayname;
        this.state = state;
        this.friends = friends;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "User{ username='" + username + '\'' +
                ", displayname='" + displayName + '\'' +
                ", state='" + state + '\'' +
                ", friends=" + friends +
                '}';
    }
}
