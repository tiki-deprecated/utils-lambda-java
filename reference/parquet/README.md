# Parquet
Facade for reading, writing, and parsing Parquet files for use with Iceberg tables.

_NOTE: Very much a work in progress. Do not use as is._

## Dependencies

```xml
    <dependency>
      <groupId>org.apache.parquet</groupId>
      <artifactId>parquet-avro</artifactId>
      <version>1.14.0</version>
    </dependency>
    <dependency>
      <groupId>org.apache.parquet</groupId>
      <artifactId>parquet-hadoop</artifactId>
      <version>1.14.0</version>
    </dependency>
    <dependency>
      <groupId>org.apache.iceberg</groupId>
      <artifactId>iceberg-parquet</artifactId>
      <version>1.5.2</version>
    </dependency>
    <dependency>
      <groupId>org.apache.hadoop</groupId>
      <artifactId>hadoop-common</artifactId>
      <version>3.3.6</version>
<!--      <exclusions>-->
<!--        <exclusion>-->
<!--          <groupId>*</groupId>-->
<!--          <artifactId>*</artifactId>-->
<!--        </exclusion>-->
<!--      </exclusions>-->
    </dependency>
    <dependency>
      <groupId>org.apache.hadoop</groupId>
      <artifactId>hadoop-mapreduce-client-core</artifactId>
      <version>3.4.0</version>
    </dependency>
    <dependency>
      <groupId>org.apache.hadoop</groupId>
      <artifactId>hadoop-aws</artifactId>
      <version>3.4.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.iceberg</groupId>
        <artifactId>iceberg-core</artifactId>
        <version>1.5.2</version>
        </dependency>
    <dependency>
        <groupId>org.apache.iceberg</groupId>
        <artifactId>iceberg-api</artifactId>
        <version>1.5.2</version>
        </dependency>
    <dependency>
        <groupId>org.apache.iceberg</groupId>
        <artifactId>iceberg-aws</artifactId>
        <version>1.5.2</version>
    </dependency>
```
