package com.task.delivery.service;

import java.util.Map;

public interface DeliveryService {

	public Map<String, Object> calculateDeliveryCost(double weight, double height, double width, double length, String voucherCode);
}
