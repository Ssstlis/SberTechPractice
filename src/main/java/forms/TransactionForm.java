package forms;

public class TransactionForm extends SampleForm {
    private Long amount;

    public TransactionForm(){}

    public TransactionForm(Long senderId, Long accepterId, Long amount) {
        super(senderId, accepterId);
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}