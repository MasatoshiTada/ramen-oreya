#!/usr/bin/env bash

cf stop ramen-ui-staff-service
cf stop ramen-ui-customer-service
cf stop ramen-staff-api-gateway
cf stop ramen-customer-api-gateway
cf stop ramen-goods-service
cf stop ramen-order-service