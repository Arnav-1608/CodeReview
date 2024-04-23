//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package jaime;

public class Post {
    private String postId;
    private String userId;
    private String visibility;

    public Post(String postId, String userId, String visibility) {
        this.setPostId(postId);
        this.setUserId(userId);
        this.setVisibility(visibility);
    }

    public String getPostId() {
        return this.postId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getVisibility() {
        return this.visibility;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setVisibility(String visibility) {
        if (!visibility.equalsIgnoreCase("public") && !visibility.equalsIgnoreCase("friend")) {
            throw new IllegalArgumentException("Visibility must be either 'public' or 'friend'.");
        } else {
            this.visibility = visibility.toLowerCase();
        }
    }

    public String toString() {
        return "#" + this.postId + " by " + this.userId + " (" + this.visibility + ")";
    }
}
