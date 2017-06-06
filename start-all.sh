#!/usr/bin/env bash

cf start ramen-goods-service
cf start ramen-order-service
cf start ramen-staff-api-gateway
cf start ramen-customer-api-gateway
cf start ramen-ui-staff-service
cf start ramen-ui-customer-service