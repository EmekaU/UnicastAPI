package com.group.dao;

import javax.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscriptions {

    @EmbeddedId
    SubscriptionsKey id;

    @ManyToOne
    @MapsId("subscribers")
    @JoinColumn(name = "user_id")
    UserDao subscriber;

    @ManyToOne
    @MapsId("subscribedTo")
    @JoinColumn(name = "user_id")
    UserDao subscribedTo;
}
