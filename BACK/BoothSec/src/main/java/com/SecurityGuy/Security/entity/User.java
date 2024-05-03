package com.SecurityGuy.Security.entity;

import com.SecurityGuy.Security.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


// Implement User from Spring Security
// use table name as "_user" since "user" is already used by default sql
// This is a class with getters and setters
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    // Configure primary key and primary key type
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Unique
//    @Column(unique = true)
    private String username;

    private String name;
    private String email;
    private String password;
    private String photo;
    private String location;
    private String profileText;
    private Boolean isActive;

    // Doesn't work well
//    @OneToMany(mappedBy = "user")
//    private Set<Product> products;

    // Ordinal is default, so SELLER is 2
    // But we want the full string so EnumType.STRING More data used in storage though
    @Enumerated(EnumType.STRING)
    private Role role;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    // Authorities are permissions
    // Spring Security overall only works with authorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // List of granted authorities
        // Go through role, get permissions
        // stream and map each permission
        // Assign permission and create SimpleGrantedAuthority
        // Spring Security does not know permissions so use authorities
        List<GrantedAuthority> authorities = role.getPermissions().stream().map(
                permissionEnum -> new SimpleGrantedAuthority(permissionEnum.name())
        ).collect(Collectors.toList());

        // Add method to differentiate permission and role
        // Passing the Role with role name
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));


        return authorities;
    }
}
// An enum is a special "class" that represents a group of constants
// (unchangeable variables, like final variables).
// Can be put in a class as an "attribute" or can be declared as a class like here.
