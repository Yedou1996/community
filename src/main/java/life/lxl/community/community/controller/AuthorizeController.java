package life.lxl.community.community.controller;

import life.lxl.community.community.dto.AccessTokenDTO;
import life.lxl.community.community.provider.GithupProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class AuthorizeController {
    @Autowired
    private GithupProvider githupProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name  ="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("77e29ec87ed5c314b1e5");
        accessTokenDTO.setClient_secret("0d0f793514d17edb1cb956d5b756e71b41f86112");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        githupProvider.getAccessToken(accessTokenDTO);
    return  "index";
    }
}
