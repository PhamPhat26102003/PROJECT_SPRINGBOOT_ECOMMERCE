package projectspringboot.library.service;

import org.apache.catalina.LifecycleState;
import projectspringboot.library.model.Accessories;

import java.util.List;

public interface IAccessoriesService {
    List<Accessories> findAll();
    Accessories save(Accessories accessories);
}
