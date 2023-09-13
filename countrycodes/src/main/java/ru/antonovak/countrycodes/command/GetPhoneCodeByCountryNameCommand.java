package ru.antonovak.countrycodes.command;

import static java.util.Objects.requireNonNull;
import java.util.Objects;
import java.util.Optional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.antonovak.countrycodes.dao.CountryPhoneCodeDao;

/**
 * Отдает код номера телефона по названию страны
 *
 * @author Alex Antonov {alexantonov145@gmail.com}
 */
@Component
@Slf4j
public class GetPhoneCodeByCountryNameCommand {
  private final CountryPhoneCodeDao countryPhoneCodeDao;

  public GetPhoneCodeByCountryNameCommand(@NonNull CountryPhoneCodeDao countryPhoneCodeDao) {
    this.countryPhoneCodeDao = requireNonNull(countryPhoneCodeDao, "countryPhoneCodeDao");
  }

  public Optional<String> execute(String countryName) {
    log.info("execute() countryName={}", countryName);
    return countryPhoneCodeDao.getPhoneCodeByCountryName(countryName);
  }
}
