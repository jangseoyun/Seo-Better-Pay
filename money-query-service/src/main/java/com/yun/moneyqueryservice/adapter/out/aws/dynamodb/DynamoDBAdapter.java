package com.yun.moneyqueryservice.adapter.out.aws.dynamodb;

import com.yun.moneyqueryservice.application.port.out.GetMemberAddressInfoPort;
import com.yun.moneyqueryservice.application.port.out.InsertMoneyIncreaseEventByAddress;
import com.yun.moneyqueryservice.application.port.out.MemberAddressInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class DynamoDBAdapter implements GetMemberAddressInfoPort, InsertMoneyIncreaseEventByAddress {
    @Value("money.query.table")
    private String TABLE_NAME;
    @Value("money.dynamo.access")
    private String ACCESS_KEY;
    @Value("money.dynamo.secret")
    private String SECRET_KEY;
    private final DynamoDbClient dynamoDbClient;
    private final MoneySumByAddressMapper mapper;

    public DynamoDBAdapter() {
        this.mapper = new MoneySumByAddressMapper();
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY);
        this.dynamoDbClient = DynamoDbClient.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .build();
    }

    /*@Override
    public void insertMoneyIncreaseEventByAddress(String addressName, int moneyIncrease) {
        //1. raw event insert
        String pk = addressName + "#" + LocalDateTime.now();
        int sk = moneyIncrease;
        putItem(pk, sk);

        //2. 지역 정보 잔액 증가
        //2-1. 지역별/일별 정보
        String summaryPk = pk + "summary";
        int summarySk = -1;
        MoneySumByAddress item = getItem(summaryPk, summarySk);
        if (item == null) {
            putItem(summaryPk, summarySk, moneyIncrease);
        }

        //합산 TODO: domain으로 분리
        int balance = item.getBalance();
        balance += moneyIncrease;
        //update

        //2-2. 지역별 정보
        String addressPk = addressName;
        int addressSk = -1;
        MoneySumByAddress addressItem = getItem(addressPk, addressSk);
        if (addressItem == null) {
            putItem(addressPk, addressSk, moneyIncrease);
        }
        //합산 TODO: domain으로 분리
        int addressItemBalance = addressItem.getBalance();
        addressItemBalance += moneyIncrease;
        putItem(addressPk, addressSk, addressItemBalance);
    }*/

    @Override
    public void insertMoneyIncreaseEventByAddress(String addressName, int moneyIncrease) {
        // 3개의 일을 해야될 것이에요.

        // 1. raw event insert (Insert, put)
        // PK: 강남구#230728 SK: 5,000, balance, 5,000
        String pk = addressName + "#" + "230728";
        int sk = moneyIncrease;
        putItem(pk, sk, moneyIncrease);

        // 2. 지역 정보 잔액 증가시켜야 해요. (Query, Update)
        // 2-1. 지역별/일별 정보
        //  - PK: 강남구#230728#summary SK: -1 balance: + 5,000
        String summaryPk = pk + "#summary";
        int summarySk = -1;
        MoneySumByAddress moneySumByAddress = getItem(summaryPk, summarySk);
        if (moneySumByAddress == null) {
            putItem(summaryPk, summarySk, moneyIncrease);
        } else{
            int balance = moneySumByAddress.getBalance();
            balance += moneyIncrease;
            updateItem(summaryPk, summarySk, balance);
        }

        // 2-2. 지역별 정보
        // - PK: 강남구 SK: -1 balance: + 5,000
        String summaryPk2 = addressName;
        int summarySk2 = -1;
        MoneySumByAddress moneySumByAddress2 = getItem(summaryPk2, summarySk2);
        if (moneySumByAddress2 == null) {
            putItem(summaryPk2, summarySk2, moneyIncrease);
        } else{
            int balance2 = moneySumByAddress2.getBalance();
            balance2 += moneyIncrease;
            updateItem(summaryPk2, summarySk2, balance2);
        }
    }

    @Override
    public MemberAddressInfo getMemberAddressInfo(String membershipId) {
        return null;
    }

    private MoneySumByAddress getItem(String pk, int sk) {
        try {
            HashMap<String, AttributeValue> attrMap = new HashMap<>();
            attrMap.put("PK", AttributeValue.builder().s(pk).build());
            attrMap.put("SK", AttributeValue.builder().n(Integer.toString(sk)).build());

            GetItemRequest request = GetItemRequest.builder()
                    .tableName(TABLE_NAME)
                    .key(attrMap)
                    .build();

            GetItemResponse response = dynamoDbClient.getItem(request);

            if (response.hasItem()) {
                mapper.mapToMoneySumByAddress(response.item());
            }

        } catch (DynamoDbException e) {
            log.error("Error getting an item from the table: {} ", e.getMessage());
        }
        return null;
    }

    private void putItem(String pk, int sk) {
        try {
            HashMap<String, AttributeValue> attrMap = new HashMap<>();
            attrMap.put("PK", AttributeValue.builder().s(pk).build());
            attrMap.put("SK", AttributeValue.builder().s(Integer.toString(sk)).build());

            PutItemRequest request = PutItemRequest.builder()
                    .tableName(TABLE_NAME)
                    .item(attrMap)
                    .build();

            dynamoDbClient.putItem(request);
        } catch (DynamoDbException e) {
            log.error("Error adding an item to the table: {} ", e.getMessage());
        }
    }

    private void putItem(String pk, int sk, int balance) {
        try {
            HashMap<String, AttributeValue> attrMap = new HashMap<>();
            attrMap.put("PK", AttributeValue.builder().s(pk).build());
            attrMap.put("SK", AttributeValue.builder().s(Integer.toString(sk)).build());
            attrMap.put("Balance", AttributeValue.builder().n(Integer.toString(balance)).build());

            PutItemRequest request = PutItemRequest.builder()
                    .tableName(TABLE_NAME)
                    .item(attrMap)
                    .build();

            dynamoDbClient.putItem(request);
        } catch (DynamoDbException e) {
            log.error("Error adding an item to the table: {} ", e.getMessage());
        }
    }

    private void queryItem(String pk) {
        try {
            // PK 만 써도 돼요.
            HashMap<String, Condition> attrMap = new HashMap<>();
            attrMap.put("PK", Condition.builder()
                    .attributeValueList(AttributeValue.builder().s(pk).build())
                    .comparisonOperator(ComparisonOperator.EQ)
                    .build());

            QueryRequest request = QueryRequest.builder()
                    .tableName(TABLE_NAME)
                    .keyConditions(attrMap)
                    .build();

            QueryResponse response = dynamoDbClient.query(request);
            response.items().forEach((value) -> log.info("value: {}", value));
        } catch (DynamoDbException e) {
            log.error("Error getting an item from the table: {} ", e.getMessage());
        }
    }

    private void updateItem(String pk, int sk, int balance) {
        try {
            HashMap<String, AttributeValue> attrMap = new HashMap<>();
            attrMap.put("PK", AttributeValue.builder().s(pk).build());
            attrMap.put("SK", AttributeValue.builder().s(Integer.toString(sk)).build());

            String balanceStr = String.valueOf(balance);
            // Create an UpdateItemRequest
            UpdateItemRequest updateItemRequest = UpdateItemRequest.builder()
                    .tableName(TABLE_NAME)
                    .key(attrMap)
                    .attributeUpdates(
                            new HashMap<String, AttributeValueUpdate>() {{
                                put("balance", AttributeValueUpdate.builder()
                                        .value(AttributeValue.builder().n(balanceStr).build())
                                        .action(AttributeAction.PUT)
                                        .build());
                            }}
                    ).build();


            UpdateItemResponse response = dynamoDbClient.updateItem(updateItemRequest);

            // 결과 출력.
            Map<String, AttributeValue> attributes = response.attributes();
            if (attributes != null) {
                for (Map.Entry<String, AttributeValue> entry : attributes.entrySet()) {
                    String attributeName = entry.getKey();
                    AttributeValue attributeValue = entry.getValue();
                    System.out.println(attributeName + ": " + attributeValue);
                }
            } else {
                System.out.println("Item was updated, but no attributes were returned.");
            }
        } catch (DynamoDbException e) {
            System.err.println("Error getting an item from the table: " + e.getMessage());
        }
    }
}
