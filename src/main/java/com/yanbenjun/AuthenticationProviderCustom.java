package com.yanbenjun;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AuthenticationProviderCustom implements AuthenticationProvider
{

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException
    {
        // TODO Auto-generated method stub
        String name = auth.getName();
        if (auth.isAuthenticated())
        {
            return auth;
        }
        //auth.setAuthenticated(true);
        //auth.setAuthenticated(true);
        return auth;
    }

    @Override
    public boolean supports(Class<?> arg0)
    {
        return true;
    }

}
