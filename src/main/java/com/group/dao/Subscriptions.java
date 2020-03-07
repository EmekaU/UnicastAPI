package com.group.dao;

import javax.persistence.*;

@Entity
public class Subscriptions {

    @EmbeddedId
    SubscriptionsKey id;

    @ManyToOne
    @MapsId("subscribers")
    @JoinColumn(name = "user_id")
    UserDAO subscriber;

    @ManyToOne
    @MapsId("subscribedTo")
    @JoinColumn(name = "user_id")
    UserDAO subscribedTo;
}
