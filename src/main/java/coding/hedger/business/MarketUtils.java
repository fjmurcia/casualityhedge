package coding.hedger.business;

import coding.hedger.model.Instrument;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MarketUtils {

    public static void fillMktData(List<Instrument> instruments, InputStream is, HedgerExecutor.MktData type) {
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            reader.readLine();//remove first line / headers
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String ticker = values[0];
                switch (type) {
                    case POSITION:
                        int position = Integer.parseInt(values[1]);
                        instruments.add(new Instrument(ticker, position));
                        break;
                    case BETAS:
                        double beta = Double.parseDouble(values[1]);
                        Instrument instrument = getByTicker(instruments, ticker);
                        if (instrument != null) {
                            instrument.setBeta(beta);
                        }
                        break;
                    case PRICE:
                        double price = Double.parseDouble(values[1]);
                        instrument = getByTicker(instruments, ticker);
                        if (instrument != null) {
                            instrument.setPrice(price);
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Instrument getByTicker(List<Instrument> instruments, String ticker) {
        for (Instrument instrument : instruments) {
            if (instrument.getTicker().equalsIgnoreCase(ticker)) {
                return instrument;
            }
        }
        return null;
    }
}
