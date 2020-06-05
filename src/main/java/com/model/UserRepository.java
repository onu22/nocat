
package com.model;

import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UserRepository {

    private static final String SQL_FIND_ALL = "SELECT * FROM USERS";
    private static final String SQL_FIND_BY_DEVICEID = "SELECT * FROM USERS WHERE DEVICEID = :deviceId";
    private static final String SQL_INSERT = "INSERT INTO USERS (NAME, SPECIES) values(:name, :species)";
    private static final String SQL_UPDATELOCATION = "UPDATE USERS SET LATLONG = :latLong WHERE DEVICEID = :deviceId";

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public int save(User user) {
        final SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("deviceId", user.getDeviceId())
                .addValue("latLong", user.getLatLong());
        return jdbcTemplate.update(SQL_INSERT, paramSource);
    }

    public void updateDeviceLocation(String deviceId, String location ) {
        try {
            User user = findByDeviceId(deviceId);
            if (user != null){
                final SqlParameterSource paramSource = new MapSqlParameterSource()
                        .addValue("deviceId", user.getDeviceId())
                        .addValue("latLong", location);
                 jdbcTemplate.update(SQL_UPDATELOCATION, paramSource);
            }
        }
        catch (EmptyResultDataAccessException ex) {
           //
        }
    }

    public User findByDeviceId(String deviceId) {
        try {
            final SqlParameterSource paramSource = new MapSqlParameterSource("deviceId", deviceId);
            return jdbcTemplate.queryForObject(SQL_FIND_BY_DEVICEID, paramSource, ROW_MAPPER);
        }
        catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Iterable<User> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, ROW_MAPPER);
    }

}
