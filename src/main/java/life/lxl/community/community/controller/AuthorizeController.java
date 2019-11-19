package life.lxl.community.community.controller;

import life.lxl.community.community.dto.AccessTokenDTO;
import life.lxl.community.community.dto.GithupUser;
import life.lxl.community.community.mapper.UserMapper;
import life.lxl.community.community.model.User;
import life.lxl.community.community.provider.GithupProvider;
import life.lxl.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.devtools.tunnel.server.HttpTunnelServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Controller
public class AuthorizeController {
    @Autowired
    private GithupProvider githupProvider;
    @Value("${githup.client.id}")
    private String clientId;
    @Value("${githup.client.secret}")
    private String client_secret;
    @Value("${githup.Redirect_uri}")
    private String redirect_uri;
   @Autowired
   private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name  ="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        String accessToken = githupProvider.getAccessToken(accessTokenDTO);
        GithupUser githupUser = githupProvider.getUser(accessToken);
       if (githupUser != null){
           User user = new User();
           String token = UUID.randomUUID().toString();
           user.setToken(token);
           user.setAccountId(String.valueOf(githupUser.getId()));
           user.setName(githupUser.getName());
           user.setAvatarUrl(githupUser.getAvatar_url());
           userService.createOrUpdate(user);

           response.addCookie(new Cookie("token",token));


           //request.getSession().setAttribute("user",githupUser);
           return "redirect:/";
       }else {
           return "redirect:/";
       }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
