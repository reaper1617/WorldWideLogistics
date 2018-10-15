package com.gerasimchuk.services.interfaces;

import com.gerasimchuk.utils.JSONconvertable;

public interface StatisticService extends JSONconvertable {
    int getNumOfTrucksTotal();
    int getNumOfTrucksFree();
    int getNumOfTrucksNotReady();
    int getNumOfTrucksExecutingOrders();
    int getNumOfDriversTotal();
    int getNumOfDriversFree();
    int getNumOfDriversExecutingOrders();
}
