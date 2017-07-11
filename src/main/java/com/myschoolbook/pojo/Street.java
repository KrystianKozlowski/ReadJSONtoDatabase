package com.myschoolbook.pojo;

import javax.persistence.*;

/**
 * Created by Krystian on 2017-05-31.
 */

@Embeddable
public class Street {

    @Column(name = "streetid")
    private Long id;
    @Column(name = "streetname")
    private String name;

    public Street() {
    }


    public Street(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}