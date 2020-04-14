package io.github.kst;

import io.github.kst.client.Client;

import io.github.kst.warehouse.Warehouse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class TestShortestPath {

    @Test
    public void testExecute() {
        Client client = new Client(1, "Ala", "Makota", "tst@gmail.com", 60.0, 22.0);
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(new Warehouse(1, "w1", 53.5, 20.5));
        warehouses.add(new Warehouse(2, "w2", 55.5, 25.5));
        warehouses.add(new Warehouse(5, "w5", 51.5, 20.5));
        warehouses.add(new Warehouse(7, "w7", 50.0, 23.5));
        warehouses.add(new Warehouse(8, "w8", 53.5, 25.0));


        Service service = new Service();
        service.initiateWarehouseOrdering(warehouses, client);
    }
}