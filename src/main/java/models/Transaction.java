package models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "transactions")
public class Transaction {
    @Id
    public ObjectId _id;

    @Field("sender_id")
    public Long     senderId;
    @Field("accepter_id")
    public Long     accepterId;
    public Long     amount;
    public Date   date;

    public Transaction(){}

    public Transaction(Long senderId, Long accepterId, Long amount) {
        this._id = new ObjectId();
        this.accepterId = accepterId;
        this.senderId = senderId;
        this.amount = amount;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Transaction[" + senderId + ", " + senderId + ", " + amount + "]";
    }

    public ObjectId _id() {
        return _id;
    }
}