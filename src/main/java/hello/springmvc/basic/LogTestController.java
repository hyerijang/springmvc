package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller : 원래 뷰 이름 리턴
@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass()); // TIP : 대신 @Slf4j 쓰면 됨

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        //기존 방식
        System.out.println("name = " + name);

        //로그 적용
        // {}는 치환된다.

        //로그의 레벨을 정할 수 있다.
        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info(" info log = {}", name);
        log.warn(" warn log = {}", name);
        log.error("error log = {}", name);

        return "ok";
    }
}
