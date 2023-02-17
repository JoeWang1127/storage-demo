package com.example.storagedemo;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DisplayObjects {

  @Value("${application.project-id}")
  private String projectId;

  @Value("${application.bucket-name}")
  private String bucketName;

  private final Storage storage;


  DisplayObjects() {
    storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
  }

  public Iterable<Blob> list() {
    return storage.list(bucketName).iterateAll();
  }

  public Stream<Blob> stream() {
    return StreamSupport.stream(storage.list(bucketName).iterateAll().spliterator(), false);
  }
}
