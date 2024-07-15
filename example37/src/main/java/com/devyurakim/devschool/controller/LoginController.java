package com.devyurakim.devschool.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    //SecurityFilterChain에 설정한 /login?error=true인 경우, error에 true 대입
    //SecurityFilterChain에 설정한 /login?logout=true인 경우, logout에 true 대입
    public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "logout", required = false) String logout, Model model) {
        String errorMessage = null;
        String logoutMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            logoutMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("logoutMessage", logoutMessage);
        return "login.html";
    }

    //enabled CSRF 때문에 logout logic을 새로 작성
    /* Question: the default logout function provided by the Spring Security is a POST request and for every post request we require CSRF Token,
    the default Logout Function does contain that CSRF token hence when we fire "/logout" end point we encounter the 403 error.
    To mitigate this issue we wrote the custom logout function, this is a GET request so automatically the CSRF intercepter will ignore this logout request
    and we don't need to handle the CSRF token management for this request.
    >> Answer: To keep things simple I built it this way.
    For enterprise apps, they should handle logout using POST and make sure to send the CSRF token as well.
    >> Answer: To keep things simple, inside our app, I leveraged /login API and cleared all the session details as well.
    The approach to include the CSRF token during the /logout operation is mentioned in the below documentation.
    https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#csrf-considerations-logout
    */
    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }


}