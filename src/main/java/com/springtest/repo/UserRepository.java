package com.springtest.repo;


import com.springtest.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {

	@Query("SELECT u FROM User u WHERE u.login = ?1")
	User findByLogin(String alias);


}




