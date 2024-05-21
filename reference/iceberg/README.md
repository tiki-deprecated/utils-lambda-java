# Iceberg
Facade for connecting to Iceberg tables in S3 with a Glue metadata catalog.

## Dependencies

```xml
<dependency>
    <groupId>com.mytiki/groupId>
    <artifactId>utils-lambda-java</artifactId>
    <version>1.0.0</version>
<dependency>
    <groupId>org.apache.iceberg</groupId>
    <artifactId>iceberg-core</artifactId>
    <version>1.4.2</version>
</dependency>
<dependency>
    <groupId>org.apache.iceberg</groupId>
    <artifactId>iceberg-api</artifactId>
    <version>1.4.2</version>
</dependency>
<dependency>
    <groupId>org.apache.iceberg</groupId>
    <artifactId>iceberg-aws</artifactId>
    <version>1.4.2</version>
</dependency>
<dependency>
    <groupId>software.amazon.awssdk</groupId>
    <artifactId>glue</artifactId>
    <version>2.21.42</version>
</dependency>
<dependency>
    <groupId>software.amazon.awssdk</groupId>
    <artifactId>sts</artifactId>
    <version>2.21.42</version>
    </dependency>
<dependency>
    <groupId>software.amazon.awssdk</groupId>
    <artifactId>s3</artifactId>
    <version>2.21.42</version>
</dependency>
<dependency>
    <groupId>org.apache.hadoop</groupId>
    <artifactId>hadoop-common</artifactId>
    <version>3.3.6</version>
    <exclusions>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-reload4j</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
        </exclusion>
        <exclusion>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

_Also recommend excluding from `hadoop-common`:_
```xml
<exclusion>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
</exclusion>
<exclusion>
    <groupId>com.google.protobuf</groupId>
    <artifactId>protobuf-java</artifactId>
</exclusion>
<exclusion>
    <groupId>commons-collections</groupId>
    <artifactId>commons-collections</artifactId>
</exclusion>
<exclusion>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
</exclusion>
<exclusion>
    <groupId>org.codehaus.jettison</groupId>
    <artifactId>jettison</artifactId>
</exclusion>
<exclusion>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-xml</artifactId>
</exclusion>
```

## Usage

1) Create a `iceberg.properties` file in the root of your resources folder with the following properties:

    ```properties
    catalog-name=iceberg
    catalog-impl=org.apache.iceberg.aws.glue.GlueCatalog
    warehouse=s3://<bucket-name>
    io-impl=org.apache.iceberg.aws.s3.S3FileIO
    glue.skip-archive=true
    database-name=<glue-database-name>
    ```
   
2) Add the `Iceberg.java` file to your project
3) Call `Iceberg.load()` to load the Iceberg configuration into memory
4) Call `initialize()` to initialize the connectivity driver
5) See [iceberg.apache.org](https://iceberg.apache.org/javadoc/1.5.2/) for available methods

### Example

```java
import com.mytiki.core.iceberg.utils.Iceberg;

public example() {
   Iceberg iceberg = Iceberg.load();
   iceberg.initialize();
   
   TableIdentifier identifier = TableIdentifier.of(iceberg.getDatabase(), first.getTable());
   Table table = iceberg.loadTable(identifier);
}

