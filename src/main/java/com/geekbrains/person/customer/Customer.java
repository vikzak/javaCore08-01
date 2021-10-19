package com.geekbrains.person.customer;

import com.geekbrains.market.Market;
import com.geekbrains.person.Person;
import com.geekbrains.person.salesman.Salesman;
import com.geekbrains.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    List<Product> purchaseList;
    List<Product> expectedPurchaseList;

    public Customer(List<Product> expectedPurchaseList, int cash) {
        this.purchaseList = new ArrayList<>();
        this.expectedPurchaseList = expectedPurchaseList;
        this.setCash(cash);
    }

    public void whatIBoughtInfo() {
        System.out.println();
        StringBuilder builder = new StringBuilder("Я купил ");

        if (purchaseList.size() == 0) {
            builder.append("ничего");
        } else {
            for (Product purchase : purchaseList) {
                builder.append(purchase.getName());
                builder.append(" в количестве ");
                builder.append(purchase.getCount());
                builder.append(" ");
            }
        }

        builder.append(". У меня осталось: ");
        builder.append(this.getCash());
        builder.append(" рублей");

        System.out.println(builder);
    }

    public void addPurchase(Product product) {
        if (purchaseList == null) {
            purchaseList = new ArrayList<>();
        }

        purchaseList.add(product);
    }

    public void findProductOnMarket(Market market) {
        for (Product product : getExpectedPurchaseList()) {
            for (Salesman salesman : market.getSalesmanList()) {
                boolean isBought = salesman.sellProducts(this, product);
                if (isBought) {
                    break;
                }
            }
        }
    }

    public List<Product> getExpectedPurchaseList() {
        return expectedPurchaseList;
    }

    public void setExpectedPurchaseList(List<Product> expectedPurchaseList) {
        this.expectedPurchaseList = expectedPurchaseList;
    }
}
