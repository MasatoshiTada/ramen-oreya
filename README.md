# ramen_oreya

## 全体像

数字はポート番号

![一覧](./site/ramen_oreya.001.png)


## Config Repository

https://github.com/MasatoshiTada/ramen-config.git


## データベース

PostgreSQLを使っています。詳細は下記のプロパティを参照。

https://github.com/MasatoshiTada/ramen-config/blob/master/goods-service.properties

https://github.com/MasatoshiTada/ramen-config/blob/master/order-service.properties

## ビルド＆実行

```bash
./build-all.sh
java -jar ./config-server/target/config-server-0.0.1-SNAPSHOT.jar
java -jar ./eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar
java -jar ./goods-service/target/goods-service-0.0.1-SNAPSHOT.jar
java -jar ./order-service/target/order-server-0.0.1-SNAPSHOT.jar
java -jar ./customer-api-gateway/target/customer-api-gateway-0.0.1-SNAPSHOT.jar
java -jar ./staff-api-gateway/target/staff-api-gateway-0.0.1-SNAPSHOT.jar
java -jar ./ui-customer-service/target/ui-customer-service-0.0.1-SNAPSHOT.jar
java -jar ./ui-staff-service/target/ui-staff-service-0.0.1-SNAPSHOT.jar
java -jar ./hystrix-dashboard/target/hystrix-dashboard-0.0.1-SNAPSHOT.jar
```

ブラウザで下記のURLにアクセスしてください。

- お客用UI

http://localhost:8090/

- 店員用UI

http://localhost:8080/
