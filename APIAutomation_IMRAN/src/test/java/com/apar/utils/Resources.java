package com.apar.utils;

public enum Resources {
	
	booking("/booking");
	
	private String resource;
	
	Resources(String resourceName)
	{
		this.resource= resourceName;
	}

	 
	public String getResource() {
		return resource;
	}

}
