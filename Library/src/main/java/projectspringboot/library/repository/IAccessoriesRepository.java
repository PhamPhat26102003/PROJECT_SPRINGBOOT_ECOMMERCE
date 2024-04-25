package projectspringboot.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectspringboot.library.model.Accessories;

@Repository
public interface IAccessoriesRepository extends JpaRepository<Accessories, Long> {
}
