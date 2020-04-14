package io.github.kst.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    public Integer id;
    public String name;
    public Double latitude;
    public Double longitude;

    /**
     * Hibernate needs it. (JPA)
     */
    @SuppressWarnings("unused")
    Warehouse() {
    }
}
