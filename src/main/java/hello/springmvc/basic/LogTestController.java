package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //lombok이 제공하는 어노테이션 을 추가하면
@RestController
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass());
    //를 작성하지 않아도 된다.

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        //log.info("trace log="+ name);
        //해도 되지만 쓰면 안됨
        //왜냐하면 로그 레벨을 debug라고 설정했을 때 위와 같이 trace를 적으면 trace는 출력이 안되지만
        // + 연산이 일어나고, 연산이 일어나면서 메모리도 사용하고 CPU도 사용하는 것
        // -> 근데 출력은 안하네... 쓸모없는 리소스를 사용..

        log.trace("trace log={}", name);
        //이렇게 하면 문자 더하기가 아니고 그냥 메서드를 호출하는 것이고 파라미터만 넘기는 것
        // -> 아무 연산이 일어나지 않는다.
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
