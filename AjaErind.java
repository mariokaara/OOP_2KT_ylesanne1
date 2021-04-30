public class AjaErind extends RuntimeException {

    public AjaErind(String s) {
        super(s + ". Võtab liiga palju aega, ei saa lisada!");
    }
}
