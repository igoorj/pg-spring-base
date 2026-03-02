package br.com.igoorj.pg_spring_base.repository;

import br.com.igoorj.pg_spring_base.documents.User;
import br.com.igoorj.pg_spring_base.dto.UserAverageAgeDTO;
import br.com.igoorj.pg_spring_base.dto.UserCityCountDTO;

import java.util.List;

public interface UserCustomRepository {

    List<User> findAdvanced(String name, Integer minAge, String city);

    List<UserCityCountDTO> countUsersByCity();

    List<UserAverageAgeDTO> averageAgeByCity();
}
