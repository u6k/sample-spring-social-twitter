
package me.u6k.sample.sample_spring_social_twitter.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import me.u6k.sample.sample_spring_social_twitter.exception.UsernameAlreadyInUseException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
public class JdbcAccountRepository implements AccountRepository {

    private final JdbcTemplate jdbcTemplate;

    private final PasswordEncoder passwordEncoder;

    @Inject
    public JdbcAccountRepository(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        log.trace("#ctor: start");

        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;

        log.trace("#ctor: end");
    }

    @Transactional
    public void createAccount(Account user) throws UsernameAlreadyInUseException {
        log.trace("#createAccount: start: user={}", user);

        try {
            log.trace("insert into account: start");
            jdbcTemplate.update(
                "insert into Account (firstName, lastName, username, password) values (?, ?, ?, ?)",
                user.getFirstName(), user.getLastName(), user.getUsername(),
                passwordEncoder.encode(user.getPassword()));
            log.trace("insert into account: success");
        } catch (DuplicateKeyException e) {
            log.warn("fail insert into account", e);
            throw new UsernameAlreadyInUseException(user.getUsername());
        }

        log.trace("#createAccount: end");
    }

    public Account findAccountByUsername(String username) {
        log.trace("#findAccountByUsername: start: username={}", username);

        log.trace("select account: start");
        Account result = jdbcTemplate.queryForObject("select username, firstName, lastName from Account where username = ?",
            new RowMapper<Account>() {

                public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Account(rs.getString("username"), null, rs.getString("firstName"), rs
                        .getString("lastName"));
                }
            }, username);
        log.trace("select account: success: result={}", result);

        return result;
    }

}
