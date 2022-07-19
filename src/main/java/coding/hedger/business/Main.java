package coding.hedger.business;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        HedgerExecutor hedgerExecutor = new HedgerExecutor();
        hedgerExecutor.loadInitialMarketData();
        hedgerExecutor.printPortfolio();
        hedgerExecutor.calculateBetaPortfolio();
        while(true) {
            hedgerExecutor.loadPrices();
            hedgerExecutor.loadPosition();
            hedgerExecutor.applyHedge();
            Thread.sleep(3000 );
        }

    }

}
