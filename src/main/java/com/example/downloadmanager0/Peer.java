package com.example.downloadmanager0;

import java.util.List;
import java.util.Set;

public class Peer {
    private String address;
    private List<String> filesToDownload;
    private Set<String> knownPeers;

    public Peer(String ip, List<String> filesToDownload, Set<String> knownPeers) {
        this.address = ip;
        this.filesToDownload = filesToDownload;
        this.knownPeers = knownPeers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getFilesToDownload() {
        return filesToDownload;
    }

    public void setFilesToDownload(List<String> filesToDownload) {
        this.filesToDownload = filesToDownload;
    }

    public Set<String> getKnownPeers() {
        return knownPeers;
    }

    public void setKnownPeers(Set<String> knownPeers) {
        this.knownPeers = knownPeers;
    }
}
