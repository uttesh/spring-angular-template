package com.uttesh.service;

import com.uttesh.model.User;
import com.uttesh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
/**
 *
 * @author Uttesh Kumar T.H.
 */
@Service
public class UserService extends AbstractEntityService<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected MongoRepository<User, String> getRepository() {
        return userRepository;
    }

    protected Class getEntityClass() {
        return User.class;
    }

    public User create(User user) {
        User newUser = save(user);
        return newUser;
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getLoggedInUser() {
        return null;
    }

}
