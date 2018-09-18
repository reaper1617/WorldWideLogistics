package com.gerasimchuk.services.impls;

import com.gerasimchuk.constants.WWLConstants;
import com.gerasimchuk.converters.OrderToDTOConverter;
import com.gerasimchuk.dto.OrderDTO;
import com.gerasimchuk.entities.Cargo;
import com.gerasimchuk.entities.City;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.entities.Truck;
import com.gerasimchuk.enums.CargoActionType;
import com.gerasimchuk.enums.OrderStatus;
import com.gerasimchuk.enums.TruckState;
import com.gerasimchuk.repositories.*;
import com.gerasimchuk.services.interfaces.OrderService;
import com.gerasimchuk.utils.PersonalNumberGenerator;
import com.gerasimchuk.utils.RoutePoint;
import com.gerasimchuk.validators.DTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {


    private CargoRepository cargoRepository;
    private TruckRepository truckRepository;
    private OrderRepository orderRepository;
    private CityRepository cityRepository;
    private RouteRepository routeRepository;
    private DTOValidator dtoValidator;

    @Autowired
    public OrderServiceImpl(CargoRepository cargoRepository, TruckRepository truckRepository, OrderRepository orderRepository, CityRepository cityRepository, RouteRepository routeRepository, DTOValidator dtoValidator) {
        this.cargoRepository = cargoRepository;
        this.truckRepository = truckRepository;
        this.orderRepository = orderRepository;
        this.cityRepository = cityRepository;
        this.routeRepository = routeRepository;
        this.dtoValidator = dtoValidator;
    }

    public Collection<Cargo> getChosenCargos(OrderDTO orderDTO) {
        if (!dtoValidator.validate(orderDTO)) return null;
        Collection<Cargo> cargos = new ArrayList<Cargo>();
        if (orderDTO.getCargosInOrder()!=null){
            String[] cargosInOrder = orderDTO.getCargosInOrder();
            for(int i = 0; i < cargosInOrder.length; i++){
                int id = 0;
                try{
                    id = Integer.parseInt(cargosInOrder[i]);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                if (id != 0) {
                    Cargo cargo = cargoRepository.getById(id);
                    if (cargo!=null) cargos.add(cargo);
                }
            }
        }
        return cargos;
    }

    public Collection<Truck> getAvailableTrucks(OrderDTO orderDTO) {
        Collection<Cargo> cargosInOrder = getChosenCargos(orderDTO);
        Collection<City> orderRoute = getOrderRoute(orderDTO);
        Collection<Truck> result = new ArrayList<Truck>();
        Collection<Truck> allTrucks = truckRepository.getAll();
        double maxCargoWeightOnRoute = getMaxCargoWeightOnRoute(orderDTO);
        for(Truck t: allTrucks){
            if (t.getState().equals(TruckState.READY) && t.getAssignedOrder()==null){
                if (t.getCapacity()*WWLConstants.TON >= maxCargoWeightOnRoute){
                    result.add(t);
                }
            }
        }
        return result;
    }

//    public Collection<Truck> getAvailableTrucks(List<Cargo> cargosInOrder) {
//        Collection<City> orderRoute = getOrderRoute(orderDTO);
//        Collection<Truck> result = new ArrayList<Truck>();
//        Collection<Truck> allTrucks = truckRepository.getAll();
//        double maxCargoWeightOnRoute = getMaxCargoWeightOnRoute(orderDTO);
//        for(Truck t: allTrucks){
//            if (t.getState().equals(TruckState.READY) && t.getAssignedOrder()==null){
//                if (t.getCapacity()*WWLConstants.TON >= maxCargoWeightOnRoute){
//                    result.add(t);
//                }
//            }
//        }
//        return result;
//    }

    private double getMaxCargoWeightOnRoute(OrderDTO orderDTO){
        Collection<Cargo> cargosInOrder = getChosenCargos(orderDTO);
        Collection<City> orderRoute = getOrderRoute(orderDTO);
//        City[] orderRouteArray = (City[]) orderRoute.toArray();
        Object[] orderRouteArray = orderRoute.toArray();
        double[] weightsOnRoute = new double[orderRoute.size()];
        for(Cargo cargo: cargosInOrder){
            boolean isLoadActionFound = false;
            for(int i = 0; i < orderRouteArray.length; i++){
                if (cargo.getRoute().getCityFrom().getName().equals(((City)orderRouteArray[i]).getName()) ){
                    weightsOnRoute[i] += cargo.getWeight();
                    isLoadActionFound = true;
                }
                if (cargo.getRoute().getCityTo().getName().equals(((City)orderRouteArray[i]).getName())){
                    if (isLoadActionFound){
                        weightsOnRoute[i] -= cargo.getWeight();
                    }
                }
            }
        }
        for( int i = 1; i < weightsOnRoute.length; i++){
            weightsOnRoute[i] += weightsOnRoute[i-1];
        }
        Arrays.sort(weightsOnRoute);
        double max = weightsOnRoute[weightsOnRoute.length-1];
        return max;
    }

    // todo: refactor!!!
    public Collection<City> getOrderRoute(OrderDTO orderDTO){
        if (!dtoValidator.validate(orderDTO)) return null;
        Collection<Cargo> cargosInOrder = getChosenCargos(orderDTO);
        Set<City> citiesInOrderRoute = new HashSet<City>();
        for(Cargo c: cargosInOrder){
            citiesInOrderRoute.add(c.getRoute().getCityFrom());
            citiesInOrderRoute.add(c.getRoute().getCityTo());
        }
        //
        List<City> normalizedRoute = getNormalizedOrderRoute(citiesInOrderRoute);
//        City[] normalizedRouteArray = (City[]) normalizedRoute.toArray();
        Object[] normalizedRouteArray = citiesInOrderRoute.toArray();
        Collection<RoutePoint> routePoints = new ArrayList<RoutePoint>();
        for(int i = 0; i < normalizedRoute.size(); i++){
            // find cargos with current city as point of load/unload
            Map<Cargo, CargoActionType> cityPointMap = new HashMap<Cargo, CargoActionType>();
            for(Cargo c: cargosInOrder){
                if (c.getRoute().getCityFrom().getName().equals(((City)normalizedRouteArray[i]).getName())) cityPointMap.put(c,CargoActionType.LOAD);
                if (c.getRoute().getCityTo().getName().equals(((City)normalizedRouteArray[i]).getName())) cityPointMap.put(c,CargoActionType.UNLOAD);
            }
            routePoints.add(new RoutePoint((City)normalizedRouteArray[i],cityPointMap));
        }
        for(Cargo c: cargosInOrder){
            boolean isLoadActionFound = false;
            for(RoutePoint routePoint: routePoints){
                if (routePoint.getCargoActionTypeMap().containsKey(c)){
                    if (routePoint.getCargoActionTypeMap().get(c).equals(CargoActionType.LOAD)){
                        isLoadActionFound=true;
                    }
                    if (routePoint.getCargoActionTypeMap().get(c).equals(CargoActionType.UNLOAD) && !isLoadActionFound){
                        normalizedRoute.add(routePoint.getCity());
                    }
                }
            }
        }
        return normalizedRoute;
    }

    public boolean createOrder(OrderDTO orderDTO) {
        if (!dtoValidator.validate(orderDTO)) return false;
        String personalNumber = PersonalNumberGenerator.generate(10);
        Date date = new Date();
        String currentDate = date.toString();
        int truckId = 0;
        try{
            truckId = Integer.parseInt(orderDTO.getAssignedTruckId());
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        Truck assignedTruck = truckRepository.getById(truckId);
        Order newOrder = null;
        if (assignedTruck!=null) {
            newOrder = orderRepository.create(personalNumber, orderDTO.getDescription(), currentDate, OrderStatus.NOT_PREPARED, assignedTruck);
        }
        else {
            return false;
        }
        if (newOrder!=null) {
            Collection<Cargo> cargosInOrder = getChosenCargos(orderDTO);
            for (Cargo c : cargosInOrder) {
                cargoRepository.update(c.getId(), c.getPersonalNumber(), c.getName(), c.getWeight(), c.getStatus(), c.getRoute(),newOrder);
            }
            return true;
        }
        return false;
    }


    public boolean updateOrder(OrderDTO orderDTO) {
        //todo: implement logics!!!
        return false;
    }


    public Map<Order, Collection<City>> getRoutes(Collection<Order> orders){
        if (orders == null) return null;
        Map<Order, Collection<City>> routes = new HashMap<Order, Collection<City>>();
        for(Order o: orders){
            OrderDTO orderDTO = OrderToDTOConverter.convert(o);
            Collection<City> route = getOrderRoute(orderDTO);
            routes.put(o,route);
        }
        return routes;
    }


    private double[][] makeDistancesMatrixForOrderRoute(Collection<City> citiesInOrderRoute){
        int size = citiesInOrderRoute.size();
        Object[] cities = citiesInOrderRoute.toArray();
        double distances[][] = new double[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if (i == j) distances[i][j] = 0;
                else{
                    City cityI = cityRepository.getByName(((City)cities[i]).getName());
                    City cityJ = cityRepository.getByName(((City)cities[j]).getName());
                    distances[i][j] = routeRepository.getByCities(cityI, cityJ).getDistance();
                    // todo: what if there is no such route??
                }
            }
        }
        return distances;
    }

    private List<City> getNormalizedOrderRoute(Collection<City> citiesInOrderRoute){
        double[][] distances = makeDistancesMatrixForOrderRoute(citiesInOrderRoute);
        List<City> result = new ArrayList<City>();
        int size = citiesInOrderRoute.size();
        Object[] cities = citiesInOrderRoute.toArray();
        if (cities == null || cities.length==0) return null;
        Collection<Integer> excludedIndexes = new HashSet<Integer>();
        result.add(((City)cities[0]));
        int nextRow = 0;
        excludedIndexes.add(nextRow);
        while (size > 1){
            nextRow = findMinDistanceIndex(distances[nextRow], excludedIndexes);
            excludedIndexes.add(nextRow);
            result.add(((City)cities[nextRow]));
            size -- ;
        }
        return result;
    }


    private int findMinDistanceIndex(double[] row, Collection<Integer> excludedIndexes){
        double min = Double.MAX_VALUE;
        int minIndex = 0;
        for(int i = 0; i < row.length; i++){
            if (!excludedIndexes.contains(i)){
                if (row[i]!=0){
                    if (row[i] < min){
                        min = row[i];
                        minIndex = i;
                    }
                }
            }
        }
        return minIndex;
    }


}
