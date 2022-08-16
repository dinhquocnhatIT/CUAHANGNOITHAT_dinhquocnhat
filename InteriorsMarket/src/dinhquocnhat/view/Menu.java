package dinhquocnhat.view;

import java.util.Scanner;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    private static ForgotPassword forgotPassword;


    public static void mainMenu() {
        System.out.println("\t ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬ MAIN MENU ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println("\t▬                                                         ▬");
        System.out.println("\t▬             1. QUẢN LÍ TÀI KHOẢN                        ▬");
        System.out.println("\t▬             2. QUẢN LÍ SẢN PHẨM                         ▬");
        System.out.println("\t▬             3. QUẢN LÍ ĐƠN ĐẶT HÀNG                     ▬");
        System.out.println("\t▬             4. ĐĂNG XUẤT                                ▬");
        System.out.println("\t▬             5. THOÁT                                    ▬");
        System.out.println("\t▬                                                         ▬");
        System.out.println("\t▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    }

    public static void login() {
        AdminView adminView = new AdminView();
        adminView.adminLogin();
        MainMenu();
    }

    public static void MainMenu() {
        boolean is = false;
        do {
            try {
                mainMenu();
                System.out.println("\nLựa chọn chức năng");
                System.out.print("☛ ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        AdminViewLaucher.run();
                        break;
                    case 2:
                        InteriorsProductViewLauncher.run();
                        break;
                    case 3:
                        OrderViewLauncher.run();
                        break;
                    case 4:
                        login();
                        break;
                    case 5:
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        Menu.MainMenu();
                        is = true;
                        break;
                }
            } catch (Exception E) {
                System.out.println("Không đúng.Vui lòng nhập lại!");
            }
        } while (!is);
    }

    public static void firstMenu() {
        do {
            try {
                firstLauch();
                System.out.println("\nChọn chức năng");
                System.out.print("☛ ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        ForgotPassword.menuForgot();
                        break;
                    default:
                        System.out.println("Không đúng.Vui lòng nhập lại!");
                }
                }catch(Exception e) {
                System.out.println("Không đúng.Vui lòng nhập lại!");
            }
        }while (true) ;
    }

    public static void userMenu() {
        System.out.println("\t☼ ☼ ☼ ☼ ☼ ☼ ☼ ☼ ADMIN MENU ☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼");
        System.out.println("\t☼                                                 ☼");
        System.out.println("\t☼          1. TẠO TÀI KHOẢN MỚI                   ☼");
        System.out.println("\t☼          2. CHỈNH SỬA THÔNG TIN TÀI KHOẢN       ☼");
        System.out.println("\t☼          3. HIỂN THỊ TẤT CẢ TÀI KHOẢN           ☼");
        System.out.println("\t☼          4. TRỞ LẠI MAIN MENU                   ☼");
        System.out.println("\t☼          5. THOÁT                               ☼");
        System.out.println("\t☼                                                 ☼");
        System.out.println("\t☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼");
    }

    public static void managementMenu() {
        System.out.println("\t─────────────────────────INTERIORS MENU──────────────────────────────");
        System.out.println("\t───                                                               ───");
        System.out.println("\t───           1. THÊM SẢN PHẨM MỚI                                ───");
        System.out.println("\t───           2. SỬA THÔNG TIN SẢN PHẨM                           ───");
        System.out.println("\t───           3. XOÁ SẢN PHẨM                                     ───");
        System.out.println("\t───           4. HIỂN THỊ TẤT CẢ SẢN PHẨM                         ───");
        System.out.println("\t───           5. TÌM KIẾM SẢN PHẨM THEO TÊN                       ───");
        System.out.println("\t───           6. TRỞ VỀ MAIN MENU                                 ───");
        System.out.println("\t───           7. THOÁT                                            ───");
        System.out.println("\t───                                                               ───");
        System.out.println("\t─────────────────────────────────────────────────────────────────────");
    }

    public static void orderMenu() {
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ORDER MENU♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧");
        System.out.println("\t♧                                                    ♧");
        System.out.println("\t♧               1. TẠO ĐƠN HÀNG                      ♧");
        System.out.println("\t♧               2. HIỂN THỊ DANH SÁCH ĐƠN HÀNG       ♧");
        System.out.println("\t♧               3. TRỞ LẠI MAIN MENU                 ♧");
        System.out.println("\t♧               4. THOÁT                             ♧");
        System.out.println("\t♧                                                    ♧");
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧");
    }

    public static void firstLauch() {
        System.out.println("\t➻➻➻➻➻➻➻➻➻➻➻ LOGIN ➻➻➻➻➻➻➻➻➻➻➻➻");
        System.out.println("\t➻                                          ➻");
        System.out.println("\t➻           1. QUẢN LÍ ĐĂNG NHẬP           ➻");
        System.out.println("\t➻           2. QUÊN MẬT KHẨU               ➻");
        System.out.println("\t➻                                          ➻");
        System.out.println("\t➻➻➻➻➻➻➻➻➻➻➻➻➻➻➻➻➻➻➻➻➻➻➻➻➻➻➻➻");


    }

    public static void exit() {
        System.out.println("\tTạm biệt! Hẹn gặp lại");
        System.exit(5);
    }
}


