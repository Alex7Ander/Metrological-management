package ru.pavlov.MetrologicalManagement.domain;

import java.util.Set;
import com.google.common.collect.Sets;

public enum AppUserRole {
	VERIFICATOR(Sets.newHashSet(AppUserPermission.VERIFICATIONS_READ)), 
	MANAGER(Sets.newHashSet(AppUserPermission.VERIFICATIONS_ADD)), 
	TEAMLEADER(Sets.newHashSet(AppUserPermission.VERIFICATIONS_SET, AppUserPermission.USER_BLOCK)), 
	ADMIN(Sets.newHashSet(AppUserPermission.VERIFICATIONS_ADD, AppUserPermission.USER_READ, AppUserPermission.USER_ADD, AppUserPermission.USER_DELETE, AppUserPermission.USER_BLOCK));
	
	private final Set<AppUserPermission> permissions;

	AppUserRole(Set<AppUserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<AppUserPermission> getPermission() {
		return this.permissions;
	}
}
