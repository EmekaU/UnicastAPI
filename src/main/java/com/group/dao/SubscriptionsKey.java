package com.group.dao;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SubscriptionsKey implements Serializable {

    @Column(name = "subscriberid")
    private String subscriberId;

    @Column(name = "subscribetoid")
    private String subscribeToId;

//    public SubscriptionsKey(){}

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getSubscribeToId() {
        return subscribeToId;
    }

    public void setSubscribeToId(String subscribeToId) {
        this.subscribeToId = subscribeToId;
    }
}
