#!/usr/bin/env bash

mvn -f ./config-server clean package -Dmaven.test.skip
mvn -f ./eureka-server clean package -Dmaven.test.skip
mvn -f ./goods-service clean package -Dmaven.test.skip
mvn -f ./hystrix-dashboard clean package -Dmaven.test.skip
mvn -f ./order-service clean package -Dmaven.test.skip
mvn -f ./customer-api-gateway clean package -Dmaven.test.skip
mvn -f ./staff-api-gateway clean package -Dmaven.test.skip
mvn -f ./ui-customer-service clean package -Dmaven.test.skip
mvn -f ./ui-staff-service clean package -Dmaven.test.skip
