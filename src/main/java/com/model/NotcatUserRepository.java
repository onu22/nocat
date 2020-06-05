
package com.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class NotcatUserRepository {

    private static final String SQL_FIND_ALL = "SELECT * FROM nocatuser";
    private static final String SQL_FIND_BY_DEVICEID = "SELECT * FROM nocatuser WHERE DEVICEID = :deviceId";
    private static final String SQL_INSERT = "INSERT INTO nocatuser (deviceId, userName) values(:deviceId, :userName)";
    private static final String SQL_UPDATELOCATION = "UPDATE nocatuser SET LATLONG = :latLong WHERE DEVICEID = :deviceId";

    private static final BeanPropertyRowMapper<NotcatUser> ROW_MAPPER = new BeanPropertyRowMapper<>(NotcatUser.class);

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public int save(NotcatUser user) {
        final SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("deviceId", user.getDeviceId())
                .addValue("latLong", user.getLatLong());
        return jdbcTemplate.update(SQL_INSERT, paramSource);
    }

    public void updateDeviceLocation(String deviceId, String location ) {
        try {
            NotcatUser user = findByDeviceId(deviceId);
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

    public NotcatUser findByDeviceId(String deviceId) {
        try {
            final SqlParameterSource paramSource = new MapSqlParameterSource("deviceId", deviceId);
            return jdbcTemplate.queryForObject(SQL_FIND_BY_DEVICEID, paramSource, ROW_MAPPER);
        }
        catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Iterable<NotcatUser> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, ROW_MAPPER);
    }

}
