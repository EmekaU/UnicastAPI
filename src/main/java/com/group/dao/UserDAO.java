package com.group.dao;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

@Entity
@Table(name = "users")
public class UserDAO {
    @Id @Column(nullable = false) @Max(15)
    private String username;

    @Column(nullable = false) @Max(30)
    private String email;

    @Column(nullable = false) @Max(30)
    private String password;

    @OneToMany(mappedBy = "podcasts", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PodcastDAO> podcastDAOS;

    private Byte[] photo;

    private String token;

    public UserDAO(){ }

    public UserDAO(String username, String email, String password){
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

    public List<PodcastDAO> getPodcastDAOS() { return podcastDAOS; }

    public void addPodcast(PodcastDAO podcastDAO) { this.podcastDAOS.add(podcastDAO); }

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
