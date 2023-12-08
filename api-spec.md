# API Spec 

## Authentication

### Login Siswa

Request :
- Method : **POST**
- Endpoint : `/api/auth/login/siswa`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :
```json
{
  "nisn": "number",
  "password": "string"
}
```

Response :
```json
{
  "status_code": "number",
  "message": "string",
  "data": {
    "token": "string",
    "role": "string"
  }
}
```

### Login Admin / Guru

Request: 
- Method : **POST**
- Endpoint : `/api/auth/login/pengguna`
- Header :
  - Content-Type: application/json
  - Accept: application/json
- Body :
```json
{
  "email": "string",
  "password": "string"
}
```

Response:
```json
{
  "status_code": "number",
  "message": "string",
  "data": {
    "token": "string",
    "role": "string"
  }
}
```



