package dinhquocnhat.view;

import dinhquocnhat.model.Interiors;
import dinhquocnhat.service.IInteriorsService;
import dinhquocnhat.service.InteriorsService;
import dinhquocnhat.utils.AppUtils;
import dinhquocnhat.utils.InstantUtils;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class InteriorsProductView {
    private final IInteriorsService interiorsService;
    private final Scanner scanner = new Scanner(System.in);

    public InteriorsProductView() {
        interiorsService = InteriorsService.getInstance();
    }

    private boolean isRetry(InputOption inputOption) {
        do {
            try {
                switch (inputOption){
                    case ADD:
                        System.out.println("Nhấn 'y' để thêm sản phẩm khác\t|\t'q'Quay lại \t|\t 't' Thoát");
                        break;
                    case UPDATE:
                        System.out.println("Nhấn 'y' để sửa \t|\t 'q' Quay lại \t|\t 't' Thoát");
                        break;
                    case SHOW:
                        System.out.println("Nhấn 'q' Quay lại \t|\t 't' Thoát");
                        break;
                    default:
                        throw new IllegalStateException ("Unexpected value: " + inputOption);
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
                        System.out.println("Không đúng.Vui lòng nhập lại!");
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
                System.out.println("Nhập ID");
                System.out.print("➲ ");
                while (interiorsService.existById(id = Long.parseLong(scanner.nextLine()))) {
                    System.out.println("ID này đã tồn tại. Vui lòng nhập lại!!");
                    System.out.print("➲ ");
                    break;
                }
                break;
            case UPDATE:
                System.out.println("Nhập ID mà bạn muốn thay đổi");
                System.out.print("➲ ");
                while (!interiorsService.existById(id = Long.parseLong(scanner.nextLine()))) {
                    System.out.println("ID này đã tồn tại. Vui lòng nhập lại!");
                    System.out.print("➲ ");
                    break;
                }
                break;
        }
        return id;
    }

    private String inputTechsName(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập tên sản phẩm");
                break;
            case UPDATE:
                System.out.println("Nhập tên sản phẩm bạn muốn sửa đổi");
                break;
        }
        boolean is = true;
        String name = "";
        do {
            try {
                System.out.print("➲ ");
                name = scanner.nextLine();
                if (name.isEmpty()) {
                    System.out.println("Không đúng.Vui lòng nhập lại!");
                    is = false;
                } else if (interiorsService.isExistByName(name)){
                    System.out.println("Tên đã tồn tại.Vui lòng thử lại!");
                    is = false;
                }else {
                    is = true;
                }
            } catch (Exception e) {
                System.out.println("Không đúng.Vui lòng nhập lại!");
            }
        } while (!is);
        return name;
    }

    private Double inputPriceTechs(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập giá sản phẩm");
                break;
            case UPDATE:
                System.out.println("Nhập giá mới mà bạn muốn thay đổi");
                break;
        }
        boolean is = false;
        double price = 0;
        do {
            try {
                System.out.print("➲ ");
                price = Double.parseDouble(scanner.nextLine());
                if (price < 1) {
                    System.out.println("Giá phải lớn hơn 1");
                    is = false;
                } else {
                    is = true;
                }
            } catch (Exception e) {
                System.out.println("Không đúng. Vui lòng nhập lại!");
            }
        } while (!is);
        return price;
    }

    private String inputType(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập vào loại sản phẩm");
                break;
            case UPDATE:
                System.out.println("Nhập vào loại sản phẩm mới mà bạn muốn đổi");
                break;
        }
        boolean is = true;
        String type = null;
        do {
            try {
                System.out.print("➲ ");
                type = scanner.nextLine();
                if (type.isEmpty()) {
                    System.out.println("Vui lòng nhập loại sản phẩm");
                    is = true;
                } else {
                    is = false;
                }
            } catch (Exception e) {
                System.out.println("Không đúng. Vui lòng nhập lại!");
            }
        } while (is);

        return type;

    }


    private Integer inputQuantityTechs(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập vào số lượng sản phẩm");
                break;
            case UPDATE:
                System.out.println("Nhập vào số lượng mới mà bạn muốn thay đổi");
                break;
        }
        boolean is = false;
        int quantity = 0;
        do {
            try {
                System.out.print("➲ ");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 1) {
                    System.out.println("Số lượng phải lớn hơn 1.");
                    is = false;
                } else {
                    is = true;
                }
            } catch (Exception e) {
                System.out.println("Không đúng. Vui lòng nhập lại!");
            }
        } while (!is);
        return quantity;
    }

    public void addTechs() {
        do {
            try {
                Long id = System.currentTimeMillis() / 1000;
                String nameTechs = inputTechsName(InputOption.ADD);
                Double priceTechs = inputPriceTechs(InputOption.ADD);
                Integer quantityTechs = inputQuantityTechs(InputOption.ADD);
                String type = inputType(InputOption.ADD);
                Interiors techs = new Interiors(id, nameTechs, priceTechs, quantityTechs, type);
                System.out.println(techs);
                interiorsService.add(techs);
                showTechs1();
                System.out.println("Bạn đã thêm thành công!");
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Không đúng.Vui lòng nhập lại!");
            }
        } while (isRetry(InputOption.ADD));
    }

    public void showTechs() {
        System.out.println("------------------------------------------------------------------------- DANH SÁCH SẢN PHẨM --------------------------------------------------------");
        System.out.printf("%-15s %-30s %-15s %-10s %-20s %-20s %-20s\n\n", "Id", "Product Name", "Price", "Quantity", "Type", "Date Creat", "Date Update");
        List<Interiors> techs = interiorsService.getInteriors();
        Collections.sort(techs);
        for (Interiors techs1 : techs) {
            System.out.printf("%-15d %-30s %-15s %-10d %-20s %-20s %-20s \n\n", techs1.getId(), techs1.getNameInteriors(), AppUtils.doubleToVND(techs1.getPriceInteriors()), techs1.getQuantityInteriors(), techs1.getType(), InstantUtils.instantToString(techs1.getCreatDate()), techs1.getUpdateDate() == null ? "" : InstantUtils.instantToString(techs1.getUpdateDate()));
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        isRetry(InputOption.SHOW);
    }

    public static void showTechs1() {
        InteriorsService techsSevice = new InteriorsService();
        System.out.println("------------------------------------------------------------ DANH SÁCH SẢN PHẨM ---------------------------------------------------------");
        System.out.printf("%-15s %-30s %-15s %-10s %-20s %-20s %-20s\n\n", "Id", "Product Name", "Price", "Quantity", "Type", "Date Creat", "Date Update");
        List<Interiors> techs = techsSevice.getInteriors();
        Collections.sort(techs);
        for (Interiors techs1 : techs) {
            System.out.printf("%-15d %-30s %-15s %-10d %-20s %-20s %-20s \n\n", techs1.getId(), techs1.getNameInteriors(), AppUtils.doubleToVND(techs1.getPriceInteriors()), techs1.getQuantityInteriors(), techs1.getType(), InstantUtils.instantToString(techs1.getCreatDate()), techs1.getUpdateDate() == null ? "" : InstantUtils.instantToString(techs1.getUpdateDate()));

        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
    }

    public void updateProductTechs() {
        boolean isRetry = false;
        do {
            try {
                showTechs1();
                Long id = inputId(InputOption.UPDATE);
                System.out.println("\t┌ - - - - - - - CHỈNH SỬA - - - - - - - ┐");
                System.out.println("\t︲                                      ︲");
                System.out.println("\t︲        1. SỬA TÊN                    ︲");
                System.out.println("\t︲        2. SỬA GIÁ                    ︲");
                System.out.println("\t︲        3. SỬA SỐ LƯỢNG               ︲");
                System.out.println("\t︲        4. SỬA LOẠI SẢN PHẨM          ︲");
                System.out.println("\t︲        5. QUAY LẠI                   ︲");
                System.out.println("\t︲                                      ︲");
                System.out.println("\t└ - - - - - - - - - - - - - - - - - - -  ┘");
                int option;
                do {
                    System.out.println("Chọn chức năng");
                    System.out.print("➲ ");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option > 4 || option < 1) {
                        System.out.println("Không đúng. Vui lòng nhập lại! ");
                        continue;
                    }
                    break;
                } while (true);
                Interiors newTechs = new Interiors();
                newTechs.setId(id);
                switch (option) {
                    case 1:
                        String name = inputTechsName(InputOption.UPDATE);
                        newTechs.setNameInteriors(name);
                        interiorsService.updateName(newTechs);
                        System.out.println("Bạn đã đổi tên thành công!");
                        showTechs1();
                        break;
                    case 2:
                        Double price = inputPriceTechs(InputOption.UPDATE);
                        newTechs.setPriceInteriors(price);
                        interiorsService.updatePrice(newTechs);
                        System.out.println("Bạn đã thay đổi giá mới thành công!");
                        showTechs1();
                        break;
                    case 3:
                        Integer quantity = inputQuantityTechs(InputOption.UPDATE);
                        newTechs.setQuantityInteriors(quantity);
                        interiorsService.updateQuantity(newTechs);
                        System.out.println("Bạn đã thay đổi số lượng thành công!");
                        showTechs1();
                        break;
                    case 4:
                        String description = inputType(InputOption.UPDATE);
                        newTechs.setType(description);
                        interiorsService.updateDescription(newTechs);
                        System.out.println("Bạn đã thay đổi mô tả sản phẩm thành công!");
                        showTechs1();
                        break;
                }
                isRetry = option != 5 && isRetry(InputOption.UPDATE);
            } catch (Exception e) {
                System.out.println("Không đúng. Vui lòng nhập lại!");
            }
        } while (isRetry);
    }

    public void remove() {
        boolean is = true;
        do {
            try {
                showTechs1();
                interiorsService.getInteriors();
                System.out.println("Nhập vào ID mà bạn muốn xoá:  ");
                System.out.print("➲ ");
                long id = Long.parseLong(scanner.nextLine());
                Interiors interiors = interiorsService.getInteriorsById(id);
                System.out.println(interiors);
                if (interiors == null) {
                    System.out.println("ID không tìm thấy");
                    is = false;
                } else {
                    System.out.println("\t❄ ❄ ❄ ❄ ❄ ❄ ❄  XÁC NHẬN   ❄ ❄ ❄ ❄ ❄ ❄ ❄");
                    System.out.println("\t❄                                           ❄");
                    System.out.println("\t❄              1. XOÁ                       ❄");
                    System.out.println("\t❄              2. TRỞ LẠI                   ❄");
                    System.out.println("\t❄                                           ❄");
                    System.out.println("\t❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄");
                    System.out.print("➲ ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            interiorsService.remove(interiors);
                            System.out.println("Xoá thành công!");
                            showTechs1();
                            do {
                                System.out.println("\t------------------------------------------------------------");
                                System.out.println("\t| Nhấn 'y' để trở lại\t|\tNhấn 'n' để thoát  |");
                                System.out.println("\t------------------------------------------------------------");
                                System.out.print("➲ ");
                                String chose = scanner.nextLine();
                                switch (chose) {
                                    case "y":
                                        InteriorsProductViewLauncher.run();
                                        break;
                                    case "n":
                                        Menu.exit();
                                        System.exit(0);
                                        break;
                                    default:
                                        System.out.println("Không đúng. Vui lòng nhập lại!");
                                        is = false;
                                }
                            } while (is);
                            break;
                        case 2:
                            InteriorsProductViewLauncher.run();
                            break;
                        default:
                            System.out.println("Không đúng. Vui lòng nhập lại!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Không đúng. Vui lòng nhập lại!");
            }
        } while (is);
    }

    public void searchByNameProduct() {
        List<Interiors> techs = interiorsService.getInteriors();
        int count = 0;
        System.out.println();
        System.out.println("Nhập vào tên sản phẩm mà bạn muốn tìm kiếm");
        String search = scanner.nextLine();
        System.out.println("Kết quả tìm kiếm '" + search + "' là: ");
        search = search.toLowerCase();
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-20s %-20s %-15s %-20s %-20s\n",
                "Id", "Name Product", "Type", "Quantity","Price","Creat Date" );
        for (Interiors techs1 : techs){
            if(techs1.getNameInteriors().toLowerCase().contains(search)){
                count++;
                System.out.printf("%-15d %-20s %-20s %-15d %-20s %-20s\n",
                        techs1.getId(),
                        techs1.getNameInteriors(),
                        techs1.getType(),
                        techs1.getQuantityInteriors(),
                        AppUtils.doubleToVND(techs1.getPriceInteriors()) ,
                        InstantUtils.instantToString(techs1.getCreatDate()));
            }
        }
        showReturnSearch(count);
        System.out.println();
        InteriorsProductViewLauncher.run();
    }
    public void showReturnSearch(int count){
        System.out.println();
        System.out.println();
        System.out.println("Có '" + count +  "' sản phẩm được tìm thấy\n");
        char press = ' ';
        do {
            System.out.println("Nhấn 'r' để quay lại ");
            try {
                press = scanner.nextLine().charAt(0);
            }catch (Exception e){
                press = ' ';
            }
            switch (press){
                case 'r':
                case 'R':
                    InteriorsProductViewLauncher.run();
                    break;
                default:
            }
        }while (true);
    }
}