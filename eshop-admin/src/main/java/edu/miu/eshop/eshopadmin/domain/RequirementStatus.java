package edu.miu.eshop.eshopadmin.domain;

// AA

public enum RequirementStatus {
    CREATED,    // Created by customer
    RECEIVED,   // The ticket manager acknowledged
    ONGOING,    // The request is under development
    ANSWERED,   // The request is completed by eShop but not yet confirmed by Vendor
    CLOSED,     // The Vendor is confirmed to close.
    DELETED;    // Deleted request
}
