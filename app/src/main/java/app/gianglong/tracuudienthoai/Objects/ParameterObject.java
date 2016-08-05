package app.gianglong.tracuudienthoai.Objects;

import java.io.Serializable;

/**
 * Created by Giang Long on 7/22/2016.
 */

public class ParameterObject implements Serializable{
    private String screen, system, camera, cpu, ram, internalMemory, externalMemory, sim, connect, pin, type, skill;


    public ParameterObject(String screen, String system, String camera, String cpu, String ram, String internalMemory, String externalMemory, String sim, String connect, String pin, String type, String skill) {
        this.screen = screen;
        this.system = system;
        this.camera = camera;
        this.cpu = cpu;
        this.ram = ram;
        this.internalMemory = internalMemory;
        this.externalMemory = externalMemory;
        this.sim = sim;
        this.connect = connect;
        this.pin = pin;
        this.type = type;
        this.skill = skill;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getInternalMemory() {
        return internalMemory;
    }

    public void setInternalMemory(String internalMemory) {
        this.internalMemory = internalMemory;
    }

    public String getExternalMemory() {
        return externalMemory;
    }

    public void setExternalMemory(String externalMemory) {
        this.externalMemory = externalMemory;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
