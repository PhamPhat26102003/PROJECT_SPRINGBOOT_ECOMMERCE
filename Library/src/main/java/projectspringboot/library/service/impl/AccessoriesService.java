package projectspringboot.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectspringboot.library.model.Accessories;
import projectspringboot.library.repository.IAccessoriesRepository;
import projectspringboot.library.service.IAccessoriesService;

import java.util.List;

@Service
public class AccessoriesService implements IAccessoriesService {
    @Autowired
    private IAccessoriesRepository accessoriesRepository;

    @Override
    public List<Accessories> findAll() {
        return accessoriesRepository.findAll();
    }

    @Override
    public Accessories save(Accessories accessories) {
        return accessoriesRepository.save(accessories);
    }
}
