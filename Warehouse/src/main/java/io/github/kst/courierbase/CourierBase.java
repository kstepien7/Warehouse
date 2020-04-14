package io.github.kst.courierbase;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "courier_base")
public class CourierBase {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    public Integer id;
    public Double latitude;
    public Double longitude;

    /**
     * Hibernate needs it. (JPA)
     */
    @SuppressWarnings("unused")
    CourierBase(){}
}
