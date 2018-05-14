package com.revature.model;

public class CreateReimbursementModel extends SimpleEmployee {
    private double amount;
    private int category;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CreateReimbursementModel{" +  "username='" + this.getUsername() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                '}' +
                "amount=" + amount +
                ", category=" + category +
                '}';
    }
}