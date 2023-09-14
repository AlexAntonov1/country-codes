package ru.antonovak.countrycodes.provider;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.antonovak.countrycodes.feignclients.CountryIoClient;
import ru.antonovak.countrycodes.model.CountryPhoneCode;

/**
 * Поставляет сформированные данные из клиента country.io
 *
 * @author Alex Antonov {alexantonov145@gmail.com}
 */
@Slf4j
@Service
public class CountryIoDataProvider {
  private final CountryIoClient countryIoClient;

  public CountryIoDataProvider(@NonNull CountryIoClient countryIoClient) {
    this.countryIoClient = Objects.requireNonNull(countryIoClient, "countryIoClient");
  }

  public List<CountryPhoneCode> getData() {
    log.info("getData()");
    Map<String, String> countryNames = getCountryNames();
    Map<String, String> countryPhoneCodes = getCountryPhoneCodes();
    return countryNames.entrySet()
        .stream()
        .map(entry -> CountryPhoneCode.builder()
            .countryCode(entry.getKey())
            .countryName(entry.getValue())
            .phoneCode(countryPhoneCodes.get(entry.getKey()))
            .build())
        .collect(Collectors.toList());
  }

  private Map<String, String> getCountryPhoneCodes() {
    log.info("CountryIoClient getCountryPhoneCodes()");
    return countryIoClient.getCountryPhoneCodes();
  }

  private Map<String, String> getCountryNames() {
    log.info("CountryIoClient getCountryNames()");
    return countryIoClient.getCountryNames();
  }
}
