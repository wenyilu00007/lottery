package com.wyl.lotterycommon.utils.address;

public class Point {
	private Long id;
    private double lon;
    private double lat;
    
	public Point() {
	}

	public Point(double lon, double lat,Long id) {
		this.lon = lon;
		this.lat = lat;
		this.id = id;
	}
    
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}      
}
