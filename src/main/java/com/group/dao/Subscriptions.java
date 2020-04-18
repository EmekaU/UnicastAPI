package com.group.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subscriptions")
public class Subscriptions implements Serializable {

    @EmbeddedId
    SubscriptionsKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subscribers")
    @JoinColumn(name = "subscriberid", referencedColumnName = "username")
    @JsonBackReference
    UserDao subscriberid;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subscribedTo")
    @JoinColumn(name = "subscribetoid", referencedColumnName = "username")
    @JsonBackReference
    UserDao subscribetoid;

    public SubscriptionsKey getId() {
        return id;
    }

    public void setId(SubscriptionsKey id) {
        this.id = id;
    }

}
