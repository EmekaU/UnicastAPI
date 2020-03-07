package com.group.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group.model.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@SequenceGenerator(name="users_id_seq", initialValue=1, allocationSize=1)
public class UserDAO {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long user_id;

    @Column(nullable = false, unique = true) @Max(15)
    private String username;

    @Column(nullable = false, unique = true) @Max(30)
    private String email;

    @Column(nullable = false) @Max(30) @Min(8)
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "podcasts", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PodcastDAO> podcastDAOS;

    private Byte[] photo;

    @JsonIgnore
    private String sessionId;

    @OneToMany(mappedBy = "subscriptions", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Subscriptions> subscribers;

    @OneToMany(mappedBy = "subscriptions", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Subscriptions> subscriptions;

    public UserDAO(){ }

    public UserDAO(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public long getUser_id() { return user_id; }

    public void setUser_id(long user_id) { this.user_id = user_id;}

    public String getUsername() {return username;}

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

    public String getSessionId() { return sessionId; }

    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public List<PodcastDAO> getPodcastDAOS() { return podcastDAOS; }

    public void addPodcast(PodcastDAO podcastDAO) { this.podcastDAOS.add(podcastDAO); }

    public Set<Subscriptions> getSubscribers() { return subscribers;}

    public void addSubscriber(Set<Subscriptions> subscribers) { this.subscribers = subscribers; }

    public Set<Subscriptions> getSubscriptions() { return subscriptions; }

    public void addSubscriptions(Set<Subscriptions> subscriptions) { this.subscriptions = subscriptions; }

    @Override
    public String toString() {
        return "UserDAO{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", podcastDAOS=" + podcastDAOS +
                ", photo=" + Arrays.toString(photo) +
                ", sessionId='" + sessionId + '\'' +
                ", subscribers=" + subscribers +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
