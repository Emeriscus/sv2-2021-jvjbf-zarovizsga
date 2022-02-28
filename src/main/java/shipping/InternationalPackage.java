package shipping;

public class InternationalPackage implements Transportable {

    private int weight;
    private boolean breakable;
    private String destinationCountry;
    private int distance;
    public static final int DEFAULT_SHIPPING_PRICE = 1200;
    public static final int DEFAULT_FEE_PER_KM = 10;

    public InternationalPackage(int weight, boolean breakable, String destinationCountry, int distance) {
        this.weight = weight;
        this.breakable = breakable;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public String getDestinationCountry() {
        return destinationCountry;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int calculateShippingPrice() {
        if (isBreakable()) {
            return (2 * DEFAULT_SHIPPING_PRICE) + distance * DEFAULT_FEE_PER_KM;
        } else {
            return DEFAULT_SHIPPING_PRICE + distance * DEFAULT_FEE_PER_KM;
        }
    }
}
