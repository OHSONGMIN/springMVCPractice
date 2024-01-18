package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//스트림을 byte코드이다

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    //v1 개선
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//스트림을 byte코드이다
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    //v2를 개선, 스프링의 위의 복잡한 코드를 제공해준다
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        /* 이런 코드를 대신 실행해줄게~ 하고 HttpMessageConverter가 등장
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); */ //스트림을 byte코드이다
        String messageBody = httpEntity.getBody(); //http의 변환된 body를 꺼낼 수 있음
        log.info("messageBody={}", messageBody);

        //return new HttpEntity<>("ok"); //이렇게 해서 마치 HTTP 메시지 자체를 그대로 주고받는 형식으로 만들수있음
        return new ResponseEntity<String>("ok", HttpStatus.CREATED); //상태코드도 넣을 수 있음
    }

    //v3를 개선 @RequestBody에 String이면 HTPP message body 읽어서 여기에 팍 넣어준다
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);

        return "ok"; //이렇게 해서 마치 HTTP 메시지 자체를 그대로 주고받는 형식으로 만들수있음
    }//요청오는건 requestBody 응답나가는건 responseBody
}
