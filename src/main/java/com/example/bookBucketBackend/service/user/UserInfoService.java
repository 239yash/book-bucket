package com.example.bookBucketBackend.service.user;

import com.example.bookBucketBackend.entity.UserInfo;
import com.example.bookBucketBackend.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = repository.findByName(userName);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + userName));
    }

    public String addUser(UserInfo userInfo) {
        if (userInfo.getUserName() == null) {
            return "Kindly provide userName!";
        }
        String userName = userInfo.getUserName();
        Optional<UserInfo> userDetail = repository.findByName(userName);
        if (userDetail.isPresent()) {
            return "userName already taken, Please choose a different userName!";
        }
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }
}
