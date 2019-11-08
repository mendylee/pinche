package com.jinzhun.common.constant;

public enum Color {

	RED("红色", 1), GREEN("绿色", 2), WHITE("白色", 3), BLACK("黑色", 4), YELLO("黄色", 5), SILVER("银色", 6);

	private String name;

	private int index;
	
	private Color(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }

	public static String getName(int index) {
		for(Color color: Color.values()) {
			if(color.getIndex() == index) {
				return color.name;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
