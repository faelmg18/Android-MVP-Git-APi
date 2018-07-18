package mvp.android.com.br.androidmvppoc.domain;

/**
 * Created by rafael.alves on 16/01/2018.
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("login")
    public String login;
    @SerializedName("avatar_url")
    public String avatarUrl;
    @SerializedName("bio")
    public String bio;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
