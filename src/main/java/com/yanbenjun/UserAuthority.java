package com.yanbenjun;

import org.springframework.security.core.GrantedAuthority;

public class UserAuthority implements GrantedAuthority
{
    /**
     * 
     */
    private static final long serialVersionUID = 628019876829036195L;
    private String role;
    
    public UserAuthority(String role)
    {
        this.role = role;
    }
    @Override
    public String getAuthority()
    {
        return role;
    }
    public String getRole()
    {
        return role;
    }
    public void setRole(String role)
    {
        this.role = role;
    }

}
