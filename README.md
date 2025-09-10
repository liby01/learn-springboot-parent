# Learn Spring Boot Parent Project

这是一个用于学习的 **Spring Boot 多模块项目**，包含：

- **module-db-mysql**：使用 Spring JDBC (JdbcTemplate) 直接连接 MySQL。
- **module-mybatis**：使用 MyBatis (XML + Mapper) 连接 MySQL。

## 数据库准备
在本地 MySQL 创建一个数据库：
```sql
create database learn character set utf8mb4;
