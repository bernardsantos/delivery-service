package com.task.delivery.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.task.delivery.data.Voucher;
import com.task.delivery.util.DeliveryCostUtilityConstants;

@Service
public class DeliveryServiceImpl implements DeliveryService{
	
	public Map<String, Object> calculateDeliveryCost(double weight, double height, double width, double length, String code) {
		Map<String, Object> response = new HashMap<>();
		double volume = height * width * length;
		
		if (weight > 50) {
			response.put("cost", "N/A");
		} else if (weight > 10) {
			calculateHeavyParcel(weight, response, code);
		} else if (volume < 1500) {
			calculateSmallParcel(volume, response, code);
		} else if (volume >= 1500 && volume < 2500) {
			calculateMediumParcel(volume, response, code);
		} else {
			calculateLargeParcel(volume, response, code);
		}
		return response;
	}

	private Map<String, Object> calculateHeavyParcel(double weight, Map<String, Object> response, String code) {
		Voucher voucher = Voucher.getVoucherByCode(code);
		double cost = DeliveryCostUtilityConstants.HEAVY_PARCEL * weight;
		return calculateDiscount(cost, voucher, response);
	}

	private Map<String, Object> calculateSmallParcel(double volume, Map<String, Object> response, String code) {
		Voucher voucher = Voucher.getVoucherByCode(code);
		double cost = DeliveryCostUtilityConstants.SMALL_PARCEL * volume;
		return calculateDiscount(cost, voucher, response);
	}
	
	private Map<String, Object> calculateMediumParcel(double volume, Map<String, Object> response, String code) {
		Voucher voucher = Voucher.getVoucherByCode(code);
		double cost = DeliveryCostUtilityConstants.MEDIUM_PARCEL * volume;
		return calculateDiscount(cost, voucher, response);
	}
	
	private Map<String, Object> calculateLargeParcel(double volume, Map<String, Object> response, String code) {
		Voucher voucher = Voucher.getVoucherByCode(code);
		double cost = DeliveryCostUtilityConstants.LARGE_PARCEL * volume;
		return calculateDiscount(cost, voucher, response);
	}

	private Map<String, Object> calculateDiscount(double cost, Voucher voucher, Map<String, Object> response) {
		if (null != voucher) {
			double discountedCost = cost - (cost * (voucher.getDiscount()/100.00));
			response.put("discount", voucher.getDiscount()+"%");
			response.put("discountedCost", discountedCost);
		}
		response.put("cost", cost);
		return response;
	}

}
