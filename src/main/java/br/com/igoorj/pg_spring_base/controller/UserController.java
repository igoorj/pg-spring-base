package br.com.igoorj.pg_spring_base.controller;

import br.com.igoorj.pg_spring_base.documents.User;
import br.com.igoorj.pg_spring_base.dto.UserAverageAgeDTO;
import br.com.igoorj.pg_spring_base.dto.UserCityCountDTO;
import br.com.igoorj.pg_spring_base.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(service.create(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }


    // 🔎 Busca avançada
    @GetMapping("/search")
    public ResponseEntity<List<User>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) String city) {

        return ResponseEntity.ok(service.search(name, minAge, city));
    }

    // 📊 Aggregations
    @GetMapping("/count-by-city")
    public ResponseEntity<List<UserCityCountDTO>> countByCity() {
        return ResponseEntity.ok(service.countByCity());
    }

    @GetMapping("/average-age-by-city")
    public ResponseEntity<List<UserAverageAgeDTO>> averageAgeByCity() {
        return ResponseEntity.ok(service.averageAgeByCity());
    }
}
