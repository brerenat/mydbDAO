package brere.nat.mydb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import brere.nat.mydb.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findWithUserName(String name);
	
}
