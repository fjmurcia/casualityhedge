package coding.hedger.business;

import coding.hedger.model.Instrument;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class HedgerExecutor {

    private Map<String,Instrument> instrumentsPorfolio = new HashMap<>();


    enum MktData {
        POSITION, PRICE, BETAS
    }

    public static void main(String[] args) throws InterruptedException {
        HedgerExecutor hedgerExecutor = new HedgerExecutor();
        hedgerExecutor.loadInitialMarketData();
        while(true) {
            hedgerExecutor.loadPrices();
            hedgerExecutor.loadPosition();
            hedgerExecutor.calculateHedge();
            Thread.sleep(30000 );
        }//While

    }

    private void loadInitialMarketData() {
        loadPosition();
        loadBetas();
        loadPrices();
    }

    private void loadBetas()
    {
        MarketUtils.fillMktData(instrumentsPorfolio, HedgerExecutor.class.getClassLoader().getResourceAsStream("betas.csv"), MktData.BETAS);
    }
    private void loadPosition()
    {
        MarketUtils.fillMktData(instrumentsPorfolio, HedgerExecutor.class.getClassLoader().getResourceAsStream("positions.csv"), MktData.POSITION);
    }

    private void loadPrices()
    {
        MarketUtils.fillMktData(instrumentsPorfolio, HedgerExecutor.class.getClassLoader().getResourceAsStream("prices.csv"), MktData.PRICE);
    }

    private void calculateHedge() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new java.util.Date());
        System.out.println(timeStamp);
        for (Instrument instrument : instrumentsPorfolio.values()) {
            System.out.println(instrument);
        }
    }

}