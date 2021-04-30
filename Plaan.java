import java.util.ArrayList;
import java.util.List;

public class Plaan {

    private int aeg;
    private final List<Tegevus> tegevused = new ArrayList<>();

    public Plaan(int aeg) {
        this.aeg = aeg;
    }

    public int getAeg() {
        return aeg;
    }

    public List<Tegevus> getTegevused() {
        return tegevused;
    }

    public void lisaTegevus(Tegevus tegevus) {
        tegevused.add(tegevus);
        if (this.aeg - tegevus.getAjakulu() < 0) {
            throw new AjaErind(tegevus.getNimetus() + " (" + tegevus.getAjakulu() + ")");
        } else {
            this.aeg -= tegevus.getAjakulu();
        }
    }

    @Override
    public String toString() {
        return "Plaan{" +
                "aeg=" + aeg +
                ", tegevused=" + tegevused +
                '}';
    }
}
