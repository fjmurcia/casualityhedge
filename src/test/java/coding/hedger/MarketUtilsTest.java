package coding.hedger;

import coding.hedger.business.HedgerExecutor;
import coding.hedger.model.Instrument;
import coding.hedger.utils.MarketUtils;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class MarketUtilsTest {

    @Test
    public void testLoadBeta() {
        Map<String, Instrument> instrumentsPorfolio = new TreeMap<>();
        MarketUtils.fillMktData(instrumentsPorfolio, HedgerExecutor.class.getClassLoader().getResourceAsStream("betas.csv"), MarketUtils.MktData.BETAS);

        assertEquals(instrumentsPorfolio.get("AAPL").getBeta(),1.2d, 0);
    }

}