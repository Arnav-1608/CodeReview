public class Post {
    String post_id;
    String user_id;
    String visibility;

    public Post(String post_id, String user_id, String visibility) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.visibility = visibility;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + post_id +
                ", user_id=" + user_id +
                ", visibility='" + visibility + '\'' +
                '}';
    }
}
