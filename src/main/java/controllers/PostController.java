package controllers;

import forms.BillForm;
import forms.SampleForm;
import forms.TransactionForm;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import services.BillService;
import services.TransactionService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static utils.Utils.*;

@Controller
public class PostController {
    @Autowired
    private BillService billService;
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/add", method = POST)
    public String createBill(@ModelAttribute("billForm") BillForm billForm, Model model) {
        Long money = billForm.getMoney();
        Bill bill = billService.createBill(money);
        transactionService.insertTransaction(0L, bill.id, money);
        model.addAttribute("bills", billService.All());
        return "allBills";
    }

    @RequestMapping(value = "/transfer", method = POST)
    public String createTransaction(@ModelAttribute("transactionForm") TransactionForm transactionForm, Model model) {
        Long amount = transactionForm.getAmount();
        Long senderId = transactionForm.getSenderId();
        Long accepterId = transactionForm.getAccepterId();
        transactionService.insertTransaction(senderId,accepterId,amount);
        model.addAttribute("transactions", transactionService.All());
        return "allTransactions";
    }

    @RequestMapping(value = "/sampling", method = POST)
    public String transactionBySender(Model model, @ModelAttribute("sampleForm") SampleForm sampleForm) {
        Long accepterId = sampleForm.getAccepterId();
        Long senderId   = sampleForm.getSenderId();
        List<Transaction> transactions = new ArrayList<>();
        if (senderId != null)
            transactions.addAll(transactionService.findBySenderId(senderId));
        if (accepterId != null)
            transactions.addAll(transactionService.findByAccepterId(accepterId));
        transactions = transactions.stream()
                .filter(distinctByKey(Transaction::_id))
                .sorted(Comparator.comparingLong(f -> f.senderId))
                .sorted(Comparator.comparingLong(f -> f.accepterId))
                .collect(Collectors.toList());
        model.addAttribute("transactions", transactions);
        return "allTransactions";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}