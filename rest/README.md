# Car park - REST

## Root url
```sh
/pa165/rest
```

## Cars

#### Get all cars

```sh
curl -i -X GET http://localhost:8080/carpark-rest/cars
```
#### Get car by id
```sh
 curl -i -X GET http://localhost:8080/carpark-rest/cars/{id}
```
#### Create new car
```sh
curl -X POST -i -H "Content-Type: application/json" --data 
'{"serialNumber":"L789L-AA","regPlateNumber":"BA678EE","manufacturer":"Opel","type":"Astra","seats":5}' http://localhost:8080/carpark-rest/cars/create
```

### Users

#### Get all users
```sh
curl -i -X GET http://localhost:8080/carpark-rest/users
```
#### Get user by id
```sh
curl -i -X GET http://localhost:8080/carpark-rest/users/{id}
```

### Rejected record

#### Get all records
```sh
curl -i -X GET http://localhost:8080/carpark-rest/records/rejected
```
#### Get record by id
```sh
curl -i -X GET http://localhost:8080/carpark-rest/records/rejected/{id}
```
