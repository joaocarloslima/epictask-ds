package br.com.etecia.epictask.service;

import br.com.etecia.epictask.model.User;
import br.com.etecia.epictask.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        var user = super.loadUser(userRequest);
        return register(user);
    }

    public User register(OAuth2User oAuth2User) {
        var user = userRepository.findByEmail(oAuth2User.getAttribute("email"));
        if (user.isEmpty()){
            var newUser = new User();
            newUser.setEmail(oAuth2User.getAttribute("email"));
            newUser.setName(oAuth2User.getAttribute("name"));
            newUser.setAvatar(oAuth2User.getAttribute("avatar_url"));
            return userRepository.save(newUser);
        }
        return user.get();
    }
}
