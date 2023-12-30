package com.example.downloadmanager0;

import java.util.List;
import java.util.Set;

/**
 * Represents a peer in a peer-to-peer (P2P) network for file downloading.
 * This class stores information about a peer, including its address,
 * the list of files it intends to download, and a set of known peers in the network.
 */
public class Peer {
    private String address;
    private List<String> filesToDownload;
    private Set<String> knownPeers;

    /**
     * Constructs a new Peer instance.
     *
     * @param ip The IP address of the peer.
     * @param filesToDownload A list of file names that this peer wants to download.
     * @param knownPeers A set of peers known to this peer in the network.
     */
    public Peer(String ip, List<String> filesToDownload, Set<String> knownPeers) {
        this.address = ip;
        this.filesToDownload = filesToDownload;
        this.knownPeers = knownPeers;
    }

    /**
     * Gets the address of this peer.
     *
     * @return The IP address of the peer.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of this peer.
     *
     * @param address The new IP address of the peer.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the list of files that this peer intends to download.
     *
     * @return A list of file names to be downloaded.
     */
    public List<String> getFilesToDownload() {
        return filesToDownload;
    }

    /**
     * Sets the list of files that this peer intends to download.
     *
     * @param filesToDownload A new list of file names to be downloaded.
     */
    public void setFilesToDownload(List<String> filesToDownload) {
        this.filesToDownload = filesToDownload;
    }

    /**
     * Gets the set of known peers in the network for this peer.
     *
     * @return A set of known peers.
     */
    public Set<String> getKnownPeers() {
        return knownPeers;
    }

    /**
     * Sets the set of known peers in the network for this peer.
     *
     * @param knownPeers A new set of known peers.
     */
    public void setKnownPeers(Set<String> knownPeers) {
        this.knownPeers = knownPeers;
    }
}
