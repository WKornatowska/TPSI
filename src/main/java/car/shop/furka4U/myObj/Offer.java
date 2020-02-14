package car.shop.furka4U.myObj;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "offer")
public class Offer {

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", title='" + getTitle() + "'" + ", year='" + getYear() + "'"
                + ", mileage='" + getMileage() + "'" + ", engineSize='" + getEngineSize() + "'" + ", enginePower='"
                + getEnginePower() + "'" + ", doors='" + getDoors() + "'" + ", colour='" + getColour() + "'"
                + ", description='" + getDescription() + "'" + ", price='" + getPrice() + "'" + ", model='" + getModel()
                + "'" + ", bodyStyle='" + getBodyStyle() + "'" + ", fuelType='" + getFuelType() + "'" + ", created='"
                + getCreated() + "'" + "}";
    }

    public Offer() {
    }

    public Offer(String title) {

        this.title = title;
        this.year = new Random().nextInt(2000) + 1900;
        this.mileage = new Random().nextInt(5000);
        this.engineSize = new BigDecimal("0.01");
        this.enginePower = 12;
        this.doors = 2;
        this.colour = "red";
        this.description = "Automatic car";
        this.price = new Random().nextInt(1000000);
        this.setModel(new CarModel());
        this.model.setId(new Random().nextInt(50));
        this.setBodyStyle(new BodyStyle());
        this.bodyStyle.setId(1);
        this.setFuelType(new FuelType());
        this.fuelType.setId(1);
        this.UpdateDate();
    }

    public void UpdateDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.created = formatter.format(date);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Size(max = 255, min = 5)
    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "year")
    @Min(1900)
    @NotNull
    private Integer year;

    @Column(name = "mileage")
    @Min(0)
    @NotNull
    private Integer mileage;

    @Column(name = "engine_size")
    @Min(0)
    private BigDecimal engineSize;

    @Column(name = "engine_power")
    @Min(0)
    private Integer enginePower;

    @Column(name = "doors")
    @Min(1)
    @Max(5)
    @NotNull
    private Integer doors;

    @Size(max = 30, min = 3)
    @Column(name = "colour")
    @NotNull
    private String colour;

    @Lob
    @Size(max = 65535, min = 5)
    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "price")
    @Min(0)
    @NotNull
    private Integer price;

    @JoinColumn(name = "model_id", referencedColumnName = "id")
    @NotNull
    @ManyToOne
    private CarModel model;

    @JoinColumn(name = "body_style_id", referencedColumnName = "id")
    @NotNull
    @ManyToOne
    private BodyStyle bodyStyle;

    @JoinColumn(name = "fuel_type_id", referencedColumnName = "id")
    @NotNull
    @ManyToOne
    private FuelType fuelType;

    @Column(name = "created")
    private String created;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Offer id(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Offer title(String title) {
        this.title = title;
        return this;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Offer year(Integer year) {
        this.year = year;
        return this;
    }

    public Integer getMileage() {
        return this.mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Offer mileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getEngineSize() {
        return this.engineSize;
    }

    public void setEngineSize(BigDecimal engineSize) {
        this.engineSize = engineSize;
    }

    public Offer engineSize(BigDecimal engineSize) {
        this.engineSize = engineSize;
        return this;
    }

    public Integer getEnginePower() {
        return this.enginePower;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public Offer enginePower(Integer enginePower) {
        this.enginePower = enginePower;
        return this;
    }

    public Integer getDoors() {
        return this.doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public Offer doors(Integer doors) {
        this.doors = doors;
        return this;
    }

    public String getColour() {
        return this.colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Offer colour(String colour) {
        this.colour = colour;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Offer description(String description) {
        this.description = description;
        return this;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Offer price(Integer price) {
        this.price = price;
        return this;
    }

    public CarModel getModel() {
        return this.model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public Offer model(CarModel model) {
        this.model = model;
        return this;
    }

    public BodyStyle getBodyStyle() {
        return this.bodyStyle;
    }

    public void setBodyStyle(BodyStyle bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public Offer bodyStyle(BodyStyle bodyStyle) {
        this.bodyStyle = bodyStyle;
        return this;
    }

    public FuelType getFuelType() {
        return this.fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Offer fuelType(FuelType fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}