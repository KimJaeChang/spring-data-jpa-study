package kr.co.spring_data_jpa_study.study.entity.item;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("A")
@Getter
@Setter
public class Album extends Item {

    private String artist;
    private String etc;
}
