package ru.pavlov.MetrologicalManagement.domain;

public enum AppUserPermission {
	DEVICE_ADD("device:add"),
	DEVICE_DELETE("device:delete"),
		VERIFICATIONS_READ("verifications:read"),
		VERIFICATIONS_SET("verifications:set"),
		VERIFICATIONS_ADD("verifications:add"),
	USER_READ("user:read"),
	USER_ADD("user:add"),
	USER_DELETE("user:delete"),
	USER_BLOCK("user:block");
	
	private final String permission;
	
	AppUserPermission(String permission){
		this.permission = permission;
	}
	
	public String getPermission() {
		return this.permission;
	}
}