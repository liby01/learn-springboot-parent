package com.example.mybatis.mapper;

import com.example.mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAll();

    List<User> selectById(Long id);

    int insert(User user);

    int update(User user);

    int deleteById(Long id);

    @Select({
            "<script>",
            "select * from t_user",
            "<where>",
            "  <if test='q != null and q.trim() != \"\"'>",
            "    name like concat('%', #{q}, '%')",
            "  </if>",
            "</where>",
            "order by id",
            "limit #{size} offset #{offset}",
            "</script>"
    })
    List<User> selectPage(@Param("offset") int offset,
                          @Param("size") int size,
                          @Param("q") String q);

    @Select("select count(*) from t_user")
    int countAll();

    @Select({
            "<script>",
            "select count(*) from t_user",
            "<where>",
            "  <if test='q != null and q.trim() != \"\"'>",
            "    name like concat('%', #{q}, '%')",
            "  </if>",
            "</where>",
            "</script>"
    })
    int countByKeyword(@Param("q") String q);
}
