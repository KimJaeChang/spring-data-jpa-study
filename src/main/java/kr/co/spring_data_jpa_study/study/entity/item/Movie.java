package kr.co.spring_data_jpa_study.study.entity.item;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
public class Movie extends Item {

    private String director;
    private String actor;
}
