package com.yanbenjun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsBean implements UserDetails
{
    /**
     * 
     */
    private static final long serialVersionUID = 7014854673476807054L;
    private String username;
    public UserDetailsBean(String username)
    {
        this.username = username;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<UserAuthority> uas = new ArrayList<>();
        if ("admin".equals(username))
        {
            uas.add(new UserAuthority("ROLE_ADMIN"));
        }
        else if("user".equals(username))
        {
            uas.add(new UserAuthority("ROLE_USER"));
        }
        else if("db".equals(username))
        {
            uas.add(new UserAuthority("ROLE_DBA"));
        }
        return uas;
    }

    @Override
    public String getPassword()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsername()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEnabled()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
