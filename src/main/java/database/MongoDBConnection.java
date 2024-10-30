package database;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoDBConnection {
    public static void IniciarConexao(){
        String connectionString = "mongodb+srv://ytalo:academic123@academicevents.edc1p.mongodb.net/?retryWrites=true&w=majority&appName=AcademicEvents";
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("AcademicEvents");
        mongoDatabase.createCollection("Emocoes");
        MongoCollection<Document> collection = mongoDatabase.getCollection("Emocoes");

        Document document = new Document("Nome","Alegria").append("Idade","18");

        collection.insertOne(document);

    }
}
