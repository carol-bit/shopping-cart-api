#!/bin/bash

echo "Running 'mvn clean install'..."
mvn clean install

if [$? -ne 0]; then
  echo "Error executing 'mvn clean install'. Exiting..."
  exit 1
fi

echo "Starting a shopping cart API...."
java -jar ./target/shopping-cart-api-v1.jar