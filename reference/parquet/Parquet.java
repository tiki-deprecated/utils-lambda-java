import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.iceberg.Schema;
import org.apache.iceberg.avro.AvroSchemaUtil;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.avro.AvroSchemaConverter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;
import org.apache.iceberg.util.SerializableSupplier;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.io.LocalOutputFile;
import org.apache.parquet.schema.MessageType;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.providers.DefaultAwsRegionProviderChain;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;


public class Parquet {

    public create() {
        String avroSchema = "{\"namespace\": \"example.avro\",\n" +
        " \"type\": \"record\",\n" +
        " \"name\": \"User\",\n" +
        " \"fields\": [\n" +
        "     {\"name\": \"name\", \"type\": \"string\"},\n" +
        "     {\"name\": \"favorite_number\",  \"type\": [\"int\", \"null\"]},\n" +
        "     {\"name\": \"favorite_color\", \"type\": [\"string\", \"null\"]}\n" +
        " ]\n" +
        "}";

        Schema schema = new Schema.Parser().parse(avroSchema);
        GenericRecord user = new GenericData.Record(schema);
        user.put("name", "Alyssa");
        user.put("favorite_number", 256);

        LocalOutputFile outputPath = new LocalOutputFile(Path.of("users.parquet"));
        ParquetWriter<GenericRecord> writer = AvroParquetWriter
                .<GenericRecord>builder(outputPath)
                .withSchema(dataFileReader.getSchema())
                .withCompressionCodec(CompressionCodecName.SNAPPY)
                .build();

        writer.write(user);
        writer.close();
    }

    public readS3() {
        DefaultCredentialsProvider credentialsProvider = DefaultCredentialsProvider.builder().build();
        S3Client s3Client = S3Client.builder()
                .credentialsProvider(credentialsProvider)
                .region(DefaultAwsRegionProviderChain.builder().build().getRegion())
                .build();
        SerializableSupplier<S3Client> s3ClientSupplier = (SerializableSupplier<S3Client> & Serializable) () -> s3Client;
        Configuration conf = new Configuration();
        AwsSessionCredentials creds = (AwsSessionCredentials) credentialsProvider.resolveCredentials();
        conf.set("fs.s3a.access.key", creds.accessKeyId());
        conf.set("fs.s3a.secret.key", creds.secretAccessKey());
        if(creds.sessionToken() != null) conf.set("fs.s3a.session.token", creds.sessionToken());
        conf.set("fs.s3a.endpoint", "s3.amazonaws.com");

        org.apache.hadoop.fs.Path s3Path = new org.apache.hadoop.fs.Path("s3a://file.parquet");
        HadoopInputFile inputFile = HadoopInputFile.fromPath(s3Path, conf);
        ParquetFileReader reader = ParquetFileReader.open(inputFile);
    }

    public inferIcebergSchema() {
        ParquetFileReader reader = ParquetFileReader.open(inputFile);
        ParquetMetadata footer = reader.getFooter();
        MessageType parquetSchema = footer.getFileMetaData().getSchema();
        AvroSchemaConverter converter = new AvroSchemaConverter();
        Schema schema = AvroSchemaUtil.toIceberg(converter.convert(parquetSchema));
    }
}
