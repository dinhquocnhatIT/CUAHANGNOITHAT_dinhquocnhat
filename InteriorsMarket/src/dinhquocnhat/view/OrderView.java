package dinhquocnhat.view;

import dinhquocnhat.model.Order;
import dinhquocnhat.model.OrderItem;
import dinhquocnhat.model.Interiors;
import dinhquocnhat.service.*;
import dinhquocnhat.utils.AppUtils;
import dinhquocnhat.utils.InstantUtils;
import dinhquocnhat.utils.ValidateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private final Scanner scanner = new Scanner(System.in);
    private final IInteriorsService INTERIORS_SERVICE;
    private final IOrderService ORDER_SERVICE;
    private final IOrderItemService ORDER_ITEM_SERVICE;

    public OrderView() {
        INTERIORS_SERVICE = InteriorsService.getInstance();
        ORDER_SERVICE = OrderService.getInstance();
        ORDER_ITEM_SERVICE = OrderItemService.getInstance();
    }

    public OrderItem addOrderItem(long orderId) {
        do {
            try {
                ORDER_ITEM_SERVICE.findAll();
                InteriorsProductView interiorsProductView = new InteriorsProductView();
                interiorsProductView.showTechs1();
                long id = System.currentTimeMillis() / 1000;
                System.out.println("Nhập ID sản phẩm ");
                System.out.print("⭆ ");
                int interiorsId = Integer.parseInt(scanner.nextLine());
                while (!INTERIORS_SERVICE.existById(interiorsId)) {
                    System.out.println("ID sản phẩm không tồn tại");
                    System.out.println("Nhập ID sản phẩm: ");
                    System.out.print("⭆ ");
                    interiorsId = Integer.parseInt(scanner.nextLine());
                }
                Interiors product = INTERIORS_SERVICE.getInteriorsById(interiorsId);
                double price = product.getPriceInteriors();
                System.out.println("Nhập vào số lượng ");
                System.out.print("⭆ ");
                int quantity = Integer.parseInt(scanner.nextLine());
                while (!checkQualityTechs(product, quantity)) {
                    System.out.println("Số lượng không hợp lệ! Vui lòng thử lại");
                    System.out.println("Nhập vào số lượng");
                    System.out.print("⭆ ");
                    quantity = Integer.parseInt(scanner.nextLine());
                }
                String interiorsName = product.getNameInteriors();
                double total = quantity * price;
                OrderItem orderItem = new OrderItem(id, price, quantity, orderId, interiorsId, interiorsName, total);
                INTERIORS_SERVICE.updateNewQuantity(interiorsId, quantity);
                return orderItem;
            } catch (Exception e) {
                System.out.println("Không đúng. Vui lòng nhập lại!");
            }
        } while (true);
    }

    public boolean checkQualityTechs(Interiors techs, int quantity) {
        if (quantity <= techs.getQuantityInteriors() && quantity > 0)
            return true;
        else
            return false;
    }

    public void addOrder() {
        try {
            ORDER_ITEM_SERVICE.findAll();
            long orderId = System.currentTimeMillis() / 1000;
            System.out.println("Nhập vào tên(Ví dụ: Dinh Quoc Nhat)");
            System.out.print(" ⭆ ");
            String name = scanner.nextLine();
            while (name.isEmpty()) {
                System.out.println("Tên không được bỏ trống");
                System.out.println("Nhập tên(Ví dụ: Dinh Quoc Nhat)");
                System.out.print(" ⭆ ");
                name = scanner.nextLine();
            }
            System.out.println("Nhập vào số điện thoại");
            System.out.print(" ⭆ ");
            String phone = scanner.nextLine();
            while (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Số điện thoai " + phone + " Không đúng. Vui lòng nhập lại( Số điên thoại bắt đầu 0 và có 10 chữ số)");
                System.out.println("Nhập vào số điện thoại(Ví dụ: 0389054366)");
                System.out.print(" ⭆ ");
                phone = scanner.nextLine();
            }
            System.out.println("Nhập địa chỉ");
            System.out.print(" ⭆ ");
            String address = scanner.nextLine();
            while (address.isEmpty()) {
                System.out.println("Không được để trống");
                System.out.println("Nhập địa chỉ");
                System.out.print(" ⭆ ");
                address = scanner.nextLine();
            }
            Order order = new Order(orderId, name, phone, address);
            List<OrderItem> orderItems = addOrderItems(orderId);

            for (OrderItem orderItem : orderItems) {
                ORDER_ITEM_SERVICE.add(orderItem);
            }
            ORDER_SERVICE.add(order);

            System.out.println("Tạo đơn hàng thành công!!!");
            System.out.println("✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ");
            System.out.println("✾                                           ✾ ");
            System.out.println("✾            1. IN HOÁ ĐƠN                  ✾ ");
            System.out.println("✾            2. QUAY LẠI                    ✾ ");
            System.out.println("✾            3. THOÁT                       ✾ ");
            System.out.println("✾                                           ✾ ");
            System.out.println("✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ✾ ");
            System.out.print(" ⭆ ");
            do {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        showPaymentInfo(order);
                        break;
                    case 2:
                        OrderViewLauncher.run();
                        break;
                    case 3:
                        AppUtils.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Không đúng. Vui lòng nhập lại!");
                        break;
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Không đúng. Vui lòng nhập lại!");
        }
    }

    public void showPaymentInfo(Order order) {

        try {
            System.out.println("=============================================================================");
            System.out.println("|                                   HOÁ ĐƠN                                 |");
            System.out.println("=============================================================================");
            System.out.printf("|\t%-20s\t %-25s %20s |\n", "Full Name   :", order.getFullName(), "");
            System.out.printf("|\t%-20s\t %-25s %20s |\n", "NumberPhone :", order.getMobile(), "");
            System.out.printf("|\t%-20s\t %-25s %20s |\n", "Address     :", order.getAddress(), "");
            System.out.printf("|\t%-20s\t %-25s %20s |\n", "Creat Date  : ", InstantUtils.instantToString(order.getCreatedAt()), "");
            System.out.println("=============================================================================");
            System.out.printf("%-3s%-17s\t %-25s %-25s \n","","Product Name", "Quantity", "Price");
            List<OrderItem> orderItem = ORDER_ITEM_SERVICE.findAll();
            double sum = 0;
            int count = 0;
            for (OrderItem orderItem1 : orderItem) {
                if (orderItem1.getOrderId() == order.getId()) {
                    sum += orderItem1.getTotal();
                    count++;
                    orderItem1.setGrandTotal(sum);
                    ORDER_ITEM_SERVICE.update(orderItem1.getOrderId(), orderItem1.getPrice(), sum);
                    System.out.printf("%-2s.%-20s\t %-25s %-25s \n",count,
                            orderItem1.getProductName(),
                            orderItem1.getQuantity(),
                            AppUtils.doubleToVND(orderItem1.getPrice()));
                }
            }
            System.out.println("                                             TỔNG:" + AppUtils.doubleToVND(sum) + "\n");
            System.out.println("-------------------------------CỬA HÀNG NỘI THẤT dinhquocnhat-----------------------------\n");
            System.out.println("                                                     kí tên:\n");
            System.out.println("                                                          dinhquocnhat");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để quay lại \t|\t 't' Thoát");
                System.out.print("⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Không đúng. Vui lòng nhập lại!");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            System.out.println("Không đúng. Vui lòng nhập lại!");
        }
    }

    public void showAllOrder() {
        List<Order> orders = ORDER_SERVICE.findAll();
        List<OrderItem> orderItems = ORDER_ITEM_SERVICE.findAll();
        OrderItem newOrderItem = new OrderItem();
        List<OrderItem> orderItemList = new ArrayList<>();
        try {
            int count = 0;
            double printTotal = 0;
            double total = 0;
            double sum = 0;
            double grandTotal = 0;
            System.out.println("----------------------------------------------------------------------LIST ORDER--------------------------------------------------------------------------------");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getId()) {
                        newOrderItem = orderItem;
                        orderItemList.add(newOrderItem);
                        double price = orderItem.getPrice();
                        int quantity = orderItem.getQuantity();
                        sum = price * quantity;
                        grandTotal += sum;
                    }
                }
                newOrderItem.setGrandTotal(grandTotal);
                ORDER_ITEM_SERVICE.update(newOrderItem.getOrderId(), newOrderItem.getPrice(), grandTotal);
                System.out.println("=================================================================================================================================================");
                System.out.printf("|\t%-20s%-20s%-30s%-20s%-25s%-25s|\n", "Id: ", order.getId(), " ", "Customer Name:", order.getFullName(), "");
                System.out.printf("|\t%-20s%-20s%-30s%-20s%-25s%-25s|\n", "Number Phone: ", order.getMobile(), " ", "Address: ", order.getAddress(), "");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("|\t%-4s%-20s%-20s%-10s%-15s%-15s%-10s%-10s%-18s %-15s %-1s|\n","STT","","Product Name","","","Quantity"," ","Price","     ", "Total Product","" );
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");

                for (OrderItem orderItem : orderItemList) {
                    count++;
                    total = orderItem.getPrice() * orderItem.getQuantity();
                    System.out.printf("|\t%-2d.%-1s%-20s%-20s%-10s%-15s%-15d%-10s%-18s%-11s%14s\t|\n",count,"", "", orderItem.getProductName(), " ", "", orderItem.getQuantity()
                            , "", AppUtils.doubleToVND(orderItem.getPrice())
                            , "", AppUtils.doubleToVND(total));
                }
                orderItemList.clear();
                 printTotal += grandTotal;
                System.out.println("==================================================================================================================== TỔNG HOÁ ĐƠN:  " + AppUtils.doubleToVND(grandTotal) + "\n");
                sum = 0;
                grandTotal = 0;
                count = 0;
            }
            System.out.println("|--------------------- TỔNG DOANH THU: "+ AppUtils.doubleToVND(printTotal)+"---------------------|");
            System.out.println("|=====================================================================|");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' Quay lại \t|\t 't' Thoát");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Không đúng. Vui lòng nhập lại!");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            System.out.println("Không đúng. Vui lòng nhập lại!");
        }
    }

    public List<OrderItem> addOrderItems(long orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        InteriorsProductView techsProduct = new InteriorsProductView();
        techsProduct.showTechs1();
        System.out.println("Nhập số lượng sản phẩm bạn muốn đặt hàng ");
        System.out.print("➲ ");
        int choice = Integer.parseInt(scanner.nextLine());
        int count = 0;
        do {
            try {
                orderItems.add(addOrderItem(orderId));
                count++;
            } catch (Exception e) {
                System.out.println("Không đúng. VUi lòng nhập lại!");
            }
        } while (count < choice);
        return orderItems;
    }
}
