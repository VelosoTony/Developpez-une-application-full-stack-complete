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
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  /** The id associated with the user. */
  private Integer id;
  /** The username associated with the user. */
  private String username;
  /** The email associated with the user. */
  private String email;

  /**
   * The password associated with the user. This field is ignored during JSON
   * serialization.
   */
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
