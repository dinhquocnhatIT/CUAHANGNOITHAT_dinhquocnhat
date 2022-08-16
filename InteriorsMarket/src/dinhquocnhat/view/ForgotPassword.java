package dinhquocnhat.view;

import dinhquocnhat.model.Admin;
import dinhquocnhat.service.AdminService;

import java.util.Scanner;

public class ForgotPassword {
    private static AdminService adminService = new AdminService();
    private static Scanner scanner = new Scanner(System.in);

    public static void menuForgot() {
        do {
            try {
                System.out.println("\t ☣☣☣☣☣☣☣☣   QUÊN MẬT KHẨU  ☣☣☣☣☣☣☣☣☣☣☣");
                System.out.println("\t☣                                             ☣");
                System.out.println("\t☣           1. Đổi Mật Khẩu                   ☣");
                System.out.println("\t☣           2. Đăng nhập                      ☣");
                System.out.println("\t☣                                             ☣");
                System.out.println("\t☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣☣");
                System.out.print("⭆ ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        changePassWord();
                        break;
                    case 2:
                        Menu.login();
                        break;
                    default:
                        System.out.println("Không đúng, Vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Không đúng,Vui lòng nhập lại!");
            }
        } while (true);
    }

    private static void changePassWord() {
        Admin admin = fressInforAdmin();
        adminService.update(admin);
        System.out.println("Mật khẩu đã được đặt lại mặc định! Mật khẩu là: 123456789 " );
    }

    private static Admin fressInforAdmin() {
        System.out.println("Nhấn 'b' Để quay lại Menu quên mật khẩu ");
        System.out.println("Vui lòng nhập User Name của bạn");
        System.out.print("⭆ ");
        String username = scanner.nextLine();
        while (adminService.getUserByAdminName(username) == null) {
            backToEarlier(username);
            System.out.println("Tên đăng nhập không tồn tại");
            System.out.print("⭆ ");
            username = scanner.nextLine();
        }
        Admin admin = adminService.getUserByAdminName(username);

        System.out.println("Vui lòng Xác nhận lại Email Tài khoản:");
        String email = scanner.nextLine();
        while (!admin.getEmail().equals(email)) {
            backToEarlier(email);
            System.out.println("Email bạn đã nhập không chính xác! Vui lòng thử lại");
            System.out.print("⭆ ");
            email = scanner.nextLine();
        }
        return admin;
    }

    private static void backToEarlier(String str) {
        if (str.equals("b")) {
            System.out.println("Bạn đã quay lại");
            menuForgot();
        }
    }

    public static void backToFirstMenu(String str) {
        if (str.equals("b")) {
            System.out.println("Bạn đã quay lại");
                Menu.firstMenu();
        }
    }
}