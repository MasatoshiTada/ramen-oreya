#!/usr/bin/env bash

cf push -p ./goods-service/ -f ./goods-service/manifest.yml
cf push -p ./order-service/ -f ./order-service/manifest.yml
cf push -p ./customer-api-gateway/ -f ./customer-api-gateway/manifest.yml
cf push -p ./staff-api-gateway/ -f ./staff-api-gateway/manifest.yml
cf push -p ./ui-customer-service/ -f ./ui-staff-service/manifest.yml
cf push -p ./ui-staff-service/ -f ./ui-customer-service/manifest.yml