package br.com.igoorj.pg_spring_base.repository;

import br.com.igoorj.pg_spring_base.documents.User;
import br.com.igoorj.pg_spring_base.dto.UserAverageAgeDTO;
import br.com.igoorj.pg_spring_base.dto.UserCityCountDTO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserCustomRepositoryImpl  implements UserCustomRepository {

    private final MongoTemplate mongoTemplate;

    public UserCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<User> findAdvanced(String name, Integer minAge, String city) {

        List<Criteria> criteriaList = new ArrayList<>();
        if (name != null) {
            criteriaList.add(Criteria.where("name").regex(name, "i"));
        }
        if (minAge != null) {
            criteriaList.add(Criteria.where("age").gte(minAge));
        }
        if (city != null) {
            criteriaList.add(Criteria.where("city").is(city));
        }

        Criteria criteria = new Criteria();
        if (!criteriaList.isEmpty()) {
            criteria.andOperator(criteriaList.toArray(new Criteria[0]));
        }

        Query query = new Query(criteria);
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public List<UserCityCountDTO> countUsersByCity() {

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("city").count().as("total"),
                Aggregation.project("total").and("_id").as("city")
        );

        AggregationResults<UserCityCountDTO> results = mongoTemplate.aggregate(
                aggregation, "users", UserCityCountDTO.class
        );

        return results.getMappedResults();
    }

    @Override
    public List<UserAverageAgeDTO> averageAgeByCity() {

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("city").avg("age").as("averageAge"),
                Aggregation.project("averageAge").and("_id").as("city")
        );

        AggregationResults<UserAverageAgeDTO> results = mongoTemplate.aggregate(
                aggregation, "users", UserAverageAgeDTO.class
        );

        return results.getMappedResults();
    }
}
