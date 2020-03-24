package com.group.dao;

import javax.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscriptions {

    @EmbeddedId
    SubscriptionsKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subscribers")
    @JoinColumn(name = "subscriberid", referencedColumnName = "user_id")
    UserDao subscriberid;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subscribedTo")
    @JoinColumn(name = "subscribetoid", referencedColumnName = "user_id")
    UserDao subscribetoid;
}
