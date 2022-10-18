package com.smart.consumption.repository;

import com.smart.consumption.entity.Meters;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MetersEntityRepository extends CrudRepository<Meters, String> {
    List<Meters> findAll();
}
