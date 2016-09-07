package com.mfg.entity;

public class GoodConstraint {

	public static Boolean validate(Good good) {
		if (good != null) {
			// check name
			String name = good.getName();
			if (name == null || name.isEmpty() || name.length() > 50) {
				return false;
			}
			
			// check age
			Integer age = good.getAge();
			if (age == null || age < 0) {
				return false;
			}
			return true;
		}
		return false;
	}
}
