package life.lxl.community.community.controller;

import life.lxl.community.community.dto.PaginationDTO;
import life.lxl.community.community.dto.QuestionDTO;
import life.lxl.community.community.mapper.UserMapper;
import life.lxl.community.community.model.User;
import life.lxl.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page" ,defaultValue = "1") Integer page,
                        @RequestParam(name = "size" ,defaultValue = "6") Integer size){
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length!=0)
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if (user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        PaginationDTO pagination = questionService.list(page,size);

        model.addAttribute("pagination",pagination);
        return "index";
    }
}