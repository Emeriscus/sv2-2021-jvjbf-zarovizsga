package shipping;

import java.util.*;
import java.util.stream.Collectors;

public class ShippingService {

    private List<Transportable> packages = new ArrayList<>();

    public List<Transportable> getPackages() {
        return packages;
    }

    public void addPackage(Transportable transportable) {
        packages.add(transportable);
    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int weight) {
        return packages.stream()
                .filter(t -> t.isBreakable() == breakable)
                .filter(t -> t.getWeight() >= weight)
                .toList();
    }

    // Ez nem működik...
    public Map<String, Integer> collectTransportableByCountry() {
        Map<String, Integer> result = new TreeMap<>();
        for (Transportable actual : packages) {
            if (isInternationalPackage(actual)) {
                int actualDistance = ((InternationalPackage) actual).getDistance();
                if (!result.containsKey(actual.getDestinationCountry())) {
                    result.put(actual.getDestinationCountry(), actualDistance);
                } else {
                    result.put(actual.getDestinationCountry(),
                            result.get(actual.getDestinationCountry()) + actualDistance);
                }
            }
        }
        return result;
    }

    private boolean isInternationalPackage(Transportable transportable) {
        return !transportable.getDestinationCountry().equals("Hungary");
    }

    public List<Transportable> sortInternationalPackagesByDistance() {
        return packages.stream()
                .filter(t -> t instanceof InternationalPackage)
                .sorted(Comparator.comparing(p -> ((InternationalPackage) p).getDistance()))
                .toList();
    }
}
