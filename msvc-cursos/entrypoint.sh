#!/bin/sh

# Find the JAR file
JAR_FILE=$(ls msvc-cursos*.jar)

# Start the application
exec java -jar $JAR_FILE