package io.github.kst.courierbase;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@AllArgsConstructor
@Entity
@Table(name = "distance_to_base")
public class DistanceToBase implements Serializable {
    @Id
    @Column(name = "base_Id")
    public Integer baseId;
    @Id
    @Column(name = "warehouse_Id")
    public Integer warehouseId;
    public Double distance;

    /**
     * Hibernate needs it. (JPA)
     */
    @SuppressWarnings("unused")
    DistanceToBase(){}
}
