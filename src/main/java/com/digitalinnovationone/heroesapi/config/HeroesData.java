package com.digitalinnovationone.heroesapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import static com.digitalinnovation.livecoding.constants.HeroesConstant.ENDPOINT_DYNAMO;
import static com.digitalinnovation.livecoding.constants.HeroesConstant.REGION_DYNAMO;

public class HeroesData {
    public static void main(String[] args) throws Exception {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
                .build();
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("HEROES_API_TABLE_LEARNING");

        Item hero = new Item()
                .withPrimaryKey("ID", "1")
                .withString("NAME", "Wonder Woman")
                .withString("UNIVERSE", "DC COMICS")
                .withNumber("FILMS", 2);

        Item hero2 = new Item()
                .withPrimaryKey("ID", "2")
                .withString("NAME", "Spider Man")
                .withString("UNIVERSE", "MARVEL")
                .withNumber("FILMS", 2);

        Item hero3 = new Item()
                .withPrimaryKey("ID", "3")
                .withString("NAME", "Iron Man")
                .withString("UNIVERSE", "MARVEL")
                .withNumber("FILMS", 2);

        PutItemOutcome outcome1 = table.putItem(hero);
        PutItemOutcome outcome2 = table.putItem(hero2);
        PutItemOutcome outcome3 = table.putItem(hero3);

    }

}
