package coding.hedger.business;

import coding.hedger.model.Instrument;

import java.util.ArrayList;
import java.util.List;

public class HedgerExecutor {

    private List<Instrument> instrumentsPorfolio = new ArrayList();

    enum MktData {
        POSITION, PRICE, BETAS
    }

    public static void main(String[] args) {
        HedgerExecutor hedgerExecutor = new HedgerExecutor();
        hedgerExecutor.loadMarketData();
    }

    private void loadMarketData()
    {
        //Position file goes first
        MarketUtils.fillMktData(instrumentsPorfolio, HedgerExecutor.class.getClassLoader().getResourceAsStream("positions.csv"), MktData.POSITION);
        MarketUtils.fillMktData(instrumentsPorfolio, HedgerExecutor.class.getClassLoader().getResourceAsStream("prices.csv"), MktData.PRICE);
        MarketUtils.fillMktData(instrumentsPorfolio, HedgerExecutor.class.getClassLoader().getResourceAsStream("betas.csv"), MktData.BETAS);

        for (Instrument instrument : instrumentsPorfolio) {
            System.out.println(instrument);
        }
    }



}