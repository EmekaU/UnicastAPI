package com.group.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id @Column(nullable = false) @Max(15)
    private String username;

    @Column(nullable = false) @Max(30)
    private String email;

    @Column(nullable = false) @Max(30)
    private String password;

    @OneToMany(mappedBy = "podcasts", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Podcast> podcasts;

    private Byte[] photo;

    private String token;

    public User(){ }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte[] getPhoto() { return photo; }

    public void setPhoto(Byte[] photo) { this.photo = photo; }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public List<Podcast> getPodcasts() { return podcasts; }

    public void addPodcast(Podcast podcast) { this.podcasts.add(podcast); }

    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
