package com.ecoomerce.services;

import com.ecoomerce.dto.LoginResponseDto;
import com.ecoomerce.dto.MerchantRequest;
import com.ecoomerce.dto.MerchantResponse;
import com.ecoomerce.model.Merchant;
import com.ecoomerce.model.Roles;
import com.ecoomerce.repository.MerchantRepository;
import com.ecoomerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    private final MerchantRepository merchantRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    public final ModelMapper modelMapper;

    private String merchantCode(){
        Random random = new Random();
        int productCode = random.nextInt(9000)+1000;
        return "M-".concat(String.valueOf(productCode));
    }
    public MerchantResponse registerMerchant(MerchantRequest load){
        String encodedPassword = passwordEncoder.encode(load.getPassword());
        Roles userRole = roleRepository.findByAuthority("MERCHANT").get();
        Set<Roles> authorities = new HashSet<>();
        authorities.add(userRole);
        Merchant merchant = new Merchant();
        merchant.setUsername(load.getEmail());
        merchant.setEmail(load.getEmail());
        merchant.setPassword(encodedPassword);
        merchant.setMerchantCode(merchantCode());
        merchant.setFirstName(load.getFirstName());
        merchant.setLastName(load.getLastName());
        merchant.setDateCreated(new Date());
        merchant.setBusinessName(load.getBusinessName());
        merchant.setAuthorities(authorities);
        merchantRepository.save(merchant);
        return modelMapper.map(merchant, MerchantResponse.class);
    }


    public String updateRoles(Integer roleId, String authority){
        Optional<Roles> rolesOptional = roleRepository.findRolesByRoleId(roleId);
        if(rolesOptional.isEmpty()){
            throw new RuntimeException("Roles Not Found");
        }else {
            Roles roles =rolesOptional.get();
            roles.setAuthority(authority.toUpperCase());
            roleRepository.save(roles);
            return "Your roles is ".concat(authority);
        }
    }

    public LoginResponseDto loginUser(String email, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDto(merchantRepository.findByEmail(email).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDto(null, "");
        }
    }

}
