package ru.antonovak.countrycodes.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.antonovak.countrycodes.dao.CountryPhoneCodeDao;

/**
 * Тесты для {@link GetPhoneCodeByCountryNameCommand}
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class GetPhoneCodeByCountryNameCommandTest {
	private CountryPhoneCodeDao countryPhoneCodeDao;
	private GetPhoneCodeByCountryNameCommand getPhoneCodeByCountryNameCommand;

	@BeforeAll
	void setup() {
		countryPhoneCodeDao = Mockito.mock(CountryPhoneCodeDao.class);
		getPhoneCodeByCountryNameCommand = new GetPhoneCodeByCountryNameCommand(countryPhoneCodeDao);
	}

	@Test
	void should_return_russian_code() {
		when(countryPhoneCodeDao.getPhoneCodeByCountryName("Russia")).thenReturn(Optional.of("7"));
		Optional<String> result = getPhoneCodeByCountryNameCommand.execute("Russia");
		Assertions.assertTrue(result.isPresent());
		Assertions.assertEquals("7", result.get());
	}

	@Test
	void should_return_empty_optional() {
		when(countryPhoneCodeDao.getPhoneCodeByCountryName("MythicalCountryName")).thenReturn(Optional.empty());
		Optional<String> result = getPhoneCodeByCountryNameCommand.execute("MythicalCountryName");
		Assertions.assertTrue(result.isEmpty());
	}
}
