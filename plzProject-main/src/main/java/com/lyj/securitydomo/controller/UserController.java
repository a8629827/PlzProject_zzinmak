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


    // 마이페이지 정보 조회 (GET 요청)
    @GetMapping("/myPage")
    public String getMyPage(Model model) {
        // 현재 로그인한 사용자 정보를 가져옵니다.
        User user = userService.getCurrentUser(); // 현재 로그인한 사용자의 정보를 가져오는 메소드 (로그인 처리 방식에 따라 달라질 수 있습니다.)

        // 모델에 사용자 정보를 추가하여 뷰로 전달합니다.
        model.addAttribute("user", user);

        // 마이페이지 HTML 뷰를 반환합니다.
        return "myPage";
    }

    // 사용자 정보 수정 처리 (POST 요청)
    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        // 사용자가 수정한 정보로 사용자 업데이트
        userService.updateUser(user);

        // 업데이트 후 마이페이지로 리다이렉트
        return "redirect:/user/myPage";
    }

}
