package io.github.kst.courierbase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierBaseRepository extends JpaRepository<DistanceToBase, Integer> {
    List<DistanceToBase> findByWarehouseIdIn(List<Integer> warehouseId);
}