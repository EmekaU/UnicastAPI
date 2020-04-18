package com.group.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@SequenceGenerator(name="users_seq", initialValue=1, allocationSize=1)
public class UserDao implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @Column(name = "user_id")
    private long id;

    @Column(nullable = false, unique = true) @Max(30)
    private String username;

    private String bio;

    @Column(nullable = false, unique = true) @Max(30)
    private String email;

    @Column(nullable = false) @Max(30) @Min(8)
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Podcast> podcasts;

    private String photo;

    @OneToMany(mappedBy = "subscriberid", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Subscriptions> subscriptions;

    @OneToMany(mappedBy = "subscribetoid", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Subscriptions> subscribers;

    public UserDao(){ }

    public UserDao(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public long getId() { return id; }

    public void setId(long id) { this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() { return bio; }

    public void setBio(String bio) { this.bio = bio; }

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

    public String getPhoto() { return photo; }

    public void setPhoto(String photo) { this.photo = photo; }

    public List<Podcast> getPodcasts() { return podcasts; }

    public void addPodcast(Podcast podcast) {
        this.podcasts.add(podcast);
        podcast.setCreator(this);
    }

    public void deletePodcast(Podcast podcast){
        podcast.setCreator(null);
        this.podcasts.remove(podcast);
    }

    public Set<Subscriptions> getSubscribers() { return subscribers;}

    public void addSubscriber(Set<Subscriptions> subscribers) { this.subscribers = subscribers; }

    public Set<Subscriptions> getSubscriptions() { return subscriptions; }

    public void addSubscriptions(Set<Subscriptions> subscriptions) { this.subscriptions = subscriptions; }

    @Override
    public String toString() {
        return "UserDao{" +
                "user_id=" + id +
                ", username='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", podcasts=" + podcasts +
                ", photo='" + photo + '\'' +
                ", subscribers=" + subscribers +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
