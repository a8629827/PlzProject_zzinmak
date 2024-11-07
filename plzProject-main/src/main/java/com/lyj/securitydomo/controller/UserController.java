package com.lyj.securitydomo.controller;

import com.lyj.securitydomo.domain.Address;
import com.lyj.securitydomo.domain.User;
import com.lyj.securitydomo.repository.AddressRepository;
import com.lyj.securitydomo.repository.UserRepository;
import com.lyj.securitydomo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Log4j2
public class UserController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AddressRepository addressRepository;
    private final UserService userService;

    @GetMapping("/join")
    public void join(){
    }

    @PostMapping("/join")
    public String register(User user) {
        log.info("123412312412312412314");
        log.info(user.toString());
        System.out.println("회원가입 진행 : " + user);
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("USER");

//        Address address = new Address();
//        address.setCity(address.getCity());
//        address.setState(address.getState());
//        address.setUser(user);

        // User에 Address 추가
//        user.getAddresses().add(address);

        // User 저장 (cascade 설정에 의해 Address도 저장됨)
        userService.save(user);

        return "redirect:/login";
    }
    @GetMapping("login")
    public void login(){
    }

}
