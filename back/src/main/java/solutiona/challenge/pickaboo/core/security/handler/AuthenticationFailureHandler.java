package solutiona.challenge.pickaboo.core.security.handler;

import solutiona.challenge.pickaboo.core.common.ExceptionDto;
import solutiona.challenge.pickaboo.core.exception.ErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.minidev.json.JSONValue;

public class AuthenticationFailureHandler {
    protected void errorResponse(
            HttpServletResponse response,
            ErrorCode errorCode
    ) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorCode.getHttpStatus().value());

        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("data", null);
        result.put("error", ExceptionDto.of(errorCode));

        response.getWriter().write(JSONValue.toJSONString(result));
    }
}
