#!/bin/bash

# Script to install the mybatis-gen-plugin and test it in example-project

echo "Installing mybatis-gen-plugin to local Maven repository..."
mvn clean install -DskipTests

if [ $? -eq 0 ]; then
    echo "Plugin installed successfully!"
    
    echo "Testing plugin in example-project..."
    cd example-project
    mvn mybatis-gen:generate
    
    if [ $? -eq 0 ]; then
        echo "Plugin test completed successfully!"
        echo "Generated files:"
        echo "Entities:"
        ls -la src/main/java/com/lucaswangdev/entity/
        echo "Mappers:"
        ls -la src/main/java/com/lucaswangdev/mapper/
        echo "XML Mappings:"
        ls -la src/main/resources/com/lucaswangdev/mapper/
    else
        echo "Plugin test failed!"
        exit 1
    fi
else
    echo "Plugin installation failed!"
    exit 1
fi