# docling-java

A Quarkus application that converts documents (PDF, DOCX, etc.) to Markdown using [Docling](https://github.com/docling-project/docling).

## Prerequisites

- GraalVM
- Podman/Docker

## Running the Application

```bash
mvn quarkus:dev
```

## Build a GraalVM Native Image

```shell
mvn package -Dnative
# mvn package -Dnative -Dquarkus.native.additional-build-args="-Ob"
```

## Run

```
shell
podman run -p 5001:5001 quay.io/docling-project/docling-serve

./target/docling-java-1.0.0-SNAPSHOT-runner -Dquarkus.docling.base-url=http://localhost:5001
```


## API Endpoints

### Convert from URL

**GET** `/convert/url?url=<document-url>`

```bash
curl "http://localhost:8080/convert/url?url=https://ontheline.trincoll.edu/images/bookdown/sample-local-pdf.pdf"
```

### Convert from File Upload

**POST** `/convert/file`

```bash
# curl
curl -X POST -F "file=@hello.pdf" http://localhost:8080/convert/file

# httpie
http --form POST localhost:8080/convert/file file@document.pdf
```
