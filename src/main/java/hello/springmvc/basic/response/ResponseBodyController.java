package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController // NOTE: @RestController = @ResponseBody + @Controller , API 만들때 일반적으로 사용
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }

    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData data = new HelloData();
        data.setUsername("userA");
        data.setAge(20);
        return new ResponseEntity<HelloData>(data, HttpStatus.OK); //HTTP 상태코드 변경 가능
    }

    // NOTE : @ResponseStatus 로 상태코드 설정 가능
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData data = new HelloData();
        data.setUsername("userA");
        data.setAge(20);
        return data;

    }


}
