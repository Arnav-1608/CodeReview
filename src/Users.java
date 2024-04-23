import java.util.List;

public class Users {
    private String username;
    private String displayName;
    private String state;
    private List<String> friends;

    public Users(String username, String displayName, String state, List<String> friends) {
        this.username = username;
        this.displayName = displayName;
        this.state = state;
        this.friends = friends;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getState() {
        return state;
    }

    public List<String> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", state='" + state + '\'' +
                ", friends=" + friends +
                '}';
    }
}