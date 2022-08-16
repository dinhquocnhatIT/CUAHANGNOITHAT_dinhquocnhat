package dinhquocnhat.view;

import java.util.Scanner;

public class InteriorsProductViewLauncher {
    public static Scanner scanner = new Scanner(System.in);

    public static void run() {
        InteriorsProductView techsProduct = new InteriorsProductView();
        boolean is = false;
        do {
            try {
                Menu.managementMenu();
                System.out.println("\n Chọn chức năng");
                System.out.print("☛ ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        techsProduct.addTechs();
                        break;
                    case 2:
                        techsProduct.updateProductTechs();
                        break;
                    case 3:
                        techsProduct.remove();
                        break;
                    case 4:
                        techsProduct.showTechs();
                        break;
                    case 5:
                        techsProduct.searchByNameProduct();
                        break;
                    case 6:
                        Menu.MainMenu();
                        break;
                    case 7:
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        InteriorsProductViewLauncher.run();
                        is = true;
                }
            } catch (Exception e) {
                System.out.println("Không đúng.Vui lòng nhập lại!");
            }
        } while (!is);
    }
}
