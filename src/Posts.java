public class Posts {
    private String postId;
    private String userId;
    private String visibility;

    public Posts(String postId, String userId, String visibility) {
        this.postId = postId;
        this.userId = userId;
        this.visibility = visibility;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
