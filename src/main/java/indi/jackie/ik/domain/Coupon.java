package indi.jackie.ik.domain;

import java.util.Date;

public class Coupon extends BaseEntity{
    private Integer id;

    private Date expireDate;

    private Integer offerType;

    private Integer offerCondition;

    private Float offerLimit;

    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getOfferType() {
        return offerType;
    }

    public void setOfferType(Integer offerType) {
        this.offerType = offerType;
    }

    public Integer getOfferCondition() {
        return offerCondition;
    }

    public void setOfferCondition(Integer offerCondition) {
        this.offerCondition = offerCondition;
    }

    public Float getOfferLimit() {
        return offerLimit;
    }

    public void setOfferLimit(Float offerLimit) {
        this.offerLimit = offerLimit;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}