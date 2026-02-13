package com.learn.journal.service;

import com.learn.journal.Entity.JournalEntry;
import com.learn.journal.Entity.User;
import com.learn.journal.repo.JournalRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry entry, String userName){
        User user = userService.findByUserName(userName);
        entry.setDate(LocalDateTime.now());
        JournalEntry save = journalRepo.save(entry);
        user.getJournalEntryList().add(save);
        userService.saveUser(user);
    }

    public void saveEntry(JournalEntry entry){
        journalRepo.save(entry);
    }
    public List<JournalEntry> getAll(){
        return journalRepo.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalRepo.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntryList().removeIf(x->x.getId().equals(id));
            if(removed) {
                userService.saveUser(user);
                journalRepo.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return removed;
    }
}
