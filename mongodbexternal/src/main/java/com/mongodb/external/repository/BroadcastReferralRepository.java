package com.mongodb.external.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.external.entity.BroadcastReferral;

public interface BroadcastReferralRepository extends MongoRepository<BroadcastReferral, String> {

}
