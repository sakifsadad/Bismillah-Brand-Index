package com.example.bismillahbrandindex.Model;

public class UpcomingProducts {

    private String ProductName, Price, images, Battery, Camera, Color, Display, Fingerprint, ROM, Sim, Others, Network, Processor, RAM, YoutubeVideoLink, date, pid, time;
    private Images ImageUris;

    public UpcomingProducts() {

    }

    public UpcomingProducts(String productName, String price, String images, String battery, String camera, String color, String display, String fingerprint, String ROM, String sim, String others, String network, String processor, String RAM, String youtubeVideoLink, String date, String pid, String time, Images ImageUris) {
        this.ProductName = productName;
        this.Price = price;
        this.images = images;
        this.Battery = battery;
        this.Camera = camera;
        this.Color = color;
        this.Display = display;
        this.Fingerprint = fingerprint;
        this.ROM = ROM;
        this.Sim = sim;
        this.Others = others;
        this.Network = network;
        this.Processor = processor;
        this.RAM = RAM;
        this.YoutubeVideoLink = youtubeVideoLink;
        this.date = date;
        this.pid = pid;
        this.time = time;
        this.ImageUris = ImageUris;
    }

     {
        this.ImageUris = ImageUris;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getBattery() {
        return Battery;
    }

    public void setBattery(String battery) {
        Battery = battery;
    }

    public String getCamera() {
        return Camera;
    }

    public void setCamera(String camera) {
        Camera = camera;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getDisplay() {
        return Display;
    }

    public void setDisplay(String display) {
        Display = display;
    }

    public String getFingerprint() {
        return Fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        Fingerprint = fingerprint;
    }

    public String getROM() {
        return ROM;
    }

    public void setROM(String ROM) {
        this.ROM = ROM;
    }

    public String getSim() {
        return Sim;
    }

    public void setSim(String sim) {
        Sim = sim;
    }

    public String getOthers() {
        return Others;
    }

    public void setOthers(String others) {
        Others = others;
    }

    public String getNetwork() {
        return Network;
    }

    public void setNetwork(String network) {
        Network = network;
    }

    public String getProcessor() {
        return Processor;
    }

    public void setProcessor(String processor) {
        Processor = processor;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getYoutubeVideoLink() {
        return YoutubeVideoLink;
    }

    public void setYoutubeVideoLink(String youtubeVideoLink) {
        YoutubeVideoLink = youtubeVideoLink;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Images getImageUris() {
        return ImageUris;
    }

    public void setImageUris(Images imageUris) {
        this.ImageUris = imageUris;
    }
}