package dinhquocnhat.view;

import dinhquocnhat.model.Admin;
import dinhquocnhat.model.Role;
import dinhquocnhat.service.AdminService;
import dinhquocnhat.service.IAdminService;
import dinhquocnhat.utils.AppUtils;
import dinhquocnhat.utils.InstantUtils;
import dinhquocnhat.utils.ValidateUtils;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AdminView {
    private final IAdminService adminService;
    private final Scanner scanner = new Scanner(System.in);

    public AdminView() {
        adminService = new AdminService();
    }

    private boolean isRetry(InputOption inputOption) {
        do {
            try {
                switch (inputOption) {
                    case ADD:
                        System.out.println("Nhấn 'y'để thêm người dùng khác \t|\t 'q' Quay lại \t|\t 't' Thoát");
                        break;
                    case UPDATE:
                        System.out.println("Nhấn 'y' để chỉnh sửa \t|\t 'q' Quay lại \t|\t 't' Thoát");
                        break;
                    case SHOW:
                        System.out.println("Nhấn 'q' Quay lại \t|\t 't' Thoát");
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + inputOption);

                }
                System.out.print("➲ ");
                String option = scanner.nextLine();
                switch (option) {
                    case "y":
                        return true;
                    case "q":
                        return false;
                    case "t":
                        Menu.exit();
                        break;
                    default:
                        System.out.println("Không đúng. Vui lòng nhập lại!");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Không đúng. Vui lòng nhập lại!");
                ex.printStackTrace();
            }
        } while (true);
    }

    private Long inputId(InputOption option) {
        Long id = null;
        switch (option) {
            case ADD:
                System.out.println("Nhập Id");
                System.out.print("➲ ");
                while (adminService.existById(id = Long.parseLong(scanner.nextLine()))) {
                    System.out.println("ID này đã tồn tại. Vui lòng nhập ID khác");
                    System.out.print("➲ ");
                }
                break;
            case UPDATE:
                System.out.println("Nhập ID bạn muốn sửa: ");
                System.out.print("➲ ");
                while (!adminService.existById(id = Long.parseLong(scanner.nextLine()))) {
                    System.out.println("Id này không tồn tại, vui lòng nhập Id khác!");
                    System.out.print("➲ ");
                }
                break;
        }
        return id;
    }

    public String inputAdminName() {
        boolean restry = true;
        String adminname = null;
        do {
            try {
                System.out.println("Nhập user name: ");
                System.out.print("➲ ");
                while (adminService.checkDuplicateUserName(adminname = scanner.nextLine())) {
                    System.out.println("Người dùng này đã tồn tại. Vui lòng thử lại!");
                    System.out.print("➲ ");
                }
                if (adminname.isEmpty()) {
                    System.out.println("Không thể để trống");
                    restry = false;
                } else {
                    restry = true;
                }
            } catch (Exception e) {
                System.out.println("Không đúng, Vui lòng nhập lại!!");
            }
        } while (!restry);
        return adminname;
    }

    private String inputFullname(InputOption option) {
        String fullName = null;
        try {
            switch (option) {
                case ADD:
                    System.out.println("Nhập Họ Và Tên (Ví dụ: Dinh Quoc Nhat): ");
                    break;
                case UPDATE:
                    System.out.println("Nhập họ và tên mà bạn muốn thay đổi:");
                    break;
            }
            System.out.print("➲ ");
            while (!ValidateUtils.isNameValid(fullName = scanner.nextLine())) {
                System.out.println("Tên  " + fullName + " Không đúng định dạng! Vui lòng thử lại (Viết hoa chữ cái đầu tiên và không có dấu)");
                System.out.println("Nhập tên (Ví dụ: Nhat Dinh)");
                System.out.print("➲ ");

            }
        } catch (Exception e) {
            System.out.println("Không đúng, vui lòng thử lại!");
        }
        return fullName;
    }


    public String inputPhone(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập vào số điện thoại (Ví dụ: 0389054366): ");
                break;
            case UPDATE:
                System.out.println("Nhập vào số điện thoại bạn muốn sửa: ");
                break;
        }
        System.out.print("➲ ");
        String phone;
        while (!ValidateUtils.isPhoneValid(phone = scanner.nextLine())) {
            System.out.println("Số điện thoại " + phone + " Không đúng, Vui lòng nhập lại ( Bắt đầu từ số 0 và phải 10 chữ số)");
            System.out.println("Nhập vào số điện thoại (Ví dụ: 0389054366)");
            System.out.print("➲ ");
            if (adminService.checkDuplicatePhone(phone)) {
                System.out.println("Số điện thoại này đã tồn tại");
                System.out.print("➲ ");
            }
        }
        return phone;
    }

    private String inputEmail() {
        System.out.println("Nhập vài email( Ví dụ: nhatd87@gmail.com ) ");
        System.out.print("➲ ");
        String email;
        while (!ValidateUtils.isEmailValid(email = scanner.nextLine())) {
            System.out.println("Email " + email + " Không đúng, Vui lòng nhập lại!!");
            System.out.println("Nhập Email( Ví dụ: nhatd87@gmail.com )");
            System.out.print("➲ ");
        }
        return email;
    }

    private String inputPassword() {
        System.out.println("Nhập vào mật khẩu( Mật khẩu phải lớn hơn 8 kí tự)");
        System.out.print("➲ ");
        String password;
        while (!ValidateUtils.isPasswordValid(password = scanner.nextLine())) {
            System.out.println("Mật khẩu yếu, Vui lòng nhập lại!");
            System.out.print("➲ ");
        }
        return password;
    }

    private String inputAddress(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập địa chỉ( Ví dụ: Huế)");
                break;
            case UPDATE:
                System.out.println("Nhập vào địa chỉ bạn muốn thay đổi: ");
                break;
        }
        boolean is = false;
        String address = null;
        do {
            try {
                System.out.print("➲ ");
                address = scanner.nextLine();
                if (address.isEmpty()) {
                    System.out.println("không thể để trống ");
                    is = false;
                } else {
                    is = true;
                }
            } catch (Exception e) {
                System.out.println("Không đúng, vui lòng nhập lại!");
            }
        } while (!is);
        return address;
    }

    public void addAdmin() {
        do {
            try {
                Long id = System.currentTimeMillis() / 1000;
                String username = inputAdminName();
                String password = inputPassword();
                String fullName = inputFullname(InputOption.ADD);
                String phone = inputPhone(InputOption.ADD);
                String address = inputAddress(InputOption.ADD);
                String email = inputEmail();

                Admin admin = new Admin(id, username, password, fullName, phone, email, address, Role.USER);
                setRole(admin);
                adminService.add(admin);
            } catch (Exception e) {
                System.out.println("Không đúng, Vui lòng nhập lại!");
            }
        } while (isRetry(InputOption.ADD));
    }

    public void setRole(Admin admin) {
        System.out.println("= = SET ROLE = =");
        System.out.println("∥              ∥");
        System.out.println("∥   1. USER    ∥");
        System.out.println("∥   2. ADMIN   ∥");
        System.out.println("∥              ∥");
        System.out.println("= = = =  = = = = ");
        System.out.println("Chọn Role: ");
        System.out.print("➲ ");
        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1:
                admin.setRole(Role.USER);
                System.out.println("Đã thêm tài khoản thành công!");

                break;
            case 2:
                admin.setRole(Role.ADMIN);
                System.out.println("Đã thêm tài khoản thành công!");

                break;
            default:
                System.out.println("Không đúng, Vui lòng nhập lại!");
                break;
        }
    }

    public void showAdmin() {
        System.out.println("-----------------------------------------------------------  DANH SÁCH KHÁCH HÀNG   -------------------------------------------------------------");
        System.out.printf("%-15s%-22s%-15s%-30s%-20s%-15s%-15s\n\n", "ID", "Name", "Number Phone", "Email", "Address", "ROLE", "Date Creat");
        List<Admin> admins = adminService.getAdmin();
        Collections.sort(admins);
        for (Admin admin : admins) {
            System.out.printf("%-15d%-22s%-15s%-30s%-20s%-15s%-15s  \n\n", admin.getId(), admin.getName(), admin.getPhone(), admin.getEmail(), admin.getAddress(), admin.getRole(), InstantUtils.instantToString(admin.getCreatDate()));

        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        isRetry(InputOption.SHOW);
    }

    public void updateAdmin() {
        boolean isRetry = false;
        do {
            try {
                showUser1();
                Long id = inputId(InputOption.UPDATE);
                System.out.println("\t┌ - - - - - - CHỈNH SỬA -- - - - - ┐");
                System.out.println("\t︲                                 ︲");
                System.out.println("\t︲        1. Sửa Tên               ︲");
                System.out.println("\t︲        2. Sửa số điện thoại     ︲");
                System.out.println("\t︲        3. Sửa địa chỉ           ︲");
                System.out.println("\t︲        4. Quay lại              ︲");
                System.out.println("\t︲                                 ︲");
                System.out.println("\t└ - - - - - - - - - - - - - - - - -┘");
                int option;
                do {
                    System.out.println("Chọn chức năng: ");
                    System.out.print("➲ ");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option > 4 || option < 1) {
                        System.out.println("Không đúng, Vui lòng nhập lại!");
                        continue;
                    }
                    break;
                } while (true);
                Admin newAdmin = new Admin();
                newAdmin.setId(id);
                switch (option) {
                    case 1:
                        String name = inputFullname(InputOption.UPDATE);
                        newAdmin.setName(name);
                        adminService.updateName(newAdmin);
                        System.out.println("Bạn đã đổi tên thành công!");
                        showUser1();
                        break;
                    case 2:
                        String phone = inputPhone(InputOption.UPDATE);
                        newAdmin.setPhone(phone);
                        adminService.updatePhone(newAdmin);
                        System.out.println("Bạn đã đổi số điện thoại thành công!");
                        showUser1();
                        break;
                    case 3:
                        String address = inputAddress(InputOption.UPDATE);
                        newAdmin.setAddress(address);
                        adminService.updateAddress(newAdmin);
                        System.out.println("Bạn đã đổi địa chỉ thành công!");
                        showUser1();
                        break;
                }
                isRetry = option != 4 && isRetry(InputOption.UPDATE);

            } catch (Exception e) {
                System.out.println("Không đúng, Vui lòng nhập lại!");
            }
        } while (isRetry);
    }

    public void adminLogin() {
        boolean isRetry = false;
        System.out.println("✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
        System.out.println("✦                                                ✦");
        System.out.println("✦           CỬA HÀNG NỘI THẤT_dinhquocnhat       ✦");
        System.out.println("✦                                                ✦");
        System.out.println("✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦\n");
        System.out.println("✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦  QUẢN LÍ ĐĂNG NHẬP ✦ ✦ ✦ ✦ ✦ ✦ ✦");
        System.out.println("Nhấn 'b' để quay lại ");
        do {
            try {
                String username;
                boolean check = false;
                do {
                    System.out.println("Username");
                    System.out.print("⭆ ");
                    username = scanner.nextLine();
                    ForgotPassword.backToFirstMenu(username);
                    if (username.isEmpty()) {
                        System.out.println("Không đúng. Vui lòng nhập lại!");
                        check = true;
                    } else {
                        check = false;
                    }
                } while (check);
                String password;
                do {
                    System.out.println("PassWord");
                    System.out.print(" ⭆ ");
                    password = scanner.nextLine();
                    ForgotPassword.backToFirstMenu(password);
                    if (password.isEmpty()) {
                        System.out.println("Không đúng. Vui lòng nhập lại");
                        check = true;

                    } else {
                        check = false;
                    }
                } while (check);

                if (adminService.loginAdmin(username, password) != null) {
                    Admin admin = adminService.getUserByAdminName(username);
                    if (admin.getRole() == Role.ADMIN) {
                        System.out.println("Vui lòng nhập email để xác nhận đăng nhập");
                        System.out.print("⭆ ");
                        checkLoginEmailAdmin(admin.getEmail());
                    }
                } else {
                    System.out.println("Tài khoản này không hợp lệ");
                    isRetry = isRetry();

                }
            } catch (Exception e) {
                System.out.println("Không đúng. Vui lòng nhập lại");
            }
        } while (isRetry);
    }


    private boolean isRetry() {
        do {
            try {
                System.out.println("\t✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ Lựa Chọn ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
                System.out.println("\t✦                                                                  ✦");
                System.out.println("\t✦                   1. Nhấn 'y' để quay lại                        ✦");
                System.out.println("\t✦                   2. Nhấn 'n' để thoát                           ✦");
                System.out.println("\t✦                                                                  ✦");
                System.out.println("\t✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
                System.out.print("➲ ");
                String option = scanner.nextLine();
                switch (option) {
                    case "y":
                        return true;
                    case "n":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhập lại lựa chọn");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Không đúng.Vui lòng nhập lại!");
                ex.printStackTrace();
            }
        } while (true);
    }

    public void showUser1() {
        System.out.println("------------------------------------------------------DANH SÁCH KHÁCH HÀNG------------------------------------------------------");
        System.out.printf("%-15s%-22s%-15s%-30s%-20s%-10s%-10s\n\n", "ID", "Name", "Number Phone", "Email", "Address", "ROLE", "Date Creat");
        List<Admin> admins = adminService.getAdmin();
        Collections.sort(admins);
        for (Admin admin : admins) {
            System.out.printf("%-15d%-22s%-15s%-30s%-20s%-10s%-10s  \n\n", admin.getId(), admin.getName(), admin.getPhone(), admin.getEmail(), admin.getAddress(), admin.getRole(), InstantUtils.instantToString(admin.getCreatDate()));

        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
    }


    public void checkLoginEmailAdmin(String emailAdmin) {
        String email = scanner.nextLine().toLowerCase();
        if (!email.equals(emailAdmin)) {
            ForgotPassword.backToFirstMenu(email);
            System.out.println(" Nhập 'b' quay lại ");
            System.out.println("Email không đúng. Vui lòng nhập lại");
            System.out.print("➲  ");
            checkLoginEmailAdmin(emailAdmin);
        } else {
            System.out.println("\t Bạn đã đăng nhập thành công!!!");
            System.out.println("\t Chào mừng đến cửa hàng nội thất của dinhquocnhat!!! WELCOME!");
            Menu.MainMenu();
        }
    }
}