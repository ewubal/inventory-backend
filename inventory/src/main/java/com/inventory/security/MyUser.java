package com.inventory.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class MyUser extends User {

    private Integer id;
    private DbRole dbRole;
    private String firstName;
    private String lastName;


    public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer id,
                  DbRole dbRole, String firstName, String lastName) {
        super(username, password, authorities);
        this.id = id;
        this.dbRole = dbRole;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public MyUser(String username, String password, boolean enabled, boolean accountNonExpired,
                  boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                  Integer id, DbRole dbRole) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.dbRole = dbRole;
    }
}
