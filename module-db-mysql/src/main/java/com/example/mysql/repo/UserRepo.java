package com.example.mysql.repo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
@Repository
public class UserRepo {
    private final JdbcTemplate jdbc;

    public UserRepo(JdbcTemplate jdbc) { this.jdbc = jdbc; }
    public List<Map<String,Object>> list() {
        return jdbc.queryForList("select id,name from t_user order by id");
    }
}
