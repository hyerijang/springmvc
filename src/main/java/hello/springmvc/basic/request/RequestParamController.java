package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        //NOTE: return 타입이 void 인 경우 response에 쓴 값이 웹에 출력됨
        response.getWriter().write("ok");
    }

    /**
     * NOTE: @Controller 에서 String으로 리턴한 값 ResponseBody 에 넣는 법
     * "ok" 가 출력되도록 하는 법 (ResponseBody 에 "ok" 넣는 법)
     * 1. @Controller -> @RestController 로 변경 (전체 클래스에 적용됨)
     * 2. 해당 메서드에 @ResponseBody 추가
     **/
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }


    //NOTE:  HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략 가능
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }


    // NOTE: String , int , Integer 등의 단순 타입이면 @RequestParam 도 생략 가능
    // 이렇게 애노테이션을 완전히 생략해도 되는데, 너무 없는 것도 약간 과하다는 주관적 생각이 있다.
    // @RequestParam 이 있으면 명확하게 요청 파리미터에서 데이터를 읽는 다는 것을 알 수 있다.
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //NOTE: @RequestParam.required : 파라미터 필수 여부
    //기본값이 파라미터 필수( true )이다.

    /**
     * WARN : int age -> null을 int에 입력하는 것은 불가능,
     * 따라서 객체형인 Integer로 변경해야 함(또는 다음에 나오는 defaultValue 사용)
     */

    // WARN : /request-param?username= -> 빈문자로 통과
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age
    ) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }


    // NOTE : 파라미터에  defaultValue 적용
    // defaultValue가 들어가면 required 가 있든 없든 상관 없게됨 ( 값이 있든 없든 디폴트 값이 들어가므로)
    // WARN : defaultValue 는 빈문자의 경우에도 설정한 기본 값 적용 됨
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    // NOTE : 파라미터를 Map으로 조회하기
    // TIP : 파라미터의 값이 1개가 확실하다면 Map 을 사용해도 되지만, 그렇지 않다면 MultiValueMap 을 사용하자.
    // 근데 보통 1개만 쓰지 애매하게 2개 쓰고하지 않음
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap
    ) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

}
