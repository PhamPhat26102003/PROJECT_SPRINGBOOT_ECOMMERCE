package projectspringboot.library.service;

import org.apache.catalina.LifecycleState;
import projectspringboot.library.model.Accessories;

import java.util.List;

public interface IAccessoriesService {
    List<Accessories> findAll();
    List<Accessories> findAllAccessoriesByActivated();
    Accessories findById(Long id);
    Accessories save(Accessories accessories);
    Accessories updateAccessories(Accessories accessories);
    List<Accessories> searchAccessories(String keyword);
    void activatedById(Long id);
    void deletedById(Long id);
}
