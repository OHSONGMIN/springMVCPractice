package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    //@RequestMapping("/hello-basic", "/hello-go")
    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션 (코드보기)
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable String userId
     */
    @GetMapping("/mapping/{userId}") //url자체게 값이 들어가있는 것
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId={}", data);
        return "ok";
    }
    /* 처럼 쓸 수도 있다.
    public String mappingPath(@PathVariable String userId){
        log.info("mappingPath userId={}", data);
        return "ok";
    } */

    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long
            orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode" //모드가 없어야 한다
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"} //mode가 debug이거나 data가 good이어야 한다
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug") //params = "mode=debug" 가 있어야 호출된다
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug" //header에 K-V가 맞아야하고
     * headers="mode!=debug" (! = ) //header에 K-V가 맞지않아야하고
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    //@PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE) 처럼 써도 됨
    //header의 Content-Type이 "application.json"인 경우에만 호출
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type //accept 헤더가 필요함
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    //@PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE) 처럼 써도 됨
    //얘를 text/html을 생산하는데 accept가 application/json이라면 406 오류가 나타남
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
