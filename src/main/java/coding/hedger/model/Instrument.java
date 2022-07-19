package coding.hedger.model;

public class Instrument {
    private String ticker;
    private int position;
    private double price;
    private double beta;



    public Instrument(String ticker) {
        this.ticker = ticker;
    }


    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }




    @Override
    public String toString() {
        return "Instrument{" +
                "ticker='" + ticker + '\'' +
                ", position=" + position +
                ", price=" + price +
                ", beta=" + beta +
                '}';
    }
}
