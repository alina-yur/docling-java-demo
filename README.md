# docling-java

A Quarkus application that converts documents (PDF, DOCX, etc.) to Markdown using [Docling](https://github.com/docling-project/docling).

## Prerequisites

- Java 21+
- Docker or Podman running

## Running the Application

```bash
mvn quarkus:dev
```

## Build a GraalVM Native Image

```shell
mvn package -Dnative
# mvn package -Dnative -Dquarkus.native.additional-build-args="-Ob"

./target/docling-java-1.0.0-SNAPSHOT-runner -Dquarkus.docling.base-url=http://localhost:5001
```


This automatically starts a Docling container via Dev Services. The app will be available at `http://localhost:8080`.

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

## Alternative: Docling4j (Embedded Python)

If you want to run Docling without a separate container, you can use [docling4j](https://github.com/docling-project/docling4j) which embeds Python via GraalPy.

**Note:** This project is still WIP (Work In Progress).

### Setup

1. Add the dependency:

```xml
<dependency>
    <groupId>com.ibm.docling</groupId>
    <artifactId>docling4j</artifactId>
    <version>0.1.1</version>
</dependency>
```

2. Use Oracle GraalVM JDK (recommended for performance)

3. No external container needed - Docling runs embedded in the JVM

## Resources

- [Quarkus Docling Documentation](https://docs.quarkiverse.io/quarkus-docling/dev/index.html)
- [docling4j GitHub](https://github.com/docling-project/docling4j)
