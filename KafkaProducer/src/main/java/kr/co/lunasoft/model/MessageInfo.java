package kr.co.lunasoft.model;

import lombok.Data;

@Data
public class MessageInfo {
	private String seq;
	private String name;
	private String desc;
	private GeoPoint geopoint;
	private ChildInfo childInfoList;
	private String created;
}
