import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBApp {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("myDatabase");
        MongoCollection<Document> collection = database.getCollection("users");

        createPerson(collection);
        createPerson2(collection);
        readPerson(collection);
        updatePerson(collection);
        deletePerson(collection);

        mongoClient.close();
    }

    private static void createPerson(MongoCollection<Document> collection) {
        Document person = new Document("name", "John Doe")
                .append("age", 30)
                .append("city", "New York");
        collection.insertOne(person);
    }

    private static void createPerson2(MongoCollection<Document> collection) {
        Document person = new Document("name", "Sara")
                .append("age", 22)
                .append("city", "Helsinki");
        collection.insertOne(person);
    }

    private static void readPerson(MongoCollection<Document> collection) {
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
    }

    private static void updatePerson(MongoCollection<Document> collection) {
        Document query = new Document("name", "Alice");
        Document update = new Document("$set", new Document("city", "Los Angeles"));
        collection.updateOne(query, update);
        System.out.println("Updated person with name Alice to city Los Angeles.");
    }

    private static void deletePerson(MongoCollection<Document> collection) {
        Document query = new Document("name", "Alice");
        collection.deleteOne(query);
        System.out.println("Deleted person with name Alice.");
    }
}
