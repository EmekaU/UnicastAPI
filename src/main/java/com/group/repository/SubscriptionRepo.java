package com.group.repository;

import com.group.dao.Subscriptions;
import com.group.dao.SubscriptionsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepo  extends JpaRepository<Subscriptions, SubscriptionsKey> {
    @Override
    <S extends Subscriptions> S save(S s);

    @Override
    void deleteById(SubscriptionsKey subscriptionsKey);

    @Override
    Subscriptions getOne(SubscriptionsKey subscriptionsKey);
}
