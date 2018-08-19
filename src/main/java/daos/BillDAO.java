package daos;

import models.Bill;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface BillDAO extends MongoRepository<Bill, ObjectId> {
}
