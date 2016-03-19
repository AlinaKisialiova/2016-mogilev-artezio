package by.artezio.hackathon.repository;

import by.artezio.hackathon.model.AdviceListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Repository
public interface AdviceListItemRepository extends JpaRepository<AdviceListItem, Long>, JpaSpecificationExecutor<AdviceListItem> {
}
