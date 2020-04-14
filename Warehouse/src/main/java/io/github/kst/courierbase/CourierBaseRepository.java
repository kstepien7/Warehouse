package io.github.kst.courierbase;

import io.github.kst.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;

public class CourierBaseRepository {

    public List<DistanceToBase> findDistancesForWarehouseIds(List<Integer> warehousesId) {

        var session= HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        Query query = session.createQuery("from DistanceToBase DTB where DTB.warehouseId in (:warehousesId)");
        query.setParameter("warehousesId", warehousesId);
        List<DistanceToBase> result = (List<DistanceToBase>) ((Query) query).getResultList();

        transaction.commit();
        session.close();
        return result;
    }
}

