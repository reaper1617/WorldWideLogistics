package com.gerasimchuk.services.impls;

import com.gerasimchuk.repositories.DriverRepository;
import com.gerasimchuk.repositories.TruckRepository;
import com.gerasimchuk.services.interfaces.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    private DriverRepository driverRepository;
    private TruckRepository truckRepository;

    @Autowired
    public StatisticServiceImpl(DriverRepository driverRepository, TruckRepository truckRepository) {
        this.driverRepository = driverRepository;
        this.truckRepository = truckRepository;
    }

    @Override
    public int getNumOfTrucksTotal() {
        return truckRepository.getNumberOfTrucksTotal();
    }

    @Override
    public int getNumOfTrucksFree() {
        return truckRepository.getNumberOfTrucksFree();
    }

    @Override
    public int getNumOfTrucksNotReady() {
        return truckRepository.getNumberOfTrucksNotReady();
    }

    @Override
    public int getNumOfTrucksExecutingOrders() {
        return truckRepository.getNumberOfTrucksExecutingOrder();
    }

    @Override
    public int getNumOfDriversTotal() {
        return driverRepository.getNumOfDriversTotal();
    }

    @Override
    public int getNumOfDriversFree() {
        return driverRepository.getNumOfDriversFree();
    }

    @Override
    public int getNumOfDriversExecutingOrders() {
        return driverRepository.getNumOfDriversExecutingOrders();
    }
}
