package car.shop.furka4U.myObj;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.stereotype.Service;

@Service
public class OfferFilter {

    // Filters
    // -1 - all
    private Integer manufacturer;
    // -1 - all
    private Integer model;

    private Integer minYear = 0;
    private Integer maxYear;

    private Integer fuelType;

    private String desc;

    private Integer page;

    private final Integer perPage = 20;

    // Sort
    private String sort;

    private List<String> sortList;

    public OfferFilter() {
        sortList = new ArrayList<String>();
        sortList.add("price");
        sortList.add("created");
    }

    public Integer getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(Integer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public OfferFilter manufacturer(Integer manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public Integer getModel() {
        return this.model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public OfferFilter model(Integer model) {
        this.model = model;
        return this;
    }

    public Integer getMinYear() {
        return this.minYear;
    }

    public void setMinYear(Integer minYear) {
        this.minYear = minYear;
    }

    public OfferFilter minYear(Integer minYear) {
        this.minYear = minYear;
        return this;
    }

    public Integer getMaxYear() {
        return this.maxYear;
    }

    public void setMaxYear(Integer maxYear) {
        this.maxYear = maxYear;
    }

    public OfferFilter maxYear(Integer maxYear) {
        this.maxYear = maxYear;
        return this;
    }

    public Integer getFuelType() {
        return this.fuelType;
    }

    public void setFuelType(Integer fuelType) {
        this.fuelType = fuelType;
    }

    public OfferFilter fuelType(Integer fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public OfferFilter desc(String desc) {
        this.desc = desc;
        return this;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public OfferFilter page(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getPerPage() {
        return this.perPage;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public OfferFilter sort(String sort) {
        this.sort = sort;
        return this;
    }

    public List<String> getSortList() {
        return this.sortList;
    }

    public void setSortList(List<String> sortList) {
        this.sortList = sortList;
    }

    public OfferFilter sortList(List<String> sortList) {
        this.sortList = sortList;
        return this;
    }

}