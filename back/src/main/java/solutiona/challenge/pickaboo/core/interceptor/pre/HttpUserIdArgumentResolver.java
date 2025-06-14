package solutiona.challenge.pickaboo.core.interceptor.pre;

import solutiona.challenge.pickaboo.core.annotation.UserId;
import solutiona.challenge.pickaboo.core.exception.CustomException;
import solutiona.challenge.pickaboo.core.exception.ErrorCode;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.UUID;

@Component
public class HttpUserIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserId.class)
                && parameter.getParameterType().equals(UUID.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        final Object userId = webRequest.getAttribute("userId", NativeWebRequest.SCOPE_REQUEST);

        if (userId == null) {
            throw new CustomException(ErrorCode.INVALID_METHOD_ARGUMENT);
        }

        return UUID.fromString(userId.toString());

    }
}
