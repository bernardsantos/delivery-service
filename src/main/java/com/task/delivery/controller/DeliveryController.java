package com.task.delivery.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.delivery.service.DeliveryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DeliveryController {
	
	private final DeliveryService deliveryService;
	
	@GetMapping(value = "/calculate", produces = "application/JSON")
	public ResponseEntity<?> calculateCost(@RequestParam(value = "weight", required=true) double weight,
			@RequestParam(value = "height", required=true) double height,
			@RequestParam(value = "width", required=true) double width,
			@RequestParam(value = "length", required=true) double length,
			@RequestParam(value = "code", required= false) String code) {
		return new ResponseEntity<Map<String, Object>>(deliveryService.calculateDeliveryCost(weight, height, width, length, code), HttpStatus.OK);
	}
}
