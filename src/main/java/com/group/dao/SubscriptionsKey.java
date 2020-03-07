package com.group.dao;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SubscriptionsKey implements Serializable {

    @Column(name = "subscribers")
    Long subscriberId;

    @Column(name = "subscribedTo")
    Long subscribeToId;
}
