package com.gsm.chwijuntime.model;

import com.gsm.chwijuntime.model.tagmapping.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TagIdx;

    @Column(nullable = false)
    private String TagName;
}
