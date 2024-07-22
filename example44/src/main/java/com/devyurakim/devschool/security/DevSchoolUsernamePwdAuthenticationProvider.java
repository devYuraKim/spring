package com.devyurakim.devschool.security;

import com.devyurakim.devschool.model.Person;
import com.devyurakim.devschool.model.Roles;
import com.devyurakim.devschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*supports: helps in selecting the appropriate authentication provider (어떤 방식으로 authenticate할 것인지를 인식해서 그에 맞는 provider 제공)
authenticate: implements the specific logic to authenticate users based on the provided credentials and user information. */
@Component
public class DevSchoolUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*UsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities)
    * principal = username(user object도 가능), credential = password, authorities = 부여된 권한들(여기서는 student 하나 정도지만 보통 권한이 여러개여서)
    * */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        //credentials는 password, token, 다른 어떤 종류든지 가능해서 .toString()으로 전환이 필요
        Person person = personRepository.readByEmail(email);
        //if(person!=null && person.getPersonId()>0 && pwd.equals(person.getPwd())){
        if(person!=null && person.getPersonId()>0 && passwordEncoder.matches(pwd, person.getPwd())){
            //return new UsernamePasswordAuthenticationToken(person.getName(), pwd, getGrantedAuthorities(person.getRoles()));
            //return new UsernamePasswordAuthenticationToken(person.getName(), null, getGrantedAuthorities(person.getRoles()));
            return new UsernamePasswordAuthenticationToken(email, null, getGrantedAuthorities(person.getRoles()));
        }else{
            throw new BadCredentialsException("invalid credentials");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Roles roles){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+roles.getRoleName()));
        return grantedAuthorities;
    }

    /*The supports method ensures that the correct AuthenticationProvider is selected based on the type of Authentication object being used.
    In this case, it specifically checks if the Authentication object is of type UsernamePasswordAuthenticationToken,
    indicating that this provider handles username-password authentication requests.*/
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
