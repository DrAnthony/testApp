package com.example.testapp.entity;

public class Seat {
    private Integer id;

    private String seatId;

    private Floor seatFloor;

    private Boolean seatStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId == null ? null : seatId.trim();
    }

    public Floor getSeatFloor() {
        return seatFloor;
    }

    public void setSeatFloor(Floor seatFloor) {
        this.seatFloor = seatFloor;
    }

    public Boolean getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(Boolean seatStatus) {
        this.seatStatus = seatStatus;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", seatId='" + seatId + '\'' +
                ", seatFloor=" + seatFloor +
                ", seatStatus=" + seatStatus +
                '}';
    }
}
