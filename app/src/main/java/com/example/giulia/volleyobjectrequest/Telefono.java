package com.example.giulia.volleyobjectrequest;

/**
 * Created by Giulia on 27/03/2018.
 */

public class Telefono {
    String rom,screenSize,backCamera,companyName,name,frontCamera,battery,operatingSystem,processor,url,ram;

    public Telefono(String rom, String screenSize, String backCamera, String companyName, String name, String frontCamera, String battery, String operatingSystem, String processor, String url, String ram) {
        this.rom = rom;
        this.screenSize = screenSize;
        this.backCamera = backCamera;
        this.companyName = companyName;
        this.name = name;
        this.frontCamera = frontCamera;
        this.battery = battery;
        this.operatingSystem = operatingSystem;
        this.processor = processor;
        this.url = url;
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public String getBackCamera() {
        return backCamera;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getName() {
        return name;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public String getBattery() {
        return battery;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getProcessor() {
        return processor;
    }

    public String getUrl() {
        return url;
    }

    public String getRam() {
        return ram;
    }
}
