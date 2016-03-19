package by.artezio.hackathon.repository;

import by.artezio.hackathon.model.Advice;
import by.artezio.hackathon.model.User;
import by.artezio.hackathon.model.UserAdvice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Repository
public interface UserAdviceRepository extends JpaRepository<UserAdvice, Long>, JpaSpecificationExecutor<UserAdvice> {

    UserAdvice findOneByAdviceAndUser(Advice advice, User user);
}
