package daos;

import models.Transaction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TransactionDAO extends MongoRepository<Transaction, ObjectId> {

    @Query("{sender_id: ?0}")
    List<Transaction> findBySenderId(Long senderId);

    @Query("{accepter_id: ?0}")
    List<Transaction> findByAccepterId(Long accepterId);

    @Query("{accepter_id: ?0, sender_id: ?1}")
    List<Transaction> findByAccepterIdAndSenderId(Long accepterId, Long senderId);
}
