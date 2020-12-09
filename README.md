## Bowling Game Exercise
### Run the application

#### With gradle
```
./gradlew run --args "roll1 .. rollN"
# the rolls are space separated in quotes
# Example: 
#   ./gradlew run --args "1 4 4 5 6 4 5 5 10 0 1 7 3 6 4 10 2 8 6"
```

#### Straight
```
  ./gradlew compileJava   
  java -cp build/classes/java/main 'com.mruhwedel.Application' roll1 ... rollN
  # the rolls are space separated and unquoted
  # Example:
  #   java -cp build/classes/java/main 'com.mruhwedel.Application' 1 4 4 5 6 4 5 5 10 0 1 7 3 6 4 10 2 8 6
```

### Test the Application
```
./gradlew test
```
