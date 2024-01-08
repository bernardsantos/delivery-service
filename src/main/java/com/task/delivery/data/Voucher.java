package com.task.delivery.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Voucher {

	private String code;
	private int discount;
	
	public Voucher(String code, int discount) {
		this.code = code;
		this.discount = discount;
	}
	
	public static final Set<Voucher> vouchers = new HashSet<Voucher>(Arrays.asList(
		new Voucher("abc", 10),
		new Voucher("xyz", 5)
	));
	
	public static Voucher getVoucherByCode(String code) {
		return vouchers.stream()
				.filter(voucher -> voucher.getCode()
				.equals(code))
				.findFirst()
				.orElse(null);

	}
}
