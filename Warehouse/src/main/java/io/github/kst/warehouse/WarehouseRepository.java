package io.github.kst.warehouse;

import io.github.kst.HibernateUtil;

import org.hibernate.query.Query;

import java.util.List;


public class WarehouseRepository {

   public List<DistanceToWarehouse> findDistancesForWarehouseIds(List<Integer> selectedWarehousesId){

        var session= HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        String hql = "FROM DistanceToWarehouse W WHERE W.fromWarehouseId in (:selectedWarehousesId) and" +
                " W.toWarehouseId in (:selectedWarehousesId)";
        Query query = session.createQuery(hql);
        query.setParameter("selectedWarehousesId", selectedWarehousesId);
        List<DistanceToWarehouse> result = (List<DistanceToWarehouse>) ((Query) query).getResultList();
        transaction.commit();
        session.close();
        return result;

    }
}
