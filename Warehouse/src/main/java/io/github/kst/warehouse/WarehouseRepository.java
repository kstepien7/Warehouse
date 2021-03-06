package io.github.kst.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WarehouseRepository extends JpaRepository<DistanceToWarehouse, Integer> {
     List<DistanceToWarehouse> findByFromWarehouseIdInAndToWarehouseIdIn(List<Integer> fromWarehouseId, List<Integer> toWarehouseId);
}