package ru.antonovak.countrycodes.command;

import static java.util.Objects.requireNonNull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.antonovak.countrycodes.dao.CountryPhoneCodeDao;
import ru.antonovak.countrycodes.provider.CountryIoDataProvider;

/**
 * Перезагружает данные из country.io в базу данных
 *
 * @author Alex Antonov {alexantonov145@gmail.com}
 */
@Component
@Slf4j
public class ReloadDataCommand {
  private final CountryIoDataProvider countryIoDataProvider;
  private final CountryPhoneCodeDao countryPhoneCodeDao;

  public ReloadDataCommand(@NonNull CountryIoDataProvider countryIoDataProvider,
                           @NonNull CountryPhoneCodeDao countryPhoneCodeDao) {
    this.countryIoDataProvider = requireNonNull(countryIoDataProvider, "countryIoDataProvider");
    this.countryPhoneCodeDao = requireNonNull(countryPhoneCodeDao, "countryPhoneCodeDao");
  }

  /**
   * Отрабатывает в 2 часа ночи по МСК
   */
  @Scheduled(cron = "0 2 * * * ?", zone = "Europe/Moscow")
  public void execute() {
    log.info("execute()");
    countryPhoneCodeDao.reloadData(countryIoDataProvider.getData());
  }
}
