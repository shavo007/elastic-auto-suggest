# elastic-auto-suggest

##Getting started
Spin up elasticsearch 2.x using docker

docker-compose up

Start the app ``` ./gradlew bootRun ```

Create the hotels index and index the hotel documents

* hotel_mapping.json
* hotel_documents.txt

Demo auto suggest by calling

GET http://localhost:8081/suggest?q=m



##TODO
Add in jest
Add in retry annotation to cluster
Resource suggest with param
Resource full text search
Project for UI (use yeoman generator / add in angular directive for auto suggest)
