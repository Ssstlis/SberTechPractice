package forms;

public class BillForm {
    private Long money;

    public BillForm(){}

    public BillForm(Long money) {
        this.money = money;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }
}