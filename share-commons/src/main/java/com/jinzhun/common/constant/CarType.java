package com.jinzhun.common.constant;

public enum CarType {

	SMALL(1),SUV(2),BUS(3),UNKNOW(4);
	
	private int value;
	
	private CarType(int value) {
    	this.value = value;
    }
	
	public int getValue() {
		return value;
	}
}
