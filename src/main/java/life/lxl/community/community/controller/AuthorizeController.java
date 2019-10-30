package life.lxl.community.community.controller;

import life.lxl.community.community.dto.AccessTokenDTO;
import life.lxl.community.community.dto.GithupUser;
import life.lxl.community.community.provider.GithupProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



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

    @GetMapping("/callback")
    public String callback(@RequestParam(name  ="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        String accessToken = githupProvider.getAccessToken(accessTokenDTO);
        GithupUser user = githupProvider.getUser(accessToken);
        System.out.println(user.getName());
        return  "index";
    }
}
