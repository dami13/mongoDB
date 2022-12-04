package src;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        MongoClient mongoClient = getMongoDbClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("vehicles");
        MongoCollection mongoCollection = mongoDatabase.getCollection("cars");


//        save(mongoCollection);
//        read(mongoCollection);
        readByParamType(mongoCollection, "Mark", "BMW");

        System.out.print("DB-name: " + mongoDatabase.getName());
    }

    private static void save(MongoCollection mongoCollection) {
        Document document1 = new Document();
        document1.put("Mark", "Fiat");
        document1.put("Model", "126p");

        Document document2 = new Document();
        document2.put("Mark", "BMW");
        document2.put("Model", "One");
        document2.put("Color", Arrays.asList("Red", "Black", "Green"));

        mongoCollection.insertMany(Arrays.asList(document1, document2));
    }

    private static void read(MongoCollection mongoCollection) {
        Document first = (Document) mongoCollection.find().first();
        System.out.println(first);
    }

    private static void readByParamType(MongoCollection mongoCollection, String param, Object value) {
        Document document = new Document();
        document.put(param, value);

        Document first = (Document) mongoCollection.find(document).first();
        System.out.println(first);
    }

    private static MongoClient getMongoDbClient() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://admin:xsadars13@cluster0.9fdtlvg.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings =
                MongoClientSettings.builder()
                        .applyConnectionString(connectionString).build();

        return MongoClients.create(settings);
    }
}
