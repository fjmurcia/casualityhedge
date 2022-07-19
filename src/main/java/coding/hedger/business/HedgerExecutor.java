package coding.hedger.business;

import coding.hedger.model.Instrument;
import coding.hedger.utils.MarketUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class HedgerExecutor {

    private Map<String,Instrument> instrumentsPorfolio = new HashMap<>();

    private int spyHedge = 0;

    protected void printPortfolio() {
        for(Instrument instrument : instrumentsPorfolio.values())
        {
            System.out.println(instrument);
        }
    }

    protected void applyHedge() {
        //calculate
        spyHedge = 0;
        for (Instrument instrument : instrumentsPorfolio.values()) {
            spyHedge += (int) Math.round((-1*instrument.getBeta()*instrument.getPrice()*instrument.getPosition())/(instrumentsPorfolio.get("SPY").getPrice()));
        }
        //apply
        Instrument spyIndex = instrumentsPorfolio.get("SPY");
        int initialPos = spyIndex.getPosition();
        if(Math.abs(spyHedge)>0)
        {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new java.util.Date());
            System.out.printf("%n%s",timeStamp);
            spyIndex.setPosition(spyIndex.getPosition()+spyHedge);
            System.out.printf("%nHedge exec. done. SPY Curr:[%d] Adj:[%d] Fin:[%d]%n", initialPos, spyIndex.getPosition()-initialPos, spyIndex.getPosition());
            calculateBetaPortfolio();
        }
    }

    protected void calculateBetaPortfolio() {
        double totalValPortfolio=0.0;
        for (Instrument instrument : instrumentsPorfolio.values()) {
            totalValPortfolio += instrument.getPosition() * instrument.getPrice();
        }
        double betaPortfolio=0.0;
        for (Instrument instrument : instrumentsPorfolio.values()) {
            betaPortfolio += instrument.getBeta()*((instrument.getPosition() * instrument.getPrice())/(totalValPortfolio));
        }
        System.out.printf("Beta Portfolio: %5.4f %n", betaPortfolio);
    }


    protected void loadInitialMarketData() {
        loadPosition();
        loadBetas();
        loadPrices();
    }

    protected void loadBetas()
    {
        MarketUtils.fillMktData(instrumentsPorfolio, HedgerExecutor.class.getClassLoader().getResourceAsStream("betas.csv"), MarketUtils.MktData.BETAS);
    }
    protected void loadPosition()
    {
        Instrument spyIndex = instrumentsPorfolio.get("SPY");
        int initialPos=0;
        if(spyIndex!=null) {
            initialPos = spyIndex.getPosition();
        }
        MarketUtils.fillMktData(instrumentsPorfolio, HedgerExecutor.class.getClassLoader().getResourceAsStream("positions.csv"), MarketUtils.MktData.POSITION);
        if(spyIndex!=null)
        {
            spyIndex.setPosition(initialPos);
        }
    }

    protected void loadPrices()
    {
        MarketUtils.fillMktData(instrumentsPorfolio, HedgerExecutor.class.getClassLoader().getResourceAsStream("prices.csv"), MarketUtils.MktData.PRICE);
    }


}