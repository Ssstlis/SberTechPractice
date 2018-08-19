package forms;

public class SampleForm {
    protected Long senderId;
    protected Long accepterId;

    public SampleForm(){}

    public SampleForm(Long senderId, Long accepterId) {
        this.accepterId = accepterId;
        this.senderId = senderId;
    }

    public Long getAccepterId() {
        return accepterId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setAccepterId(Long accepterId) {
        this.accepterId = accepterId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }
}