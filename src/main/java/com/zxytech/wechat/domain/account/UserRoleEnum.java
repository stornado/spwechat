package com.zxytech.wechat.domain.account;

/**
 * @author xwxia
 * @date 2018/3/6 11:07
 */
public enum UserRoleEnum {
    USER("USER", "ROLE_USER", "职员"),
    ADMIN("ADMIN", "ROLE_ADMIN", "管理员");

    private String name;
    private String role;
    private String title;

    UserRoleEnum(String name, String role, String title) {
        this.name = name;
        this.role = role;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
