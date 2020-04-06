package com.group.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subscriptions")
public class Subscriptions implements Serializable {

//    public Subscriptions(){}

    @EmbeddedId
    SubscriptionsKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subscribers")
    @JoinColumn(name = "subscriberid", referencedColumnName = "username")
    UserDao subscriberid;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subscribedTo")
    @JoinColumn(name = "subscribetoid", referencedColumnName = "username")
    UserDao subscribetoid;


    public SubscriptionsKey getId() {
        return id;
    }

    public void setId(SubscriptionsKey id) {
        this.id = id;
    }

}
