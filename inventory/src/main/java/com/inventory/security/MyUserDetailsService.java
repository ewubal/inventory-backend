package com.inventory.security;

import com.inventory.models.Owner;
import com.inventory.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class MyUserDetailsService  implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public MyUser loadUserByUsername(String email) throws UsernameNotFoundException {
        List<Owner> owners = userRepository.findAllByEmail(email);
        if (CollectionUtils.isEmpty(owners)){
            throw new UsernameNotFoundException(format("email not found: %s", email));
        }
        Owner owner = owners.get(0);
        return new MyUser(owner.getEmail(), owner.getPassword(), getAuthorities(owner), owner.getId(), owner.getRole(),
                owner.getName(), owner.getSurname());
    }

    /**
     * convert db roles to GrantedAuthority object
     */
    private Collection<? extends GrantedAuthority> getAuthorities(Owner owner) {
        return getRoles(owner)
                .map(DbRole::toSpringRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * if user is admin, then they get all the roles in application
     */
    private Stream<DbRole> getRoles(Owner owner) {
        if (owner.getRole().isAdmin()) {
            return Arrays.stream(DbRole.values());
        }
        return Stream.of(owner.getRole());
    }
}
