package com.learn.journal.repo;

import com.learn.journal.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepo extends MongoRepository<JournalEntry, ObjectId> {

}
