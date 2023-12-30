package com.example.downloadmanager0;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestContrler
@RequestMapping("/peer")
public class PeerController {

    private Logger logger = Logger.getLogger(PeerController.class.getName());

    private Peer currentPeer;

    public PeerController(Peer peer) {
        currentPeer = peer;
    }

    @PostMapping("/connect")
    public ResponseEntity<Set<String>> connect(@RequestBody Set<String> addresses) {
        Set<String> currentKnownPeers = currentPeer.getKnownPeers();
        currentKnownPeers.add(currentPeer.getAddress());
        currentKnownPeers.addAll(addresses);
        logger.info("Connected to peers: " + currentKnownPeers);
        return new ResponseEntity<>(currentKnownPeers, HttpStatus.OK);
    }


    @PostMapping("/files{file}")
    public ResponseEntity<String> addFile(@PathVariable String file, @RequestBody String fileName) {
        //TODO add file to current peer
        return new ResponseEntity<>("File added successfully", HttpStatus.OK);
    }

    @GetMapping("/files{file}")
    public ResponseEntity<Resource> getFiles(
            @RequestParam String filePath,
            @RequestParam int chunkSize,
            @RequestParam long delay) {

        logger.info("File download request: " + filePath + " chunkSize: " + chunkSize + " delay: " + delay);
        try (InputStream inputStream = new FileInputStream(filePath);
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            byte[] dataChunk = new byte[chunkSize];
            int bytesRead;
            while ((bytesRead = inputStream.read(dataChunk, 0, dataChunk.length)) != -1) {
                buffer.write(dataChunk, 0, bytesRead);
                Thread.sleep(delay); // Delay to control download speed
            }

             byte[] byteArray = buffer.toByteArray();
            int halfLength = byteArray.length / 2;
            byte[] data = Arrays.copyOfRange(byteArray, 0, halfLength);

            ByteArrayResource byteArrayResource = new ByteArrayResource(buffer.toByteArray());

            File file = new File(filePath);
            MediaType mediaType = MediaType.parseMediaType(Files.probeContentType(file.toPath()));

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                    .contentType(mediaType)
                    .contentLength(byteArrayResource.contentLength())
                    .body(byteArrayResource);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred while downloading file", e);
            return ResponseEntity.internalServerError().build();
        }
    }

        @PutMapping("/files/{address}")
        public ResponseEntity<String> updateFile(@RequestParam String oldName,
                @RequestParam String newName){
            //TODO тут обновить файл из списка файлов currentPeer
            //todo результат должен сразу показаться на фронте
            return new ResponseEntity<>("File added successfully", HttpStatus.OK);
        }


        @DeleteMapping("/files/{address}")
        public ResponseEntity<String> deleteFile (@RequestParam String fileName){
            //TODO тут типа файл  из списка файлов currentPeer
            //todo результат должен сразу показаться на фронте
            return new ResponseEntity<>(null);
        }
    }

