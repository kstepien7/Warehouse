package io.github.kst;

import io.github.kst.client.Client;
import io.github.kst.courierbase.CourierBaseRepository;
import io.github.kst.tour.Distance;
import io.github.kst.tour.Tour;
import io.github.kst.warehouse.Warehouse;
import io.github.kst.warehouse.WarehouseRepository;


import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class Service {


    private CourierBaseRepository courierBaseRepository;
    private WarehouseRepository warehouseRepository;
    private Distance countDistance;
    private Tour tour;


    public Service(CourierBaseRepository courierBaseRepository,WarehouseRepository warehouseRepository, Distance countDistance) {
        this.courierBaseRepository = courierBaseRepository;
        this.warehouseRepository = warehouseRepository;
        this.countDistance = countDistance;
    }

    public List<Warehouse> initiateWarehouseOrdering(List<Warehouse> warehouses, Client client) {

        Map<Integer, Warehouse> mapOfWarehouses = initMapOfWarehouses(warehouses);
        double[][] distanceMatrix = prepareDistanceMatrix(mapOfWarehouses, client);
        int msize = mapOfWarehouses.size() + 2;

        // get Tour
        int startNode = 0;
        Tour solver = new Tour(startNode, distanceMatrix);
        var getTour = solver.getTour();
        // check if dummy point is at the begging then reverse
        if (getTour.get(1) == msize)
            Collections.reverse(getTour);

        List<Warehouse> sortedWarehouses = new LinkedList<>();

        for (int i = 0; i < getTour.size(); i++) {
            var x = getTour.get(i);
            var y = mapOfWarehouses.get(x);
            // check if not dummy or courier base or client
            if (y != null)
                sortedWarehouses.add(mapOfWarehouses.get(x));
        }
        return sortedWarehouses;
    }

    private Map<Integer, Warehouse> initMapOfWarehouses(List<Warehouse> warehouses) {
        //sor warehouses by id
        Collections.sort(warehouses, Comparator.comparing(Warehouse::getId));
        Map<Integer, Warehouse> mapOfWarehouses = new HashMap<>();
        for (int i = 0; i < warehouses.size(); i++) {
            // column number and warehouse
            mapOfWarehouses.put(i + 1, warehouses.get(i));
        }
        return mapOfWarehouses;
    }


    private double[][] prepareDistanceMatrix(Map<Integer, Warehouse> mapOfWarehouses, Client client) {
        // warehouses + client + courierBase
        int msize = mapOfWarehouses.size() + 2;
        // create table of distances
        //+ dummy point between base and client
        double[][] distanceMatrix = new double[msize + 1][msize + 1];
        for (double[] row : distanceMatrix) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // dummy point
        distanceMatrix[msize][msize] = 0;
        distanceMatrix[0][msize] = 0;
        distanceMatrix[msize][0] = 0;
        distanceMatrix[msize][msize - 1] = 0;
        distanceMatrix[msize - 1][msize] = 0;
        // client - client
        distanceMatrix[msize - 1][msize - 1] = 0;
        //because of for below
        distanceMatrix[msize - 2][msize - 2] = 0;

        // distances between warehouses - base
        List<Integer> warehousesId = mapOfWarehouses.values().stream().map(Warehouse::getId).collect(Collectors.toList());
        var baseDistances = courierBaseRepository.findByWarehouseIdIn(warehousesId);

        // distances between warehouses
        var warehouseDistances = warehouseRepository.findByFromWarehouseIdInAndToWarehouseIdIn(warehousesId, warehousesId);

        int m = 0;
        for (int i = 0; i < msize - 2; i++) {
            distanceMatrix[i][i] = 0;
            // base - warehouse
            var distanceBase = baseDistances.get(i).getDistance();
            distanceMatrix[0][i + 1] = distanceBase;
            distanceMatrix[i + 1][0] = distanceBase;
            System.out.println();
            // client - warehouse
            calculateDistanceBetweenClientAndWarehouse(mapOfWarehouses.get(i + 1), client, distanceMatrix, i, msize);
            // warehouse - warehouse
            for(int j = i; j < msize - 3; j++){
                var x = warehouseDistances.get(m).getFromWarehouseId();
                var y = warehouseDistances.get(m).getToWarehouseId();
                var distanceWarehouse = warehouseDistances.get(m).getDistance();
                distanceMatrix[i+1][j+2] = distanceWarehouse;
                distanceMatrix[j+2][i+1] = distanceWarehouse;
                m++;
            }
        }
        return distanceMatrix;
    }

    private void calculateDistanceBetweenClientAndWarehouse(Warehouse warehouse, Client client, double[][] distanceMatrix, int i, int msize) {
        // client - warehouse
        var distanceClient = countDistance.calculateDistanceBetweenPoints(warehouse.getLatitude(),
                warehouse.getLongitude(),
                client.getLatitude(),
                client.getLongitude());
        distanceMatrix[i + 1][msize - 1] = distanceClient;
        distanceMatrix[msize - 1][i+1] = distanceClient;
    }
}

