package com.server.bayztracker.entity;

public enum Alert {
    NEW, //if the price is not in the target price
    TRIGGERRED, //if the pice is reached
    ACKED, //if the user closes the alert
    CANCELLED //if the user cancels the alert
}
