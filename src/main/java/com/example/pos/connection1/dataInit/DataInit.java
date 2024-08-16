package com.example.pos.connection1.dataInit;

import com.example.pos.connection1.entity.User;
import com.example.pos.connection1.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final UserRepository userRepository;


    @PostConstruct
    public void initData(){
        User user = new User();
    }
}
