package com.ecoomerce.services;

import com.ecoomerce.dto.LoginResponseDto;
import com.ecoomerce.dto.MerchantRequest;
import com.ecoomerce.dto.MerchantResponse;
import com.ecoomerce.exception.BadRequestException;
import com.ecoomerce.model.Configuration;
import com.ecoomerce.model.Merchant;
import com.ecoomerce.model.Roles;
import com.ecoomerce.repository.ConfigurationRepository;
import com.ecoomerce.repository.MerchantRepository;
import com.ecoomerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    private final ConfigurationRepository configurationRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    public final ModelMapper modelMapper;

    private String merchantCode(){
        Random random = new Random();
        int productCode = random.nextInt(9000)+1000;
        return "M-".concat(String.valueOf(productCode));
    }

    private String configCode(){
        Random random = new Random();
        int configCode = random.nextInt(9000)+1000;
        return "C-".concat(String.valueOf(configCode));
    }
    public ResponseEntity<String> registerMerchant(MerchantRequest load){
        Optional<Merchant> merchantOptional = merchantRepository.findByEmail(load.getEmail());
        if(merchantOptional.isPresent()){
            return ResponseEntity.badRequest().body("Merchant already exist");
        }
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
       Configuration configuration = new Configuration();
       configuration.setColour("Blue");
       configuration.setConfigurationCode(configCode());
       configuration.setLogoUrl("interswitchimage");
       configuration.setFonts("Time News Roman");
       configuration.setMerchant(merchant);
       configurationRepository.save(configuration);
        merchantRepository.save(merchant);
        return ResponseEntity.ok().body("Merchant registered successfully");
    }


    public LoginResponseDto loginUser(String email, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDto(merchantRepository.findByEmail(email).get(), token);

        } catch (BadCredentialsException e) {
            return new LoginResponseDto(null, "Incorrect email or password");
        } catch (AuthenticationException e) {
            return new LoginResponseDto(null, "Authentication failed: " + e.getMessage());
        }
    }

}
