package com.gerasimchuk.services.interfaces;

public interface StatisticService {
    int getNumOfTrucksTotal();
    int getNumOfTrucksFree();
    int getNumOfTrucksNotReady();
    int getNumOfTrucksExecutingOrders();
    int getNumOfDriversTotal();
    int getNumOfDriversFree();
    int getNumOfDriversExecutingOrders();
}
