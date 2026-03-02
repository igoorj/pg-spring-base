package br.com.igoorj.pg_spring_base.repository;

import br.com.igoorj.pg_spring_base.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserCustomRepository {

    Optional<User> findTopByEmailOrderByCreatedAtDesc(String email);

    List<User> findByCity(String city);

    List<User> findByAgeGreaterThan(Integer age);
}
