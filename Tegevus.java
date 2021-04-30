public class Tegevus {

    private final String nimetus;
    private final int ajakulu;

    public Tegevus(String nimetus, int ajakulu) {
        this.nimetus = nimetus;
        this.ajakulu = ajakulu;
    }

    public String getNimetus() {
        return nimetus;
    }

    public int getAjakulu() {
        return ajakulu;
    }

    @Override
    public String toString() {
        return getNimetus() + " (" + getAjakulu() + ")";
    }
}
