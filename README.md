### Bowling game exercise
### Run the application

#### With gradle
```
./gradlew run --args "roll1 .. rollN"
# the rolls are space separated in quotes
# Example: 
#   ./gradle run --args "1 2 3"
```

#### Straight
```
  ./gradlew compileJava   
  java -cp build/classes/java/main 'com.mruhwedel.Application' roll1 ... rollN
  # the rolls are space separated and unquoted
  # Example:
  #   java -cp build/classes/java/main 'com.mruhwedel.Application' 1 2 3
```

### Test the Application
```
./gradlew test
```
