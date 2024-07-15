package com.devyurakim.devschool.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        /*Optional.ofNullable() vs Optional<T>
        * null을 반환할 수 있는 object를 다룰 때는 Optional.ofNullable(targetObject)를 사용한다
        * SecurityContextHolder.getContext().getAuthentication().getName()은 반환 타입이 String이기 때문에
        * Optional 객체로 변환 가능한 Optional.ofNullable()을 사용해야 한다*/
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}

/*Optional.ofNullable() vs Optional.of()
* 전자는 null이면 empty Optional을 반환
* 후자는 null이면 NullPointerException 발생*/