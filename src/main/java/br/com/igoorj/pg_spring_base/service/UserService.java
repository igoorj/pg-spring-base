package br.com.igoorj.pg_spring_base.service;


import br.com.igoorj.pg_spring_base.documents.User;
import br.com.igoorj.pg_spring_base.dto.UserAverageAgeDTO;
import br.com.igoorj.pg_spring_base.dto.UserCityCountDTO;
import br.com.igoorj.pg_spring_base.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(User user) {
        user.setCreatedAt(LocalDateTime.now());
        return this.userRepository.save(user);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public List<User> search(String name, Integer minAge, String city) {
        return this.userRepository.findAdvanced(name, minAge, city);
    }

    public List<UserCityCountDTO> countByCity() {
        return userRepository.countUsersByCity();
    }

    public List<UserAverageAgeDTO> averageAgeByCity() {
        return userRepository.averageAgeByCity();
    }
}
