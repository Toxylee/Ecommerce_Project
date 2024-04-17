package com.ecoomerce.services;

import com.ecoomerce.repository.MerchantRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {


    private final PasswordEncoder encoder;

    private final MerchantRepository merchantRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("In the user details service");

        return (UserDetails) merchantRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }

}