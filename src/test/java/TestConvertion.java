import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import fr.iut.Convertion;
import fr.iut.Money;

public class TestConvertion {

	@Mock Convertion conv;
	@InjectMocks private Money money;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		System.out.println(money.getConvertion());
	}

}
