// Product class
public class MobilePhone {
    private String processor;
    private String RAM;
    private String storage;
    private String camera;
    private String battery;

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    @Override
    public String toString() {
        return "MobilePhone [Processor=" + processor + ", RAM=" + RAM + ", Storage=" + storage + ", Camera=" + camera + ", Battery=" + battery + "]";
    }
}

// Builder Interface
interface MobilePhoneBuilder {
    void buildProcessor();
    void buildRAM();
    void buildStorage();
    void buildCamera();
    void buildBattery();
    MobilePhone getMobilePhone();
}

// Concrete Builder
class SmartphoneBuilder implements MobilePhoneBuilder {
    private MobilePhone mobilePhone;

    public SmartphoneBuilder() {
        this.mobilePhone = new MobilePhone();
    }

    @Override
    public void buildProcessor() {
        mobilePhone.setProcessor("Snapdragon 888");
        System.out.println("Processor complete");
    }

    @Override
    public void buildRAM() {
        mobilePhone.setRAM("8GB");
        System.out.println("RAM complete");
    }

    @Override
    public void buildStorage() {
        mobilePhone.setStorage("128GB");
        System.out.println("Storage complete");
    }

    @Override
    public void buildCamera() {
        mobilePhone.setCamera("108MP");
        System.out.println("Camera complete");
    }

    @Override
    public void buildBattery() {
        mobilePhone.setBattery("4500mAh");
        System.out.println("Battery complete");
    }

    @Override
    public MobilePhone getMobilePhone() {
        return this.mobilePhone;
    }
}

// Director
class Director {
    private MobilePhoneBuilder mobilePhoneBuilder;

    public Director(MobilePhoneBuilder mobilePhoneBuilder) {
        this.mobilePhoneBuilder = mobilePhoneBuilder;
    }

    public MobilePhone constructMobilePhone() {
        mobilePhoneBuilder.buildProcessor();
        mobilePhoneBuilder.buildRAM();
        mobilePhoneBuilder.buildStorage();
        mobilePhoneBuilder.buildCamera();
        mobilePhoneBuilder.buildBattery();
        return mobilePhoneBuilder.getMobilePhone();
    }
}

// Client
public class BuilderPatternExample {
    public static void main(String[] args) {
        MobilePhoneBuilder mobilePhoneBuilder = new SmartphoneBuilder();
        Director director = new Director(mobilePhoneBuilder);

        MobilePhone mobilePhone = director.constructMobilePhone();
        System.out.println("Mobile Phone is: " + mobilePhone);
    }
}
