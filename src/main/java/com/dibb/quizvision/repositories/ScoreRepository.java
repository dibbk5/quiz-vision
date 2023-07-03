package com.dibb.quizvision.repositories;

import com.dibb.quizvision.entities.Score;
import com.dibb.quizvision.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByUserEquals(User user);
}
