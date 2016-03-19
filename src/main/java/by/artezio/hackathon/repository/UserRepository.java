package by.artezio.hackathon.repository;

import by.artezio.hackathon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByLogin(String login);

    @Query("select count(u) > 0 from User u where u.login = :login or u.email = :email")
    Boolean isUserWithLoginOrEmailExists(@Param("login") String login, @Param("email") String email);

    @Query("select u from User u where u.mood > 0")
    List<User> findAllUsersWithMoodGreaterThenZero();
}
