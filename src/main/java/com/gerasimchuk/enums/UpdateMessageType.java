package com.gerasimchuk.enums;

public enum UpdateMessageType {

    /** successful action messages**/

    ORDER_CREATED,
    ORDER_EDITED,
    ORDER_DELETED,

    DRIVER_CREATED,
    DRIVER_EDITED,
    DRIVER_DELETED,

    MANAGER_CREATED,
    MANAGER_EDITED,
    MANAGER_DELETED,

    ADMIN_CREATED,
    ADMIN_EDITED,
    ADMIN_DELETED,

    USER_CREATED,
    USER_EDITED,
    USER_DELETED,


    TRUCK_CREATED,
    TRUCK_EDITED,
    TRUCK_DELETED,


    TRUCK_FIELDS_UPDATED,

    /** error messages will be here**/
    // todo: add error messages

    // orders
    ORDER_STATUS_UPDATED,
    ERROR_ORDER_DTO_IS_NOT_VALID,

    ERROR_CAN_NOT_PARSE_ORDER_ID,


    ERROR_CAN_NOT_CREATE_ORDER,

    ERROR_CAN_NOT_UPDATE_ORDER,

    ERROR_NO_ORDER_WITH_THIS_ID,

    ERROR_CAN_NOT_DELETE_ORDER_WITH_SUCH_STATUS,
    ERROR_ORDER_ID_IS_NOT_VALID,
    ERROR_ORDER_STATUS_IS_NULL,
    ERROR_CAN_NOT_UPDATE_ORDER_STATUS_TO_LOWER,
    ERROR_CAN_NOT_UPDATE_STATUS_FOR_ORDER_WITH_UNDELIVERED_CARGOS,
    ERROR_ASSIGNED_TRUCK_IS_NULL_FOR_EXECUTING_ORDER,
    ERROR_CAN_NOT_GET_ORDER_ROUTE,

    // trucks
    ERROR_NO_TRUCK_WITH_THIS_ID,

    ERROR_TRUCK_HAS_NO_ASSIGNED_DRIVERS_TO_EXECUTE_ORDER,

    // cargos
    CARGO_CREATED,
    CARGO_DELETED,
    CARGO_STATUS_UPDATED,
    ERROR_CAN_NOT_PARSE_CARGO_ID,
    ERROR_CARGO_DTO_IS_NOT_VALID,
    ERROR_NO_CARGO_WITH_THIS_ID,
    ERROR_CAN_NOT_DELETE_CARGO_WITH_SUCH_STATUS,
    ERROR_CARGO_ID_IS_NOT_VALID,
    ERROR_CARGO_STATUS_IS_NOT_VALID,
    ERROR_CAN_NOT_CHANGE_CARGO_STATUS_FROM_DELIVERED_TO_ANOTHER,

    // cities
    CITY_DELETED,
    ERROR_CAN_NOT_PARSE_CITY_ID,
    ERROR_NO_CITY_WITH_THIS_ID,
    ERROR_THIS_CITY_USED_IN_ROUTES,
    ERROR_NO_CITY_WITH_THIS_NAME,


    // drivers
    DRIVER_STATUS_UPDATED,
    ALL_DRIVERS_HOURS_WORKED_VALID,

    ERROR_CAN_NOT_PARSE_DRIVER_ID,

    ERROR_DRIVER_HOURS_WORKED_OVER_LIMIT,

    ERROR_DRIVER_DTO_IS_NOT_VALID,

    ERROR_CAN_NOT_UPDATE_DRIVER,

    ERROR_USER_IS_NOT_A_DRIVER,

    ERROR_DRIVER_ID_IS_NOT_VALID,
    ERROR_DRIVER_STATUS_IS_NULL,
    ERROR_NO_DRIVER_WITH_THIS_ID,
    ERROR_DRIVER_HAS_NO_ASSIGNED_ORDER,
    ERROR_DRIVER_HAS_UNEXECUTED_ASSIGNED_ORDER,
    ERROR_CAN_NOT_COUNT_ORDER_EXECUTING_TIME,

    //managers

    ERROR_MANAGER_DTO_IS_NOT_VALID,
    ERROR_CAN_NOT_UPDATE_MANAGER,
    ERROR_USER_IS_NOT_A_MANAGER,
    // admins

    ERROR_ADMIN_DTO_IS_NOT_VALID,
    ERROR_CAN_NOT_UPDATE_ADMIN,
    ERROR_USER_IS_NOT_AN_ADMIN,
    // users

    ERROR_NO_USER_WITH_THIS_ID,

    ERROR_USER_DTO_IS_NOT_VALID,
    ERROR_CAN_NOT_PARSE_USER_ID,

    ERROR_CAN_NOT_CREATE_USER,
    ERROR_CAN_NOT_EDIT_USER,

    ERROR_CAN_NOT_DELETE_USER,
    // trucks

    ERROR_CAN_NOT_PARSE_NUM_OF_DRIVERS_AND_CAPACITY,
    ERROR_TRUCK_DTO_IS_NOT_VALID,

    ERROR_CAN_NOT_PARSE_TRUCK_ID,
    ERROR_CAN_NOT_PARSE_NUM_OF_DRIVERS,
    ERROR_CAN_NOT_PARSE_CAPACITY,


    ERROR_CAN_NOT_EDIT_TRUCK_WITH_ASSIGNED_ORDER,

    ERROR_NUM_OF_DRIVERS_TO_ASSIGN_MORE_THAN_MAXIMAL_FOR_THIS_TRUCK,

    ERROR_NEW_NUM_OF_DRIVERS_LESS_THAN_NUM_OF_CURRENT_ASSIGNED_DRIVERS,
    ERROR_CAN_NOT_UPDATE_TRUCK,


    ERROR_CAN_NOT_DELETE_TRUCK_WITH_EXEC_ORDER,

    // routes

    ROUTE_CREATED,
    ROUTE_EDITED,
    ROUTE_DELETED,

    ERROR_ROUTE_DTO_IS_NOT_VALID,
    ERROR_ROUTE_ID_IS_NOT_VALID,

    ERROR_CITY_FROM_IS_NULL,
    ERROR_CITY_TO_IS_NULL,
    ERROR_CITY_FROM_EQUALS_CITY_TO,

    ERROR_CAN_NOT_PARSE_DISTANCE_VALUE,
    ERROR_DISTANCE_IS_NOT_VALID,
    ERROR_NO_ROUTE_WITH_THIS_ID,
    ERROR_NO_ROUTE_BETWEEN_THESE_CITIES,
    // for all

    ERROR_ID_IS_NOT_VALID

}
