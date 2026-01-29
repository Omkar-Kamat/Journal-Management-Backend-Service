package com.learn.journal.repo;

import com.learn.journal.Entity.JournalEntry;
import com.learn.journal.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo  extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
}
