import java.util.Scanner;

class UcakBiletiHesaplama {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Mesafeyi km türünden giriniz: ");
        int mesafe = scanner.nextInt();

        System.out.print("Yaşınızı giriniz: ");
        int yas = scanner.nextInt();

        System.out.print("Yolculuk tipini giriniz (1 => Tek Yön, 2 => Gidiş Dönüş): ");
        int yolculukTipi = scanner.nextInt();

        double normalTutar = mesafe * 0.10;
        double indirimliTutar = normalTutar;

        if (mesafe <= 0 || yas <= 0 || (yolculukTipi != 1 && yolculukTipi != 2)) {
            System.out.println("Hatalı Veri Girdiniz!");
            return;
        }

        if (yas < 12) {
            indirimliTutar -= normalTutar * 0.50;
        } else if (yas <= 24) {
            indirimliTutar -= normalTutar * 0.10;
        } else if (yas >= 65) {
            indirimliTutar -= normalTutar * 0.30;
        }

        if (yolculukTipi == 2) {
            indirimliTutar *= 0.80;
        }

        double toplamTutar = yolculukTipi == 2 ? indirimliTutar * 2 : indirimliTutar;

        System.out.println("Toplam Tutar = " + toplamTutar + " TL");
    }
}
