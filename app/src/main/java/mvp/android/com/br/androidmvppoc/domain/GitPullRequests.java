package mvp.android.com.br.androidmvppoc.domain;

/**
 * Created by rafael.alves on 16/01/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class GitPullRequests implements Serializable {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    @SerializedName("body")
    @Expose
    public String body;
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("html_url")
    @Expose
    public String htmlUrl;
    @SerializedName("updated_at")
    @Expose
    public Date updatedAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
