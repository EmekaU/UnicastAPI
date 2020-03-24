package com.group.dao;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SubscriptionsKey implements Serializable {

    @Column(name = "subscriberid")
    Long subscriberId;

    @Column(name = "subscribetoid")
    Long subscribeToId;
}
