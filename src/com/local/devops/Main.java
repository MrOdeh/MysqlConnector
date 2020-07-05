package com.local.devops;

import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataSource.getInstance().open();
        customers();
        DataSource.getInstance().close();

    }

    public static void customers() {
        List<Customer> customers = DataSource.getInstance().getCustomers();
        if (customers == null) {
            System.out.println("Null value !");
            return;
        } else if (customers.isEmpty()) {
            System.out.println("no data were found");
            return;
        }

        Iterator<Customer> iterator = customers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
