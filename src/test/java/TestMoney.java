import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.iut.Convertion;
import fr.iut.Money;

public class TestMoney {

	private Money oneEur;
	private Money oneDoll;
	
	@Mock
	Convertion conv;
	
	@InjectMocks
	private Money money;
	

	@Before
	public void setUp() throws Exception {
		oneEur = new Money(1.0, "EUR");
		oneDoll = new Money(1.0, "USD");
		MockitoAnnotations.initMocks(this);
		when(conv.unit_Convertion("EUR-USD")).thenReturn(1.29);
		when(conv.unit_Convertion("USD-EUR")).thenReturn(1/1.29);
		when(conv.unit_Convertion(" ")).thenThrow(IllegalArgumentException.class);
	}

	@Test
	public void testConstructeurOK() {
		assertThat(oneEur.getAmount(), IsEqual.equalTo(1.0));
		assertThat(oneEur.getCurrency(), IsEqual.equalTo("EUR"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructeurAmountSupOuEgal0() {
		new Money(-1.0, "EUR");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructeurCurrencyNull() {
		new Money(1.0, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructeurCurrencyDiff() {
		new Money(10, "EMG");
	}
	
	
	@Test
	public void testGetter() {
		assertThat(oneEur.getAmount(), IsEqual.equalTo(1.0));
		assertThat(oneEur.getCurrency(), IsEqual.equalTo("EUR"));
	}
	
	@Test
	public void testAddPlus0() {
		oneEur.add(0.0, "EUR");
		assertThat(oneEur.getAmount(), IsEqual.equalTo(1.0));
		oneDoll.add(0.0, "USD");
		assertThat(oneDoll.getAmount(), IsEqual.equalTo(1.0));
	}
	
	@Test
	public void testAddPlus1MemeCurr() {
		oneEur.add(1.0, "EUR");
		assertThat(oneEur.getAmount(), IsEqual.equalTo(2.0));
		oneDoll.add(1.0, "USD");
		assertThat(oneDoll.getAmount(), IsEqual.equalTo(2.0));
	}
	
	@Test
	public void testAddPlus1DiffCurr() {
		oneEur.add(1.0, "USD");
		assertThat(oneEur.getAmount(), IsEqual.equalTo(2.29));
		oneDoll.add(1.0, "EUR");
		assertThat(oneDoll.getAmount(), IsEqual.equalTo(1.0 + 1.0/1.29));
	}

	@Test
	public void testAddMoneyEUR() {
		oneEur.add(oneDoll);
		assertThat(oneEur.getAmount(), IsEqual.equalTo(2.29));
	}
	
	@Test
	public void testAddMoneyUSD() {
		oneDoll.add(oneEur);
		assertThat(oneDoll.getAmount(), IsEqual.equalTo(1.0 + 1.0/1.29));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddMoneyCurr() {
		oneEur.add(1.0, "GBP");
	}
	
	@Test
	public void testUnitConvertionEurUsd() {
		assertThat(conv.unit_Convertion("EUR-USD"), IsEqual.equalTo(1.29));
	}
	
	@Test
	public void testUnitConvertionUsdEur() {
		assertThat(conv.unit_Convertion("USD-EUR"), IsEqual.equalTo(1/1.29));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUnitConvertionErr() {
		conv.unit_Convertion(" ");
	}
	
	@Test
	public void testSubMoins0() {
		oneEur.sub(0.0, "EUR");
		assertThat(oneEur.getAmount(), IsEqual.equalTo(1.0));
		oneDoll.sub(0.0, "USD");
		assertThat(oneDoll.getAmount(), IsEqual.equalTo(1.0));
	}
	
	@Test
	public void testSubMoins1MemeCurr() {
		oneEur.sub(1.0, "EUR");
		assertThat(oneEur.getAmount(), IsEqual.equalTo(0.0));
		oneDoll.sub(1.0, "USD");
		assertThat(oneDoll.getAmount(), IsEqual.equalTo(0.0));
	}
	
	@Test
	public void testSubMoins1DiffCurr() {
		oneEur.sub(1.0, "USD");
		assertThat(oneEur.getAmount(), IsEqual.equalTo(1.0 - 1/1.29));
		oneDoll.sub(1.0, "EUR");
		assertThat(oneDoll.getAmount(), IsEqual.equalTo(1.0 - 1.29));
	}
	
	@Test
	public void testSubMoneyEUR() {
		oneEur.sub(oneDoll);
		assertThat(oneEur.getAmount(), IsEqual.equalTo(1.0 - 1.0/1.29));
	}
	
	@Test
	public void testSubMoneyUSD() {
		oneDoll.sub(oneEur);
		assertThat(oneDoll.getAmount(), IsEqual.equalTo(1.0 - 1.29));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSubMoneyCurr() {
		oneEur.sub(1.0, "GBP");
	}
}
