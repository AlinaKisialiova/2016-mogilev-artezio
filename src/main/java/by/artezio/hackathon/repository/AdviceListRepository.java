package by.artezio.hackathon.repository;

import by.artezio.hackathon.model.AdviceList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Repository
public interface AdviceListRepository extends JpaRepository<AdviceList, Long>, JpaSpecificationExecutor<AdviceList> {

    AdviceList findByUserIdAndEndDateIsNull(Long userId);

    Page<AdviceList> findByUserIdAndEndDateIsNotNullOrderByEndDateDesc(Long userId, Pageable pageable);
}
