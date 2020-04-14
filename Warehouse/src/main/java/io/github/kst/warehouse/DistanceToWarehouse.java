package io.github.kst.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Entity
@Table(name = "distance_to_warehouse")
public class DistanceToWarehouse implements Serializable {

    @Id
    @Column(name = "from_Warehouse_Id")
    public Integer fromWarehouseId;
    @Id
    @Column(name = "to_Warehouse_Id")
    public Integer toWarehouseId;
    public Double distance;


    /**
     * Hibernate needs it. (JPA)
     */
    @SuppressWarnings("unused")
    DistanceToWarehouse(){}
}
