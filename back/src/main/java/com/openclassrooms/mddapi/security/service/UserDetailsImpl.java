package com.openclassrooms.mddapi.security.service;

import java.util.Collection;
import java.util.HashSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Implementation of Spring Security's UserDetails interface that represents a
 * user.
 *
 * @author Tony
 * @version $Id: $Id
 */
@Builder
@AllArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Integer id;

  private String username;

  private String email;

  @JsonIgnore
  private String password;

  /**
   * Returns the authorities granted to the user.
   *
   * @return the authorities (in this implementation, always an empty set)
   */
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new HashSet<>();
  }

  /** {@inheritDoc} */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEnabled() {
    return true;
  }
}
