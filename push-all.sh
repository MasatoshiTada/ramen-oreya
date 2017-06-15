#!/usr/bin/env bash

./build-all.sh

cf push -p ./goods-service/target/goods-service-0.0.1-SNAPSHOT.jar -f ./goods-service/manifest.yml
cf push -p ./order-service/target/order-service-0.0.1-SNAPSHOT.jar -f ./order-service/manifest.yml
cf push -p ./customer-api-gateway/target/customer-api-gateway-0.0.1-SNAPSHOT.jar -f ./customer-api-gateway/manifest.yml
cf push -p ./staff-api-gateway/target/staff-api-gateway-0.0.1-SNAPSHOT.jar -f ./staff-api-gateway/manifest.yml
cf push -p ./ui-customer-service/target/ui-customer-service-0.0.1-SNAPSHOT.jar -f ./ui-staff-service/manifest.yml
cf push -p ./ui-staff-service/target/ui-staff-service-0.0.1-SNAPSHOT.j -f ./ui-customer-service/manifest.yml