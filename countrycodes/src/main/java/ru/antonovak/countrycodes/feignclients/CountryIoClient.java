package ru.antonovak.countrycodes.feignclients;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Клиент для работы с api country.io
 *
 * @author Alex Antonov {alexantonov145@gmail.com}
 */
@FeignClient(value = "country-io", url = "${country.io.url}")
public interface CountryIoClient {

  /**
   * @return Map где ключ - код Страны, значение - имя Страны.
   */
  @GetMapping("names.json")
  Map<String, String> getCountryNames();

  /**
   * @return Map где ключ - код Страны, значение - код номера телефона.
   */
  @GetMapping("phone.json")
  Map<String, String> getCountryPhoneCodes();
}
