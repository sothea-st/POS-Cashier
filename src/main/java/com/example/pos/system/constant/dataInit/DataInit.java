package com.example.pos.system.constant.dataInit;

import com.example.pos.system.domain.User;
import com.example.pos.system.layer.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData(){
        long userCount = userRepository.count();
        String userIdStr = "";
        userCount++;
        if( userCount < 10 ) {
            userIdStr="000"+userCount;
        } else if ( userCount < 100 ) {
            userIdStr="00"+userCount;
        } else if ( userCount < 1000 ) {
            userIdStr="0"+userCount;
        } else {
            userIdStr="0"+userCount;
        }
        if( userRepository.count() == 0 ) {
            User user = new User();
            user.setFullName("Admin");
            user.setUserCode(userIdStr);
            user.setPassword(passwordEncoder.encode("TT@126$kh#"));
            user.setRole(1);
            userRepository.save(user);
        }


//        var user = new User()
//                .setFullName(input.getFullName())
//                .setUserCode(userIdStr)
//                .setPassword(passwordEncoder.encode(input.getPassword()));
//        user.setRole(input.getRole());
//        return userRepository.save(user);

    }
}
