import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

public class Planeerija {

    public static void main(String[] args) throws Exception {
        Map<String, List<Tegevus>> tegevusteLiigid = loeTegevused("tegevused.txt");
        Scanner sc = new Scanner(System.in);
        System.out.println("Mitu minutit on aega tegevuste tegemiseks? ");
        int aeg = Integer.parseInt(sc.nextLine());
        Plaan plaan = new Plaan(aeg);

        while (true) {
            System.out.println("Vali tegevuse liik: " + tegevusteLiigid.keySet() + " Lõpetamiseks vajuta ENTER");
            String liik = sc.nextLine();
            if (liik.equals("")) {
                break;
            }
            if (!tegevusteLiigid.containsKey(liik)) {
                continue;
            }
            int listiPikkus = tegevusteLiigid.get(liik).size();
            List<Tegevus> tegevused = tegevusteLiigid.get(liik);
            Random randInt = new Random();
            int index = randInt.nextInt(listiPikkus);
            Tegevus tegevus = tegevused.get(index);
            try {
                plaan.lisaTegevus(tegevus);
            } catch (AjaErind e) {
                System.out.println("Veateade " + e);
                System.out.println("Maksimaalne tegevuse aeg saab olla " + plaan.getAeg() + " minutit");
            }
            System.out.println("Lisatus tegevus: " + tegevus.getNimetus() + " (" + tegevus.getAjakulu() + ")");
            System.out.println("Planeeritavat aega jäänud " + plaan.getAeg() + " minutit");


        }
        String failinimi = "tegevused_" + LocalDate.now() + ".dat";
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(failinimi))) {
            int tegevusteArv = plaan.getTegevused().size();
            List<Tegevus> tegevused = plaan.getTegevused();
            dos.writeInt(tegevusteArv);
            for (Tegevus tegevus : tegevused) {
                dos.writeUTF(tegevus.getNimetus());
                dos.writeInt(tegevus.getAjakulu());
            }
        }
    }

    private static Map<String, List<Tegevus>> loeTegevused(String failinimi) throws Exception {
        Map<String, List<Tegevus>> localHashMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(failinimi), StandardCharsets.UTF_8))) {
            String rida = br.readLine();
            while (rida != null) {
                String[] osad = rida.split(";");
                Tegevus tegevus = new Tegevus(osad[1], Integer.parseInt(osad[2]));
                if (!localHashMap.containsKey(osad[0])) {
                    List<Tegevus> list = new ArrayList<>();
                    list.add(tegevus);
                    localHashMap.put(osad[0], list);
                } else {
                    localHashMap.get(osad[0]).add(tegevus);
                }
                rida = br.readLine();
            }
        }
        return localHashMap;
    }
}
