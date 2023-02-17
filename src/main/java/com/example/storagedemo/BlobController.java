package com.example.storagedemo;

import com.google.cloud.storage.Blob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlobController {
  private static final Logger LOGGER = LoggerFactory.getLogger(BlobController.class);
  private final DisplayObjects displayObjects;

  BlobController(DisplayObjects displayObjects) {
    this.displayObjects = displayObjects;
  }

  @GetMapping("/blobs")
  public void printBlobs() {
    LOGGER.info("============== list ==============");
    for (Blob blob : displayObjects.list()) {
      LOGGER.info(blob.getName());
    }

    LOGGER.info("============== stream ==============");
    displayObjects.stream().forEach(blob -> LOGGER.info(blob.getName()));
  }
}
