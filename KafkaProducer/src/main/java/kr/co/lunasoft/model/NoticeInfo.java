package kr.co.lunasoft.model;

import lombok.Data;

@Data
public class NoticeInfo {
	private String id;
	private String title;
	private String contents;
	private String status;
	private String regUser;
	private String regDate;
}
