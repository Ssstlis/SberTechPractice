package controllers;

import forms.BillForm;
import forms.SampleForm;
import forms.TransactionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import services.BillService;
import services.TransactionService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class GetController {
    @Autowired
    private BillService billService;
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = {"/", "/index"}, method = GET)
    public String index(Model model) {
        model.addAttribute("title","Главная страница");
        return "index";
    }

    @RequestMapping(value = "/all", method = GET)
    public String bills(Model model) {
        model.addAttribute("bills", billService.All());
        return "all";
    }

    @RequestMapping(value = "/transactions", method = GET)
    public String transactions(Model model) {
        model.addAttribute("transactions", transactionService.All());
        return "transactions";
    }

    @RequestMapping(value = "/add", method = GET)
    public String createBill(Model model) {
        model.addAttribute("billForm", new BillForm());
        return "add";
    }

    @RequestMapping(value = "/search", method = GET)
    public String search(Model model) {
        model.addAttribute("sampleForm", new SampleForm());
        return "search";
    }

    @RequestMapping(value = "/transfer", method = GET)
    public String transaction(Model model) {
        model.addAttribute("transferForm", new TransactionForm());
        return "transfer";
    }
}