package com.spring.tktapp.domain.repositories;

import com.spring.tktapp.application.entity.MyDataMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyDataMongoRepository extends MongoRepository<MyDataMongo, Long> {
}
