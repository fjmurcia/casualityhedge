package coding.hedger.utils;

import coding.hedger.model.Instrument;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MarketUtils {

    public enum MktData {
        POSITION, PRICE, BETAS
    }

    public static void fillMktData(Map<String,Instrument> instrumentsMap, InputStream is, MktData type) {
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            reader.readLine();//remove first line / headers
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String ticker = values[0];
                if(!instrumentsMap.containsKey(ticker))
                {
                    instrumentsMap.put(ticker,new Instrument(ticker));
                }
                switch (type) {
                    case POSITION:
                        int position = Integer.parseInt(values[1]);
                        instrumentsMap.get(ticker).setPosition(position);
                        break;
                    case BETAS:
                        double beta = Double.parseDouble(values[1]);
                        instrumentsMap.get(ticker).setBeta(beta);
                        break;
                    case PRICE:
                        double price = Double.parseDouble(values[1]);
                        instrumentsMap.get(ticker).setPrice(price);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
