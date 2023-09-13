package ru.antonovak.countrycodes.controller;

import static java.util.Objects.requireNonNull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.antonovak.countrycodes.command.GetPhoneCodeByCountryNameCommand;
import ru.antonovak.countrycodes.command.ReloadDataCommand;

/**
 * Controller для работы с таблицей в бд "country_phone_code"
 *
 * @author Alex Antonov {alexantonov145@gmail.com}
 */
@RestController
@Slf4j
public class CountryPhoneCodesController {

  private final ReloadDataCommand reloadDataCommand;
  private final GetPhoneCodeByCountryNameCommand getPhoneCodeByCountryNameCommand;

  public CountryPhoneCodesController(@NonNull ReloadDataCommand reloadDataCommand,
                                     @NonNull GetPhoneCodeByCountryNameCommand getPhoneCodeByCountryNameCommand) {
    this.reloadDataCommand = requireNonNull(reloadDataCommand, "reloadDataCommand");
    this.getPhoneCodeByCountryNameCommand = requireNonNull(getPhoneCodeByCountryNameCommand, "getPhoneCodeByCountryNameCommand");
  }


  @PostMapping("/reload")
  public void reload() {
    log.info("reload()");
    reloadDataCommand.execute();
  }

  @GetMapping("/code/{COUNTRYNAME}")
  public ResponseEntity<String> getAll(@PathVariable("COUNTRYNAME") String countryName) {
    log.info("getAll() countryName={}", countryName);
    return getPhoneCodeByCountryNameCommand.execute(countryName)
        .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
