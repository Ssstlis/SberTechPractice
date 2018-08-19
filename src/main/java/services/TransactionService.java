package services;

import daos.TransactionDAO;
import models.Bill;
import models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionDAO dao;
    private final BillService billService;

    @Autowired
    public TransactionService(TransactionDAO transactionDAO, BillService billService) {
        this.dao = transactionDAO;
        this.billService = billService;
    }

    private boolean isValidTransaction(Long senderId, Long accepterId, Long money) {
        return billService.getById(accepterId) != null && (senderId == 0 || billService.isEnoughtMoney(senderId, money));
    }

    public void insertTransaction(Long senderId, Long accepterId, Long money) {
        if (isValidTransaction(senderId, accepterId, money)) {
            if (senderId != 0){
                Bill sender = billService.getById(senderId);
                Bill accepter = billService.getById(accepterId);
                sender.balance -= money;
                accepter.balance += money;
                Bill senderBill = billService.update(sender, senderId);
                Bill accepterBill = billService.update(accepter, accepterId);
                if (senderBill == null && accepterBill == null) return ;
                dao.insert(new Transaction(senderId, accepterId, money));
            } else {
                Bill accepter = billService.getById(accepterId);
                accepter.balance += money;
                Bill accepterBill = billService.update(accepter, accepterId);
                if (accepterBill == null) return ;
                dao.insert(new Transaction(senderId, accepterId, money));
            }
        }
    }

    public List<Transaction> findBySenderId(Long id) {
        return dao.findBySenderId(id);
    }

    public List<Transaction> findByAccepterId(Long id) {
        return dao.findByAccepterId(id);
    }

    public List<Transaction> findByAccepterAndSender(Long accepter, Long sender) {
       return dao.findByAccepterIdAndSenderId(accepter, sender);
    }

    public List<Transaction> All() {
        return dao.findAll();
    }
}