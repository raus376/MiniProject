package com.ecom.models;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SessoinDTO {

	private String authKey;
	
	private LocalDateTime sessionStartTime;
}
