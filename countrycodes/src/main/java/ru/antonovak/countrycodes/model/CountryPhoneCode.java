package ru.antonovak.countrycodes.model;

import lombok.Builder;
import lombok.Data;


/**
 * Модель для взаимодействия с таблицей в бд "country_phone_code"
 *
 * @author Alex Antonov {alexantonov145@gmail.com}
 */
@Data
@Builder
public class CountryPhoneCode {
  private final String countryCode;
  private final String countryName;
  private final String phoneCode;
}
