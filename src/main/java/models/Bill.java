package models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bills")
public class Bill {
    @Id
    public ObjectId    _id;

    @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    public Long        id;
    public Long        balance;

    public Bill(){}

    public Bill(Long id, Long balance) {
        this.id = id;
        this.balance = balance;
        _id = new ObjectId();
    }

    @Override
    public String toString() {
        return "Bill["+id + ", "+ balance + "]";
    }
}