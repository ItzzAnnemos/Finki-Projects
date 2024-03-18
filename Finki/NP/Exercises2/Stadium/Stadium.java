package mk.ukim.finki.NP.ZadaciZaVezbanje2.Stadium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Stadium {
    private String name;
    private List<Sector> sectors;

    public Stadium(String name) {
        this.name = name;
        this.sectors = new ArrayList<>();
    }

    public void createSectors(String[] sectorNames, int[] sizes) {
        for (int i = 0; i < sizes.length; i++) {
            sectors.add(new Sector(sectorNames[i], sizes[i]));
        }
    }

    public void buyTicket(String sectorName, int seat, int type) throws SeatNotAllowedException, SeatTakenException {
        for (Sector sector : sectors) {
            if (sector.getCode().equals(sectorName)) {
                if (sector.getSeats().get(seat - 1) != -1) {
                    throw new SeatTakenException();
                } else {
                    if (type == 0) {
                        sector.getSeats().set(seat - 1, type);
                        return;
                    }
                    if (sector.getType() == -1) {
                        sector.setType(type);
                        sector.getSeats().set(seat - 1, type);
                    } else if (sector.getType() != type) {
                        throw new SeatNotAllowedException();
                    } else {
                        sector.getSeats().set(seat - 1, type);
                    }
                }
            }
        }
    }

    public void showSectors() {
        Comparator<Sector> comparator = Comparator.comparing(Sector::getNumFreeSeats).reversed()
                .thenComparing(Sector::getCode);

        sectors.sort(comparator);

        sectors.forEach(System.out::println);
    }
}
