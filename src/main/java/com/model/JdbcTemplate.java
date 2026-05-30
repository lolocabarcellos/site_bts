package com.model;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class JdbcTemplate {

    private org.springframework.jdbc.core.JdbcTemplate jdbc;

    public JdbcTemplate(DataSource dataSource) {
        this.jdbc = new org.springframework.jdbc.core.JdbcTemplate(dataSource);
    }

    public void update(String sql, Object... params) {
        jdbc.update(sql, params);
    }

    public Map<String, Object> queryForMap(String sql, Object... params) {
        return jdbc.queryForMap(sql, params);
    }

    public List<Map<String, Object>> queryForList(String sql, Object... params) {
        return jdbc.queryForList(sql, params);
    }
}