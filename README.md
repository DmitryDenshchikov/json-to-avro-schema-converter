# [WIP] json-to-avro-schema-converter
## General information
This application provides functionality for converting json schema to avro schema.

#### Example of Run command:

```java -jar .\json-to-avro-schema-converter.jar --path-to-file="path/to/file"```
<br/><br/>

#### Example of coversion (for understanding the main functionality):
- Json schema (input data)
```
{
  "title": "EmployeeRecord",
  "type": "object",
  "properties": {
    "id": {
      "type": "number"
    },
    "name": {
      "type": "string"
    },
    "age": {
      "type": "number"
    },
    "hobbies": {
      "type": "object",
      "properties": {
        "indoor": {
          "type": "array",
          "items": {
            "type": "string"
          }
        },
        "outdoor": {
          "type": "array",
          "items": {
            "type": "string"
          }
        }
      }
    }
  }
}
```
- Avro schema (result output)
```
{
  "type": "record",
  "name": "EmployeeRecord",
  "fields": [
    {
      "name": "id",
      "type": "double"
    },
    {
      "name": "name",
      "type": "string"
    },
    {
      "name": "age",
      "type": "double"
    },
    {
      "name": "hobbies",
      "type": {
        "name": "hobbiesRecord",
        "type": "record",
        "fields": [
          {
            "name": "indoor",
            "type": {
              "type": "array",
              "items": {
                "name": "arrayItem_e0fa9fbc_6202_4817_8844_994d5142c796",
                "type": "string"
              }
            }
          },
          {
            "name": "outdoor",
            "type": {
              "type": "array",
              "items": {
                "name": "arrayItem_fa86a323_ce92_436c_b89a_b53f6f02f2b3",
                "type": "string"
              }
            }
          }
        ]
      }
    }
  ]
}

```

## Possible ways for data providing
At present there are several ways for providing input json schema:
- Through the file using ```--path-to-file``` option:
example: ```java -jar .\json-to-avro-schema-converter.jar --path-to-file="path/to/file"```

- Through the cli using ```--content-via-cli``` option
example: ```java -jar .\json-to-avro-schema-converter.jar --content-via-cli=true```
