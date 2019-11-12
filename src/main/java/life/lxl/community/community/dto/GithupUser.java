package life.lxl.community.community.dto;

public class GithupUser {
    private String name;
    private long id;
    private String bio;
    private  String avatarUrl;

    public String getAvatar_url() {
        return avatarUrl;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatarUrl = avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
