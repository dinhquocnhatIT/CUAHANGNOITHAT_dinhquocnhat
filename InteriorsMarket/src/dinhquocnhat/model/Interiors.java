package dinhquocnhat.model;

import java.time.Instant;
import java.util.Objects;

public class Interiors implements Comparable<Interiors> {

    private long id;
    private String nameInteriors;
    private double priceInteriors;
    private int quantityInteriors;
    private String type;
    private Instant creatDate;
    private Instant updateDate;

    public Interiors(Long id, String nameInteriors, Double priceInteriors, Integer quantityInteriors, String type) {
        this.id = id;
        this.nameInteriors = nameInteriors;
        this.priceInteriors = priceInteriors;
        this.quantityInteriors = quantityInteriors;
        this.type = type;
    }

    public Interiors() {
    }

    public static Interiors parseTechs(String rar) {
        Interiors interiors = new Interiors();
        String[] strings = rar.split(",");
        interiors.id = Integer.parseInt(strings[0]);
        interiors.nameInteriors = strings[1];
        interiors.priceInteriors = Double.parseDouble(strings[2]);
        interiors.quantityInteriors = Integer.parseInt(strings[3]);
        interiors.type = strings[4];
        String temp = strings[5];
        interiors.creatDate = Instant.parse(temp);
        temp = strings[6];
        if (temp != null && !temp.equals("null"))
            interiors.updateDate = Instant.parse(temp);
        return interiors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameInteriors() {
        return nameInteriors;
    }

    public void setNameInteriors(String nameInteriors) {
        this.nameInteriors = nameInteriors;
    }

    public double getPriceInteriors() {
        return priceInteriors;
    }

    public void setPriceInteriors(double priceInteriors) {
        this.priceInteriors = priceInteriors;
    }

    public int getQuantityInteriors() {
        return quantityInteriors;
    }

    public void setQuantityInteriors(int quantityInteriors) {
        this.quantityInteriors = quantityInteriors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instant getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Instant creatDate) {
        this.creatDate = creatDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return id + "," +
                nameInteriors + "," +
                priceInteriors + "," +
                quantityInteriors + "," +
                type + "," +
                creatDate + ","
                + updateDate;
    }

    @Override
    public int compareTo(Interiors o) {
        if (priceInteriors - o.priceInteriors == 0) {
            if (quantityInteriors - o.quantityInteriors == 0) {
                return nameInteriors.compareTo(o.nameInteriors);
            } else {
                return quantityInteriors - o.quantityInteriors;
            }
        }
        return (int) (priceInteriors - o.priceInteriors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interiors interiors = (Interiors) o;
        return id == interiors.id && quantityInteriors == interiors.quantityInteriors && priceInteriors == interiors.priceInteriors && Objects.equals(nameInteriors, interiors.nameInteriors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameInteriors, priceInteriors, quantityInteriors);
    }
}
