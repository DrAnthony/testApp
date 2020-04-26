package com.example.testapp.entity;

public class Floor {
    private Integer id;
    private Integer seatNum;
    private Library library;
    private Integer level;
    private String mapFile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getMapFile() {
        return mapFile;
    }

    public void setMapFile(String mapFile) {
        this.mapFile = mapFile;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id=" + id +
                ", seatNum=" + seatNum +
                ", library=" + library +
                ", level=" + level +
                ", mapFile='" + mapFile + '\'' +
                '}';
    }
}
