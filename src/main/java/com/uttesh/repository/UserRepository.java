package com.uttesh.repository;


import com.uttesh.model.User;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Uttesh Kumar T.H.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(final String username);
    List<User> findByUsernameLike(final String username);
    
}
