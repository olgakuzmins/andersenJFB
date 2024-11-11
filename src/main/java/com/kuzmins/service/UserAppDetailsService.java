package com.kuzmins.service;

import com.kuzmins.model.users.User;
import com.kuzmins.repositories.UserRepository;
import com.kuzmins.security.UserDetailsImpl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAppDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserAppDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findUserByName(username);
    if (user.isPresent()) {
      return new UserDetailsImpl(user.get());
    }
    throw new UsernameNotFoundException("User not found");
  }
}
