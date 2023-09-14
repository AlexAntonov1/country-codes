package ru.antonovak.countrycodes.dao;

import static java.util.Objects.requireNonNull;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;
import ru.antonovak.countrycodes.model.CountryPhoneCode;

/**
 * Dao для работы с таблицей в бд "country_phone_code"
 *
 * @author Alex Antonov {alexantonov145@gmail.com}
 */
@Repository
@Slf4j
public class CountryPhoneCodeDao {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final JdbcTemplate jdbcTemplate;
  private final TransactionTemplate transactionTemplate;

  public CountryPhoneCodeDao(@NonNull NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                             @NonNull TransactionTemplate transactionTemplate) {
    this.namedParameterJdbcTemplate = requireNonNull(namedParameterJdbcTemplate, "namedParameterJdbcTemplate");
    this.jdbcTemplate = requireNonNull(namedParameterJdbcTemplate.getJdbcTemplate(), "jdbcTemplate");
    this.transactionTemplate = requireNonNull(transactionTemplate, "transactionTemplate");
  }


  public void reloadData(List<CountryPhoneCode> countryPhoneCodes) {
    log.info("reloadData() countryPhoneCodes={}", countryPhoneCodes);
    transactionTemplate.executeWithoutResult((transactionStatus) -> {
      jdbcTemplate.execute("TRUNCATE TABLE country_phone_code");
      countryPhoneCodes.forEach(
          countryPhoneCode -> jdbcTemplate.update("INSERT INTO country_phone_code VALUES (?, ?, ?)",
              countryPhoneCode.getCountryName(),
              countryPhoneCode.getCountryCode(),
              countryPhoneCode.getPhoneCode())
      );
    });
  }

  public Optional<String> getPhoneCodeByCountryName(String countryName) {
    log.info("getPhoneCodeByCountryName() countryName={}", countryName);
    try {
      return Optional.of(namedParameterJdbcTemplate.queryForObject(
          "SELECT phone_code FROM country_phone_code WHERE COUNTRY_NAME = :countryName",
          new MapSqlParameterSource().addValue("countryName", countryName), String.class));
    } catch (EmptyResultDataAccessException e) {
      log.warn("Phone code not found", e);
      return Optional.empty();
    }
  }
}
