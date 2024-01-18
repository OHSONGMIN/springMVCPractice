package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) { //String으로 반환하면 Model이 필요함
        model.addAttribute("data", "hello!");

        return "response/hello";
        //@Controller면서 String을 반환하면 return String이 뷰의 논리적인 이름이 된다.
    } //이때 @ResponseBody를 하면 뷰로 가지 않고 문자로 reponse/hello라고 덜렁 보일 것이다.

    //Controller의 경로 이름과 뷰의 논리적 이름이 똑같으면
    //그리고 아무것도 반환을 안하면
    //response/hello이 논리적 뷰의 이름으로 진행이 되어버린다.
    @RequestMapping("/response/hello") //권장X
    public void responseViewV3(Model model) { //String으로 반환하면 Model이 필요함
        model.addAttribute("data", "hello!");

    }

}
