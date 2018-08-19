package services;

import daos.BillDAO;
import models.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {
    private final BillDAO dao;

    @Autowired
    public BillService(BillDAO billDAO) {
        this.dao = billDAO;
    }

    public List<Bill> All() {
        return dao.findAll()
                .stream()
                .sorted(Comparator.comparingLong(f -> f.id))
                .collect(Collectors.toList());
    }

    public Bill createBill(Long money) {
        if (money == null) return null;
        List<Bill> bills = dao.findAll();
        Long bill = bills.size() == 0 ? 0L : bills
                .stream()
                .map(i -> i.id)
                .max(Long::compare)
                .orElse(0L);

        Long id = bill + 1;
        return dao.save(new Bill(id, 0L));
    }

    Bill getById(Long id) {
        return dao.findAll()
                .stream()
                .filter(b -> b.id.equals(id))
                .findFirst()
                .orElse(null);
    }

    boolean isEnoughtMoney(Long id, Long money) {
        Bill bill = getById(id);
        return bill != null && bill.balance != null && bill.balance >= money;
    }


    Bill update(Bill bill, Long id) {
        Bill fromDB = getById(id);
        if (fromDB != null && bill != null) {
            fromDB.balance = bill.balance;
            return dao.save(fromDB);
        } else return null;
    }
}