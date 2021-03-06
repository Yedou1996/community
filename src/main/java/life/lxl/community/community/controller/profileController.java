package life.lxl.community.community.controller;

import life.lxl.community.community.dto.PaginationDTO;
import life.lxl.community.community.model.User;
import life.lxl.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class profileController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("/profile/{action}")
    public  String profile(HttpServletRequest request,
                           @PathVariable(name = "action") String action,
                           @RequestParam(name = "page" ,defaultValue = "1") Integer page,
                           @RequestParam(name = "size" ,defaultValue = "5") Integer size,
                           Model model){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }
        if ("question".equals(action)){
            model.addAttribute("section","question");
            model.addAttribute("sectionName","我的提问");
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
