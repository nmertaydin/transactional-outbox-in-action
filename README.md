# Transactional Outbox in Action

This is a PoC I prepared to demonstrate the [transactional outbox](https://microservices.io/patterns/data/transactional-outbox.html) pattern / approach.

### The Ingredients

- Registration system / service (adds, updates people)
- Data store (stores information about people)
- Outbox system (informs related parties as soon as a new person is added or an existing person is modified)

### Technologies Used

- Kotlin (JVM)
- Spring Boot (Web, JPA, Flyway)
- MySQL
- Maxwell
- Kafka
- Python

### To Run

Run the following command (make sure that you have Docker installed):

`docker-compose up`

This will make all the infrastructure ready, up and running. Wait for some time to have all containers settle down.

`./gradlew bootRun`

This will start the registration system / service and the outbox system.

On a separate terminal window follow the logs of the Python Kafka consumer to see the results:

Run the command to list active containers:

`docker ps`

Note the container id of the Python consumer container (from the image `python:3`)

Then run the following command:

`docker logs <noted-container-id> --follow`

Send a GET request to `http://localhost:8080/v1/api/person`

This will retrieve the list of existing people.

Send a POST request to `http://localhost:8080/v1/api/person` with a request body (raw, JSON) like:

`{
    "name": "Nurettin",
    "age": 39
}`

This will add a new person.

Observe the message Python consumer consumes from Kafka.

Send a PUT request to `http://localhost:8080/v1/api/person/2` with a request body (raw, JSON) like:

`{
"name": "Nuri",
"age": 30
}`

This will update an existing person.

Observe the message Python consumer consumes from Kafka queue.


